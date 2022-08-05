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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public void create(StudentModel studentModel) {
        Student studentEntity = new Student();
        studentEntity = Mapper.ConvertModelToStudent(studentModel);
        studentRepository.save(studentEntity);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentModel findById(Long id) {
        Optional<Student> dataFromDatabase = studentRepository.findById(id);
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException("User with this id = " + id +" is Not Found!!!");
        }
        StudentModel studentModel = new StudentModel();
        studentModel = Mapper.ConvertStudentToModel(dataFromDatabase.get());
        return studentModel;
    }

    @Override
    public List<StudentModel> findAll() {
        var studentModelList = new ArrayList<StudentModel>();
        var dataFromDatabase = studentRepository.findAll();
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException(" No object To Show !!");
        }
        dataFromDatabase.forEach(user -> studentModelList.add(Mapper.ConvertStudentToModel(user)));
        return studentModelList;
    }

    @Override
    public void update(StudentModel newValueStudentModel, long id) {
        Student currentStudentValue = studentRepository.findById(id).get();
        var newStudentValue = Mapper.ConvertModelToStudent(newValueStudentModel);
        if(currentStudentValue.equals(newStudentValue)){
            throw new ObjectExistException("this Object is already Exist in data base");
        }
        if(newStudentValue.equals(null)){
            throw new EmptyObjectException("this object is Empty");
        }
        currentStudentValue.setGpa(newStudentValue.getGpa());
        currentStudentValue.setStudentKClockId(newStudentValue.getStudentKClockId());
        currentStudentValue.setProfile(newStudentValue.getProfile());
        currentStudentValue.setDepartment(newStudentValue.getDepartment());
        currentStudentValue.setJobHistoryList(newStudentValue.getJobHistoryList());

        studentRepository.save(currentStudentValue);
    }

}