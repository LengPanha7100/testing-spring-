package com.example.restapi.service;

import com.example.restapi.model.Instructor;
import com.example.restapi.model.request.InstructorRequest;

import java.util.List;

public interface InstructorService {
    List<Instructor> getAllInstructors(Long pageNo , Long pageSize);

    Instructor getInstructorsById(Long id);

    Instructor createInstructor(InstructorRequest instructorRequest);

    Instructor updateInstructor(Long id, InstructorRequest instructorRequest);

    void deleteInstructor(Long id);
}
