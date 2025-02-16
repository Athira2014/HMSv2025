package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athira.demo.dao.IServiceRequestDao;
import com.athira.demo.entity.ServiceRequest;

@Transactional
@Service
public class ServiceRequestsService implements IServiceRequestsService {

	@Autowired
	IServiceRequestDao serviceRequestDao;

	public List<ServiceRequest> getAllRoomServiceRequests() {
		return serviceRequestDao.findAll();
	}

	public Optional<ServiceRequest> getServiceRequestById(Integer id) {

		try {
			return serviceRequestDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Service request not found with id: " + id);
		}
	}

	public List<ServiceRequest> getServiceRequestsByBookingId(Integer bookingId) {

		try {
			return serviceRequestDao.findByBookingId(bookingId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Service request not found with booking Id: " + bookingId);
		}
	}

	public ServiceRequest saveRoomServiceRequests(ServiceRequest serviceRequest) {
		try {
			Integer generatedServiceReqId = serviceRequestDao.spLogServiceRequestForBooking(serviceRequest.getServiceId(), 
					serviceRequest.getBookingId(), serviceRequest.getAvailability());

			Optional<ServiceRequest> serviceReqEntity = getServiceRequestById(generatedServiceReqId);
			return serviceReqEntity.get();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("An error occured while logging a room service request"+e.getMessage());
		}
	}

	public ServiceRequest updateRoomServiceRequests(ServiceRequest serviceRequest) {
		try {
			return serviceRequestDao.save(serviceRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("An error occured while updating a room service request"+e.getMessage());
		}
	}

	public void deleteRoomServiceRequests(Integer id) {
		serviceRequestDao.deleteById(id);
	}

}
