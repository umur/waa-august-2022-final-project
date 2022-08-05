package com.example.demo.service.interfaces;

import com.example.demo.model.DepartmentModel;

import java.util.List;


public interface DepartmentServices {

    void create(DepartmentModel departmentModel);
    void delete(Long id);
    DepartmentModel findById(Long id);
    List<DepartmentModel> findAll();
    void update(DepartmentModel departmentModel, long id);
}
