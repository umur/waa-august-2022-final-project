package com.example.demo.controller;
import com.example.demo.model.TagModel;
import com.example.demo.service.interfaces.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
@CrossOrigin
public class TagController {

    private final TagService tagService;

    @GetMapping()
    public List<TagModel> getAllProfiles() {
        return tagService.findAll();
    }

    @GetMapping("{id}")
    public TagModel getUser(@PathVariable long id) {
        return tagService.findById(id);
    }

    @PostMapping()
    public void save(@RequestBody TagModel jobHistoryModel) {
        tagService.create(jobHistoryModel);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        tagService.delete(id);
    }

    @PutMapping("{id}")
    public void update(@RequestBody TagModel jobHistoryModel, @PathVariable long id) {
        tagService.update(jobHistoryModel);
    }

}
