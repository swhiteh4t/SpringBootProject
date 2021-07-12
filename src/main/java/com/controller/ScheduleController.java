package main.java.com.controller;

import java.net.URI;
import java.time.ZonedDateTime;
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

import main.java.com.entity.Construction;
import main.java.com.entity.Employee;
import main.java.com.entity.Schedule;
import main.java.com.repository.ConstructionRepo;
import main.java.com.repository.EmployeeRepo;
import main.java.com.repository.ScheduleRepo;

@RestController
public class ScheduleController {

	@Autowired
	private ScheduleRepo scheduleRepository;
	@Autowired
	private EmployeeRepo employeeRepository;
	@Autowired
	private ConstructionRepo constructionRepo;
	
	@GetMapping("/schedules")
	public List<Schedule> retrieveAllschedules() {
		return (List<Schedule>) scheduleRepository.findAll();
	}

	@GetMapping("/schedules/{id_schedule}")
	public Schedule retrieveschedule(@PathVariable String id_schedule) {
		Optional<Schedule> schedule = scheduleRepository.findById(Long.parseLong(id_schedule));

		if (!schedule.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule Not Found");

		return schedule.get();
	}

	@DeleteMapping("/schedules/{id_schedule}")
	public void deleteschedule(@PathVariable String id_schedule, @PathVariable String construction_code) {

		scheduleRepository.deleteById(Long.parseLong(id_schedule));
	}

	@PostMapping("/schedules")
	public ResponseEntity<Object> createschedule(@RequestBody ScheduleRequest req) {
		Optional<Construction> c = constructionRepo.findById(req.getId_construction());
		Optional<Employee> e = employeeRepository.findById(req.getId_employee());
		
		if (!c.isPresent())
			return ResponseEntity.notFound().build();
		if (!e.isPresent())
			return ResponseEntity.notFound().build();
		
		Schedule savedSchedule = scheduleRepository.save(new Schedule(e.get(),c.get(),req.getDate()));
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedSchedule.getId().toString()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/schedules/{id_schedule}")
	public ResponseEntity<Object> updateschedule(@RequestBody Schedule schedule, @PathVariable String id_schedule) {
		
		Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule.getId());

		if (!scheduleOptional.isPresent())
			return ResponseEntity.notFound().build();

		schedule.setId(Long.parseLong(id_schedule));

		scheduleRepository.save(schedule);

		return ResponseEntity.noContent().build();
	}
}

class ScheduleRequest{
	private String id_employee;
	private Long id_construction;
	private ZonedDateTime date;
	public String getId_employee() {
		return id_employee;
	}
	public void setId_employee(String id_employee) {
		this.id_employee = id_employee;
	}
	public Long getId_construction() {
		return id_construction;
	}
	public void setId_construction(Long id_construction) {
		this.id_construction = id_construction;
	}
	public ZonedDateTime getDate() {
		return date;
	}
	public void setDate(ZonedDateTime date) {
		this.date = date;
	}
	public ScheduleRequest(String id_employee, Long id_construction, ZonedDateTime date) {
		super();
		this.id_employee = id_employee;
		this.id_construction = id_construction;
		this.date = date;
	}
	
	public ScheduleRequest() {}
}

