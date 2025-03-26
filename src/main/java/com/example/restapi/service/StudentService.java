package com.example.restapi.service;

import com.example.restapi.model.Student;
import com.example.restapi.model.request.StudentRequest;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent(Long pageNo, Long pageSize);

    Student getStudentById(Long id);

    Student createStudent(StudentRequest studentRequest);

    Student updateStudent(StudentRequest studentRequest, Long id);

    void deleteStudent(Long id);

    List<Student> searchStudentByName(String name);
}
