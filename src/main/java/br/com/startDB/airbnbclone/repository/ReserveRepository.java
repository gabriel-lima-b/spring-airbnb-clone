package br.com.startDB.airbnbclone.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Reserve;

public interface ReserveRepository extends CrudRepository<Reserve, UUID> {

	Iterable<Reserve> findAllReservesByGuest(String guest);

}
