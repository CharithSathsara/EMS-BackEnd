package lk.employeeManagement.response;

import lk.employeeManagement.model.Attendance;
import lk.employeeManagement.model.Payment;
import lombok.Data;

@Data
public class DetailsViaDateResponse {
	
	private String message;
	private String code;
	private Iterable<Attendance> attendances;
	private Iterable<Payment> payments;

}
