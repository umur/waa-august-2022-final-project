package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobHistory {

    @Id
    @SequenceGenerator(
            name = "jobHistory_sequence",
            sequenceName = "jobHistory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "jobHistory_sequence"
    )
    private Long Id;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reasonToLeave;
    private String comments;
    private boolean isDeleted = false;
}
