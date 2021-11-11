package br.com.startDB.airbnbclone.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

//import br.com.startDB.airbnbclone.model.Host;
import br.com.startDB.airbnbclone.model.Room;

public class JacksonCustomRoomDeserializer extends StdDeserializer<Room>{

	public JacksonCustomRoomDeserializer(){
		this(null);
	}

	public JacksonCustomRoomDeserializer(Class<Room> t) {
		super(t);
	}

	@Override
	public Room deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		Room room = new Room();
		String title = node.get("title").asText(null);
		String description = node.get("description").asText(null);

		BigDecimal price = new BigDecimal(node.get("price").asText(null));
		
		if (node.hasNonNull("id")) {
			room.setId(UUID.fromString(node.get("id").asText(null)));
		}
		
        room.setTitle(title);
        room.setDescription(description);
        room.setPrice(price);
		return room;
	}
}
