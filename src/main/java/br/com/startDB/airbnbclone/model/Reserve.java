package br.com.startDB.airbnbclone.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Reserve {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;	
    
	@Column(name = "checkIn_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date checkIn;
    
    @Column(name = "checkOut_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date checkOut;
	
    @ManyToOne
    @JoinColumn(name = "room_id")
	private Room room;

	@ManyToOne
	@JoinColumn(name = "guest_id")
	private Guest guest;
	
	public Reserve() {
		
	}

	public Reserve(Date checkIn, Date checkOut, Room room, Guest guest) {
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.room = room;
		this.guest = guest;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	
	
}
