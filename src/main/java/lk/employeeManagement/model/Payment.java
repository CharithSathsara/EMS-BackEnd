package lk.employeeManagement.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "paymentid")
	private Integer paymentid;
	
	@Column(name = "paydate")
	private Date paydate;
	
	@Column(name = "amount")
	private double amount;
	
	@ManyToOne
    @JoinColumn(name = "empid", referencedColumnName = "empid")
	private Employee employee;

	public Payment() {
	}
	
	public Payment(Date paydate, double amount, Employee employee) {
		this.paydate = paydate;
		this.amount = amount;
		this.employee = employee;
	}
}
