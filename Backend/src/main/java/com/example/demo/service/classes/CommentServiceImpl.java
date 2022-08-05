package com.example.demo.service.classes;

import com.example.demo.UtilityClasses.Mapper;
import com.example.demo.entity.Comment;
import com.example.demo.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import com.example.demo.Exception.EmptyObjectException;
import com.example.demo.Exception.ObjectExistException;
import com.example.demo.Exception.ObjectNotFoundException;
import com.example.demo.model.CommentModel;
import com.example.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public void create(CommentModel commentModel) {
        Comment comment = new Comment();
        comment = Mapper.ConvertModelToComment(commentModel);
        commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public CommentModel findById(Long id) {
        Optional<Comment> dataFromDatabase = commentRepository.findById(id);
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException("User with this id = " + id +" is Not Found!!!");
        }
        CommentModel commentModel = new CommentModel();
        commentModel = Mapper.ConvertCommentToModel(dataFromDatabase.get());
        return commentModel;
    }

    @Override
    public List<CommentModel> findAll() {
        var commentModelList = new ArrayList<CommentModel>();
        var dataFromDatabase = commentRepository.findAll();
        if(dataFromDatabase.isEmpty()){
            throw new ObjectNotFoundException(" No object To Show !!");
        }
        dataFromDatabase.forEach(user -> commentModelList.add(Mapper.ConvertCommentToModel(user)));
        return commentModelList;
    }

    @Override
    public void update(CommentModel newValueCommentModel, long id) {
        Comment currentCommentValue = commentRepository.findById(id).get();
        var newCommentValue = Mapper.ConvertModelToComment(newValueCommentModel);
        if(currentCommentValue.equals(newCommentValue)){
            throw new ObjectExistException("this Object is already Exist in data base");
        }
        if(newCommentValue.equals(null)){
            throw new EmptyObjectException("this object is Empty");
        }
        currentCommentValue.setCommentDetails(newCommentValue.getCommentDetails());
        currentCommentValue.setStudent(newCommentValue.getStudent());
        currentCommentValue.setFaculty(newCommentValue.getFaculty());

        commentRepository.save(currentCommentValue);
    }
}
