package com.example.demo.model;

import com.example.demo.entity.Department;
import com.example.demo.enums.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileModel {
    private String profileKClockId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private boolean isDeleted;
    private ProfileType profileType;
    private Department department;
    private List<JobHistoryModel> jobHistoryList;
}
