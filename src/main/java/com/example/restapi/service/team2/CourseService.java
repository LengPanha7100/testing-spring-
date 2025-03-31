package com.example.restapi.service.team2;

import com.example.restapi.model.Course;
import com.example.restapi.model.request.CourseRequest;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse(Long pageNo, Long pageSize);

    Course getByIdCourse(Long id);

    Course createCourse(CourseRequest courseRequest);

    Course updateCourse(CourseRequest courseRequest, Long id);

    Course deleteCouse(Long id);
}
