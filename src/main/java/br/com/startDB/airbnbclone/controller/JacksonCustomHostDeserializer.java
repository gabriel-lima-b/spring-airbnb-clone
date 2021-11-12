package br.com.startDB.airbnbclone.controller;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.com.startDB.airbnbclone.model.Host;


public class JacksonCustomHostDeserializer extends StdDeserializer<Host> {

	public JacksonCustomHostDeserializer(){
		this(null);
	}

	public JacksonCustomHostDeserializer(Class<Host> t) {
		super(t);
	}

	@Override
	public Host deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		Host host= new Host();
		String name = node.get("name").asText(null);
		String lastName = node.get("lastName").asText(null);
		String email = node.get("email").asText(null);
		String password = node.get("password").asText(null);
		String phone = node.get("phone").asText(null);
		
		if (node.hasNonNull("id")) {
			host.setId(UUID.fromString(node.get("id").asText(null)));
		}
		
        host.setName(name);
        host.setLastName(lastName);
        host.setEmail(email);
        host.setPassword(password);
        host.setPhone(phone);
		return host;
	}

}
