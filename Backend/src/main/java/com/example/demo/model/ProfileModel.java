package com.example.demo.model;

import com.example.demo.entity.Department;
import com.example.demo.entity.Faculty;
import com.example.demo.entity.Student;
import com.example.demo.enums.ProfileType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.OneToOne;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileModel {
    private Long Id;
    private String profileKClockId;
    private String firstName;
    private String lastName;
    private boolean isDeleted;
    private ProfileType profileType;
    private Student student;
    private Faculty faculty;
    //private Department department;
    //private List<JobHistoryModel> jobHistoryList;
}
