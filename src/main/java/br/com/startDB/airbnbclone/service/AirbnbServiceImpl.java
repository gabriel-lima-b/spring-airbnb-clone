package br.com.startDB.airbnbclone.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Room;
import br.com.startDB.airbnbclone.repository.GuestRepository;
import br.com.startDB.airbnbclone.repository.HostRepository;
import br.com.startDB.airbnbclone.repository.RoomRepository;

@Service
public class AirbnbServiceImpl implements AirbnbService {

	private GuestRepository guestRepository;
	private HostRepository hostRepository;
	private RoomRepository roomRepository;

	@Autowired
	public AirbnbServiceImpl(GuestRepository guestRepository, HostRepository hostRepository,
			RoomRepository roomRepository) {
		this.guestRepository = guestRepository;
		this.hostRepository = hostRepository;
		this.roomRepository = roomRepository;
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
	public Iterable<Guest> findAllGuests() throws DataAccessException {
		return guestRepository.findAll();
	}

	@Override
	public void saveGuest(Guest guest) throws DataAccessException {
		guestRepository.save(guest);
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
	public Iterable<Host> findAllHosts() throws DataAccessException {
		return hostRepository.findAll();
	}

	@Override
	public void saveHost(Host host) throws DataAccessException {
		hostRepository.save(host);
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
	public Iterable<Room> findAllRoomsByCity(String city) throws DataAccessException {
		return roomRepository.findAllByCity(city);
	}

	@Override
	public Iterable<Room> findAllRooms() throws DataAccessException {
		return roomRepository.findAll();
	}

	@Override
	public void saveRoom(Room room) throws DataAccessException {
		roomRepository.save(room);
	}

	@Override
	public void deleteRoom(Room room) throws DataAccessException {
		roomRepository.delete(room);
	}

}
