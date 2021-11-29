package br.com.startDB.airbnbclone.controller;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


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

import br.com.startDB.airbnbclone.model.Guest;

import br.com.startDB.airbnbclone.service.AirbnbService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/guests")
public class GuestController {
	
	@Autowired
	private AirbnbService airbnbService;
	
	@RequestMapping(value = "/{guestId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Guest> getGuest(@PathVariable("guestId") UUID guestId){
		Guest guest = this.airbnbService.findGuestById(guestId);
		if(guest == null){
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}
		
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Guest>> getGuests(){
		List<Guest> guests = (List<Guest>) this.airbnbService.findAllGuests();
		if(guests.isEmpty()){
			return new ResponseEntity<Collection<Guest>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Guest>>(guests, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Guest> getGuestByEmail(@PathVariable("email") String email){
		Guest guest = this.airbnbService.findGuestByEmailIgnoreCase(email);
		if(guest == null){
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}	
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Guest> save(@RequestBody Guest guest, BindingResult bindingResult) {
		if(bindingResult.hasErrors() || (guest == null) || (guest.getName() == null)){
			return new ResponseEntity<Guest>(guest, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Guest>(airbnbService.saveGuest(guest), HttpStatus.CREATED); 
	}
	
	@RequestMapping(value = "/{guestId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Guest> deleteById(@PathVariable("guestId") UUID guestId) {			
		Guest guest = this.airbnbService.findGuestById(guestId);		
		airbnbService.deleteGuest(guest);	
		if (guest == null) {
			return new ResponseEntity<Guest>(guest, HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{guestId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Guest> updateGuest(@PathVariable("guestId") UUID guestId, @RequestBody Guest guest, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (guest == null) || (guest.getName() == null)){
			return new ResponseEntity<Guest>(guest, HttpStatus.BAD_REQUEST);
		}
		Guest updatedGuest = this.airbnbService.updateGuest(guestId, guest);
				
		if(updatedGuest == null){
			return new ResponseEntity<Guest>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Guest>(updatedGuest, HttpStatus.OK);
	}
	

}
