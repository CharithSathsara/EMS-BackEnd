package lk.employeeManagement.service;


import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.employeeManagement.model.Attendance;
import lk.employeeManagement.model.Employee;
import lk.employeeManagement.model.Payment;
import lk.employeeManagement.model.Salary;
import lk.employeeManagement.repository.AttendanceRepository;
import lk.employeeManagement.repository.EmployeeManagementRepository;
import lk.employeeManagement.repository.PaymentRepository;
import lk.employeeManagement.repository.SalaryRepository;
import lk.employeeManagement.response.AttendanceGetResponse;
import lk.employeeManagement.response.AttendanceResponse;
import lk.employeeManagement.response.DetailsViaDateResponse;
import lk.employeeManagement.response.EmployeeManagementResponse;
import lk.employeeManagement.response.PaymentResponse;
import lk.employeeManagement.response.PaymentsGetResponse;


@Service
public class EmployeeManagementService {
	
	@Autowired
	EmployeeManagementRepository employeeManagementRepository;
		
	@Autowired
	SalaryRepository salaryRepository;
	
	@Autowired
	AttendanceRepository attendanceRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	public EmployeeManagementResponse createAccount(Employee employee) {
		
		EmployeeManagementResponse response = new EmployeeManagementResponse();
		
		Salary salary = new Salary(employee.getJoindate(), 0);
		Employee ee = new Employee(employee.getPhonenumber(), employee.getAddress(), employee.getFirstname(),
									employee.getLastname(), employee.getJoindate(), salary);
		
		try {
			Employee emp = employeeManagementRepository.save(ee);
			
			response.setCode("1");
			response.setMessage("succesfully created");
			response.setEmployee(emp);
			return response;
		}
		catch(Exception e){
			response.setCode("-1");
			response.setMessage("something went wrong ");
			return response;
		}
	}
	
	public Iterable<Employee> getAllAccountList(){
		
		return employeeManagementRepository.findAll();
	}
	
	public EmployeeManagementResponse getSpecificEmployee(Integer empid) {
		
		EmployeeManagementResponse response = new EmployeeManagementResponse();
		
		try {
			Employee employee =  employeeManagementRepository.findById(empid).get();
			if(employee==null) {
				response.setMessage("Invalid Employee Id!");
				response.setCode("-1");
				return response;
			}
			else {
				response.setMessage("Account find succesfull!");
				response.setCode("1");
				response.setEmployee(employee);
				return response;
			}
		} catch (Exception e) {
			response.setMessage("Invalid Employee Id!");
			response.setCode("-1");
			return response;
		}
}
	
	@Transactional
	public AttendanceResponse attendanceMark(Attendance attendance) {
		
		AttendanceResponse response = new AttendanceResponse();
		Attendance obj = new Attendance(attendance.getDate(), attendance.getDailysalary(), attendance.getWorktype(), attendance.getEmployee());
		
		try {
			
			Attendance temp = attendanceRepository.getAttendanceByDate(attendance.getEmployee().getEmpid(),attendance.getDate());
			if(temp==null) {
				Attendance att = attendanceRepository.save(obj);
				if(att == null) {
					response.setMessage("Something went wrong!");
					response.setCode("-1");
					return response;
				}
				else{
					double updateSalary = salaryRepository.getSalary(attendance.getEmployee().getSalary().getSalaryid()).getTotalsalary() + attendance.getDailysalary();
					salaryRepository.updateAttendanceSalary(updateSalary, att.getEmployee().getSalary().getSalaryid());
					response.setMessage("Attendance mark succesfully and your current Salary is " + updateSalary);
					response.setCode("1");
					response.setAttendance(attendanceRepository.findById(att.getAttendanceid()).get());
					return response;
				}
			}
			else {
				response.setMessage("Attendance is already marked.Please check the date!");
				response.setCode("-1");
				response.setAttendance(temp);
				return response;
			}
		} catch (Exception e) {
			response.setMessage("Error occured!");
			response.setCode("-1");
			return response;		
		}
		
	}
	
