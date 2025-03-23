package com.example.restapi.controller;
import com.example.restapi.model.APIResponse;
import com.example.restapi.model.Course;
import com.example.restapi.model.request.CourseRequest;
import com.example.restapi.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Course>>> getAllCourse(@RequestParam (defaultValue = "1") Long pageNo,
                                                                           @RequestParam (defaultValue = "10") Long pageSize) {
        List<Course> courseList = courseService.getAllCourse(pageNo , pageSize);
        APIResponse<List<Course>> apiResponse = APIResponse.<List<Course>>builder()
                .message("Get all instructors success")
                .status(HttpStatus.OK)
                .payload(courseList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Course>> getByIdCourse(@PathVariable Long id){
        Course course = courseService.getByIdCourse(id);
        APIResponse<Course> apiResponse = APIResponse.<Course>builder()
                .message("Gey by id success")
                .status(HttpStatus.OK)
                .payload(course)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<APIResponse<Course>> createCourse(@RequestBody CourseRequest courseRequest){
        Course course = courseService.createCourse(courseRequest);
        APIResponse<Course> apiResponse = APIResponse.<Course>builder()
                .message("Create instructor success")
                .status(HttpStatus.OK)
                .payload(course)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Course>> updateCourse(@RequestBody CourseRequest courseRequest , @PathVariable Long id){
        Course course = courseService.updateCourse(courseRequest,id);
        APIResponse<Course> apiResponse = APIResponse.<Course>builder()
                .message("Update instructor success")
                .status(HttpStatus.OK)
                .payload(course)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Course>> deleteCourse(@PathVariable Long id){
        Course course = courseService.deleteCouse(id);
        APIResponse<Course> apiResponse = APIResponse.<Course>builder()
                .message("Delete instructor success")
                .status(HttpStatus.OK)
                .payload(course)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
