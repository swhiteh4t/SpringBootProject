package main.java.com.entity;

import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.txw2.annotation.XmlElement;

import main.java.com.tools.ClientSerializer;

@Entity
@Table(name="CLIENT")
@JsonSerialize(using = ClientSerializer.class)
@XmlRootElement(name = "client")
public class Client extends Person{
	
	@Column(name="Account_number")
	private String accountNumber;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonManagedReference(value="client_construction")
	private Set<Construction> constructions;

	@XmlElement
	public Set<Construction> getConstructions() {
		return constructions;
	}
	
	@XmlElement
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Client(String id, String name, String surname, String address, String phoneNumber, String accountNumber) {
		super(id,name,surname,address,phoneNumber);
		this.accountNumber = accountNumber;
	}

	public Client() {
	}

	@JsonIgnore
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Client: \n")
		  .append(super.toString())
		  .append("Account number :").append(accountNumber);
		return sb.toString();
	}
	
	

}
