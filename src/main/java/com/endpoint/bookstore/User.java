package com.endpoint.bookstore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// Hibernate makes a table out of this class
public class User {

	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

  	private String email;
	private String password;

	// Setter methods
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Getter Methods
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}