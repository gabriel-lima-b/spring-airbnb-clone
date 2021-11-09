package br.com.startDB.airbnbclone.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;

@Entity
public class Host extends User{

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "host", fetch = FetchType.EAGER)
    private Set<Room> rooms;

}
