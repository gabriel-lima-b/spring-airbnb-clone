package br.com.startDB.airbnbclone.controller;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Room;
import br.com.startDB.airbnbclone.service.AirbnbService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/rooms")
public class RoomController {

	@Autowired
	private AirbnbService airbnbService;

	@RequestMapping(value = "/{roomId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Room> getRoom(@PathVariable("roomId") UUID roomId) {
		Room room = this.airbnbService.findRoomById(roomId);
		if (room == null) {
			return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Room>> getRooms() {
		List<Room> rooms = (List<Room>) this.airbnbService.findAllRooms();
		if (rooms.isEmpty()) {
			return new ResponseEntity<Collection<Room>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Room>>(rooms, HttpStatus.OK);
	}

	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Room>> getRoomsByCity(@PathVariable("city") String city) {
		List<Room> rooms = (List<Room>) this.airbnbService.findAllRoomsByCity(city);
		if (rooms.isEmpty()) {
			return new ResponseEntity<Collection<Room>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Room>>(rooms, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Room> save(@RequestBody Room room, BindingResult bindingResult) {
		if (bindingResult.hasErrors() || (room == null)) {
			return new ResponseEntity<Room>(room, HttpStatus.BAD_REQUEST);
		}
	
		airbnbService.saveRoom(room);
		return new ResponseEntity<Room>(room, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{roomId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Room> deleteById(@PathVariable("roomId") UUID roomId) {
		Room room = this.airbnbService.findRoomById(roomId);
		if (room == null) {
			return new ResponseEntity<Room>(room, HttpStatus.NOT_FOUND);
		}
		room.getHost().removeRoom(room);
		airbnbService.deleteRoom(room);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{roomId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Room> updateRoom(@PathVariable("roomId") UUID roomId, @RequestBody Room room, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (room == null)){
			return new ResponseEntity<Room>(room, HttpStatus.BAD_REQUEST);
		}
		Room currentRoom = this.airbnbService.findRoomById(roomId);
		if(currentRoom == null){
			return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
		}
		currentRoom.setTitle(room.getTitle());
		currentRoom.setDescription(room.getDescription());
		currentRoom.setCity(room.getCity());
		currentRoom.setPrice(room.getPrice());
				
		this.airbnbService.saveRoom(currentRoom);
		return new ResponseEntity<Room>(currentRoom, HttpStatus.NO_CONTENT);
	}

}
