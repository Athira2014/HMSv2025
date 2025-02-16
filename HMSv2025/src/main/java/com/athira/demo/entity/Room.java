package com.athira.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rooms")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "spGetAllRoomDetails", procedureName = "spGetAllRoomDetails", resultClasses = Room.class),
		@NamedStoredProcedureQuery(name = "spGetAllRoomByRoomNo", procedureName = "spGetAllRoomByRoomNo", resultClasses = Room.class, parameters = {
				@StoredProcedureParameter(name = "room_no", type = Integer.class, mode = ParameterMode.IN) }) })
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "roomId", nullable = false)
	private Integer roomId;

	@Column(name = "roomNo", nullable = false)
	private Integer roomNo;

	@Column(name = "Type", nullable = false, length = 20)
	private String type;

	@Column(name = "PricePerNight", nullable = false)
	private double pricePerNight;

	@Column(name = "Availability", nullable = false, length = 20)
	private String availability;

	@JsonIgnore
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<Booking> bookings;

	public Room() {
	}

	public Room(Integer roomId, Integer roomNo, String type, double pricePerNight, String availability) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.type = type;
		this.pricePerNight = pricePerNight;
		this.availability = availability;
	}

	public Room(Integer roomId, Integer roomNo, String type, double pricePerNight, String availability,
			List<Booking> bookings) {
		super();
		this.roomId = roomId;
		this.roomNo = roomNo;
		this.type = type;
		this.pricePerNight = pricePerNight;
		this.availability = availability;
		this.bookings = bookings;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		if (roomNo == null || roomNo <= 0) {
			throw new IllegalArgumentException("Room number must be greater than 0.");
		}
		this.roomNo = roomNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type == null || type.length() < 3 || type.length() > 20) {
			throw new IllegalArgumentException("Room type must be between 3 and 20 characters.");
		}
		this.type = type;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		if (pricePerNight <= 0) {
			throw new IllegalArgumentException("Price per night must be greater than 0.");
		}
		this.pricePerNight = pricePerNight;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		if (availability == null || !availability.matches("AVAILABLE|BOOKED|MAINTENANCE")) {
			throw new IllegalArgumentException(
					"Availability must be one of the following: AVAILABLE, BOOKED, MAINTENANCE.");
		}
		this.availability = availability;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

}
