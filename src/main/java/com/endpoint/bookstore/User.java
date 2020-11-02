package com.endpoint.bookstore;

import javax.persistence.Column;
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

	// Register Only Unique Email Users
	@Column(unique=true)
	private String email;
	private String password;
	private Long timestamp;

	protected User() {}

	public User(String email, String password, long timestamp) {
		this.email = email;
		this.password = password;
		this.timestamp = timestamp;
	}

	// Getter Methods
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Long getTimestamp() {
		return timestamp;
	}
}