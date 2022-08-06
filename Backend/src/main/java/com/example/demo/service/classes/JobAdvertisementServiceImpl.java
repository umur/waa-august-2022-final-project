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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class JobAdvertisementServiceImpl implements JobAdvertisementService {

    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final ModelMapper modelMapper;


    @Override
    public void create(JobAdvertisementModel jobAdvertisementModel) {
        jobAdvertisementRepository.save(modelMapper.map(jobAdvertisementModel, JobAdvertisement.class));
    }

    @Override
    public void delete(Long id) {
        jobAdvertisementRepository.deleteById(id);
    }

    @Override
    public JobAdvertisementModel findById(Long id) {
        Optional<JobAdvertisement> dataFromDatabase = jobAdvertisementRepository.findById(id);
        if (dataFromDatabase.isEmpty()) {
            throw new ObjectNotFoundException("User with this id = " + id + " is Not Found!!!");
        }
        return modelMapper.map(dataFromDatabase.get(), JobAdvertisementModel.class);
    }

    @Override
    public List<JobAdvertisementModel> findAll() {
        var jobAdvertisementModelList = new ArrayList<JobAdvertisementModel>();
        var dataFromDatabase = jobAdvertisementRepository.findAll();
        if (dataFromDatabase.isEmpty()) {
            throw new ObjectNotFoundException(" No object To Show !!");
        }

        return dataFromDatabase.stream()
                .map(d -> modelMapper.map(d, JobAdvertisementModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(JobAdvertisementModel newValueJobAdvertisementModel, long id) {

        jobAdvertisementRepository.save(modelMapper.map(newValueJobAdvertisementModel,JobAdvertisement.class));
//        JobAdvertisement currentJobAdvertisementValue = jobAdvertisementRepository.findById(id).get();
//        var newJobAdvertisementValue = Mapper.ConvertModelToJobAdvertisement(newValueJobAdvertisementModel);
//        if (currentJobAdvertisementValue.equals(newJobAdvertisementValue)) {
//            throw new ObjectExistException("this Object is already Exist in data base");
//        }
//        if (newJobAdvertisementValue.equals(null)) {
//            throw new EmptyObjectException("this object is Empty");
//        }
//        currentJobAdvertisementValue.setDescription(newJobAdvertisementValue.getDescription());
//        currentJobAdvertisementValue.setBenefits(newJobAdvertisementValue.getBenefits());
//
//        jobAdvertisementRepository.save(currentJobAdvertisementValue);
    }
}
