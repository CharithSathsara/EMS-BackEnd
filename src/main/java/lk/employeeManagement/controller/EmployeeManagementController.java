package lk.employeeManagement.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.employeeManagement.model.Attendance;
import lk.employeeManagement.model.Employee;
import lk.employeeManagement.model.Payment;
import lk.employeeManagement.response.AttendanceGetResponse;
import lk.employeeManagement.response.AttendanceResponse;
import lk.employeeManagement.response.EmployeeManagementResponse;
import lk.employeeManagement.response.PaymentResponse;
import lk.employeeManagement.response.PaymentsGetResponse;
import lk.employeeManagement.response.DetailsViaDateResponse;
import lk.employeeManagement.service.EmployeeManagementService;

@RestController
public class EmployeeManagementController {
	
	@Autowired
	EmployeeManagementService employeeManagementService;
	
	@PostMapping("/createAccount")
	public EmployeeManagementResponse createAccount(@RequestBody Employee employee) {
		return employeeManagementService.createAccount(employee);
	}
	
	@GetMapping("/getAllAccountList")
	public Iterable<Employee> getAllAccountList(){
		return employeeManagementService.getAllAccountList();
	}
	
	@GetMapping("/getSpecificEmployee/{empid}")
	public EmployeeManagementResponse getSpecificEmployee(@PathVariable Integer empid) {
		return employeeManagementService.getSpecificEmployee(empid);
	}
	
	@PostMapping("/attendanceMark")
	public AttendanceResponse attendanceMark(@RequestBody Attendance attendance) {
		return employeeManagementService.attendanceMark(attendance);
	}
	
	@PostMapping("/lastMonthAttendances")
	public AttendanceGetResponse lastMonthAttendance(@RequestBody Employee employee){
		return employeeManagementService.lastMonthAttendance(employee);
	}
	
	@PostMapping("/payToEmployee")
	public PaymentResponse payToEmployee(@RequestBody Payment payment) {
		return employeeManagementService.payToEmployee(payment);
	}
	
	@GetMapping("/detailsViaDate/{date}")
	public DetailsViaDateResponse detailsViaDate(@PathVariable Date date) {
		return employeeManagementService.detailsViaDate(date);
	}
	
	@GetMapping("/getLastPayments")
	public PaymentsGetResponse getLastPayments() {
		return employeeManagementService.getLastPayments();
	}
	
	@PostMapping("/getLastFivePayments")
	public PaymentsGetResponse getLastFivePayments(@RequestBody Employee employee) {
		return employeeManagementService.getLastFivePayments(employee);
	}
	@GetMapping("/temp")
	public String temp() {
		return "Waruni Sandareka";
	}

	
};
