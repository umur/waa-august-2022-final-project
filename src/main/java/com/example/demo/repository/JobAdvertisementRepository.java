package com.example.demo.repository;

import com.example.demo.entity.JobAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, Long> {
}
