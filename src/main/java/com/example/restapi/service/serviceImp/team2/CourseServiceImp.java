package com.example.restapi.service.serviceImp.team2;

import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Course;
import com.example.restapi.model.Instructor;
import com.example.restapi.model.request.CourseRequest;
import com.example.restapi.repository.team2.CourseRepository;
import com.example.restapi.service.team2.CourseService;
import com.example.restapi.service.team2.InstructorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {
    private final CourseRepository courseRepository;
    private final InstructorService instructorService;

    public CourseServiceImp(CourseRepository courseRepository, InstructorService instructorService) {
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
    }

    @Override
    public List<Course> getAllCourse(Long pageNo, Long pageSize) {
        if (pageNo <1){
            throw new BadRequestException("pageNo must be greater than zero");
        }
        return courseRepository.getAllCourse(pageNo,pageSize);
    }

    @Override
    public Course getByIdCourse(Long id) {
        Course course = courseRepository.getByIdCourse(id);
        if(course == null){
            throw new BadRequestException("Get by id "+id+ " not found");
        }
        return course;
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {
        Long instructorId = courseRequest.getInstructorId();
        Instructor instructor = instructorService.getInstructorsById(instructorId);
        return courseRepository.createCourse(courseRequest);
    }

    @Override
    public Course updateCourse(CourseRequest courseRequest, Long id) {
        getByIdCourse(id);
        Long instructorId = courseRequest.getInstructorId();
        Instructor instructor = instructorService.getInstructorsById(courseRequest.getInstructorId());
        return courseRepository.updateCourse(courseRequest, id);
    }

    @Override
    public Course deleteCouse(Long id) {
        getByIdCourse(id);
        return courseRepository.deleteCourse(id);
    }
}
