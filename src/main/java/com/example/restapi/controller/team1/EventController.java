package com.example.restapi.controller.team1;

import com.example.restapi.model.APIResponse;
import com.example.restapi.model.Event;
import com.example.restapi.model.request.EventRequest;
import com.example.restapi.service.team1.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Event>>> getAllEvents(@RequestParam (defaultValue = "1") Long pageNo,
                                                                 @RequestParam (defaultValue = "10") Long pageSize) {
        List<Event> events = eventService.getAllEvent(pageNo,pageSize);
        APIResponse<List<Event>> apiResponse = APIResponse.<List<Event>>builder()
                .message("Get all events successfully")
                .status(HttpStatus.OK)
                .payload(events)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Event>> getEventsById(@PathVariable Long id) {
        Event events = eventService.getEventsById(id);
        APIResponse<Event> apiResponse = APIResponse.<Event>builder()
                .message("Get events by id successfully")
                .status(HttpStatus.OK)
                .payload(events)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Event>> createEvents(@RequestBody EventRequest eventRequest) {
        Event events = eventService.createEvents(eventRequest);
        APIResponse<Event> apiResponse = APIResponse.<Event>builder()
                .message("Create events successfully")
                .status(HttpStatus.OK)
                .payload(events)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Event>> updateEventsById(@PathVariable Long id , @RequestBody EventRequest eventRequest) {
        Event events = eventService.updateEventsById(id,eventRequest);
        APIResponse<Event> apiResponse = APIResponse.<Event>builder()
                .message("Update event by id successfully")
                .status(HttpStatus.OK)
                .payload(events)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Event>> deleteEventsById(@PathVariable Long id) {
        Event events = eventService.deleteEventsById(id);
        APIResponse<Event> apiResponse = APIResponse.<Event>builder()
                .message("Delete events by id "+id+" successfully")
                .status(HttpStatus.OK)
                .payload(events)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
