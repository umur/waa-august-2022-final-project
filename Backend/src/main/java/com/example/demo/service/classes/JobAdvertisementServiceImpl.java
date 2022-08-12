package com.example.demo.service.classes;

import com.example.demo.UtilityClasses.Mapper;
import com.example.demo.entity.JobAdvertisement;
import com.example.demo.entity.Tag;
import com.example.demo.model.TagModel;
import com.example.demo.repository.StudentRepository;
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
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class JobAdvertisementServiceImpl implements JobAdvertisementService {

    private final JobAdvertisementRepository jobAdvertisementRepository;
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    @Override
    public void create(JobAdvertisementModel jobAdvertisementModel) {
        var mappedModel = modelMapper.map(jobAdvertisementModel, JobAdvertisement.class);
        mappedModel.setStudent(studentRepository.findByProfile_ProfileKClockId(jobAdvertisementModel.getOwnerId()));
        jobAdvertisementRepository.save(mappedModel);
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
            return new ArrayList<>();
        }

        return dataFromDatabase.stream()
                .filter(jobAdvertisement -> !jobAdvertisement.isDeleted())
                .map(d ->
                {
                    var result = modelMapper.map(d, JobAdvertisementModel.class);
                    result.setOwnerId(d.getStudent().getProfile().getProfileKClockId());
                    return result;
                })
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

    @Override
    public List<JobAdvertisementModel> getAllByState(String state) {
        List<JobAdvertisement> jobAdvertisements = jobAdvertisementRepository.findAllByState(state);
        return jobAdvertisements
                .stream()
                .map(jobAdvertisement -> modelMapper.map(jobAdvertisement, JobAdvertisementModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobAdvertisementModel> getAllByCity(String city) {
        List<JobAdvertisement> jobAdvertisements = jobAdvertisementRepository.findAllByCity(city);
        return jobAdvertisements
                .stream()
                .map(jobAdvertisement -> modelMapper.map(jobAdvertisement, JobAdvertisementModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobAdvertisementModel> getAllByTags(List<TagModel> tags) {

        List<Tag> mappedTags = tags.stream()
                .map(tagModel -> modelMapper.map(tagModel, Tag.class))
                .collect(Collectors.toList());

        Stream<JobAdvertisement> filteredJobs = jobAdvertisementRepository.findAll()
                .stream()
                .filter(jobAdvertisement -> jobAdvertisement
                        .getTagList()
                        .stream()
                        .anyMatch(tag -> mappedTags
                                .stream()
                                .anyMatch(tag1 ->
                                     tag1.getTagName().equals(tag.getTagName())
                                )));
//        List<JobAdvertisement> jobAdvertisements = jobAdvertisementRepository.findAllByTagListContains(tags);
        return filteredJobs
                .map(jobAdvertisement -> modelMapper.map(jobAdvertisement, JobAdvertisementModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<JobAdvertisementModel> getAllByCompanyName(String name) {
        List<JobAdvertisement> jobAdvertisements = jobAdvertisementRepository.findAllByCompanyName(name);
        return jobAdvertisements
                .stream()
                .map(jobAdvertisement -> modelMapper.map(jobAdvertisement, JobAdvertisementModel.class))
                .collect(Collectors.toList());
    }
}
