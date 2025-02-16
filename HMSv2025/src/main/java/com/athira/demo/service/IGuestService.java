package com.athira.demo.service;

import java.util.List;
import java.util.Optional;

import com.athira.demo.entity.Guest;

public interface IGuestService {

	List<Guest> getAllGuests();

	Optional<Guest> getGuestById(Integer id);

	List<Guest> getGuestByName(String name);

	List<Guest> getGuestByPhone(Long phone);

	Guest saveGuest(Guest guest);

}
