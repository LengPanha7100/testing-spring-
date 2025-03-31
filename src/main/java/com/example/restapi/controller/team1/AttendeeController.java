package com.example.restapi.controller.team1;

import com.example.restapi.model.APIResponse;
import com.example.restapi.model.Attendee;
import com.example.restapi.model.request.AttendeeRequest;
import com.example.restapi.service.team1.AttendeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendee")
@AllArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Attendee>>> getAllAttendee(@RequestParam (defaultValue = "1") Long pageNo,
                                                                      @RequestParam (defaultValue = "10") Long pageSize) {
        List<Attendee> attendees = attendeeService.getAllAttendee(pageNo, pageSize);
        APIResponse<List<Attendee>> apiResponse = APIResponse.<List<Attendee>>builder()
                .message("Get all attendee successfully")
                .status(HttpStatus.OK)
                .payload(attendees)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Attendee>> getAttendeeById(@PathVariable Long id) {
        Attendee attendees = attendeeService.getAttendeeById(id);
        APIResponse<Attendee> apiResponse = APIResponse.<Attendee>builder()
                .message("Get attendee by id successfully")
                .status(HttpStatus.OK)
                .payload(attendees)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Attendee>> createAttendee(@RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendees = attendeeService.createAttendee(attendeeRequest);
        APIResponse<Attendee> apiResponse = APIResponse.<Attendee>builder()
                .message("Create attendee successfully")
                .status(HttpStatus.OK)
                .payload(attendees)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Attendee>> updateAttendeeById(@PathVariable Long id , @RequestBody AttendeeRequest attendeeRequest) {
        Attendee attendees = attendeeService.updateAttendeeById(id , attendeeRequest);
        APIResponse<Attendee> apiResponse = APIResponse.<Attendee>builder()
                .message("Update attendee by id successfully")
                .status(HttpStatus.OK)
                .payload(attendees)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Attendee>> deleteAttendeeById(@PathVariable Long id) {
        attendeeService.deleteAttendeeById(id);
        APIResponse<Attendee> apiResponse = APIResponse.<Attendee>builder()
                .message("Delete attendee by id "+id+" successfully")
                .status(HttpStatus.OK)
                .payload(null)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
