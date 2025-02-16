package com.athira.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.athira.demo.entity.Guest;

@Repository
public interface IGuestDao extends JpaRepository<Guest, Integer> {

	@Query("from Guest WHERE gName LIKE %?1%")
	List<Guest> findByGName(String name);

	@Query("from Guest WHERE phone= :phone")
	List<Guest> findByPhone(@Param("phone") Long phone);

}
