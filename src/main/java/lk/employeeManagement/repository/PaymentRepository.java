package lk.employeeManagement.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import lk.employeeManagement.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {

	@Query("SELECT p FROM Payment p WHERE paydate=:date")
	public List<Payment> getDetailsViaDate(@Param(value="date")Date date);
	
	@Query(value = "SELECT*FROM payment ORDER BY paymentid DESC limit 20",nativeQuery = true)
	public List<Payment> getLastPayments();
	
	@Query(value = "SELECT*FROM payment WHERE empid=:empid ORDER BY paymentid DESC limit 5",nativeQuery = true )
	public List<Payment> getLastFivePayments(@Param(value="empid")Integer empid);
	
}
