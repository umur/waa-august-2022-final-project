package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobAdvertisement {

    @Id
    @SequenceGenerator(
            name = "jobAdvertisement_sequence",
            sequenceName = "jobAdvertisement_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "jobAdvertisement_sequence"
    )

    private Long Id;
    private String description;
    private String benefits;

    private String city;
    private String state;
    private String companyName;
    @ManyToMany
    private List<Tag> tagList;

    @OneToMany
    private List<File> fileList;
}
