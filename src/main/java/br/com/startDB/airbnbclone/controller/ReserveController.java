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

import br.com.startDB.airbnbclone.model.Reserve;
import br.com.startDB.airbnbclone.service.AirbnbService;

@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/reserves")
public class ReserveController {
	
	@Autowired
	private AirbnbService airbnbService;
	
	@RequestMapping(value = "/{reserveId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Reserve> getReserve(@PathVariable("reserveId") UUID reserveId){
		Reserve reserve = this.airbnbService.findReserveById(reserveId);
		if(reserve == null){
			return new ResponseEntity<Reserve>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Reserve>(reserve, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Reserve>> getReserves(){
		List<Reserve> reserves = (List<Reserve>) this.airbnbService.findAllReserves();
		if(reserves.isEmpty()){
			return new ResponseEntity<Collection<Reserve>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Reserve>>(reserves, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Reserve> save(@RequestBody Reserve reserve, BindingResult bindingResult) {
		if(bindingResult.hasErrors() || (reserve == null)){
			return new ResponseEntity<Reserve>(reserve, HttpStatus.BAD_REQUEST);
		}
		airbnbService.saveReserve(reserve);
		return new ResponseEntity<Reserve>(reserve, HttpStatus.CREATED); 
	}
	
	@RequestMapping(value = "/{reserveId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Reserve> deleteById(@PathVariable("reserveId") UUID reserveId) {			
		Reserve reserve = this.airbnbService.findReserveById(reserveId);		
		airbnbService.deleteReserve(reserve);	
		if (reserve == null) {
			return new ResponseEntity<Reserve>(reserve, HttpStatus.NOT_FOUND);
		}		
		return new ResponseEntity<Reserve>(reserve, HttpStatus.OK);	
	}
	
	@RequestMapping(value = "/{reserveId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Reserve> updateReserve(@PathVariable("reserveId") UUID reserveId, @RequestBody Reserve reserve, BindingResult bindingResult){
		if(bindingResult.hasErrors() || (reserve == null)){
			return new ResponseEntity<Reserve>(reserve, HttpStatus.BAD_REQUEST);
		}
		Reserve currentReserve = this.airbnbService.findReserveById(reserveId);
		if(currentReserve == null){
			return new ResponseEntity<Reserve>(HttpStatus.NOT_FOUND);
		}
		currentReserve.setCheckIn(reserve.getCheckIn());
		currentReserve.setCheckOut(reserve.getCheckOut());		
		
		this.airbnbService.saveReserve(currentReserve);
		return new ResponseEntity<Reserve>(currentReserve, HttpStatus.NO_CONTENT);
	}
	

}
