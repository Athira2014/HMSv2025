package com.athira.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athira.demo.common.APIResponse;
import com.athira.demo.entity.ServiceRequest;
import com.athira.demo.service.IServiceRequestsService;
import com.athira.demo.util.JwtUtils;

@RestController
@RequestMapping("api/")
public class ServiceRequestController {

	@Autowired
	IServiceRequestsService serviceRequestsService;
	
	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("roomservicereq")
	public List<ServiceRequest> getAllRoomServiceRequests() {
		return serviceRequestsService.getAllRoomServiceRequests();
	}

	// get booking by booking id
	@GetMapping("roomservicereq/{id}")
	private ResponseEntity<APIResponse> getRoomServicesById(@PathVariable Integer id,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		APIResponse apiResponse = new APIResponse();

		try {
			Optional<ServiceRequest> services = serviceRequestsService.getServiceRequestById(id);
			apiResponse.setStatus(200);
			apiResponse.setData(services);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// get booking by booking id
	@GetMapping("roomservicereq/search/{bookingId}")
	private ResponseEntity<APIResponse> getRoomServiceRequestsByBookingId(@PathVariable Integer bookingId,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		APIResponse apiResponse = new APIResponse();

		try {
			List<ServiceRequest> services = serviceRequestsService.getServiceRequestsByBookingId(bookingId);
			apiResponse.setStatus(200);
			apiResponse.setData(services);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// create service requests
	@PostMapping("roomservicereq")
	private ResponseEntity<APIResponse> saveRoomServiceRequests(@RequestBody ServiceRequest serviceRequest,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		APIResponse apiResponse = new APIResponse();

		try {
			ServiceRequest services = serviceRequestsService.saveRoomServiceRequests(serviceRequest);
			apiResponse.setStatus(200);
			apiResponse.setData(services);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// create service requests
	@PutMapping("roomservicereq")
	private ResponseEntity<APIResponse> updateRoomServiceRequests(@RequestBody ServiceRequest serviceRequest,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}
		
		APIResponse apiResponse = new APIResponse();

		try {
			ServiceRequest services = serviceRequestsService.updateRoomServiceRequests(serviceRequest);
			apiResponse.setStatus(200);
			apiResponse.setData(services);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// Delete service requests
	@DeleteMapping("roomservicereq/{id}")
	private ResponseEntity<APIResponse> deleteRoomServiceRequests(@PathVariable Integer id,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) {

		ResponseEntity<APIResponse> tokenVerificationResponse = jwtUtils.verifyToken(auth);

		// Return the response if the token is invalid/expired
		if (tokenVerificationResponse != null) {
			return tokenVerificationResponse;
		}

		APIResponse apiResponse = new APIResponse();

		try {
			serviceRequestsService.deleteRoomServiceRequests(id);
			apiResponse.setStatus(200);
			apiResponse.setData("Room service requested deleted successfully.");

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
