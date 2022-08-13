package com.example.demo.service.classes;

import com.example.demo.UtilityClasses.Mapper;
import com.example.demo.entity.Department;
import com.example.demo.service.interfaces.DepartmentServices;
import lombok.RequiredArgsConstructor;
import com.example.demo.Exception.EmptyObjectException;
import com.example.demo.Exception.ObjectExistException;
import com.example.demo.Exception.ObjectNotFoundException;
import com.example.demo.model.DepartmentModel;
import com.example.demo.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentServices {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public void create(DepartmentModel departmentModel) {
        Department departmentEntity = new Department();
        departmentEntity = Mapper.ConvertModelToDepartment(departmentModel);
        departmentRepository.save(departmentEntity);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentModel findById(Long id) {
        Optional<Department> dataFromDatabase = departmentRepository.findById(id);
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException("User with this id = " + id +" is Not Found!!!");
        }
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel = Mapper.ConvertDepartmentToModel(dataFromDatabase.get());
        return departmentModel;
    }

    @Override
    public List<DepartmentModel> findAll() {
        var DepartmentModelList = new ArrayList<DepartmentModel>();
        var dataFromDatabase = departmentRepository.findAll();
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException(" No object To Show !!");
        }

        return dataFromDatabase
                .stream()
                .filter(department -> !department.isDeleted())
                .map(d -> modelMapper.map(d,DepartmentModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(DepartmentModel newValueDepartmentModel, long id) {
        Department currentDepartmentValue = departmentRepository.findById(id).get();
        var newDepartmentValue = Mapper.ConvertModelToDepartment(newValueDepartmentModel);
        if(currentDepartmentValue.equals(newDepartmentValue)){
            throw new ObjectExistException("this Object is already Exist in data base");
        }
        if(newDepartmentValue.equals(null)){
            throw new EmptyObjectException("this object is Empty");
        }
        currentDepartmentValue.setName(newDepartmentValue.getName());
        departmentRepository.save(currentDepartmentValue);
    }

}
