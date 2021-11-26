package br.com.startDB.airbnbclone.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.startDB.airbnbclone.model.Guest;

public interface GuestRepository extends CrudRepository<Guest, UUID>{
	
	Guest findByEmailIgnoreCase(String email);

}
