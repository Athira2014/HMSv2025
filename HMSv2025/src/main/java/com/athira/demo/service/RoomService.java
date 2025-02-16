package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.athira.demo.dao.IRoomDao;
import com.athira.demo.entity.Room;

@Transactional
@Service
public class RoomService implements IRoomService {

	@Autowired
	IRoomDao roomDao;

	public List<Room> getAllRooms() {
		return roomDao.findAll();
	}

	public Optional<Room> getRoomById(Integer id) {

		if (id == null) {
			throw new IllegalArgumentException("Id should not be null.");
		}

		try {
			return roomDao.findById(id);
		} catch (DataAccessException e) {

			e.printStackTrace();
			throw new RuntimeException("Error occured while accessing data" + e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unknown error occured while accessing data" + e.getMessage());
		}
	}

	public List<Room> getRoomsByType(String type) {
		if (type == null || type.trim().isEmpty()) {
			throw new IllegalArgumentException("Type field should not be null or empty.");
		}
		if (type.length() > 20) {
			throw new IllegalArgumentException("Type field should not contains more than 20 letters.");
		}
		try {
			return roomDao.findByType(type);
		} catch (DataAccessException e) {

			e.printStackTrace();
			throw new RuntimeException("Error occured while accessing data" + e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unknown error occured while accessing data" + e.getMessage());
		}
	}

	public Optional<Room> getRoomByRoomNumber(Integer num) {
		
		if (num == null) {
			throw new IllegalArgumentException("Room number should not be null.");
		}
		
		try {
			return roomDao.spGetAllRoomByRoomNo(num);
		} catch (DataAccessException e) {

			e.printStackTrace();
			throw new RuntimeException("Error occured while accessing room data by room number" + e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unknown error occured while accessing data by room number" + e.getMessage());
		}
	}

	public Room saveRoom(Room room) {
		try {
			return roomDao.save(room);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public List<Room> getAvailableRooms() {
		try {
			return roomDao.findByAvailability();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage() );
		}
	}

}
