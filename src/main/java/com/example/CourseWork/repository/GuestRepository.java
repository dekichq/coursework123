package com.example.CourseWork.repository;

import com.example.CourseWork.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> findByName(String name);
    List<Guest> findBySurname(String surname);
}
