package com.example.demo.controller;

import com.example.demo.model.FacultyModel;
import com.example.demo.service.interfaces.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService){
    this.facultyService = facultyService;
    }


    @GetMapping()
    public List<FacultyModel> getAllProfiles(){
        return facultyService.findAll();
    }

    @GetMapping("{id}")
    public FacultyModel getUser(@PathVariable long id){
        return facultyService.findById(id);
    }

    @PostMapping()
    public void save (@RequestBody FacultyModel facultyModel){
        facultyService.create(facultyModel);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        facultyService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@RequestBody FacultyModel facultyModel,@PathVariable long id){
        facultyService.update(facultyModel,id);
    }
}
