package com.example.demo.service.interfaces;

import com.example.demo.entity.Tag;
import com.example.demo.model.JobAdvertisementModel;
import com.example.demo.model.TagModel;

import java.util.List;

public interface JobAdvertisementService {

    void create(JobAdvertisementModel jobAdvertisementModel);
    void delete(Long id);
    JobAdvertisementModel findById(Long id);
    List<JobAdvertisementModel> findAll();
    void update(JobAdvertisementModel jobAdvertisementModel, long id);
    List<JobAdvertisementModel> getAllByState(String state);
    List<JobAdvertisementModel> getAllByCity(String city);
    List<JobAdvertisementModel> getAllByTags(List<TagModel> tags);
    List<JobAdvertisementModel> getAllByCompanyName(String name);
}
