package lk.employeeManagement.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "salary")
public class Salary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "salaryid")
	private Integer salaryid;
	
	@Column(name = "lastpaydate")
	private Date lastpaydate;
	
	@Column(name = "totalsalary")
	private double totalsalary;
	
	public Salary() {
		
	}

	public Salary(Date lastpaydate, double totalsalary) {
		this.lastpaydate = lastpaydate;
		this.totalsalary = totalsalary;
	}

}
