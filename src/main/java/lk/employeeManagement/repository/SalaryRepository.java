package lk.employeeManagement.repository;



import java.sql.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.employeeManagement.model.Salary;



@Repository
public interface SalaryRepository extends CrudRepository<Salary, Integer> {
	
	@Query("SELECT s FROM Salary s WHERE salaryid=:salaryid")
	public Salary getSalary(@Param(value="salaryid")Integer salaryid);
	
	@Query("UPDATE Salary SET totalsalary=:totalsalary WHERE salaryid=:salaryid")
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	public void updateAttendanceSalary(@Param(value = "totalsalary")double totalsalary, @Param(value = "salaryid")Integer salaryid);
			
	
	@Query("UPDATE Salary SET totalsalary=:totalsalary,lastpaydate=:lastpaydate WHERE salaryid=:salaryid")
	@Modifying(flushAutomatically = true,clearAutomatically = true)
	public void updateSalary(@Param(value = "totalsalary")double totalsalary, @Param(value = "salaryid")Integer salaryid
			,@Param(value = "lastpaydate")Date lastpaydate);


}
