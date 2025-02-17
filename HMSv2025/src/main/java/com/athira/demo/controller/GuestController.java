package com.athira.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athira.demo.common.APIResponse;
import com.athira.demo.entity.Guest;
import com.athira.demo.service.IGuestService;
import com.athira.demo.util.JwtUtils;

@RestController
@RequestMapping("api/")
public class GuestController {

	@Autowired
	IGuestService guestService;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("guests")
	public List<Guest> getAllGuests() {
		return guestService.getAllGuests();
	}

	@GetMapping("guests/{id}")
	public ResponseEntity<APIResponse> getGuestById(@PathVariable Integer id,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		APIResponse apiResponse = new APIResponse();

		try {
			Optional<Guest> guest = guestService.getGuestById(id);
			apiResponse.setStatus(200);
			apiResponse.setData(guest.get());

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@GetMapping("guests/searchbyname/{name}")
	public ResponseEntity<APIResponse> getGuestByName(@PathVariable String name,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		APIResponse apiResponse = new APIResponse();

		try {
			List<Guest> guests = guestService.getGuestByName(name);
			apiResponse.setStatus(200);
			apiResponse.setData(guests);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@GetMapping("guests/searchbyphone/{phone}")
	public ResponseEntity<APIResponse> getGuestByPhone(@PathVariable Long phone,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		APIResponse apiResponse = new APIResponse();

		try {
			List<Guest> guests = guestService.getGuestByPhone(phone);
			apiResponse.setStatus(200);
			apiResponse.setData(guests);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PostMapping("guests")
	public ResponseEntity<APIResponse> saveGuest(@RequestBody Guest guest,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}
		APIResponse apiResponse = new APIResponse();

		try {
			Guest guests = guestService.saveGuest(guest);
			apiResponse.setStatus(200);
			apiResponse.setData(guests);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	@PutMapping("guests")
	public ResponseEntity<APIResponse> updateGuest(@RequestBody Guest guest,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}
		APIResponse apiResponse = new APIResponse();

		try {
			Guest guests = guestService.saveGuest(guest);
			apiResponse.setStatus(200);
			apiResponse.setData(guests);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
