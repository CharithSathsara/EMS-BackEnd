package lk.employeeManagement.response;


import lk.employeeManagement.model.Employee;
import lombok.Data;

@Data
public class EmployeeManagementResponse {
	
	private String message;
	private String code;
	private Employee employee;
	
	

}
