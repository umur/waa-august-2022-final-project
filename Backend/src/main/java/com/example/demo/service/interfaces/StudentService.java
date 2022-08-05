package com.example.demo.service.interfaces;


import com.example.demo.model.StudentModel;

import java.util.List;

public interface StudentService {
    void create(StudentModel studentModel);
    void delete(Long id);
    StudentModel findById(Long id);
    List<StudentModel> findAll();
    void update(StudentModel studentModel, long id);
}
