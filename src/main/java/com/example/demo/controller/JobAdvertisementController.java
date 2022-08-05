package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import com.example.demo.model.DepartmentModel;
import com.example.demo.model.JobAdvertisementModel;
import com.example.demo.model.JobHistoryModel;
import com.example.demo.service.interfaces.DepartmentServices;
import com.example.demo.service.interfaces.JobAdvertisementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobsAdvertisment")
@RequiredArgsConstructor
public class JobAdvertisementController {

    private final JobAdvertisementService jobAdvertisementService;

    @GetMapping()
    public List<JobAdvertisementModel> getAllProfiles(){
        return jobAdvertisementService.findAll();
    }

    @GetMapping("{id}")
    public JobAdvertisementModel getUser(@PathVariable long id){
        return jobAdvertisementService.findById(id);
    }

    @PostMapping()
    public void save (@RequestBody JobAdvertisementModel jobAdvertisementModel){
        jobAdvertisementService.create(jobAdvertisementModel);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        jobAdvertisementService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@RequestBody JobAdvertisementModel jobAdvertisementModel,@PathVariable long id){
        jobAdvertisementService.update(jobAdvertisementModel,id);
    }
}
