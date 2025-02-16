package com.athira.demo.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.athira.demo.common.Validation;
import com.athira.demo.dao.IGuestDao;
import com.athira.demo.entity.Guest;

@Transactional
@Service
public class GuestService implements IGuestService {
	
	@Autowired
	IGuestDao guestDao;
	
	@Autowired
	Validation validation;

	public List<Guest> getAllGuests() {
		return guestDao.findAll();
	}

	public Optional<Guest> getGuestById(Integer id) {
		
		try {
			return guestDao.findById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Enter valid guest id.");
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Data access exception occured while accessing guest data.");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occured while accessing guest data.");
		}
	}

	public List<Guest> getGuestByName(String name) {
		
		try {
			return guestDao.findByGName(name);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Enter valid guest id.");
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Data access exception occured while accessing guest data.");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occured while accessing guest data.");
		}
	}

	public List<Guest> getGuestByPhone(Long phone) {
		
		try {
			return guestDao.findByPhone(phone);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Enter valid guest id.");
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Data access exception occured while accessing guest data.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occured while accessing guest data.");
		}
	}

	public Guest saveGuest(Guest guest) {
		
		if (!validation.isValidEmail(guest.getEmail())) {
			throw new IllegalArgumentException("Email format is invalid.");
		}
		
		if (!validation.isPhoneNumberValid(String.valueOf(guest.getPhone()))) {
			throw new IllegalArgumentException("Phone number format is invalid.");
		}
	    
		try {
			return guestDao.save(guest);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Enter valid inputs.");
		}catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException("Data access exception occured while accessing guest data.");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unexpected error occured while accessing guest data.");
		}	
	}

}
