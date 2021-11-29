package br.com.startDB.airbnbclone.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
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

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Room;
import br.com.startDB.airbnbclone.service.AirbnbService;
import br.com.startDB.airbnbclone.service.ApplicationTestConfig;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class RoomControllerTests {

	@Autowired
	private RoomController roomController;
	
	@MockBean
	protected AirbnbService airbnbService;

	private MockMvc mockMvc;
	
	private List<Room> rooms;
	
	@BeforeEach	
    public void initRooms(){
    	this.mockMvc = MockMvcBuilders.standaloneSetup(roomController)    			
    			.build();
    	rooms = new ArrayList<Room>();

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
    	rooms.add(room);

    	Room room2 = new Room();
    	room2.setId(UUID.fromString("1af82961-36f3-4501-9a7b-a8f46cdfd635"));
    	room2.setTitle("Prédio");
    	room2.setDescription("Cinza");;
    	room2.setCity("São Leopoldo");
    	room2.setPrice(new BigDecimal("80"));
    	room2.setHost(host);
    	rooms.add(room2);    	
    }
	
	@Test
    public void testGetRoomSuccess() throws Exception {
    	given(this.airbnbService.findRoomById(UUID.fromString("1ffd0c84-920c-49e4-9ff3-262fd95741cf"))).willReturn(rooms.get(0));
        this.mockMvc.perform(get("/api/rooms/1ffd0c84-920c-49e4-9ff3-262fd95741cf")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value("1ffd0c84-920c-49e4-9ff3-262fd95741cf"))
            .andExpect(jsonPath("$.title").value("Casa"));
               
    }
	
    @Test    
    public void testGetRoomNotFound() throws Exception {
    	given(this.airbnbService.findRoomById(UUID.fromString("9aedab49-af59-4494-903d-fe869e38b89a"))).willReturn(null);
        this.mockMvc.perform(get("/api/rooms/9aedab49-af59-4494-903d-fe869e38b89a")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
    
    @Test
    public void testGetAllRoomsSuccess() throws Exception {
    	given(this.airbnbService.findAllRooms()).willReturn(rooms);
        this.mockMvc.perform(get("/api/rooms/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.[0].id").value("1ffd0c84-920c-49e4-9ff3-262fd95741cf"))
            .andExpect(jsonPath("$.[0].title").value("Casa"))
            .andExpect(jsonPath("$.[0].description").value("Amarela"))
            .andExpect(jsonPath("$.[0].city").value("Canoas"))
            .andExpect(jsonPath("$.[0].price").value("180"))
            .andExpect(jsonPath("$.[0].host.id").value("710d9fd8-84df-497a-b37a-527028d654a9"))
            .andExpect(jsonPath("$.[0].host.name").value("Eduardo"))
            .andExpect(jsonPath("$.[0].host.lastName").value("Rodriguez"))
            .andExpect(jsonPath("$.[0].host.email").value("dudu@yahoo.com"))
            .andExpect(jsonPath("$.[0].host.password").value("dudududedu"))
            .andExpect(jsonPath("$.[0].host.phone").value("6085558763"))
            .andExpect(jsonPath("$.[1].id").value("1af82961-36f3-4501-9a7b-a8f46cdfd635"))
            .andExpect(jsonPath("$.[1].title").value("Prédio"))
            .andExpect(jsonPath("$.[1].description").value("Cinza"))
            .andExpect(jsonPath("$.[1].city").value("São Leopoldo"))
            .andExpect(jsonPath("$.[1].price").value("80"))
            .andExpect(jsonPath("$.[1].host.id").value("710d9fd8-84df-497a-b37a-527028d654a9"))
            .andExpect(jsonPath("$.[1].host.name").value("Eduardo"))
            .andExpect(jsonPath("$.[1].host.lastName").value("Rodriguez"))
            .andExpect(jsonPath("$.[1].host.email").value("dudu@yahoo.com"))
            .andExpect(jsonPath("$.[1].host.password").value("dudududedu"))
            .andExpect(jsonPath("$.[1].host.phone").value("6085558763"));
    }
    
    @Test
    public void testGetAllRoomsNotFound() throws Exception {
    	rooms.clear();
    	given(this.airbnbService.findAllRooms()).willReturn(rooms);
        this.mockMvc.perform(get("/api/rooms/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateRoomSuccess() throws Exception {
    	Room newRoom = rooms.get(0);
    	newRoom.setId(UUID.fromString("1fcd0c84-420c-49e4-9ff8-262fd95741cf"));
    	ObjectMapper mapper = new ObjectMapper();
    	String newRoomAsJSON = mapper.writeValueAsString(newRoom);
    	this.mockMvc.perform(post("/api/rooms/")
    		.content(newRoomAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
    		.andExpect(status().isCreated());
    }

    @Test
    public void testCreateRoomErrorTitleEqualsNullAndIdEqualsNull() throws Exception {
    	Room newRoom = rooms.get(0);
    	newRoom.setId(null);
    	newRoom.setTitle(null);
    	ObjectMapper mapper = new ObjectMapper();
    	String newRoomAsJSON = mapper.writeValueAsString(newRoom);
    	this.mockMvc.perform(post("/api/rooms/")
        		.content(newRoomAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
     }
    
    @Test
    public void testCreateRoomErrorRoomIsNull() throws Exception {
    	Room newRoom = null;
    	ObjectMapper mapper = new ObjectMapper();
    	String newRoomAsJSON = mapper.writeValueAsString(newRoom);
    	this.mockMvc.perform(post("/api/rooms/")
        		.content(newRoomAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
     }

//TODO: (DÚVIDA) ele está retornando um status diferente pois dentro do update esta retornando um objeto null
//    @Test
//    public void testUpdateRoomSuccess() throws Exception {
//    	given(this.airbnbService.findRoomById(UUID.fromString("1ffd0c84-920c-49e4-9ff3-262fd95741cf"))).willReturn(rooms.get(0));
//    	Room newRoom = rooms.get(0);
//    	newRoom.setTitle("Fazenda");
//    	newRoom.setDescription("Verde");
//    	newRoom.setCity("Agudo");
//    	newRoom.setPrice(new BigDecimal("500"));
//    	
//    	ObjectMapper mapper = new ObjectMapper();
//    	String newRoomAsJSON = mapper.writeValueAsString(newRoom);
//    	this.mockMvc.perform(put("/api/rooms/"+rooms.get(0).getId())
//    		.content(newRoomAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
//        	.andExpect(status().isOk())
//        	.andExpect(content().contentType("application/json"));
//
//    	this.mockMvc.perform(get("/api/rooms/1ffd0c84-920c-49e4-9ff3-262fd95741cf")
//           	.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType("application/json"))
//            .andExpect(jsonPath("$.id").value("1ffd0c84-920c-49e4-9ff3-262fd95741cf"))
//            .andExpect(jsonPath("$.title").value("Fazenda"))
//            .andExpect(jsonPath("$.description").value("Verde"))
//            .andExpect(jsonPath("$.city").value("Agudo"))
//            .andExpect(jsonPath("$.price").value("500"));
//           // .andExpect(jsonPath("$.host").value(host));
//    	
//    }
    
    @Test
    public void testUpdateRoomErrorTitleEqualsNull() throws Exception {
    	Room newRoom = rooms.get(0);
    	newRoom.setTitle(null);
    	ObjectMapper mapper = new ObjectMapper();
    	String newRoomAsJSON = mapper.writeValueAsString(newRoom);
    	this.mockMvc.perform(put("/api/rooms/1ffd0c84-920c-49e4-9ff3-262fd95741cf")
    		.content(newRoomAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isBadRequest());
     }
    
    @Test
    public void testUpdateRoomErrorRoomIsNull() throws Exception {
    	Room newRoom = null;
    	ObjectMapper mapper = new ObjectMapper();
    	String newRoomAsJSON = mapper.writeValueAsString(newRoom);
    	this.mockMvc.perform(put("/api/rooms/1ffd0c84-920c-49e4-9ff3-262fd95741cf")
    		.content(newRoomAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isBadRequest());
     }
    
    @Test
    public void testDeleteRoomSuccess() throws Exception {
    	Room newRoom = rooms.get(0);
    	ObjectMapper mapper = new ObjectMapper();
    	String newRoomAsJSON = mapper.writeValueAsString(newRoom);
    	given(this.airbnbService.findRoomById(UUID.fromString("1ffd0c84-920c-49e4-9ff3-262fd95741cf"))).willReturn(rooms.get(0));
    	this.mockMvc.perform(delete("/api/rooms/1ffd0c84-920c-49e4-9ff3-262fd95741cf")
    		.content(newRoomAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isOk());
    }

    @Test
    public void testDeleteRoomErrorInvalidId() throws Exception {
    	Room newRoom = rooms.get(0);
    	ObjectMapper mapper = new ObjectMapper();
    	String newRoomAsJSON = mapper.writeValueAsString(newRoom);
    	given(this.airbnbService.findRoomById(UUID.fromString("9aedab49-af59-4494-903d-fe869e38b89a"))).willReturn(null);
    	this.mockMvc.perform(delete("/api/rooms/9aedab49-af59-4494-903d-fe869e38b89a")
    		.content(newRoomAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isNotFound());
    }
    
}
