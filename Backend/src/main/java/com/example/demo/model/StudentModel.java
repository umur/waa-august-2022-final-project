package com.example.demo.model;

import com.example.demo.entity.Department;
import com.example.demo.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
