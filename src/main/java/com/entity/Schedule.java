package main.java.com.entity;



import java.time.ZonedDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "SCHEDULE")
public class Schedule {
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	
	@ManyToOne
    @JoinColumn(name="id_employee", nullable=false)
	@JsonBackReference(value="schedule_employee")
	@JsonProperty("id_employee")
    private Employee employee;
	
	@ManyToOne
    @JoinColumn(name="code_construction", nullable=false)
	@JsonBackReference(value="schedule_construction")
	@JsonProperty("id_construction")
    private Construction construction;
	
	@Column(name="Date")
	private ZonedDateTime date;

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Construction getConstruction() {
		return construction;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
		
	}
	
	public Schedule() {
		
	}

	public Schedule(Employee employee, Construction construction, ZonedDateTime date) {
		this.employee = employee;
		this.construction = construction;
		this.date = date;
	}
	
	
}

