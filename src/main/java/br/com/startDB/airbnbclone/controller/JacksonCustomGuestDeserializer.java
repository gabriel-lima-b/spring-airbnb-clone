package br.com.startDB.airbnbclone.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Reserve;

public class JacksonCustomGuestDeserializer extends StdDeserializer<Guest> {

	public JacksonCustomGuestDeserializer() {
		this(null);
	}

	public JacksonCustomGuestDeserializer(Class<Guest> t) {
		super(t);
	}

	@Override
	public Guest deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		Guest guest = new Guest();
		String name = node.get("name").asText(null);
		String lastName = node.get("lastName").asText(null);
		String email = node.get("email").asText(null);
		String password = node.get("password").asText(null);
		String phone = node.get("phone").asText(null);

		if (node.hasNonNull("id")) {
			guest.setId(UUID.fromString(node.get("id").asText(null)));
		}
		HashSet<Reserve> reserves = new HashSet<>();
		if(node.isArray()) {
			ObjectMapper mapper = new ObjectMapper();
			    for (final JsonNode reserveNode : node) {
			    	reserves.add(mapper.treeToValue(reserveNode, Reserve.class));
			    }
			}
			guest.setReserves(reserves);
		
		
		guest.setName(name);
		guest.setLastName(lastName);
		guest.setEmail(email);
		guest.setPassword(password);
		guest.setPhone(phone);
		return guest;
	}

}
