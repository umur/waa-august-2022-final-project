package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import com.example.demo.model.DepartmentModel;
import com.example.demo.service.interfaces.DepartmentServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentServices departmentServices;

    @GetMapping()
    public List<DepartmentModel> getAllProfiles(){
        return departmentServices.findAll();
    }

    @GetMapping("{id}")
    public DepartmentModel getUser(@PathVariable long id){
        return departmentServices.findById(id);
    }

    @PostMapping()
    public void save (@RequestBody DepartmentModel departmentModel){
        departmentServices.create(departmentModel);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        departmentServices.delete(id);
    }

    @PutMapping("{id}")
    public void update(@RequestBody DepartmentModel departmentModel,@PathVariable long id){
        departmentServices.update(departmentModel,id);
    }
}
