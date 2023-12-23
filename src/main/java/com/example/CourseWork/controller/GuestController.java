package com.example.CourseWork.controller;

import com.example.CourseWork.model.Guest;
import com.example.CourseWork.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "localhost:8080")
@RestController
@RequestMapping("/api/guests")
public class GuestController {
    @Autowired
    GuestRepository guestRepository;

    @GetMapping()
    public ResponseEntity<List<Guest>> getAllGuests(@RequestParam String name) {
        try {
            List<Guest> guests = new ArrayList<>();

            if(name == null) {
                guestRepository.findAll().forEach(guests::add);
            } else {
                guestRepository.findByName(name).forEach(guests::add);
            }

            if(guests.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(guests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable("id") long id) {
        Optional<Guest> guestData = guestRepository.findById(id);

        if(guestData.isPresent()) {
            return new ResponseEntity<>(guestData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        try {
            Guest _guest = guestRepository
                    .save(new Guest(guest.getName(), guest.getSurname(), guest.getPhoneNumber()));
            return new ResponseEntity<>(_guest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> update(@PathVariable("id") long id, @RequestBody Guest guest) {
        Optional<Guest> guestData = guestRepository.findById(id);

        if(guestData.isPresent()) {
            Guest _guest = guestData.get();
            _guest.setName(guest.getName());
            _guest.setSurname((guest.getSurname()));
            _guest.setPhoneNumber(guest.getPhoneNumber());
            return new ResponseEntity<>(guestRepository.save(_guest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Guest> getGuestByName(@PathVariable("name") String name) {
        List<Guest> guestData = guestRepository.findByName(name);

        if(guestData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(guestData.get(0), HttpStatus.OK);
        }
    }

    @GetMapping("/{surname}")
    public ResponseEntity<Guest> getGuestBySurname(@PathVariable("surname") String surname) {
        List<Guest> guestData = guestRepository.findBySurname(surname);

        if(guestData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(guestData.get(0), HttpStatus.OK);
        }
    }
}
