package com.example.demo.service.classes;

import com.example.demo.entity.Tag;
import com.example.demo.model.TagModel;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TagRepositroy;
import com.example.demo.service.interfaces.TagService;
import io.swagger.models.Model;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepositroy tagRepositroy;

    private final ModelMapper modelMapper;

    @Override
    public void create(TagModel tagModel) {
        tagRepositroy.save(modelMapper.map(tagModel, Tag.class));
    }

    @Override
    public void update(TagModel tagModel) {
        tagRepositroy.save(modelMapper.map(tagModel, Tag.class));
    }

    @Override
    public void delete(Long id) {
        tagRepositroy.deleteById(id);
    }

    @Override
    public TagModel findById(Long id) {
        return modelMapper.map(tagRepositroy.findById(id),TagModel.class);
    }

    @Override
    public List<TagModel> findAll() {
        return tagRepositroy.findAll()
                .stream()
                .map( t -> modelMapper.map(t,TagModel.class))
                .collect(Collectors.toList());
    }
}
