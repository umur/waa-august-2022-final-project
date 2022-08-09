package com.example.demo.model;

import com.example.demo.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyModel {

    private Long Id;
    private String facultyKClockId;
    private Profile profile;
    private DepartmentModel department;
}
