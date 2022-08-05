package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import com.example.demo.model.CommentModel;
import com.example.demo.model.DepartmentModel;
import com.example.demo.service.interfaces.CommentService;
import com.example.demo.service.interfaces.DepartmentServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping()
    public List<CommentModel> getAllProfiles(){
        return commentService.findAll();
    }

    @GetMapping("{id}")
    public CommentModel getUser(@PathVariable long id){
        return commentService.findById(id);
    }

    @PostMapping()
    public void save (@RequestBody CommentModel commentModel){
        commentService.create(commentModel);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id){
        commentService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@RequestBody CommentModel commentModel,@PathVariable long id) {
        commentService.update(commentModel, id);
    }
}
