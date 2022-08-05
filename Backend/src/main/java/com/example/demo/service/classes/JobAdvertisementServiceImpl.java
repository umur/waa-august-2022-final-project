package com.example.demo.service.classes;

import com.example.demo.UtilityClasses.Mapper;
import com.example.demo.entity.JobAdvertisement;
import com.example.demo.service.interfaces.JobAdvertisementService;
import lombok.RequiredArgsConstructor;
import com.example.demo.Exception.EmptyObjectException;
import com.example.demo.Exception.ObjectExistException;
import com.example.demo.Exception.ObjectNotFoundException;
import com.example.demo.model.JobAdvertisementModel;
import com.example.demo.repository.JobAdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobAdvertisementServiceImpl implements JobAdvertisementService {

    private final JobAdvertisementRepository jobAdvertisementRepository;


    @Override
    public void create(JobAdvertisementModel jobAdvertisementModel) {
        JobAdvertisement jobAdvertisementEntity = new JobAdvertisement();
        jobAdvertisementEntity =  Mapper.ConvertModelToJobAdvertisement(jobAdvertisementModel);
        jobAdvertisementRepository.save(jobAdvertisementEntity);
    }

    @Override
    public void delete(Long id) {
        jobAdvertisementRepository.deleteById(id);
    }

    @Override
    public JobAdvertisementModel findById(Long id) {
        Optional<JobAdvertisement> dataFromDatabase = jobAdvertisementRepository.findById(id);
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException("User with this id = " + id +" is Not Found!!!");
        }
        JobAdvertisementModel jobAdvertisementModel = new JobAdvertisementModel();
        jobAdvertisementModel = Mapper.ConvertJobAdvertisementToModel(dataFromDatabase.get());
        return jobAdvertisementModel;
    }

    @Override
    public List<JobAdvertisementModel> findAll() {
        var jobAdvertisementModelList = new ArrayList<JobAdvertisementModel>();
        var dataFromDatabase = jobAdvertisementRepository.findAll();
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException(" No object To Show !!");
        }
        dataFromDatabase.forEach(user -> jobAdvertisementModelList.add(Mapper.ConvertJobAdvertisementToModel(user)));
        return jobAdvertisementModelList;
    }

    @Override
    public void update(JobAdvertisementModel newValueJobAdvertisementModel, long id) {

        JobAdvertisement currentJobAdvertisementValue = jobAdvertisementRepository.findById(id).get();
        var newJobAdvertisementValue = Mapper.ConvertModelToJobAdvertisement(newValueJobAdvertisementModel);
        if(currentJobAdvertisementValue.equals(newJobAdvertisementValue)){
            throw new ObjectExistException("this Object is already Exist in data base");
        }
        if(newJobAdvertisementValue.equals(null)){
            throw new EmptyObjectException("this object is Empty");
        }
        currentJobAdvertisementValue.setDescription(newJobAdvertisementValue.getDescription());
        currentJobAdvertisementValue.setBenefits(newJobAdvertisementValue.getBenefits());

        jobAdvertisementRepository.save(currentJobAdvertisementValue);
    }
}
