package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.entity.Department;
import com.example.demo.entity.JobHistory;
import com.example.demo.entity.Profile;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
    private String studentKClockId;
    private double gpa;
    private Profile profile;
    private Department department;
    private List<JobHistoryModel> jobHistoryList;
}
