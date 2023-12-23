package com.example.CourseWork.service;

import com.example.CourseWork.model.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    Guest save(Guest guest);

    List<Guest> findByGuestName(String name);

    Optional<Guest> findById(Long id);
}
