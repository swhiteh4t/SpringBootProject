package main.java.com.entity;

import javax.persistence.*;

@MappedSuperclass
abstract class Person {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;
    
    @Column(name = "Address")
    private String address;
    
    @Column(name= "Phone_number")
    private String phoneNumber;
    
    //Getters
    
    public String getId() {
    	return this.id;
    }
        
    public String getName() {
		return name;
	}
    
    public String getSurname() {
		return surname;
	}
    
    public String getAddress() {
		return address;
	}
    
    public String getPhoneNumber() {
		return phoneNumber;
	}
    
    //Setters
    public void setId(String id) {
    	this.id = id;
    }
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void setSurname(String surname) {
		this.surname = surname;
	}
    
    public void setAddress(String address) {
		this.address = address;
	}
    
    public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    public Person(String id,String name, String surname,String Address, String phoneNumber){
    	this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = Address;
		this.phoneNumber = phoneNumber;
	}
    
    public Person() {
    	
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		return ", name=" + name + ", surname=" + surname + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + "]";
	}
    
    
}
