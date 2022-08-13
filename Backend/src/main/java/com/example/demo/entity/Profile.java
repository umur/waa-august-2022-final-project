package com.example.demo.entity;

import com.example.demo.enums.ProfileType;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(
//        name = "tbl_profile",
//        uniqueConstraints = @UniqueConstraint(
//                name = "emailId_unique",
//                columnNames = "email_address"
//        )
//)
public class Profile {
    @Id
    @SequenceGenerator(
            name = "profile_sequence",
            sequenceName = "profile_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_sequence"
    )
    private Long id;
    private String profileKClockId;
    private String firstName;
    private String lastName;
    private boolean isDeleted = false;

//    @ManyToOne
//    private Department department;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private Student student;

    @OneToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private Faculty faculty;

    private ProfileType profileType;
}
