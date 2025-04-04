package com.example.restapi.service.serviceImp.team2;

import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Student;
import com.example.restapi.model.request.StudentRequest;
import com.example.restapi.repository.team2.StudentRepository;
import com.example.restapi.service.team2.CourseService;
import com.example.restapi.service.team2.StudentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    public StudentServiceImp(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    @Override
    public List<Student> getAllStudent(Long pageNo, Long pageSize) {
        if(pageNo < 1){
            throw new BadRequestException("pageNo must be greater than zero");
        }
        return studentRepository.getAllStudent(pageNo,pageSize);
    }

    @Override
    public Student getStudentById(Long id) {
        Student student = studentRepository.getStudentById(id);
        if(student == null){
            throw new BadRequestException("Student by id "+id+" not found");
        }
        return student;
    }

    @Override
    public Student createStudent(StudentRequest studentRequest) {
        Student student = studentRepository.createStudent(studentRequest);
        for(Long courseId : studentRequest.getCourseList()){
            studentRepository.insertCourseIdAndStudentId(student.getStudentId(),courseId);
        }
        return getStudentById(student.getStudentId());
    }

    @Override
    public Student updateStudent(StudentRequest studentRequest, Long id) {
        getStudentById(id);
        Student student = studentRepository.updateStudent(studentRequest,id);
        studentRepository.deleteByStudentByIdAndCourse(id);
        for(Long courseId : studentRequest.getCourseList()){
            studentRepository.insertCourseIdAndStudentId(student.getStudentId(),courseId);
        }
        return getStudentById(student.getStudentId());
    }

    @Override
    public void deleteStudent(Long id) {
        getStudentById(id);
      studentRepository.deleteStudent(id);
    }

    @Override
    public List<Student> searchStudentByName(String name) {
        List<Student> students = studentRepository.searchStudentByName(name);

        if (students.isEmpty()) {
            throw new BadRequestException("No student found with the name: " + name);
        }
        return students;
    }
}
