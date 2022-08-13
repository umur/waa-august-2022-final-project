package com.example.demo.controller;

import com.example.demo.model.TagModel;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.JobAdvertisementModel;
import com.example.demo.service.interfaces.JobAdvertisementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobsAdvertisment")
@RequiredArgsConstructor
@CrossOrigin
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

    @PutMapping("/{id}")
    public void update(@RequestBody JobAdvertisementModel jobAdvertisementModel,@PathVariable long id){
        jobAdvertisementService.update(jobAdvertisementModel,id);
    }

    @GetMapping("/filter-by-state")
    public List<JobAdvertisementModel> getAllByState(@RequestParam String state) {
        return jobAdvertisementService.getAllByState(state);
    }

    @GetMapping("/filter-by-city")
    public List<JobAdvertisementModel> getAllByCity(@RequestParam String city) {
        return jobAdvertisementService.getAllByCity(city);
    }

    @GetMapping("/filter-by-company-name")
    public List<JobAdvertisementModel> getAllByCompanyName(@RequestParam String companyName) {
        return jobAdvertisementService.getAllByCompanyName(companyName);
    }

    @GetMapping("/filter-by-tag")
    public List<JobAdvertisementModel> getAllByTag(@RequestBody List<TagModel> tagModels){
        return jobAdvertisementService.getAllByTags(tagModels);
    }
}
