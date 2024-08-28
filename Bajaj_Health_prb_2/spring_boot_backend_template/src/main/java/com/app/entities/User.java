package com.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity 
{

	@Column(name = "roll_number", length = 25) 
	private String rollNumber;
	
	@Column(name = "first_name", length = 25) 
	private String firstName;
	
	@Column(name = "last_name", length = 25) 
	private String lastName;
	
	@Column(length = 40, unique = true) 
	private String email;

	@Column(length = 10, unique = true) 
	private String phonenumber;

		
	public String getRollNumber() {
		return rollNumber;
	}


	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
