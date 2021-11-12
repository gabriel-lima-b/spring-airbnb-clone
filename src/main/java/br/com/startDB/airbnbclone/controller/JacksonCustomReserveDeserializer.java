package br.com.startDB.airbnbclone.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import br.com.startDB.airbnbclone.model.Guest;
import br.com.startDB.airbnbclone.model.Reserve;
import br.com.startDB.airbnbclone.model.Room;

public class JacksonCustomReserveDeserializer extends StdDeserializer<Reserve> {

	public JacksonCustomReserveDeserializer() {
		this(null);
	}

	public JacksonCustomReserveDeserializer(Class<Reserve> t) {
		super(t);
	}

	@Override
	public Reserve deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Reserve reserve = new Reserve();

		ObjectMapper mapper = new ObjectMapper();

		Guest guest = new Guest();
		JsonNode guest_node = node.get("guest");
		guest = mapper.treeToValue(guest_node, Guest.class);

		Room room = new Room();
		JsonNode room_node = node.get("room");
		room = mapper.treeToValue(room_node, Room.class);

		Date checkIn = null;
		Date checkOut = null;

		String checkInStr = node.get("checkIn").asText(null);
		try {
			checkIn = formatter.parse(checkInStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		String checkOutStr = node.get("checkOut").asText(null);
		try {
			checkOut = formatter.parse(checkOutStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IOException(e);
		}

		if (node.hasNonNull("id")) {
			reserve.setId(UUID.fromString(node.get("id").asText(null)));
		}

		reserve.setCheckIn(checkIn);
		reserve.setCheckOut(checkOut);
		reserve.setGuest(guest);
		reserve.setRoom(room);
		return reserve;
	}

}
