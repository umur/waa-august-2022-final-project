package com.example.demo.service.classes;

import com.example.demo.UtilityClasses.Mapper;
import com.example.demo.entity.Profile;
import com.example.demo.service.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import com.example.demo.Exception.EmptyObjectException;
import com.example.demo.Exception.ObjectExistException;
import com.example.demo.Exception.ObjectNotFoundException;
import com.example.demo.model.ProfileModel;
import com.example.demo.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public void create(ProfileModel profileModel) {
        Profile profileEntity = Mapper.ConvertModelToProfile(profileModel);
        profileRepository.save(profileEntity);
    }

    @Override
    public void delete(Long id) {
        profileRepository.deleteById(id);
    }

//    @Override
//    public ProfileModel findByEmail(String email) {
//        Optional<Profile> dataFromDatabase = Optional.ofNullable(profileRepository.findByEmail(email));
//        if(dataFromDatabase.isEmpty()){
//            throw new ObjectNotFoundException("User with this id = " + email +" is Not Found!!!");
//        }
//        ProfileModel profileModel = new ProfileModel();
//        profileModel = Mapper.ConvertProfileToModel(dataFromDatabase.get());
//        return profileModel;
//
//    }

    @Override
    public ProfileModel findById(Long id) {
        Optional<Profile> dataFromDatabase = profileRepository.findById(id);
        if (dataFromDatabase.isEmpty()) {
            throw new ObjectNotFoundException("User with this id = " + id + " is Not Found!!!");
        }
        ProfileModel profileModel = new ProfileModel();
        profileModel = Mapper.ConvertProfileToModel(dataFromDatabase.get());
        return profileModel;
    }

    @Override
    public ProfileModel findByKeycloakId(String id) {
        Profile dataFromDatabase = profileRepository.findByProfileKClockId(id);
        if (dataFromDatabase == null)
            return null;
        ProfileModel profileModel = new ProfileModel();
        profileModel = Mapper.ConvertProfileToModel(dataFromDatabase);
        return profileModel;
    }

    @Override
    public List<ProfileModel> findAll() {
        var profileModelList = new ArrayList<ProfileModel>();
        var dataFromDatabase = profileRepository.findAll();
        if (dataFromDatabase.isEmpty()) {
            throw new ObjectNotFoundException(" No profiles To Show !!");
        }
        dataFromDatabase.forEach(user -> profileModelList.add(Mapper.ConvertProfileToModel(user)));
        return profileModelList;
    }

    @Override
    public void update(ProfileModel newValueProfileModel, long id) {
        Profile currentProfileValue = profileRepository.findById(id).get();
        var newProfileValue = Mapper.ConvertModelToProfile(newValueProfileModel);
        if (currentProfileValue.equals(newProfileValue)) {
            throw new ObjectExistException("this Object is already Exist in data base");
        }
        if (newProfileValue.equals(null)) {
            throw new EmptyObjectException("this object is Empty");
        }

        currentProfileValue.setProfileKClockId(newProfileValue.getProfileKClockId());
        currentProfileValue.setFirstName(newProfileValue.getFirstName());
        currentProfileValue.setLastName(newProfileValue.getLastName());
//        currentProfileValue.setPassword(newProfileValue.getPassword());
//        currentProfileValue.setEmail(newProfileValue.getEmail());
        currentProfileValue.setProfileType(newProfileValue.getProfileType());
        currentProfileValue.setDeleted(newProfileValue.isDeleted());

        profileRepository.save(currentProfileValue);
    }
}