	@Transactional
	public PaymentResponse payToEmployee(Payment payment) {
		
		PaymentResponse response = new PaymentResponse();
		Payment obj = new Payment(payment.getPaydate(), payment.getAmount(), payment.getEmployee());
		
		try {
			Salary salary = salaryRepository.getSalary(payment.getEmployee().getSalary().getSalaryid());
			if(salary!=null) {
				if(salary.getTotalsalary()>=payment.getAmount()) {
					double updateBalance = salary.getTotalsalary() - payment.getAmount();
					Payment pay = paymentRepository.save(obj);
					salaryRepository.updateSalary(updateBalance, salary.getSalaryid(),payment.getPaydate());
					pay.setEmployee(employeeManagementRepository.findById(payment.getEmployee().getEmpid()).get());
					response.setCode("100");
					response.setMessage("Payment is success and existing balance is Rs : " + updateBalance);
					response.setPayment(pay);
				}
				else {
					response.setCode("-300");
					response.setMessage("Your balance is not enough!");
				}
			}
			else {
				response.setCode("-100");
				response.setMessage("invalid details!");
			}

			
		} catch (Exception e) {
			response.setCode("-100");
			response.setMessage("Exception occured");
		}
		return response;
		
	}

	public DetailsViaDateResponse detailsViaDate(Date date) {
		
		DetailsViaDateResponse response = new DetailsViaDateResponse();
	
		try {
			List<Attendance> att = attendanceRepository.getDetailsViaDate(date);
			List<Payment> pay = paymentRepository.getDetailsViaDate(date);
			if(att.size() == 0 && pay.size() == 0) {
				response.setMessage("Invalid Date!");
				response.setCode("-1");
				return response;
			}
			else {
				response.setAttendances(att);
				response.setPayments(pay);
				response.setMessage("Details get Succesfully!");
				response.setCode("1");
				return response;
			}
		} catch (Exception e) {
			response.setMessage("Error occured!");
			response.setCode("-1");
			return response;		
		}
	}

	public PaymentsGetResponse getLastPayments() {
		
		PaymentsGetResponse response = new PaymentsGetResponse();
		try {
			List<Payment> payments = paymentRepository.getLastPayments();
			
			if(payments.size()==0) {
				response.setMessage("You haven't pay to anyone yet!");
				response.setCode("-1");
			}
			else {
				response.setMessage("Last payments get succesfull!");
				response.setCode("1");
				response.setPayments(payments);
			}
		} catch (Exception e) {
			response.setMessage("Error occured!");
			response.setCode("-1");
		}
		return response;
	}

	public PaymentsGetResponse getLastFivePayments(Employee employee) {
		
		PaymentsGetResponse response = new PaymentsGetResponse();
		try {
			List<Payment> payments = paymentRepository.getLastFivePayments(employee.getEmpid());
			
			if(payments.size()==0) {
				response.setMessage("You haven't pay to him yet!");
				response.setCode("-1");
			}
			else {
				response.setMessage("Last five payments get succesfull!");
				response.setCode("1");
				response.setPayments(payments);
			}
		} catch (Exception e) {
			response.setMessage("Error occured!");
			response.setCode("-1");
		}
		return response;
	}

	public AttendanceGetResponse lastMonthAttendance(Employee employee) {
		
		AttendanceGetResponse response = new AttendanceGetResponse();
		try {
			List<Attendance> attendances = attendanceRepository.lastMonthAttendance(employee.getEmpid());
			
			if(attendances.size()==0) {
				response.setMessage("He hasn't come to work yet!");
				response.setCode("-1");
			}
			else {
				response.setMessage("Last month attendances get succesfull!");
				response.setCode("1");
				response.setAttendances(attendances);
			}
		} catch (Exception e) {
			response.setMessage("Error occured!");
			response.setCode("-1");
		}
		return response;
	}
}
