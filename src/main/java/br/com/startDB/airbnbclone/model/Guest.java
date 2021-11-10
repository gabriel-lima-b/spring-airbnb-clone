package br.com.startDB.airbnbclone.model;

import javax.persistence.Entity;

@Entity
public class Guest extends User{
	public Guest(){
		super();
		
	}

	public Guest(String email, String password, String name, String lastName, String phone) {
		super(email, password, name, lastName, phone);
		// TODO Auto-generated constructor stub
	}
	
	
	
	}