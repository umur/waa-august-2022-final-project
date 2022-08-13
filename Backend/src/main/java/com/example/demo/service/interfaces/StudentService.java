package com.example.demo.service.interfaces;


import com.example.demo.entity.JobAdvertisement;
import com.example.demo.entity.Student;
import com.example.demo.model.JobAdvertisementModel;
import com.example.demo.model.JobAdvertisementModel;
import com.example.demo.model.StudentModel;

import java.util.List;

public interface StudentService {
    void create(StudentModel studentModel);
    void delete(Long id);
    StudentModel findById(Long id);
    StudentModel findByProfileId(Long id);
    List<StudentModel> findAll();
    void update(StudentModel studentModel, long id);

    List<JobAdvertisementModel> findAppliedJobsById(long id);

    void applyJob(long jobAdvertisementId, Long studentId);

    List<JobAdvertisementModel> getAppliedJobs(Long studentId);
}
