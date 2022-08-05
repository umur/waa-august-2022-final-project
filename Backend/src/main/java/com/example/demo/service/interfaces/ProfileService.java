package com.example.demo.service.interfaces;

import com.example.demo.model.ProfileModel;

import java.util.List;

public interface ProfileService {
    void create(ProfileModel profileModel);
    void delete(Long id);
//    ProfileModel findByEmail(String email);
    ProfileModel findById(Long id);
    List<ProfileModel> findAll();
    void update(ProfileModel profileModel, long id);
}
