package br.com.startDB.airbnbclone.service;

import java.util.UUID;

import org.springframework.dao.DataAccessException;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Reserve;
import br.com.startDB.airbnbclone.model.Room;

public interface AirbnbService {

	Guest findGuestById(UUID id) throws DataAccessException;
	Iterable<Guest> findAllGuests() throws DataAccessException;
	void saveGuest(Guest guest) throws DataAccessException;
	void deleteGuest(Guest guest) throws DataAccessException;
	
	Host findHostById(UUID id) throws DataAccessException;
	Iterable<Host> findAllHosts() throws DataAccessException;
	void saveHost(Host host) throws DataAccessException;
	void deleteHost(Host host) throws DataAccessException;

	Room findRoomById(UUID id) throws DataAccessException;
	Iterable<Room> findAllRoomsByCity(String city) throws DataAccessException;
	Iterable<Room> findAllRooms() throws DataAccessException;
	void saveRoom(Room room) throws DataAccessException;
	void deleteRoom(Room room) throws DataAccessException;	
	
	Reserve findReserveById(UUID id) throws DataAccessException;
	Iterable<Reserve> findAllReserves() throws DataAccessException;
	void saveReserve(Reserve reserve) throws DataAccessException;
	void deleteReserve(Reserve reserve) throws DataAccessException;
	Iterable<Reserve> findAllReservesByGuest(String guest) throws DataAccessException;
	

}
