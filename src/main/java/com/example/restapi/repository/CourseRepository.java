package com.example.restapi.repository;

import com.example.restapi.model.Course;
import com.example.restapi.model.request.CourseRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseRepository {
    List<Course> getAllCourse(Long pageNo, Long pageSize);

    Course getByIdCourse(Long id);

    Course createCourse(CourseRequest courseRequest);

    Course updateCourse(CourseRequest courseRequest, Long id);

    Course deleteCourse(Long id);
}
