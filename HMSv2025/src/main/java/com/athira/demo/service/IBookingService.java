package com.athira.demo.service;

import java.util.List;

import com.athira.demo.entity.Booking;

public interface IBookingService {

	List<Booking> getAllBookings();

	Booking getBookingById(Integer id);

	List<Booking> getBookingByGuestName(String name);

	Booking getCompleteBookingDetails(Integer id);

	void saveBooking(Booking booking);

	Booking updateBooking(Booking booking);

}
