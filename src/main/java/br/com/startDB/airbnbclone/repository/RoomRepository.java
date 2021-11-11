package br.com.startDB.airbnbclone.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import br.com.startDB.airbnbclone.model.Room;

public interface RoomRepository extends CrudRepository<Room, UUID>{

	Iterable<Room> findAllByCity(String city); 

}
