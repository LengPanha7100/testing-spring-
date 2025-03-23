package com.example.restapi.service.serviceImp;

import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Course;
import com.example.restapi.model.request.CourseRequest;
import com.example.restapi.repository.CourseRepository;
import com.example.restapi.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;

    public CourseServiceImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourse(Long pageNo, Long pageSize) {
        return courseRepository.getAllCourse(pageNo,pageSize);
    }

    @Override
    public Course getByIdCourse(Long id) {
        if(id == null){
            throw new BadRequestException("Get by id "+id+ " not found");
        }
        return courseRepository.getByIdCourse(id);
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {
        return courseRepository.createCourse(courseRequest);
    }

    @Override
    public Course updateCourse(CourseRequest courseRequest, Long id) {
        return courseRepository.updateCourse(courseRequest, id);
    }

    @Override
    public Course deleteCouse(Long id) {
        return courseRepository.deleteCourse(id);
    }
}
