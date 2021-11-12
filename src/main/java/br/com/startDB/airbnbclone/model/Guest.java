package br.com.startDB.airbnbclone.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.startDB.airbnbclone.controller.JacksonCustomGuestSerializer;

@JsonSerialize(using = JacksonCustomGuestSerializer.class)
@Entity
public class Guest extends User {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "guest", fetch = FetchType.EAGER)
	private Set<Reserve> reserves;

	public Guest() {
		super();
		this.reserves = new HashSet<>();
	}

	public Guest(String email, String password, String name, String lastName, String phone) {
		super(email, password, name, lastName, phone);
		this.reserves = new HashSet<>();
	}

	public Set<Reserve> getReserves() {
		return reserves;
	}

	public void setReserves(Set<Reserve> reserves) {
		this.reserves = reserves;
	}

	public void addReserve(Reserve reserve) {
		this.reserves.add(reserve);
	}

	public boolean removeReserve(Reserve reserve) {
		return this.reserves.remove(reserve);
	}

}