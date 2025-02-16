package com.athira.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.athira.demo.entity.ServiceRequest;

@Repository
public interface IServiceRequestDao extends JpaRepository<ServiceRequest, Integer> {

	@Query("from ServiceRequest WHERE bookingId LIKE %?1%")
	List<ServiceRequest> findByBookingId(Integer bookingId);

	@Procedure("spLogServiceRequestForBooking")
	Integer spLogServiceRequestForBooking(Integer serviceId, Integer bookingId, String availability);

}
