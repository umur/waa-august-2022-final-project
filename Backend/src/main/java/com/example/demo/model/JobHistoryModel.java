package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobHistoryModel {
    private Long Id;
    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reasonToLeave;
    private String comments;
}
