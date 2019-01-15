package com.victor.antoine.L15.L15bank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	private @Id @GeneratedValue int id;
	private String lastName, firstName, email, password;

    public User(String lastName, String firstName, String email, String password) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password=password;
	}
    
    public User() {}

	@Override
	public String toString() {
		return lastName + " " + firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
