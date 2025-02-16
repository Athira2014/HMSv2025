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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athira.demo.common.APIResponse;
import com.athira.demo.entity.Room;
import com.athira.demo.service.IRoomService;


@RestController
@RequestMapping("api/")
public class RoomController {
	
	@Autowired
	IRoomService roomService;
	
	@GetMapping("rooms")
	public List<Room> getAllRooms() {
		return roomService.getAllRooms();
	}
	
	@GetMapping("rooms/{id}")
	public ResponseEntity<APIResponse> getRoomById(@PathVariable Integer id) {
		APIResponse apiResponse = new APIResponse();

		try {
			Optional<Room> room = roomService.getRoomById(id);
			apiResponse.setStatus(200);
			apiResponse.setData(room.get());
			
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	//get room by room number
	@GetMapping("rooms/number/{num}")
	public ResponseEntity<APIResponse> getRoomByRoomNumber(@PathVariable Integer num) {
		APIResponse apiResponse = new APIResponse();

		try {
			Optional<Room> room = roomService.getRoomByRoomNumber(num);
			apiResponse.setStatus(200);
			apiResponse.setData(room.get());
			
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping("rooms/type/{type}")
	public ResponseEntity<APIResponse> getRoomsByType(@PathVariable String type) {
		APIResponse apiResponse = new APIResponse();

		try {
			List<Room> rooms = roomService.getRoomsByType(type);
			apiResponse.setStatus(200);
			apiResponse.setData(rooms);
			
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@GetMapping("rooms/availability")
	public ResponseEntity<APIResponse> getAvailableRooms() {
		APIResponse apiResponse = new APIResponse();

		try {
			List<Room> rooms = roomService.getAvailableRooms();
			apiResponse.setStatus(200);
			apiResponse.setData(rooms);
			
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@PostMapping("rooms")
	public ResponseEntity<APIResponse> saveRoom(@RequestBody Room room) {
		APIResponse apiResponse = new APIResponse();

		try {
			Room rooms = roomService.saveRoom(room);
			apiResponse.setStatus(200);
			apiResponse.setData(rooms);
			
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	}
	
	@PutMapping("rooms")
	public ResponseEntity<APIResponse> updateRoom(@RequestBody Room room) {
		APIResponse apiResponse = new APIResponse();

		try {
			Room rooms = roomService.saveRoom(room);
			apiResponse.setStatus(200);
			apiResponse.setData(rooms);
			
		} catch (Exception e) {
			apiResponse.setStatus(500);
			apiResponse.setError(e.getMessage());
		}
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	} 
}
