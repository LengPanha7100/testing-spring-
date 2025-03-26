package com.example.restapi.service.serviceImp;

import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Course;
import com.example.restapi.model.Instructor;
import com.example.restapi.model.request.CourseRequest;
import com.example.restapi.repository.CourseRepository;
import com.example.restapi.service.CourseService;
import com.example.restapi.service.InstructorService;
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
        if(id == null){
            throw new BadRequestException("Get by id "+id+ " not found");
        }
        return courseRepository.getByIdCourse(id);
    }

    @Override
    public Course createCourse(CourseRequest courseRequest) {
        Long instructorId = courseRequest.getInstructorId();
        Instructor instructor = instructorService.getInstructorsById(courseRequest.getInstructorId());
        if(instructor == null){
            throw new BadRequestException("Instructor with ID " + instructorId + " does not exist");
        }
        return courseRepository.createCourse(courseRequest);
    }

    @Override
    public Course updateCourse(CourseRequest courseRequest, Long id) {
        getByIdCourse(id);
        Long instructorId = courseRequest.getInstructorId();
        Instructor instructor = instructorService.getInstructorsById(courseRequest.getInstructorId());
        if(instructor == null){
            throw new BadRequestException("Instructor with ID " + instructorId + " does not exist");
        }
        return courseRepository.updateCourse(courseRequest, id);
    }

    @Override
    public Course deleteCouse(Long id) {
        getByIdCourse(id);
        return courseRepository.deleteCourse(id);
    }
}
