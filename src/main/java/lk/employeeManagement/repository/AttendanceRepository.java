package lk.employeeManagement.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import lk.employeeManagement.model.Attendance;

public interface AttendanceRepository extends CrudRepository<Attendance,Integer>{
	
	
	@Query("SELECT a FROM Attendance a WHERE date=:date")
	public List<Attendance> getDetailsViaDate(@Param(value="date")Date date);

	@Query(value =  "SELECT*FROM attendance WHERE empid=:empid AND date=:date",nativeQuery = true )
	public Attendance getAttendanceByDate(@Param(value="empid")Integer empid,@Param(value="date")Date date);
	
	@Query(value =  "SELECT*FROM attendance WHERE empid=:empid ORDER BY attendanceid DESC limit 30",nativeQuery = true )
	public List<Attendance> lastMonthAttendance(@Param(value="empid")Integer empid);

}
