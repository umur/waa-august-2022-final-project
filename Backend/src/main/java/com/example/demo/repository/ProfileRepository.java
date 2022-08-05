package com.example.demo.repository;

import com.example.demo.entity.Profile;
import com.example.demo.enums.ProfileType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findByProfileType(ProfileType profileType);
    Profile findByEmail(String email);
}
