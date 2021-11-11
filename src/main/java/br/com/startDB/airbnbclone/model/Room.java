package br.com.startDB.airbnbclone.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.startDB.airbnbclone.controller.JacksonCustomRoomDeserializer;
import br.com.startDB.airbnbclone.controller.JacksonCustomRoomSerializer;

@JsonSerialize(using = JacksonCustomRoomSerializer.class)
@JsonDeserialize(using = JacksonCustomRoomDeserializer.class)
@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String city;

	@ManyToOne
	@JoinColumn(name = "host_id")
	private Host host;

	@Column(nullable = false)
	private BigDecimal price;

	public Room() {

	}

	public Room(String title, String description, Host host, BigDecimal price, String city) {
		this.title = title;
		this.description = description;
		this.host = host;
		this.price = price;
		this.city = city;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Host getHost() {
		return host;
	}

	public void setHost(Host host) {
		this.host = host;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
}
