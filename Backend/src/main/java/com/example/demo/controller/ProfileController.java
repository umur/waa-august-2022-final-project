package com.example.demo.controller;

import com.example.demo.model.ProfileModel;
import com.example.demo.service.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }


    @GetMapping()
    public List<ProfileModel> getAllProfiles(){
        return profileService.findAll();
    }

    @GetMapping("{id}")
    public ProfileModel getUser(@PathVariable long id){
        return profileService.findById(id);
    }

    @PostMapping()
    public void save (@RequestBody ProfileModel profileModel){
        profileService.create(profileModel);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        profileService.delete(id);
    }
    @PutMapping("{id}")
    public void update(@RequestBody ProfileModel profileModel,@PathVariable long id){
        profileService.update(profileModel,id);
    }
}
