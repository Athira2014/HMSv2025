package com.athira.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "guests")
@NamedQueries({ @NamedQuery(name = "Guest.findAll", query = "select g from Guest g"),
		@NamedQuery(name = "Guest.findByEmail", query = "select g from Guest g where g.email = :email") })
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GuestNo", nullable = false)
	private Integer guestNo;

	@Column(name = "GName", nullable = false, length = 50)
	private String gName;

	@Column(name = "Phone", nullable = false)
	private Long phone;

	@Column(name = "Address", nullable = false, length = 100)
	private String address;

	@Column(name = "Email", nullable = false, length = 100)
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "guest")
	private List<Booking> booking;

	public Guest(Integer guestNo, String gName, long phone, String address, String email, List<Booking> booking) {
		super();
		this.guestNo = guestNo;
		this.gName = gName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.booking = booking;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public Guest() {
	}

	public Integer getGuestNo() {
		return guestNo;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		if (gName == null || gName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be null or empty.");
		}
		if (gName.length() > 50) {
			throw new IllegalArgumentException("Name cannot exceed 50 characters.");
		}
		this.gName = gName;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		if (phone == null ) {
			throw new IllegalArgumentException("Phone number cannot be null or empty.");
		}
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address == null || address.trim().isEmpty()) {
			throw new IllegalArgumentException("Address cannot be null or empty.");
		}
		if (address.length() > 100) {
	        throw new IllegalArgumentException("Address cannot exceed 100 characters.");
	    }
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be null or empty.");
		}
		this.email = email;
	}

	public void setGuestNo(Integer guestNo) {
		this.guestNo = guestNo;
	}

}
