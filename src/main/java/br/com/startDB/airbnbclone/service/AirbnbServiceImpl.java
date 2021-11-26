package br.com.startDB.airbnbclone.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Reserve;
import br.com.startDB.airbnbclone.model.Room;
import br.com.startDB.airbnbclone.repository.GuestRepository;
import br.com.startDB.airbnbclone.repository.HostRepository;
import br.com.startDB.airbnbclone.repository.ReserveRepository;
import br.com.startDB.airbnbclone.repository.RoomRepository;

@Service
public class AirbnbServiceImpl implements AirbnbService {

	private GuestRepository guestRepository;
	private HostRepository hostRepository;
	private RoomRepository roomRepository;
	private ReserveRepository reserveRepository;

	@Autowired
	public AirbnbServiceImpl(GuestRepository guestRepository, HostRepository hostRepository,
			RoomRepository roomRepository, ReserveRepository reserveRepository) {
		this.guestRepository = guestRepository;
		this.hostRepository = hostRepository;
		this.roomRepository = roomRepository;
		this.reserveRepository = reserveRepository;
	}

	@Override
	public Guest findGuestById(UUID id) throws DataAccessException {
		Optional<Guest> guest = guestRepository.findById(id);
		if (guest.isEmpty()) {
			return null;
		}
		return guest.get();
	}

	@Override
	public Guest findGuestByEmailIgnoreCase(String email) throws DataAccessException {
		return guestRepository.findByEmailIgnoreCase(email);
	}
	
	@Override
	public Iterable<Guest> findAllGuests() throws DataAccessException {
		return guestRepository.findAll();
	}

	@Override
	public Guest saveGuest(Guest guest) throws DataAccessException {
		return guestRepository.save(guest);
	}

	@Override
	public Guest updateGuest(UUID guestId, Guest guest) throws DataAccessException {
		Guest currentGuest = this.findGuestById(guestId);
		
		if(currentGuest == null){
			return null;
		}
		
		currentGuest.setName(guest.getName());
		currentGuest.setLastName(guest.getLastName());
		currentGuest.setEmail(guest.getEmail());
		currentGuest.setPassword(guest.getPassword());
		currentGuest.setPhone(guest.getPhone());
		
		return guestRepository.save(currentGuest);
	}
	
	
	@Override
	public void deleteGuest(Guest guest) throws DataAccessException {
		guestRepository.delete(guest);
	}

	@Override
	public Host findHostById(UUID id) throws DataAccessException {
		Optional<Host> host = hostRepository.findById(id);
		if (host.isEmpty()) {
			return null;
		}
		return host.get();
	}
	
	@Override
	public Host findHostByEmailIgnoreCase(String email) throws DataAccessException {
		return hostRepository.findHostByEmailIgnoreCase(email);
	}


	@Override
	public Iterable<Host> findAllHosts() throws DataAccessException {
		return hostRepository.findAll();
	}

	@Override
	public Host saveHost(Host host) throws DataAccessException {
		return hostRepository.save(host);
	}
	
	@Override
	public Host updateHost(UUID hostId, Host host) throws DataAccessException {
		Host currentHost = this.findHostById(hostId);
		
		if(currentHost == null){
			return null;
		}
		
		currentHost.setName(host.getName());
		currentHost.setLastName(host.getLastName());
		currentHost.setEmail(host.getEmail());
		currentHost.setPassword(host.getPassword());
		currentHost.setPhone(host.getPhone());
		
		return hostRepository.save(currentHost);
	}

	@Override
	public void deleteHost(Host host) throws DataAccessException {
		hostRepository.delete(host);
	}

	@Override
	public Room findRoomById(UUID id) throws DataAccessException {
		Optional<Room> room = roomRepository.findById(id);
		if (room.isEmpty()) {
			return null;
		}
		return room.get();
	}

	@Override
	public Iterable<Room> findAllByCityLikeIgnoreCase(String city) throws DataAccessException {
		return 	roomRepository.findAllByCityLikeIgnoreCase(city);
	}
	

	@Override
	public Iterable<Room> findAllRooms() throws DataAccessException {
		return roomRepository.findAll();
	}

	@Override
	@Transactional
	public Room saveRoom(Room room) throws DataAccessException {
		return roomRepository.save(room);
	}
	
	@Override
	public Room updateRoom(UUID roomId, Room room) throws DataAccessException {
		Room currentRoom = this.findRoomById(roomId);
		
		if(currentRoom == null){
			return null;
		}
		
		currentRoom.setTitle(room.getTitle());
		currentRoom.setDescription(room.getDescription());
		currentRoom.setCity(room.getCity());
		currentRoom.setPrice(room.getPrice());
		
		return roomRepository.save(currentRoom);
	}

	@Override
	public void deleteRoom(Room room) throws DataAccessException {
		roomRepository.delete(room);
	}

	@Override
	public Reserve findReserveById(UUID id) throws DataAccessException {
		Optional<Reserve> reserve = reserveRepository.findById(id);
		if (reserve.isEmpty()) {
			return null;
		}
		return reserve.get();
	}

	@Override
	public Iterable<Reserve> findAllReserves() throws DataAccessException {
		return reserveRepository.findAll();
	}

	@Override
	public Reserve saveReserve(Reserve reserve) throws DataAccessException {
		return reserveRepository.save(reserve);
	}
	
	@Override
	public Reserve updateReserve(UUID reserveId, Reserve reserve) throws DataAccessException {
		Reserve currentReserve = this.findReserveById(reserveId);
		
		if(currentReserve == null){
			return null;
		}
		
		currentReserve.setCheckIn(reserve.getCheckIn());
		currentReserve.setCheckOut(reserve.getCheckOut());
		
		return reserveRepository.save(currentReserve);
	}



	@Override
	public void deleteReserve(Reserve reserve) throws DataAccessException {
		reserveRepository.delete(reserve);
	}

	@Override
	public Iterable<Reserve> findAllReservesByGuest(String guest) throws DataAccessException {
		
		UUID id = UUID.fromString(guest);
		
		return reserveRepository.findAllByGuestId(id);
	}

	
	
	


}
