package com.athira.demo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.athira.demo.dao.IBookingDao;
import com.athira.demo.entity.Booking;

@Transactional
@Service
public class BookingService implements IBookingService {

	@Autowired
	IBookingDao bookingDao;

	public List<Booking> getAllBookings() {
		// TO get all bookings
		return bookingDao.findAll();
	}

	public Booking getBookingById(Integer id) {
		
		try {
			return bookingDao.findByBookingId(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<Booking> getBookingByGuestName(String name) {
		
		try {
			return bookingDao.spGetBookingByGuestName(name);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public Booking getCompleteBookingDetails(Integer id) {
		
		try {
			return bookingDao.spRetrieveGuestsAndServicesIncluded(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public void saveBooking(Booking booking) {
		
		try {
			Timestamp checkInTimestamp = new Timestamp(booking.getCheckInDate().getMillis());
			Timestamp checkOutTimestamp = new Timestamp(booking.getCheckOutDate().getMillis());


			bookingDao.spLogBookingAndUpdateRoomAvailability(booking.getGuestNo(), booking.getRoomId(),
					checkInTimestamp, checkOutTimestamp, booking.getRemarks());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Please enter valid input.");
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Data access error occured when creating booking");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occured when creating booking");
		}
	}

	public Booking updateBooking(Booking booking) {
		
		try {
			return bookingDao.save(booking);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Please enter valid input.");
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Data access error occured when updating booking");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occured when updating booking");
		}
	}
}
