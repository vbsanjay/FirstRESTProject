package com.in28minutes.rest.webservices.restwebservices.socailMedia.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
// A user to Store all the user information


public class User {
	private Integer id;
	@Size(min = 4, message = "min 4 character required")
	private String username;
	@Past(message = "birthdate should be in the past")
	private LocalDate birthDate;
	
	public User(int id, String username, LocalDate birthDate) {
		super();
		this.id = id;
		this.username = username;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", birthDate=" + birthDate + "]";
	}
	
	
}
