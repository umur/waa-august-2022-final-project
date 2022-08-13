package com.example.demo.service.interfaces;

import com.example.demo.model.FacultyModel;

import java.util.List;

public interface FacultyService {
    void create(FacultyModel facultyModel);
    void delete(Long id);
    FacultyModel findById(Long id);
    FacultyModel findByProfileId(Long id);
    List<FacultyModel> findAll();
    void update(FacultyModel facultyModel, long id);
}
