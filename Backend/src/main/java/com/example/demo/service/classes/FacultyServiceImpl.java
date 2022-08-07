package com.example.demo.service.classes;

import com.example.demo.UtilityClasses.Mapper;
import com.example.demo.Exception.EmptyObjectException;
import com.example.demo.Exception.ObjectExistException;
import com.example.demo.Exception.ObjectNotFoundException;
import com.example.demo.entity.Faculty;
import com.example.demo.service.interfaces.FacultyService;
import com.example.demo.model.FacultyModel;
import com.example.demo.repository.FacultyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, ModelMapper modelMapper){
        this.facultyRepository = facultyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void create(FacultyModel facultyModel) {
        facultyRepository.save(modelMapper.map(facultyModel,Faculty.class));
    }

    @Override
    public void delete(Long id) {
        facultyRepository.deleteById(id);
    }


    @Override
    public FacultyModel findById(Long id) {
        Optional<Faculty> dataFromDatabase = facultyRepository.findById(id);
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException("User with this id = " + id +" is Not Found!!!");
        }
        FacultyModel facultyModel = new FacultyModel();
        facultyModel = Mapper.ConvertFacultyToModel(dataFromDatabase.get());
        return facultyModel;
    }

    @Override
    public List<FacultyModel> findAll() {
        var facultyModelList = new ArrayList<FacultyModel>();
        var dataFromDatabase = facultyRepository.findAll();
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException(" No object To Show !!");
        }
        dataFromDatabase.forEach(user -> facultyModelList.add(Mapper.ConvertFacultyToModel(user)));
        return facultyModelList;
    }

    @Override
    public void update(FacultyModel newValueFacultyModel, long id) {

        Faculty currentFacultyValue = facultyRepository.findById(id).get();
        var newFacultyValue = Mapper.ConvertModelToFaculty(newValueFacultyModel);
        if(currentFacultyValue.equals(newFacultyValue)){
            throw new ObjectExistException("this Object is already Exist in data base");
        }
        if(newFacultyValue.equals(null)){
            throw new EmptyObjectException("this object is Empty");
        }
        currentFacultyValue.setFacultyKClockId(newFacultyValue.getFacultyKClockId());
        currentFacultyValue.setProfile(newFacultyValue.getProfile());
        currentFacultyValue.setDepartment(newFacultyValue.getDepartment());
        facultyRepository.save(currentFacultyValue);
    }

}
