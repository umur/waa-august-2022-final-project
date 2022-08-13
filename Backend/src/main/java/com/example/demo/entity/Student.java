package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String studentKClockId;
    private double gpa;
    private boolean isDeleted = false;
    private String city;
    private String State;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department major;

    @OneToOne
    private File cv;

    @OneToMany(cascade = CascadeType.ALL)
    private List<JobHistory> jobHistoryList;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<JobAdvertisement> createdJobs;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<JobAdvertisement> appliedJobs;
}
