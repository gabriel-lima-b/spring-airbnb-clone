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
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import br.com.startDB.airbnbclone.model.Reserve;
import br.com.startDB.airbnbclone.model.Room;
import br.com.startDB.airbnbclone.service.AirbnbService;
import br.com.startDB.airbnbclone.service.ApplicationTestConfig;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReserveControllerTests {
	
	@Autowired
	private ReserveController reserveController;
	
	@MockBean
	protected AirbnbService airbnbService;

	private MockMvc mockMvc;
	
	private List<Reserve> reserves;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	@BeforeEach	
    public void initReserves() throws ParseException{
    	this.mockMvc = MockMvcBuilders.standaloneSetup(reserveController)    			
    			.build();
    	reserves = new ArrayList<Reserve>();
    	
    	Host host = new Host();
    	host.setId(UUID.fromString("710d9fd8-84df-497a-b37a-527028d654a9"));
    	host.setName("Eduardo");
    	host.setLastName("Rodriguez");
    	host.setEmail("dudu@yahoo.com");
    	host.setPassword("dudududedu");
    	host.setPhone("6085558763");

    	Room room = new Room();
    	room.setId(UUID.fromString("1ffd0c84-920c-49e4-9ff3-262fd95741cf"));
    	room.setTitle("Casa");
    	room.setDescription("Amarela");;
    	room.setCity("Canoas");
    	room.setPrice(new BigDecimal("180"));
    	room.setHost(host);
    	
    	Room room2 = new Room();
    	room2.setId(UUID.fromString("68c68818-ad44-4409-baaf-e0a55a64face"));
    	room2.setTitle("Casa");
    	room2.setDescription("Amarela");;
    	room2.setCity("Canoas");
    	room2.setPrice(new BigDecimal("180"));
    	room2.setHost(host);
    	
    	Guest guest = new Guest();
    	guest.setId(UUID.fromString("52a5bc9c-9618-40dc-9cef-c771d4f1890c"));
    	guest.setName("Emilio");
    	guest.setLastName("Reis");
    	guest.setEmail("ereis@gmail.com");
    	guest.setPassword("1q2w3e");
    	guest.setPhone("5132325555");
    	
    	Guest guest1 = new Guest();
    	guest1.setId(UUID.fromString("bde9a919-ce67-4487-908f-b28bd3a86e49"));
    	guest1.setName("Jennifer");
    	guest1.setLastName("Vargas");
    	guest1.setEmail("jenni@gmail.com");
    	guest1.setPassword("123456");
    	guest1.setPhone("5136555555");
    	
    	Reserve reserve = new Reserve();
    	reserve.setId(UUID.fromString("e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3"));
    	reserve.setCheckIn(formatter.parse("28/11/2021"));
    	reserve.setCheckOut(formatter.parse("30/11/2021"));
    	reserve.setRoom(room);
    	reserve.setGuest(guest);
    	reserves.add(reserve);
    	
    	Reserve reserve1 = new Reserve();
    	reserve1.setId(UUID.fromString("0159956d-d540-4d8f-b5d6-6b4b79295c0a"));
    	reserve1.setCheckIn(formatter.parse("01/12/2021"));
    	reserve1.setCheckOut(formatter.parse("05/12/2021"));
    	reserve1.setRoom(room2);
    	reserve1.setGuest(guest1);
    	reserves.add(reserve1);	
    	   	
    }

	@Test
    public void testGetReserveSuccess() throws Exception {
    	given(this.airbnbService.findReserveById(UUID.fromString("e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3"))).willReturn(reserves.get(0));
        this.mockMvc.perform(get("/api/reserves/e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value("e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3"))
            .andExpect(jsonPath("$.checkIn").value("28/11/2021"))
        	.andExpect(jsonPath("$.checkOut").value("30/11/2021"));
               
    }
	
    @Test    
    public void testGetReserveNotFound() throws Exception {
    	given(this.airbnbService.findReserveById(UUID.fromString("9aedab49-af59-4494-903d-fe869e38b89a"))).willReturn(null);
        this.mockMvc.perform(get("/api/reserves/9aedab49-af59-4494-903d-fe869e38b89a")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
    
    @Test
    public void testGetAllReservesSuccess() throws Exception {
    	given(this.airbnbService.findAllReserves()).willReturn(reserves);
        this.mockMvc.perform(get("/api/reserves/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.[0].id").value("e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3"))
            .andExpect(jsonPath("$.[0].checkIn").value("28/11/2021"))
            .andExpect(jsonPath("$.[0].checkOut").value("30/11/2021"))
            .andExpect(jsonPath("$.[0].room.id").value("1ffd0c84-920c-49e4-9ff3-262fd95741cf"))
            .andExpect(jsonPath("$.[0].room.title").value("Casa"))
            .andExpect(jsonPath("$.[0].room.price").value("180"))
            .andExpect(jsonPath("$.[0].guest.id").value("52a5bc9c-9618-40dc-9cef-c771d4f1890c"))
            .andExpect(jsonPath("$.[0].guest.name").value("Emilio"))
            .andExpect(jsonPath("$.[0].guest.lastName").value("Reis"))
            .andExpect(jsonPath("$.[0].guest.email").value("ereis@gmail.com"))
            .andExpect(jsonPath("$.[0].guest.phone").value("5132325555"))
            .andExpect(jsonPath("$.[1].id").value("0159956d-d540-4d8f-b5d6-6b4b79295c0a"))
            .andExpect(jsonPath("$.[1].checkIn").value("01/12/2021"))
            .andExpect(jsonPath("$.[1].checkOut").value("05/12/2021"))
            .andExpect(jsonPath("$.[1].room.id").value("68c68818-ad44-4409-baaf-e0a55a64face"))
            .andExpect(jsonPath("$.[1].room.title").value("Casa"))
            .andExpect(jsonPath("$.[1].room.price").value("180"))
            .andExpect(jsonPath("$.[1].guest.id").value("bde9a919-ce67-4487-908f-b28bd3a86e49"))
            .andExpect(jsonPath("$.[1].guest.name").value("Jennifer"))
            .andExpect(jsonPath("$.[1].guest.lastName").value("Vargas"))
            .andExpect(jsonPath("$.[1].guest.email").value("jenni@gmail.com"))
            .andExpect(jsonPath("$.[1].guest.phone").value("5136555555"));
    }
    
    @Test
    public void testGetAllReserveNotFound() throws Exception {
    	reserves.clear();
    	given(this.airbnbService.findAllReserves()).willReturn(reserves);
        this.mockMvc.perform(get("/api/reserves/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
//TODO: (DÚVIDA) erro ao criar nova reserva sem data    
//    @Test
//    public void testCreateReserveSuccess() throws Exception {
//    	Reserve newReserve = reserves.get(0);
//    	newReserve.setId(UUID.fromString("cfc73aca-df0b-45f9-8dee-8a0f432c2ab2"));
//    	ObjectMapper mapper = new ObjectMapper();
//    	String newReserveAsJSON = mapper.writeValueAsString(newReserve);
//    	this.mockMvc.perform(post("/api/reserves/")
//    		.content(newReserveAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
//    		.andExpect(status().isCreated());
//    }

//TODO: (DÚVIDA) não consegue mapear para json, pois não aceita data nula
//    @Test
//    public void testCreateReserveErrorCheckInEqualsNullAndIdEqualsNull() throws Exception {
//    	Reserve newReserve = reserves.get(0);
//    	newReserve.setId(null);
//    	newReserve.setCheckIn(null);
//    	ObjectMapper mapper = new ObjectMapper();
//    	String newReserveAsJSON = mapper.writeValueAsString(newReserve);
//    	this.mockMvc.perform(post("/api/reserves/")
//        		.content(newReserveAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
//        		.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
//     }
    
//TODO: (DÚVIDA) não consegue mapear para json, pois não aceita data nula
//    @Test
//    public void testCreateReserveErrorReserveIsNull() throws Exception {
//    	Reserve newReserve = null;
//    	ObjectMapper mapper = new ObjectMapper();
//    	String newReserveAsJSON = mapper.writeValueAsString(newReserve);
//    	this.mockMvc.perform(post("/api/reserves/")
//        		.content(newReserveAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
//        		.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
//     }
    
  //TODO: (DÚVIDA) ele está retornando um status diferente pois dentro do update esta retornando um objeto null
//  @Test
//  public void testUpdateReserveSuccess() throws Exception {
//  	given(this.airbnbService.findReserveById(UUID.fromString("e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3"))).willReturn(reserves.get(0));
//  	Reserve newReserve = reserves.get(0);
//  	reserve.setId(UUID.fromString("e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3"));
//		reserve.setCheckIn(formatter.parse("28/11/2021"));
//		reserve.setCheckOut(formatter.parse("30/11/2021"));
//  	
//  	ObjectMapper mapper = new ObjectMapper();
//  	String newReserveAsJSON = mapper.writeValueAsString(newReserve);
//  	this.mockMvc.perform(put("/api/reserves/e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3")
//  		.content(newRoomAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
//      	.andExpect(status().isOk())
//      	.andExpect(content().contentType("application/json"));
//
//  	this.mockMvc.perform(get("/api/reserves/e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3")
//         	.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE))
//          .andExpect(status().isOk())
//          .andExpect(content().contentType("application/json"))
//		    .andExpect(jsonPath("$.id").value("e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3"))
//  		.andExpect(jsonPath("$.checkIn").value("28/11/2021"))
//  		.andExpect(jsonPath("$.checkOut").value("30/11/2021"));
//         // .andExpect(jsonPath("$.room").value(room))
//		   // .andExpect(jsonPath("$.guest).value(guest);
//  	
//  }

//TODO: (DÚVIDA) não consegue mapear para json, pois não aceita data nula
//  @Test
//  public void testUpdateReserveErrorCheckInEqualsNull() throws Exception {
//  	Reserve newReserve = reserves.get(0);
//  	newReserve.setCheckIn(null);
//  	ObjectMapper mapper = new ObjectMapper();
//  	String newReserveAsJSON = mapper.writeValueAsString(newReserve);
//  	this.mockMvc.perform(put("/api/reserves/e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3")
//  		.content(newReserveAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
//      	.andExpect(status().isBadRequest());
//   }
  
  @Test
  public void testUpdateReserveErrorReserveIsNull() throws Exception {
  	Reserve newReserve = null;
  	ObjectMapper mapper = new ObjectMapper();
  	String newReserveAsJSON = mapper.writeValueAsString(newReserve);
  	this.mockMvc.perform(put("/api/reserves/e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3")
  		.content(newReserveAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
      	.andExpect(status().isBadRequest());
   }
  
  @Test
  public void testDeleteReserveSuccess() throws Exception {
	Reserve newReserve = reserves.get(0);
  	ObjectMapper mapper = new ObjectMapper();
  	String newReserveAsJSON = mapper.writeValueAsString(newReserve);
  	given(this.airbnbService.findReserveById(UUID.fromString("e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3"))).willReturn(reserves.get(0));
  	this.mockMvc.perform(delete("/api/reserves/e3b0f8f6-7cfb-428e-9d0c-f3d2f4d3c6f3")
  		.content(newReserveAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
      	.andExpect(status().isOk());
  }

  @Test
  public void testDeleteReserveErrorInvalidId() throws Exception {
	Reserve newReserve = reserves.get(0);
  	ObjectMapper mapper = new ObjectMapper();
  	String newReserveAsJSON = mapper.writeValueAsString(newReserve);
  	given(this.airbnbService.findReserveById(UUID.fromString("9aedab49-af59-4494-903d-fe869e38b89a"))).willReturn(null);
  	this.mockMvc.perform(delete("/api/reserves/9aedab49-af59-4494-903d-fe869e38b89a")
  		.content(newReserveAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
      	.andExpect(status().isNotFound());
  }

}
