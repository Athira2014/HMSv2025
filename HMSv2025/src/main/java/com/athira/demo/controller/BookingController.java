package com.athira.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athira.demo.common.APIResponse;
import com.athira.demo.entity.Booking;
import com.athira.demo.service.IBookingService;

@RestController
@RequestMapping("api/")
public class BookingController {

	@Autowired
	IBookingService bookingService;

	@GetMapping("bookings")
	public List<Booking> getAllBookings() {
		return bookingService.getAllBookings();
	}
	
	@GetMapping("bookings/data/{bookingId}")
	public ResponseEntity<APIResponse> getCompleteBookingDetails(@PathVariable Integer id) {
		
		APIResponse apiResponse = new APIResponse();

		try {
			Booking booking = bookingService.getCompleteBookingDetails(id);
			apiResponse.setStatus(200);
			apiResponse.setData(booking);
			
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// get booking by booking id
	@PostMapping("bookings")
	private ResponseEntity<APIResponse> createBooking(@RequestBody Booking booking) {

		APIResponse apiResponse = new APIResponse();

		try {
			bookingService.saveBooking(booking);
			apiResponse.setStatus(200);
			apiResponse.setData("Booking created succesfully");
			
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	// Update booking 
	@PutMapping("bookings")
	private ResponseEntity<APIResponse> updateBooking(@RequestBody Booking booking) {

		APIResponse apiResponse = new APIResponse();

		try {
			Booking bookingEntity = bookingService.updateBooking(booking);
			apiResponse.setStatus(200);
			apiResponse.setData(bookingEntity);
			
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	
}
