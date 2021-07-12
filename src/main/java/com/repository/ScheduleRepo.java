package main.java.com.repository;

import org.springframework.data.repository.CrudRepository;

import main.java.com.entity.Schedule;


public interface ScheduleRepo extends CrudRepository<Schedule, Long> {
		
	
}