package main.java.com.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import main.java.com.entity.Employee;
import main.java.com.repository.EmployeeRepo;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepo employeeRepository;

	@GetMapping("/employees")
	public List<Employee> retrieveAllemployees() {
		return (List<Employee>) employeeRepository.findAll();
	}
	
	@GetMapping("/employees/category/{category}")
	public List<Employee> retrieveAllemployeesByCategory(@PathVariable String category) {
		return (List<Employee>) employeeRepository.findAllBycategory(category);
	}
	
	
	@GetMapping("/employees/{id}")
	public Employee retrieveemployee(@PathVariable String id) {
		Optional<Employee> construction = employeeRepository.findById(id);

		if (!construction.isPresent())
			throw new ResponseStatusException(
			          HttpStatus.NOT_FOUND, "Employee Not Found");

		return construction.get();
	}

	@DeleteMapping("/employees/{id}")
	public void deleteemployee(@PathVariable String id) {
		employeeRepository.deleteById(id);
	}

	@PostMapping("/employees")
	public ResponseEntity<Object> createemployee(@RequestBody Employee employee) {
		Employee savedEmployee = employeeRepository.save(employee);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedEmployee.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Object> updateemployee(@RequestBody Employee employee, @PathVariable String id) {

		Optional<Employee> constructionOptional = employeeRepository.findById(id);

		if (!constructionOptional.isPresent())
			return ResponseEntity.notFound().build();

		employee.setId(id);
		
		employeeRepository.save(employee);

		return ResponseEntity.noContent().build();
	}
}