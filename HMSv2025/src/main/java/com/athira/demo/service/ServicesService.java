package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athira.demo.dao.IServiceDao;

@Transactional
@Service
public class ServicesService implements IServicesService {

	@Autowired
	IServiceDao serviceDao;

	public List<com.athira.demo.entity.Service> getAllRoomServices() {
		return (List<com.athira.demo.entity.Service>) serviceDao.findAll();
	}

	public com.athira.demo.entity.Service saveRoomServices(com.athira.demo.entity.Service service) {
		try {
			return serviceDao.save(service);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occured while saving room services");
		}
	}

	public void deleteRoomServices(Integer id) {
		try {
			serviceDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occured while deleting a room services");
		}
	}

	public Optional<com.athira.demo.entity.Service> getRoomServiceById(Integer id) {
		try {
			 return serviceDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occured while deleting a room services");
		}
	}

}
