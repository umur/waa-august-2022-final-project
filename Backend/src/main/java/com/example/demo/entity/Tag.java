
package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

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

    @Column(name = "tag_name")
    private String tagName;

}