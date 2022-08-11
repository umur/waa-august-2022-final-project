package com.example.demo.model;

import com.example.demo.entity.Faculty;
import com.example.demo.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
    private Long Id;
    private String commentDetails;
    private Student student;
    private Faculty faculty;
}
