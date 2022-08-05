package com.example.demo.entity;

import com.example.demo.enums.ProfileType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
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
    private boolean isDeleted;

    @ManyToOne
    private Department department;

    private ProfileType profileType;
}
