package br.com.startDB.airbnbclone.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Room;
import br.com.startDB.airbnbclone.service.AirbnbService;
import br.com.startDB.airbnbclone.service.ApplicationTestConfig;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GuestControllerTests {
	
	@Autowired
	private GuestController guestController;
	
	@MockBean
	protected AirbnbService airbnbService;

	private MockMvc mockMvc;
	
	private List<Guest> guests;

	@BeforeEach	
    public void initGuests(){
		this.mockMvc = MockMvcBuilders.standaloneSetup(guestController)    			
    			.build();
    	guests = new ArrayList<Guest>();
    	
    	Guest guest = new Guest();
    	guest.setId(UUID.fromString("b08dc59a-9a84-4d86-a16d-a757e32a6b74"));
    	guest.setName("Emilio");
    	guest.setLastName("Reis");
    	guest.setEmail("ereis@yahoo.com");
    	guest.setPassword("123456");
    	guest.setPhone("5132658888");
    	guests.add(guest);
    	
    	Guest guest1 = new Guest();
    	guest1.setId(UUID.fromString("1d2bf2f9-1420-4da6-adc9-5236ce7e0947"));
    	guest1.setName("Jennifer");
    	guest1.setLastName("Vargas");
    	guest1.setEmail("jenni@yahoo.com");
    	guest1.setPassword("7890");
    	guest1.setPhone("5132654444");
    	guests.add(guest1);
	
	}
	
	@Test
    public void testGetGuestSuccess() throws Exception{
		given(this.airbnbService.findGuestById(UUID.fromString("b08dc59a-9a84-4d86-a16d-a757e32a6b74"))).willReturn(guests.get(0));
        this.mockMvc.perform(get("/api/guests/b08dc59a-9a84-4d86-a16d-a757e32a6b74")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value("b08dc59a-9a84-4d86-a16d-a757e32a6b74"))
            .andExpect(jsonPath("$.name").value("Emilio"));
	}
	
	@Test    
	public void testGetGuestNotFound() throws Exception {
	    given(this.airbnbService.findGuestById(UUID.fromString("21423ae2-8541-4727-a4e9-162219f06a81"))).willReturn(null);
	    this.mockMvc.perform(get("/api/guests/21423ae2-8541-4727-a4e9-162219f06a81")
	        .accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isNotFound());
	}
	
	@Test
    public void testGetAllGuestsSuccess() throws Exception {
    	given(this.airbnbService.findAllGuests()).willReturn(guests);
        this.mockMvc.perform(get("/api/guests/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.[0].id").value("b08dc59a-9a84-4d86-a16d-a757e32a6b74"))
            .andExpect(jsonPath("$.[0].name").value("Emilio"))
            .andExpect(jsonPath("$.[0].lastName").value("Reis"))
            .andExpect(jsonPath("$.[0].email").value("ereis@yahoo.com"))
            .andExpect(jsonPath("$.[0].password").value("123456"))
            .andExpect(jsonPath("$.[0].phone").value("5132658888"))
            .andExpect(jsonPath("$.[1].id").value("1d2bf2f9-1420-4da6-adc9-5236ce7e0947"))
            .andExpect(jsonPath("$.[1].name").value("Jennifer"))
            .andExpect(jsonPath("$.[1].lastName").value("Vargas"))
            .andExpect(jsonPath("$.[1].email").value("jenni@yahoo.com"))
            .andExpect(jsonPath("$.[1].password").value("7890"))
            .andExpect(jsonPath("$.[1].phone").value("5132654444"));
	}
	
	@Test
    public void testGetAllGuestsNotFound() throws Exception {
    	guests.clear();
    	given(this.airbnbService.findAllGuests()).willReturn(guests);
        this.mockMvc.perform(get("/api/guests/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateGuestSuccess() throws Exception {
    	Guest newGuest = guests.get(0);
    	newGuest.setId(UUID.fromString("13ab5ac1-2f63-44db-90d9-2a20ceda6a00"));
    	ObjectMapper mapper = new ObjectMapper();
    	String newGuestAsJSON = mapper.writeValueAsString(newGuest);
    	this.mockMvc.perform(post("/api/guests/")
    		.content(newGuestAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
    		.andExpect(status().isCreated());
    }
    
    @Test
    public void testCreateGuestErrorNameEqualsNullAndIdEqualsNull() throws Exception {
    	Guest newGuest = guests.get(0);
    	newGuest.setId(null);
    	newGuest.setName(null);
    	ObjectMapper mapper = new ObjectMapper();
    	String newGuestAsJSON = mapper.writeValueAsString(newGuest);
    	this.mockMvc.perform(post("/api/guests/")
        		.content(newGuestAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
     }
    
    @Test
    public void testCreateGuestErrorGuestIsNull() throws Exception {
    	Guest newGuest = null;
    	ObjectMapper mapper = new ObjectMapper();
    	String newGuestAsJSON = mapper.writeValueAsString(newGuest);
    	this.mockMvc.perform(post("/api/guests/")
        		.content(newGuestAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
     }
    
    //TODO: (DÚVIDA) ele está retornando um status diferente pois dentro do update esta retornando um objeto null
//    @Test
//    public void testUpdateGuestSuccess() throws Exception {
//  	  given(this.airbnbService.findGuestById(UUID.fromString("b08dc59a-9a84-4d86-a16d-a757e32a6b74"))).willReturn(guests.get(0));
//  	  Guest newGuest = guests.get(0);
//  	  newGuest.setName("Fernando");
//  	  newGuest.setLastName("Vitti");
//  	  newGuest.setEmail("vtt@gmail.com");
//  	  newGuest.setPassword("555555");
//  	  newGuest.setPhone("5536667777");
//  	
//  	  ObjectMapper mapper = new ObjectMapper();
//  	  String newGuestAsJSON = mapper.writeValueAsString(newGuest);
//  	  this.mockMvc.perform(put("/api/guests/"+guests.get(0).getId())
//  		  .content(newGuestAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
//      	  .andExpect(status().isOk())
//      	  .andExpect(content().contentType("application/json"));
//
//  	  this.mockMvc.perform(get("/api/guests/b08dc59a-9a84-4d86-a16d-a757e32a6b74")
//         	.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType("application/json"))
//            .andExpect(jsonPath("$.id").value("b08dc59a-9a84-4d86-a16d-a757e32a6b74"))
//            .andExpect(jsonPath("$.name").value("Fernando"))
//            .andExpect(jsonPath("$.lastName").value("Vitti"))
//            .andExpect(jsonPath("$.email").value("vtt@gmail.com"))
//            .andExpect(jsonPath("$.password").value("555555"))
//            .andExpect(jsonPath("$.phone").value("553666777"));
//  	
//    }
    
    @Test
    public void testUpdateGuestErrorNameEqualsNull() throws Exception {
    	Guest newGuest = guests.get(0);
    	newGuest.setName(null);
    	ObjectMapper mapper = new ObjectMapper();
    	String newGuestAsJSON = mapper.writeValueAsString(newGuest);
    	this.mockMvc.perform(put("/api/guests/b08dc59a-9a84-4d86-a16d-a757e32a6b74")
    		.content(newGuestAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isBadRequest());
     }
    
    @Test
    public void testUpdateGuestErrorGuestIsNull() throws Exception {
    	Guest newGuest = null;
    	ObjectMapper mapper = new ObjectMapper();
    	String newGuestAsJSON = mapper.writeValueAsString(newGuest);
    	this.mockMvc.perform(put("/api/guests/b08dc59a-9a84-4d86-a16d-a757e32a6b74")
    		.content(newGuestAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isBadRequest());
     }
    
    @Test
    public void testDeleteGuestSuccess() throws Exception {
    	Guest newGuest = guests.get(0);
    	ObjectMapper mapper = new ObjectMapper();
    	String newGuestAsJSON = mapper.writeValueAsString(newGuest);
    	given(this.airbnbService.findGuestById(UUID.fromString("b08dc59a-9a84-4d86-a16d-a757e32a6b74"))).willReturn(guests.get(0));
    	this.mockMvc.perform(delete("/api/guests/b08dc59a-9a84-4d86-a16d-a757e32a6b74")
    		.content(newGuestAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isOk());
    }

    @Test
    public void testDeleteGuestErrorInvalidId() throws Exception {
    	Guest newGuest = guests.get(0);
    	ObjectMapper mapper = new ObjectMapper();
    	String newGuestAsJSON = mapper.writeValueAsString(newGuest);
    	given(this.airbnbService.findGuestById(UUID.fromString("21423ae2-8541-4727-a4e9-162219f06a81"))).willReturn(null);
    	this.mockMvc.perform(delete("/api/guests/21423ae2-8541-4727-a4e9-162219f06a81")
    		.content(newGuestAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isNotFound());
    }

}
