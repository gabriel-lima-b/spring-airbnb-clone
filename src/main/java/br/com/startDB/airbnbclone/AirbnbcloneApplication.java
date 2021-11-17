package br.com.startDB.airbnbclone;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Reserve;
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
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			
//HOSTS
            
            Host h1 = new Host( "theWolf@email.com", "whiteWolf","Gerald","of Rivia", "5514999999999");
            airbnbService.saveHost(h1);
            
            Host h2 = new Host( "ciri@email.com", "s2geraldao","Cirilla","Fiona", "5514999999998");
            airbnbService.saveHost(h2);
            
            Host h3 = new Host( "dandelion@email.com", "IloveWine","Julian","Pankratz", "5514999999997");
            airbnbService.saveHost(h3);
            
            Host h4 = new Host( "m.serenety@email.com", "passiOwner","Marquise","Serenity", "5514999999996");
            airbnbService.saveHost(h4);
        
            //ROOMS
            
            Room r1 = new Room( "Kaer Morhen", "an old keep where witchers of the School of the Wolf used to be trained.", h1, new BigDecimal("180"), "Barra Bonita");
            airbnbService.saveRoom(r1);
        
            Room r2 = new Room( "Passiflora", "Passiflora is considered the finest tavern in all of Novigrad, which is significant as there are a total of twelve within the city.", h4, new BigDecimal("120"), "Porto Alegre");
            airbnbService.saveRoom(r2);
            
            Room r3 = new Room( "Oxenfurt Academy", "Oxenfurt Academy is a renowned higher education facility located in the Redanian city of Oxenfurt. Founded by Nicodemus de Boot, its main academic rival is the Imperial Academy in Nilfgaard.", h2, new BigDecimal("120"), "Barra Bonita");
            airbnbService.saveRoom(r3);
            
            Room r4 = new Room( "Cintra Castle", "The Castle of Cintra Kingdom.",h2, new BigDecimal("420"), "Canoas");
            airbnbService.saveRoom(r4);
        
            Room r5 = new Room( "Chameleon", "The Finest Tavern of Novigrat frequented by the best bards.",h3, new BigDecimal("100"), "Porto Alegre");
            airbnbService.saveRoom(r5);
            
            //GUESTS
            Guest g1 =    new Guest("emhyr.nif@email.com","theEmp3ror","Emhyr","var Emreis","555188888888");
            airbnbService.saveGuest(g1);

            Guest g2 =    new Guest("yennefer.magic@email.com","geraldIsMine","Yennefer"," of Vengerber","555188888887");
            airbnbService.saveGuest(g2);

            Guest g3 =    new Guest("red_haired_witch@email.com","redHairIsFire","Triss","Merigold","555188888886");
            airbnbService.saveGuest(g3);
            
       //RESERVES
            
            Reserve res1 = new Reserve(formatter.parse("12/11/2021"),formatter.parse("17/11/2021"),r4,g1);
            airbnbService.saveReserve(res1);
            
            Reserve res2 = new Reserve(formatter.parse("06/12/2021"),formatter.parse("18/12/2021"),r5,g2);
            airbnbService.saveReserve(res2);
	    };
	  }
}
