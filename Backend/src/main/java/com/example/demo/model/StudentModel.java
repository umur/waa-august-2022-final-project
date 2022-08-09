package com.example.demo.model;

import com.example.demo.entity.Department;
import com.example.demo.entity.JobHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
    private Long Id;
    private String studentKClockId;
    private double gpa;
    private ProfileModel profile;
    private DepartmentModel major;
    private FileModel cv;
    private List<JobHistoryModel> jobHistoryList;
    private List<JobAdvertisementModel> appliedJobs;
    private List<JobAdvertisementModel> createdJobs;
}
