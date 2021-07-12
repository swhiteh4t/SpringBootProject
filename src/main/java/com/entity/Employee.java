package main.java.com.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.xml.txw2.annotation.XmlElement;

@Entity 
@Table(name="EMPLOYEE")
public class Employee extends Person{

	@Column(name="Category")
	private String category;
	
	@OneToMany(targetEntity=Schedule.class, mappedBy="employee",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value="schedule_employee")
	private Set<Schedule> schedules;

	@XmlElement
	public Set<Schedule> getSchedules() {
		return schedules;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Employee(String id, String name, String surname, String Address, String phoneNumber, String category) {
		super(id, name, surname, Address, phoneNumber);
		this.category = category;
	}
	
	public Employee() {
		
	}
}