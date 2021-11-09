package br.com.startDB.airbnbclone.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	@Column(nullable = false)
	String title;
	@Column(nullable = false)
	String description;
	@Column(nullable = false)
	String hostName;
	@Column(nullable = false)
	BigDecimal price;

	public Room() {

	}

	public Room(String title, String description, String hostName, BigDecimal price) {
		this.title = title;
		this.description = description;
		this.hostName = hostName;
		this.price = price;
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

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
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

}
