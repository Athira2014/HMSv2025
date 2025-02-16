package com.athira.demo.dao;

import java.sql.Timestamp;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.athira.demo.entity.Booking;

@Repository
public interface IBookingDao extends JpaRepository<Booking, Integer> {

	// Custom jpql
	
	@Query("from Booking WHERE bookingId = :id")
	Booking findByBookingId(@Param("id")Integer id);

	@Procedure("spGetBookingByGuestName")
	List<Booking> spGetBookingByGuestName(String name);

	@Procedure("spRetrieveGuestsAndServicesIncluded")
	Booking spRetrieveGuestsAndServicesIncluded(Integer id);

	@Procedure("spLogBookingAndUpdateRoomAvailability")
	void spLogBookingAndUpdateRoomAvailability(Integer guestNo, Integer roomId, Timestamp checkInTimestamp,
			Timestamp checkOutTimestamp, String remarks);

}
