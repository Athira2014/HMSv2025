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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athira.demo.common.APIResponse;
import com.athira.demo.entity.Service;
import com.athira.demo.service.IServicesService;

@RestController
@RequestMapping("api/")
public class ServiceController {

	@Autowired
	IServicesService servicesService;

	@GetMapping("roomservices")
	public List<Service> getAllRoomServices() {
		return servicesService.getAllRoomServices();
	}

	// create service requests
	@GetMapping("roomservices/{id}")
	private ResponseEntity<APIResponse> getRoomServiceById(@PathVariable Integer id) {

		APIResponse apiResponse = new APIResponse();

		try {
			Optional<Service> services = servicesService.getRoomServiceById(id);
			apiResponse.setStatus(200);
			apiResponse.setData(services.get());

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// create service requests
	@PostMapping("roomservices")
	private ResponseEntity<APIResponse> saveRoomServices(@RequestBody Service service) {

		APIResponse apiResponse = new APIResponse();

		try {
			Service services = servicesService.saveRoomServices(service);
			apiResponse.setStatus(200);
			apiResponse.setData(services);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// create service requests
	@PutMapping("roomservices")
	private ResponseEntity<APIResponse> updateRoomServices(@RequestBody Service service) {

		APIResponse apiResponse = new APIResponse();

		try {
			Service services = servicesService.saveRoomServices(service);
			apiResponse.setStatus(200);
			apiResponse.setData(services);

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}

	// Delete service requests
	@DeleteMapping("roomservices/{id}")
	private ResponseEntity<APIResponse> deleteRoomServices(@PathVariable Integer id) {

		APIResponse apiResponse = new APIResponse();

		try {
			servicesService.deleteRoomServices(id);
			apiResponse.setStatus(200);
			apiResponse.setData("Room Service deleted succesfully.");

		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
}
