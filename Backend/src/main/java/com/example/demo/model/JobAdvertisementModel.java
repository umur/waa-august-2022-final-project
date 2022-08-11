package com.example.demo.model;

import com.example.demo.entity.File;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementModel {
    private Long Id;
    private String ownerId;
    private String description;
    private String benefits;
    private String city;
    private String state;
    private String companyName;
    private List<TagModel> tagList;
    private List<File> fileList;
}
