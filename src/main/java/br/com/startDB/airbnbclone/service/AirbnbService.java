package br.com.startDB.airbnbclone.service;

import java.util.UUID;

import org.springframework.dao.DataAccessException;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Reserve;
import br.com.startDB.airbnbclone.model.Room;

public interface AirbnbService {

	Guest findGuestById(UUID id) throws DataAccessException;
	Guest findGuestByEmailIgnoreCase(String email) throws DataAccessException;
	Iterable<Guest> findAllGuests() throws DataAccessException;
	Guest saveGuest(Guest guest) throws DataAccessException;
	Guest updateGuest(UUID guestId, Guest guest) throws DataAccessException;
	void deleteGuest(Guest guest) throws DataAccessException;
	
	Host findHostById(UUID id) throws DataAccessException;
	Host findHostByEmailIgnoreCase(String email) throws DataAccessException;
	Iterable<Host> findAllHosts() throws DataAccessException;
	Host saveHost(Host host) throws DataAccessException;
	Host updateHost(UUID hostId, Host host) throws DataAccessException;
	void deleteHost(Host host) throws DataAccessException;

	Room findRoomById(UUID id) throws DataAccessException;
	Iterable<Room> findAllByCityLikeIgnoreCase(String city) throws DataAccessException;	
	Iterable<Room> findAllRooms() throws DataAccessException;
	Room saveRoom(Room room) throws DataAccessException;
	Room updateRoom(UUID roomId, Room room) throws DataAccessException;
	void deleteRoom(Room room) throws DataAccessException;
	
	Reserve findReserveById(UUID id) throws DataAccessException;
	Iterable<Reserve> findAllReserves() throws DataAccessException;
	Reserve saveReserve(Reserve reserve) throws DataAccessException;
	Reserve updateReserve(UUID reserveId, Reserve reserve) throws DataAccessException;
	void deleteReserve(Reserve reserve) throws DataAccessException;
	Iterable<Reserve> findAllReservesByGuest(String guest) throws DataAccessException;
	

}
