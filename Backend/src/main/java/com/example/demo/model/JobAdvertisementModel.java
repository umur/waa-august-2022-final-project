package com.example.demo.model;

import com.example.demo.entity.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
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
