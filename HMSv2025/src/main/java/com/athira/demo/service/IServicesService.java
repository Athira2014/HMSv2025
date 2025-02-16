package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import com.athira.demo.entity.Service;

public interface IServicesService {

	List<Service> getAllRoomServices();

	Service saveRoomServices(Service service);

	void deleteRoomServices(Integer id);

	Optional<Service> getRoomServiceById(Integer id);

}
