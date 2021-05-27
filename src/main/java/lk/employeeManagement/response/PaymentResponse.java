package lk.employeeManagement.response;

import lk.employeeManagement.model.Payment;
import lombok.Data;

@Data
public class PaymentResponse {
	
	private String message;
	private String code;
	private Payment payment;

}
