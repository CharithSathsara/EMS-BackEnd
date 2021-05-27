package lk.employeeManagement.response;

import lk.employeeManagement.model.Attendance;
import lombok.Data;

@Data
public class AttendanceGetResponse {
	
	private String message;
	private String code;
	private Iterable<Attendance> attendances;

}
