package com.example.demo.model;

import com.example.demo.entity.Department;
import com.example.demo.entity.JobHistory;
import lombok.*;

import java.util.List;

@Getter
@Setter
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
