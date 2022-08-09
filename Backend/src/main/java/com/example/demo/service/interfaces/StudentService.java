package com.example.demo.service.interfaces;


import com.example.demo.entity.JobAdvertisement;
import com.example.demo.entity.Student;
import com.example.demo.model.JobAdvertisementModel;
import com.example.demo.model.StudentModel;

import java.util.List;

public interface StudentService {
    void create(StudentModel studentModel);
    void delete(Long id);
    StudentModel findById(Long id);
    List<StudentModel> findAll();
    void update(StudentModel studentModel, long id);

    void applyJob(long jobAdvertisementId, String kCloakId);

    List<JobAdvertisementModel> getAppliedJobs(String kCloakId);
}
