package com.example.demo.service.classes;

import com.example.demo.UtilityClasses.Mapper;
import com.example.demo.entity.JobHistory;
import com.example.demo.service.interfaces.JobHistoryService;
import lombok.RequiredArgsConstructor;
import com.example.demo.Exception.EmptyObjectException;
import com.example.demo.Exception.ObjectExistException;
import com.example.demo.Exception.ObjectNotFoundException;
import com.example.demo.model.JobHistoryModel;
import com.example.demo.repository.JobHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobHistoryServiceImpl implements JobHistoryService {

    private final JobHistoryRepository jobHistoryRepository;
    private final ModelMapper modelMapper;


    @Override
    public void create(JobHistoryModel jobHistoryModel) {
        JobHistory jobHistoryEntity = new JobHistory();
        jobHistoryEntity = Mapper.ConvertModelToJobHistory(jobHistoryModel);
        jobHistoryRepository.save(jobHistoryEntity);
    }

    @Override
    public void delete(Long id) {
        jobHistoryRepository.deleteById(id);
    }

    @Override
    public JobHistoryModel findById(Long id) {
        Optional<JobHistory> dataFromDatabase = jobHistoryRepository.findById(id);
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException("User with this id = " + id +" is Not Found!!!");
        }
        JobHistoryModel jobHistoryModel = new JobHistoryModel();
        jobHistoryModel = Mapper.ConvertJobHistoryToModel(dataFromDatabase.get());
        return jobHistoryModel;
    }

    @Override
    public List<JobHistoryModel> findAll() {
        var jobHistoryModelList = new ArrayList<JobHistoryModel>();
        var dataFromDatabase = jobHistoryRepository.findAll();
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException(" No object To Show !!");
        }
        return dataFromDatabase
                .stream()
                .filter(jobHistory -> !jobHistory.isDeleted())
                .map(jobHistory -> modelMapper.map(jobHistory, JobHistoryModel.class))
                .collect(Collectors.toList());
//        dataFromDatabase.forEach(user -> jobHistoryModelList.add(Mapper.ConvertJobHistoryToModel(user)));
//        return jobHistoryModelList;
    }

    @Override
    public void update(JobHistoryModel newValueJobHistoryModel, long id) {

        JobHistory currentJobHistoryValue = jobHistoryRepository.findById(id).get();
        var newJobHistoryValue = Mapper.ConvertModelToJobHistory(newValueJobHistoryModel);
        if(currentJobHistoryValue.equals(newJobHistoryValue)){
            throw new ObjectExistException("this Object is already Exist in data base");
        }
        if(newJobHistoryValue.equals(null)){
            throw new EmptyObjectException("this object is Empty");
        }
        currentJobHistoryValue.setStartDate(newJobHistoryValue.getStartDate());
        currentJobHistoryValue.setEndDate(newJobHistoryValue.getEndDate());
        currentJobHistoryValue.setReasonToLeave(newJobHistoryValue.getReasonToLeave());
        currentJobHistoryValue.setComments(newJobHistoryValue.getComments());
        currentJobHistoryValue.setCompanyName(newJobHistoryValue.getCompanyName());

        jobHistoryRepository.save(currentJobHistoryValue);
    }
}
