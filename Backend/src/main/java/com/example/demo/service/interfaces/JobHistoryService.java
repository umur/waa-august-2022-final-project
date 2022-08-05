package com.example.demo.service.interfaces;

import com.example.demo.model.JobHistoryModel;

import java.util.List;

public interface JobHistoryService {

    void create(JobHistoryModel jobHistoryModel);
    void delete(Long id);
    JobHistoryModel findById(Long id);
    List<JobHistoryModel> findAll();
    void update(JobHistoryModel jobHistoryModel, long id);

}
