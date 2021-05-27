package lk.employeeManagement.response;


import lk.employeeManagement.model.Attendance;
import lombok.Data;

@Data
public class AttendanceResponse {
	
	private String message;
	private String code;
	private Attendance attendance;

}
