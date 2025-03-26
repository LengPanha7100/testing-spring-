package com.example.restapi.controller;

import com.example.restapi.model.APIResponse;
import com.example.restapi.model.Student;
import com.example.restapi.model.request.StudentRequest;
import com.example.restapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.JstlUtils;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Student>>> getAllStudents(@RequestParam (defaultValue = "1") Long pageNo,
                                                                     @RequestParam (defaultValue = "10") Long pageSize) {
        List<Student> students = studentService.getAllStudent(pageNo,pageSize);
        APIResponse<List<Student>> apiResponse = APIResponse.<List<Student>>builder()
                .message("Get all students successfully")
                .status(HttpStatus.OK)
                .payload(students)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Student>> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        APIResponse<Student> apiResponse = APIResponse.<Student>builder()
                .message("Get student by id successfully")
                .status(HttpStatus.OK)
                .payload(student)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping
    public ResponseEntity<APIResponse<Student>> createStudent(@RequestBody StudentRequest studentRequest) {
        Student student = studentService.createStudent(studentRequest);
        APIResponse<Student> apiResponse = APIResponse.<Student>builder()
                .message("Create student successfully")
                .status(HttpStatus.OK)
                .payload(student)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Student>> updateStudent(@RequestBody StudentRequest studentRequest , @PathVariable Long id) {
        Student student = studentService.updateStudent(studentRequest , id);
        APIResponse<Student> apiResponse = APIResponse.<Student>builder()
                .message("Update student successfully")
                .status(HttpStatus.OK)
                .payload(student)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Student>> deleteStudent( @PathVariable Long id) {
        studentService.deleteStudent( id);
        APIResponse<Student> apiResponse = APIResponse.<Student>builder()
                .message("Delete student successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/searchName")
    public ResponseEntity<APIResponse<List<Student>>> searchStudentByName(@RequestParam String name) {
        List<Student> student = studentService.searchStudentByName(name);
        APIResponse<List<Student>> apiResponse = APIResponse.<List<Student>>builder()
                .message("Search student by name successfully")
                .status(HttpStatus.OK)
                .payload(student)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }


 }
