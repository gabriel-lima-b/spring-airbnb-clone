package br.com.startDB.airbnbclone.controller;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.startDB.airbnbclone.model.Room;
import br.com.startDB.airbnbclone.service.AirbnbService;

@RestController
@RequestMapping("api/rooms")
public class RoomController {

	
		@Autowired
		private AirbnbService airbnbService;
		
		@RequestMapping(value = "/{roomId}", method = RequestMethod.GET, produces = "application/json")
		public ResponseEntity<Room> getRoom(@PathVariable("roomId") UUID roomId){
			Room room = this.airbnbService.findRoomById(roomId);
			if(room == null){
				return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Room>(room, HttpStatus.OK);
		}
		
		@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
		public ResponseEntity<Collection<Room>> getRooms(){
			List<Room> rooms = (List<Room>) this.airbnbService.findAllRooms();
			if(rooms.isEmpty()){
				return new ResponseEntity<Collection<Room>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Collection<Room>>(rooms, HttpStatus.OK);
		}
	}


