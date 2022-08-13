package com.example.demo.service.interfaces;



import com.example.demo.model.TagModel;

import java.util.List;

public interface TagService {

    void create(TagModel tagModel);
    void update(TagModel tagModel);
    void delete(Long id);
    TagModel findById(Long id);
    List<TagModel> findAll();
}
