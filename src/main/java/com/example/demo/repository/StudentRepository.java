package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentSByProfileFirstName(String firstName);
    List<Student> findStudentSByProfileFirstNameContaining(String name);
    Student findByProfile_ProfileKClockId(String id);
}
