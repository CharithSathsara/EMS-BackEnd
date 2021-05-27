package lk.employeeManagement.response;

import lk.employeeManagement.model.Payment;
import lombok.Data;

@Data
public class PaymentsGetResponse {
	
	private String message;
	private String code;
	private Iterable<Payment> payments;

}
