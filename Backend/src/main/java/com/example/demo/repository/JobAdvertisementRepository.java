package com.example.demo.repository;

import com.example.demo.entity.JobAdvertisement;
import com.example.demo.entity.Tag;
import com.example.demo.model.JobAdvertisementModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface JobAdvertisementRepository extends JpaRepository<JobAdvertisement, Long> {
    List<JobAdvertisement> findAllByState(String state);
    List<JobAdvertisement> findAllByCity(String city);
    List<JobAdvertisement> findAllByCompanyName(String name);
    List<JobAdvertisement> findAllByTagListContains(Tag tag);
}
