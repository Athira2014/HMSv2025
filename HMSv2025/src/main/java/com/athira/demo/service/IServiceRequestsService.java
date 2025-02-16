package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import com.athira.demo.entity.ServiceRequest;

public interface IServiceRequestsService {

	List<ServiceRequest> getAllRoomServiceRequests();

	Optional<ServiceRequest> getServiceRequestById(Integer id);

	List<ServiceRequest> getServiceRequestsByBookingId(Integer bookingId);

	ServiceRequest saveRoomServiceRequests(ServiceRequest serviceRequest);

	ServiceRequest updateRoomServiceRequests(ServiceRequest serviceRequest);

	void deleteRoomServiceRequests(Integer id);

}
