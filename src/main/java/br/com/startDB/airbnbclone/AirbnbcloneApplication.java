package br.com.startDB.airbnbclone;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Room;
import br.com.startDB.airbnbclone.service.AirbnbService;

@SpringBootApplication
public class AirbnbcloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbcloneApplication.class, args);
	}

	  @Bean
	  public CommandLineRunner demo(AirbnbService airbnbService) {
	    return (args) -> {     
	    	Host h1 = new Host( "exemplo@gmail.com", "senha","nome","sobrenome", "telefone");
	      airbnbService.saveHost(h1);
	      
	      Room r1 = new Room( "casa", "casa bonita e azul", h1, new BigDecimal("231"));
	      airbnbService.saveRoom(r1);
	      h1.addRoom(r1);
	      
	      airbnbService.saveGuest(new Guest("guestmail@mail.com","senha","Guest","Exemplo","telefone do guest"));
	    };
	  }
}
