package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.entity.Faculty;
import com.example.demo.entity.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {

    private String commentDetails;
    private Student student;
    private Faculty faculty;
}
