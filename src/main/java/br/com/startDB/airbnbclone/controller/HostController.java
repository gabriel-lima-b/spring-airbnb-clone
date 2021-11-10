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

import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.service.AirbnbService;

@RestController
@RequestMapping("api/hosts")
public class HostController {

	@Autowired
	private AirbnbService airbnbService;
	
	@RequestMapping(value = "/{hostId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Host> getHost(@PathVariable("hostId") UUID hostId){
		Host host = this.airbnbService.findHostById(hostId);
		if(host == null){
			return new ResponseEntity<Host>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Host>(host, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Collection<Host>> getHosts(){
		List<Host> hosts = (List<Host>) this.airbnbService.findAllHosts();
		if(hosts.isEmpty()){
			return new ResponseEntity<Collection<Host>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Host>>(hosts, HttpStatus.OK);
	}
}
	
	
	

