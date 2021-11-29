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

import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Room;
import br.com.startDB.airbnbclone.service.AirbnbService;
import br.com.startDB.airbnbclone.service.ApplicationTestConfig;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ApplicationTestConfig.class)
@WebAppConfiguration
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HostControllerTests {
	
	@Autowired
	private HostController hostController;
	
	@MockBean
	protected AirbnbService airbnbService;

	private MockMvc mockMvc;
	
	private List<Room> rooms;
	private List<Host> hosts;
	
	@BeforeEach	
    public void initHosts(){
    	this.mockMvc = MockMvcBuilders.standaloneSetup(hostController)    			
    			.build();
    	
    	rooms = new ArrayList<Room>();
    	hosts = new ArrayList<Host>();

    	Room room = new Room();
    	room.setId(UUID.fromString("1ffd0c84-920c-49e4-9ff3-262fd95741cf"));
    	room.setTitle("Casa");
    	room.setDescription("Amarela");;
    	room.setCity("Canoas");
    	room.setPrice(new BigDecimal("180"));
    	rooms.add(room);

    	Room room1 = new Room();
    	room1.setId(UUID.fromString("1af82961-36f3-4501-9a7b-a8f46cdfd635"));
    	room1.setTitle("Prédio");
    	room1.setDescription("Cinza");;
    	room1.setCity("São Leopoldo");
    	room1.setPrice(new BigDecimal("80"));  
    	rooms.add(room1);
    	
    	Host host = new Host();
    	host.setId(UUID.fromString("710d9fd8-84df-497a-b37a-527028d654a9"));
    	host.setName("Eduardo");
    	host.setLastName("Rodriguez");
    	host.setEmail("dudu@yahoo.com");
    	host.setPassword("dudududedu");
    	host.setPhone("6085558763");
    	//host.getRooms().add(room);
    	room.setHost(host);    	
    	hosts.add(host);    	
    	
    	Host host1 = new Host();
    	host1.setId(UUID.fromString("f92256d7-2cc4-43bc-bc9e-aa7f6eab66be"));
    	host1.setName("Geraldo");
    	host1.setLastName("Riveira");
    	host1.setEmail("griveira@yahoo.com");
    	host1.setPassword("1q2w3e");
    	host1.setPhone("1180802177");
    	//host.getRooms().add(room1);
    	room1.setHost(host1);    	
    	hosts.add(host1);
    }

	@Test
	public void testGetHostSuccess() throws Exception {
    	given(this.airbnbService.findHostById(UUID.fromString("710d9fd8-84df-497a-b37a-527028d654a9"))).willReturn(hosts.get(0));
        this.mockMvc.perform(get("/api/hosts/710d9fd8-84df-497a-b37a-527028d654a9")
        	.accept(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value("710d9fd8-84df-497a-b37a-527028d654a9"))
            .andExpect(jsonPath("$.name").value("Eduardo"));
        
    }
	
	@Test    
    public void testGetHostNotFound() throws Exception {
    	given(this.airbnbService.findHostById(UUID.fromString("9aedab49-af59-4494-903d-fe869e38b89a"))).willReturn(null);
        this.mockMvc.perform(get("/api/hosts/9aedab49-af59-4494-903d-fe869e38b89a")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
	
	@Test
    public void testGetAllHostsSuccess() throws Exception {
    	given(this.airbnbService.findAllHosts()).willReturn(hosts);
        this.mockMvc.perform(get("/api/hosts/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.[0].id").value("710d9fd8-84df-497a-b37a-527028d654a9"))
            .andExpect(jsonPath("$.[0].name").value("Eduardo"))
            .andExpect(jsonPath("$.[0].lastName").value("Rodriguez"))
            .andExpect(jsonPath("$.[0].email").value("dudu@yahoo.com"))
            .andExpect(jsonPath("$.[0].password").value("dudududedu"))
            .andExpect(jsonPath("$.[0].phone").value("6085558763"))
//            .andExpect(jsonPath("$.[0].rooms.id").value("1ffd0c84-920c-49e4-9ff3-262fd95741cf"))
//            .andExpect(jsonPath("$.[0].rooms.title").value("Casa"))
//            .andExpect(jsonPath("$.[0].rooms.description").value("Amarela"))
//            .andExpect(jsonPath("$.[0].rooms.city").value("Canoas"))
//            .andExpect(jsonPath("$.[0].rooms.price").value("180"))
            .andExpect(jsonPath("$.[1].id").value("f92256d7-2cc4-43bc-bc9e-aa7f6eab66be"))
            .andExpect(jsonPath("$.[1].name").value("Geraldo"))
            .andExpect(jsonPath("$.[1].lastName").value("Riveira"))
            .andExpect(jsonPath("$.[1].email").value("griveira@yahoo.com"))
            .andExpect(jsonPath("$.[1].password").value("1q2w3e"))
            .andExpect(jsonPath("$.[1].phone").value("1180802177"));
//            .andExpect(jsonPath("$.[1].rooms.id").value("1af82961-36f3-4501-9a7b-a8f46cdfd635"))
//            .andExpect(jsonPath("$.[1].rooms.title").value("Prédio"))
//            .andExpect(jsonPath("$.[1].rooms.description").value("Cinza"))
//            .andExpect(jsonPath("$.[1].rooms.city").value("São Leopoldo"))
//            .andExpect(jsonPath("$.[1].rooms.price").value("80"));
    }
    
    @Test
    public void testGetAllHostsNotFound() throws Exception {
    	hosts.clear();
    	given(this.airbnbService.findAllHosts()).willReturn(hosts);
        this.mockMvc.perform(get("/api/hosts/")
        	.accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateHostSuccess() throws Exception {
    	Host newHost = hosts.get(0);
    	newHost.setId(UUID.fromString("4891021-6e44-4a73-9de3-68f7bbb72451"));
    	ObjectMapper mapper = new ObjectMapper();
    	String newHostAsJSON = mapper.writeValueAsString(newHost);
    	this.mockMvc.perform(post("/api/hosts/")
    		.content(newHostAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
    		.andExpect(status().isCreated());
    }

    @Test
    public void testCreateHostErrorNameEqualsNullAndIdEqualsNull() throws Exception {
    	Host newHost = hosts.get(0);
    	newHost.setId(null);
    	newHost.setName(null);
    	ObjectMapper mapper = new ObjectMapper();
    	String newHostAsJSON = mapper.writeValueAsString(newHost);
    	this.mockMvc.perform(post("/api/hosts/")
        		.content(newHostAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
     }
    
    @Test
    public void testCreateHostErrorHostIsNull() throws Exception {
    	Host newHost = null;
    	ObjectMapper mapper = new ObjectMapper();
    	String newHostAsJSON = mapper.writeValueAsString(newHost);
    	this.mockMvc.perform(post("/api/hosts/")
        		.content(newHostAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        		.andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
     }

//TODO: (DÚVIDA) ele está retornando um status diferente pois dentro do update esta retornando um objeto null
//    @Test
//    public void testUpdateHostSuccess() throws Exception {
//    	given(this.airbnbService.findHostById(UUID.fromString("710d9fd8-84df-497a-b37a-527028d654a9"))).willReturn(hosts.get(0));
//    	Host newHost = hosts.get(0);
//    	newHost.setName("Eliza");
//    	newHost.setLastName("Vargas");
//    	newHost.setEmail("eliv@gmail.com");
//    	newHost.setPassword("454545");
//    	newHost.setPhone("5132652525");
//    	
//    	ObjectMapper mapper = new ObjectMapper();
//    	String newHostAsJSON = mapper.writeValueAsString(newHost);
//    	this.mockMvc.perform(put("/api/hosts/710d9fd8-84df-497a-b37a-527028d654a9")
//    		.content(newHostAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
//        	.andExpect(status().isOk())
//        	.andExpect(content().contentType("application/json"));
//
//    	this.mockMvc.perform(get("/api/hosts/710d9fd8-84df-497a-b37a-527028d654a9")
//           	.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType("application/json"))
//            .andExpect(jsonPath("$.id").value("710d9fd8-84df-497a-b37a-527028d654a9"))
//            .andExpect(jsonPath("$.name").value("Eliza"))
//            .andExpect(jsonPath("$.lastName").value("Vargas"))
//            .andExpect(jsonPath("$.email").value("eliv@gmail.com"))
//            .andExpect(jsonPath("$.password").value("454545"))
//            .andExpect(jsonPath("$.phone").value("5132652525"));
//    	
//    }
  
    @Test
    public void testUpdateHostErrorNameEqualsNull() throws Exception {
    	Host newHost = hosts.get(0);
    	newHost.setName(null);
    	ObjectMapper mapper = new ObjectMapper();
    	String newHostAsJSON = mapper.writeValueAsString(newHost);
    	this.mockMvc.perform(put("/api/hosts/710d9fd8-84df-497a-b37a-527028d654a9")
    		.content(newHostAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isBadRequest());
     }
    
    @Test
    public void testUpdateHostErrorHostIsNull() throws Exception {
    	Host newHost = null;
    	ObjectMapper mapper = new ObjectMapper();
    	String newHostAsJSON = mapper.writeValueAsString(newHost);
    	this.mockMvc.perform(put("/api/hosts/710d9fd8-84df-497a-b37a-527028d654a9")
    		.content(newHostAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isBadRequest());
     }
    
    @Test
    public void testDeleteHostSuccess() throws Exception {
    	Host newHost = hosts.get(0);
    	ObjectMapper mapper = new ObjectMapper();
    	String newHostAsJSON = mapper.writeValueAsString(newHost);
    	given(this.airbnbService.findHostById(UUID.fromString("710d9fd8-84df-497a-b37a-527028d654a9"))).willReturn(hosts.get(0));
    	this.mockMvc.perform(delete("/api/hosts/710d9fd8-84df-497a-b37a-527028d654a9")
    		.content(newHostAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isOk());
    }

    @Test
    public void testDeleteHostErrorInvalidId() throws Exception {
    	Host newHost = hosts.get(0);
    	ObjectMapper mapper = new ObjectMapper();
    	String newHostAsJSON = mapper.writeValueAsString(newHost);
    	given(this.airbnbService.findHostById(UUID.fromString("9aedab49-af59-4494-903d-fe869e38b89a"))).willReturn(null);
    	this.mockMvc.perform(delete("/api/hosts/9aedab49-af59-4494-903d-fe869e38b89a")
    		.content(newHostAsJSON).accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
        	.andExpect(status().isNotFound());
    }

}
