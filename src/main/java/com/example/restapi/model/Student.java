package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long studentId;
    private String studentName;
    private String email;
    private String phoneNumber;
    private Course course;

}
