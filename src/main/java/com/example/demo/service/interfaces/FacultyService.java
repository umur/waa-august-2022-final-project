package com.example.demo.service.interfaces;

import com.example.demo.model.FacultyModel;
import com.example.demo.model.ProfileModel;

import java.util.List;

public interface FacultyService {
    void create(FacultyModel facultyModel);
    void delete(Long id);
    FacultyModel findById(Long id);
    List<FacultyModel> findAll();
    void update(FacultyModel facultyModel, long id);
}
