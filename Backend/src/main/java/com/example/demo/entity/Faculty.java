package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Faculty {

    @Id
    @SequenceGenerator(
            name = "faculty_sequence",
            sequenceName = "faculty_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "faculty_sequence"
    )
    private Long id;
    private String facultyKClockId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
