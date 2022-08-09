package com.example.demo.controller;

import com.example.demo.model.JobAdvertisementModel;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.StudentModel;
import com.example.demo.service.interfaces.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;


    @GetMapping()
    public List<StudentModel> getAllProfiles(){
        return studentService.findAll();
    }

    @GetMapping("{id}")
    public StudentModel getUser(@PathVariable long id){
        return studentService.findById(id);
    }

    @PostMapping()
    public void save (@RequestBody StudentModel studentModel){

        studentService.create(studentModel);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        studentService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@RequestBody StudentModel studentModel,@PathVariable long id){
        studentService.update(studentModel,id);
    }

    @GetMapping("/applied-jobs/{id}")
    public List<JobAdvertisementModel> getAllAppliedJobsById(@PathVariable long id) {
        return studentService.findAppliedJobsById(id);
    }

    @PostMapping("/{kCloakId}/job-advertisements/{advertisementId}")
    public void applyJob(@PathVariable long advertisementId, @PathVariable String kCloakId) {
        studentService.applyJob(advertisementId, kCloakId);
    }

    @GetMapping("/get-applied-jobs/{kCloakId}")
    public List<JobAdvertisementModel> getAppliedJob(@PathVariable String kCloakId) {
        return studentService.getAppliedJobs(kCloakId);
    }
}
