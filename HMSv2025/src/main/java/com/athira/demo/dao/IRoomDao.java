package com.athira.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.athira.demo.entity.Room;

@Repository
public interface IRoomDao extends JpaRepository<Room, Integer> {

	@Query("from Room WHERE type LIKE %?1%")
	List<Room> findByType(String type);

	@Procedure("spGetAllRoomByRoomNo")
	Optional<Room> spGetAllRoomByRoomNo(Integer num);

	@Query("from Room WHERE availability IN ('AVAILABLE')")
	List<Room> findByAvailability();

}
