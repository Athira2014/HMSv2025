package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import com.athira.demo.entity.Room;

public interface IRoomService {

	List<Room> getAllRooms();

	Optional<Room> getRoomById(Integer id);

	List<Room> getRoomsByType(String type);

	Optional<Room> getRoomByRoomNumber(Integer num);

	Room saveRoom(Room room);

	List<Room> getAvailableRooms();

}
