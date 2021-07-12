package main.java.com.entity;


import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

@Entity
@Table(name = "LICENSE")
public class License {
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="state")
	private String state;
	
	@Column(name="Issuer")
	private String issuer;
	
	@JsonProperty("issue_date")
	@NotNull
	@Column(name="issue_date")
	private ZonedDateTime issueDate;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "construction_id", referencedColumnName = "CODE")
    private Construction lConstruction;
	
	public Construction getConstruction() {
		return lConstruction;
	}
	
	public ZonedDateTime getIssueDate() {
		return issueDate;
	}
	
	public void setIssueDate(ZonedDateTime issueDate) {
		this.issueDate = issueDate;
	}
	
	public String getId() {
		return id;
	}
	
	public String getIssuer() {
		return issuer;
	}
	
	public String getState() {
		return state;
	}
	
	public void setConstruction(Construction lConstruction) {
		this.lConstruction = lConstruction;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public License(String id, String issuer, String state) {
		this.id = id;
		this.issuer = issuer;
		this.state = state;
		}
	
	public License() {
		
	}
}