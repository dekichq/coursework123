package com.example.CourseWork.service.impl;

import com.example.CourseWork.model.Guest;
import com.example.CourseWork.repository.GuestRepository;
import com.example.CourseWork.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {
    @Autowired
    GuestRepository guestRepository;

    @Override
    public Guest save(Guest guest) {
        return guestRepository.save(guest);
    }
    @Override
    public List<Guest> findByGuestName(String name) {
        return guestRepository.findByName(name);
    }

    @Override
    public Optional<Guest> findById(Long id) {
        return guestRepository.findById(id);
    }



}
