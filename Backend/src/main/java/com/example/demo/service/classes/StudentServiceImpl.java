package com.example.demo.service.classes;

import com.example.demo.UtilityClasses.Mapper;
import com.example.demo.entity.Student;
import com.example.demo.service.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import com.example.demo.Exception.EmptyObjectException;
import com.example.demo.Exception.ObjectExistException;
import com.example.demo.Exception.ObjectNotFoundException;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.StudentRepository;
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
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    @Override
    public void create(StudentModel studentModel) {
        studentRepository.save(modelMapper.map(studentModel, Student.class));
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentModel findById(Long id) {
        Optional<Student> dataFromDatabase = studentRepository.findById(id);
        if (dataFromDatabase.isEmpty()) {
            throw new ObjectNotFoundException("User with this id = " + id + " is Not Found!!!");
        }

        return modelMapper.map(dataFromDatabase.get(), StudentModel.class);
    }

    @Override
    public List<StudentModel> findAll() {
        var studentModelList = new ArrayList<StudentModel>();
        var dataFromDatabase = studentRepository.findAll();
        if (dataFromDatabase.isEmpty()) {
            throw new ObjectNotFoundException(" No object To Show !!");
        }

        return dataFromDatabase
                .stream().map(s -> modelMapper.map(s, StudentModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(StudentModel newValueStudentModel, long id) {
        studentRepository.save(modelMapper.map(newValueStudentModel, Student.class));
    }

}
