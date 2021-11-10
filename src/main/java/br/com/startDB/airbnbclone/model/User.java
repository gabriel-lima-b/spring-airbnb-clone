package br.com.startDB.airbnbclone.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class  User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	
	@Column(nullable = false)
	String email;
	
	@Column(nullable = false)
	String password;
	
	@Column(nullable = false)
	String name;
	
	@Column(nullable = false)
	String lastName;
	
	@Column(nullable = false)
	private String phone;

		
	public User() {	
	}
	
	public User(String email, String password, String name, String lastName, String phone) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	


}
