package com.example.restapi.controller;

import com.example.restapi.model.APIResponse;
import com.example.restapi.model.Instructor;
import com.example.restapi.model.request.InstructorRequest;
import com.example.restapi.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/instructor")
@RestController
public class InstructorController
{
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @GetMapping
    public ResponseEntity<APIResponse<List<Instructor>>> getAllInstructors(@RequestParam (defaultValue = "1") Long pageNo,
                                                                           @RequestParam (defaultValue = "10") Long pageSize) {
        List<Instructor> instructors = instructorService.getAllInstructors(pageNo , pageSize);
        APIResponse<List<Instructor>> apiResponse = APIResponse.<List<Instructor>>builder()
                .message("Get all instructors success")
                .status(HttpStatus.OK)
                .payload(instructors)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Instructor>> getInstructorsById(@PathVariable Long id) {
        Instructor instructors = instructorService.getInstructorsById(id);
        APIResponse<Instructor> apiResponse = APIResponse.<Instructor>builder()
                .message("Get by id instructors success")
                .status(HttpStatus.OK)
                .payload(instructors)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Instructor>> createInstructor(@RequestBody InstructorRequest instructorRequest) {
        Instructor instructors = instructorService.createInstructor(instructorRequest);
        APIResponse<Instructor> apiResponse = APIResponse.<Instructor>builder()
                .message("Create instructors success")
                .status(HttpStatus.OK)
                .payload(instructors)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Instructor>> updateInstructor(@PathVariable Long id, @RequestBody InstructorRequest instructorRequest) {
        Instructor instructors = instructorService.updateInstructor(id,instructorRequest);
        APIResponse<Instructor> apiResponse = APIResponse.<Instructor>builder()
                .message("update instructors success")
                .status(HttpStatus.OK)
                .payload(instructors)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Instructor>> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        APIResponse<Instructor> apiResponse = APIResponse.<Instructor>builder()
                .message("update instructors success")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
