package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.entity.Department;
import com.example.demo.entity.Profile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyModel {

    private String facultyKClockId;
    private Profile profile;
    private DepartmentModel department;
}
