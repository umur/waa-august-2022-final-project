package com.example.demo.repository;

import com.example.demo.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findFacultiesByProfileFirstName(String firstName);
    List<Faculty> findFacultiesByProfileFirstNameContaining(String name);
    Faculty findByProfile_ProfileKClockId(String id);
}
