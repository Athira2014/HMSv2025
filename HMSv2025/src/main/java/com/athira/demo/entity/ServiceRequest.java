package com.athira.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;


@Entity
@Table(name = "servicerequests")
@NamedStoredProcedureQuery(name = "spLogServiceRequestForBooking", procedureName = "spLogServiceRequestForBooking", parameters = {
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "Service_Id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "Booking_Id", type = Integer.class),
		@StoredProcedureParameter(mode = ParameterMode.IN, name = "Availability", type = String.class),
		@StoredProcedureParameter(mode = ParameterMode.OUT, name = "GeneratedBookingId", type = Integer.class) })
public class ServiceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ServiceReqNo", nullable = false)
	private Integer serviceReqNo;

	@ManyToOne
	@JoinColumn(name = "ServiceId", insertable = false, updatable = false)
	private Service service;
	
	@Column(name = "ServiceId", nullable = false)
	private Integer serviceId;

	@ManyToOne
	@JoinColumn(name = "bookingId", referencedColumnName = "BookingId", insertable = false, updatable = false)
	private Booking booking;
	
	@Column(name = "bookingId", nullable = false)
	private Integer bookingId;


	@Column(name = "availability", nullable = false, length = 255)
	private String availability;

	public ServiceRequest(Integer serviceReqNo, Integer serviceId, Integer bookingId, String availability) {
		super();
		this.serviceReqNo = serviceReqNo;
		this.serviceId = serviceId;
		this.bookingId = bookingId;
		this.availability = availability;
	}

	public ServiceRequest() {
		// TODO Auto-generated constructor stub
	}

	public Integer getServiceReqNo() {
		return serviceReqNo;
	}

	public void setServiceReqNo(Integer serviceReqNo) {
		this.serviceReqNo = serviceReqNo;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

}
