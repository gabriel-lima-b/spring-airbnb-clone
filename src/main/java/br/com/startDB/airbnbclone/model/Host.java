package br.com.startDB.airbnbclone.model;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.startDB.airbnbclone.controller.JacksonCustomHostDeserializer;
import br.com.startDB.airbnbclone.controller.JacksonCustomHostSerializer;

@JsonSerialize(using = JacksonCustomHostSerializer.class)
@JsonDeserialize(using = JacksonCustomHostDeserializer.class)
@Entity
public class Host extends User{

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "host", fetch = FetchType.EAGER)
    private Set<Room> rooms;
	
	
	public Host() {
		super();
	}

	public Host(String email, String password, String name, String lastName, String phone) {
		super(email, password, name, lastName, phone);
		this.rooms = new HashSet<Room>();
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public boolean removeRoom (Room room) {
		return this.rooms.remove(room);
	}
	
}