package com.example.restapi.model.request;

import com.example.restapi.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String studentName;
    private String phoneNumber;
    private List<Long> courseList;
}
