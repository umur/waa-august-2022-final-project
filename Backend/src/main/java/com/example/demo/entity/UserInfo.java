package com.example.demo.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    String username;
    String userIDByToken;
    String userIDByMapper;
    String dob;
}
