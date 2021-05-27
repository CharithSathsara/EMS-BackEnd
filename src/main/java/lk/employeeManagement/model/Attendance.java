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
@Table(name = "attendance")
public class Attendance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "attendanceid")
	private Integer attendanceid;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "dailysalary")
	private double dailysalary;
	
	@Column(name = "worktype")
	private String worktype;
	
	@ManyToOne
    @JoinColumn(name = "empid", referencedColumnName = "empid")
	private Employee employee;
	
	public Attendance() {
	}

	public Attendance(Date date, double dailysalary, String worktype, Employee employee) {
		this.date = date;
		this.dailysalary = dailysalary;
		this.worktype = worktype;
		this.employee = employee;
	}

	

}
