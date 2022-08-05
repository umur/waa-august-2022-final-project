package com.example.demo.service.interfaces;

import com.example.demo.model.CommentModel;

import java.util.List;

public interface CommentService {

    void create(CommentModel commentModel);
    void delete(Long id);
    CommentModel findById(Long id);
    List<CommentModel> findAll();
    void update(CommentModel commentModel, long id);
}
