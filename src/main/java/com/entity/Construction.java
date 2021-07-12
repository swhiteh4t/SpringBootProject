package main.java.com.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "CONSTRUCTION")
public class Construction {
	
	@Id
	@GeneratedValue
	@Column(name="CODE")
	private Long code;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="Budget")
	private BigDecimal budget;
	
	@Column(name="State")
	private String state;
	
	@ManyToOne
    @JsonBackReference(value="client_construction")
    private Client client;
	
	@JsonIgnore
	@OneToOne(mappedBy = "lConstruction")
    private License license;
	
	@JsonIgnore
	@OneToMany(targetEntity=Schedule.class, mappedBy="construction",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value="schedule_construction")
	private Set<Schedule> schedule;
	
	@JsonIgnore
	@OneToMany(targetEntity=MaterialUsage.class, mappedBy="construction",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference(value="Material_construction")
	@Column(name="id_usage")
	private Set<MaterialUsage> materialUsageC;
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@JsonIgnore
	public License getLicense() {
		return license;
	}

	@JsonIgnore
	public Set<Schedule> getSchedule() {
		return schedule;
	}

	public void setSchedule(Set<Schedule> schedule) {
		this.schedule = schedule;
	}

	public Construction(Long code, String name, String address, BigDecimal budget, String state, Client client,
			License license) {
		super();
		this.code = code;
		this.name = name;
		this.address = address;
		this.budget = budget;
		this.state = state;
		this.client = client;
		this.license = license;
	}

	
	public Construction() {
	
	}
	
}