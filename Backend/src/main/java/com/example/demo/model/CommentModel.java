package com.example.demo.model;

import com.example.demo.entity.Faculty;
import com.example.demo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {

    private String commentDetails;
    private Student student;
    private Faculty faculty;
}
