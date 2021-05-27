package lk.employeeManagement.repository;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lk.employeeManagement.model.Employee;

@Repository
public interface EmployeeManagementRepository extends CrudRepository<Employee, Integer> {
	
}
