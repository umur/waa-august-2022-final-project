package com.example.demo.service.interfaces;

import com.example.demo.model.JobAdvertisementModel;

import java.util.List;

public interface JobAdvertisementService {

    void create(JobAdvertisementModel jobAdvertisementModel);
    void delete(Long id);
    JobAdvertisementModel findById(Long id);
    List<JobAdvertisementModel> findAll();
    void update(JobAdvertisementModel jobAdvertisementModel, long id);
}
