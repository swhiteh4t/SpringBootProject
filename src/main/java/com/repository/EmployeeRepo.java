package main.java.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import main.java.com.entity.Employee;
import main.java.com.entity.License;


public interface EmployeeRepo extends CrudRepository<Employee, String> {

	 @Query("select e from Employee e where e.category like :category")
	    List<Employee> findAllBycategory(@Param("category") String category);
}
