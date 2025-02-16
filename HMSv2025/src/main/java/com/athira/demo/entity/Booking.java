package com.athira.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bookings")
@NamedQueries({ @NamedQuery(name = "Booking.findAll", query = "select b from Booking b"),
		@NamedQuery(name = "Booking.findBookingById", query = "select b from Booking b where b.bookingId = :bookingId")

})

@NamedStoredProcedureQuery(name = "spLogBookingAndUpdateRoomAvailability", procedureName = "spLogBookingAndUpdateRoomAvailability", parameters = {
		@StoredProcedureParameter(name = "Guest_No", type = Integer.class, mode = ParameterMode.IN),
		@StoredProcedureParameter(name = "Room_No", type = Integer.class, mode = ParameterMode.IN),
		@StoredProcedureParameter(name = "CheckIn_Date", type = DateTime.class, mode = ParameterMode.IN),
		@StoredProcedureParameter(name = "CheckOut_Date", type = DateTime.class, mode = ParameterMode.IN),
		@StoredProcedureParameter(name = "Remarks", type = String.class, mode = ParameterMode.IN) })

public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BookingId", nullable = false)
	private Integer bookingId;

	@ManyToOne
	@JoinColumn(name = "GuestNo", insertable = false, updatable = false)
	private Guest guest;

	@Column(name = "GuestNo", nullable = false)
	private Integer guestNo;

	@ManyToOne
	@JoinColumn(name = "roomId", insertable = false, updatable = false)
	private Room room;

	@Column(name = "roomId", nullable = false)
	private Integer roomId;

	@Column(name = "CheckInDate", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime checkInDate;

	@Column(name = "CheckOutDate", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime checkOutDate;

	@Column(name = "Remarks", nullable = false, length = 100)
	private String remarks;

	@JsonIgnore
	@OneToMany(mappedBy = "booking")
	private List<ServiceRequest> serviceRequests;

	public Booking() {

	}

	public Booking(Integer bookingId, Guest guest, Room room, DateTime checkInDate, DateTime checkOutDate,
			String remarks, List<ServiceRequest> serviceRequests) {
		super();
		this.bookingId = bookingId;
		this.guest = guest;
		this.room = room;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.remarks = remarks;
		this.serviceRequests = serviceRequests;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public DateTime getCheckInDate() {
		return checkInDate;
	}

	public DateTime getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckInDate(DateTime checkInDate) {
		this.checkInDate = checkInDate;
	}

	public void setCheckOutDate(DateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public List<ServiceRequest> getServiceRequests() {
		return serviceRequests;
	}

	public void setServiceRequests(List<ServiceRequest> serviceRequests) {
		this.serviceRequests = serviceRequests;
	}

	public Integer getGuestNo() {
		return guestNo;
	}

	public void setGuestNo(Integer guestNo) {
		this.guestNo = guestNo;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

}
