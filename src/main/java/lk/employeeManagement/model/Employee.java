package lk.employeeManagement.model;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empid")
	private Integer empid;
	
	@Column(name = "phonenumber")
	private Integer phonenumber;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "joindate")
	private Date joindate;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salary_id", referencedColumnName = "salaryid")
    private Salary salary;

	public Employee() {
	
	}

	public Employee(Integer phonenumber, String address, String firstname, String lastname, Date joindate,
			Salary salary) {
		this.phonenumber = phonenumber;
		this.address = address;
		this.firstname = firstname;
		this.lastname = lastname;
		this.joindate = joindate;
		this.salary = salary;
	}
	
	

	
	
}
