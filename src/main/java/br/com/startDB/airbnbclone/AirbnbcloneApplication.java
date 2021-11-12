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
	    	Host h1 = new Host( "febonadio@gmail.com", "senha","Felipe","Bonadio", "5514991518232");
	    	airbnbService.saveHost(h1);
	    	
	    	Host h2 = new Host( "bisaka@gmail.com", "senha","Bianca","Sakaniwa", "5514991515687");
	    	airbnbService.saveHost(h2);
	      
	    	Room r1 = new Room( "Casa", "Localizada na rua Emilio Bressan, a casa é espaçosa, bem iluminada "
	    			+ "e confortável, passando uma sensação de familiaridade ", h1, new BigDecimal("123"), "Barra Bonita");
	    	airbnbService.saveRoom(r1);
	    	h1.addRoom(r1);
	    	
	    	Room r2 = new Room( "Hotel", "Com quartos grande e equipados com tecnologia de ponta, nosso hotel"
	    			+ " tem as melhores ofertas para você e sua família!", h2, new BigDecimal("180"), "São Paulo");
	    	airbnbService.saveRoom(r2);
	    	h2.addRoom(r2);
	      
	    	airbnbService.saveGuest(new Guest("gabriellima@mail.com","senha","Gabriel","Lima","55519948-6885"));
	    };
	  }
}
