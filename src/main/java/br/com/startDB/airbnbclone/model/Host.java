package br.com.startDB.airbnbclone.model;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Host extends User{

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "host", fetch = FetchType.EAGER)
    private Set<Room> rooms;

}
