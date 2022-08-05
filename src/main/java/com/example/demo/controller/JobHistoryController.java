package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import com.example.demo.model.DepartmentModel;
import com.example.demo.model.JobHistoryModel;
import com.example.demo.service.interfaces.DepartmentServices;
import com.example.demo.service.interfaces.JobHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobshistory")
@RequiredArgsConstructor
public class JobHistoryController {

    private final JobHistoryService jobHistoryService;

    @GetMapping()
    public List<JobHistoryModel> getAllProfiles(){
        return jobHistoryService.findAll();
    }

    @GetMapping("{id}")
    public JobHistoryModel getUser(@PathVariable long id){
        return jobHistoryService.findById(id);
    }

    @PostMapping()
    public void save (@RequestBody JobHistoryModel jobHistoryModel){
        jobHistoryService.create(jobHistoryModel);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        jobHistoryService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@RequestBody JobHistoryModel jobHistoryModel,@PathVariable long id){
        jobHistoryService.update(jobHistoryModel,id);
    }
}
