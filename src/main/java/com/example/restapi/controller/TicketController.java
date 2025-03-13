package com.example.restapi.controller;

import com.example.restapi.model.APIResponse;
import com.example.restapi.model.Ticket;
import com.example.restapi.model.request.TicketRequest;
import com.example.restapi.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class TicketController {
    private final TicketService ticketService;


    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Ticket>>> getAllTicket(@RequestParam (defaultValue = "1") Integer page,
                                                                  @RequestParam (defaultValue = "10") Integer size) {
        List<Ticket> ticketList = ticketService.getAllTicket(page,size);
        APIResponse<List<Ticket>> listAPIResponse = APIResponse.<List<Ticket>>builder()
                .message("All tickets retrieved successfully.")
                .payload(ticketList)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(listAPIResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Ticket>> getTicketById(@PathVariable Long id) {
        Ticket ticket1   = ticketService.getTicketById(id);
        APIResponse<Ticket> apiResponse = APIResponse.<Ticket>builder()
                .message("Get tickets retrieved by id successfully.")
                .payload(ticket1)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping
    public ResponseEntity<APIResponse<Ticket>> createTicket(@RequestBody TicketRequest ticketRequest) {
        Ticket ticket1   = ticketService.createTicket(ticketRequest);
        APIResponse<Ticket> apiResponse = APIResponse.<Ticket>builder()
                .message("Created ticket successfully.")
                .payload(ticket1)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Ticket>> updateTicketById(@PathVariable Long id , @RequestBody TicketRequest ticketRequest) {
        Ticket ticket1   = ticketService.updateTicketById(id , ticketRequest);
        APIResponse<Ticket> apiResponse = APIResponse.<Ticket>builder()
                .message("Update tickets retrieved by id successfully.")
                .payload(ticket1)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Ticket>> deleteTickerById(@PathVariable Long id ) {
        Ticket ticket1   = ticketService.deleteTickerById(id );
        APIResponse<Ticket> apiResponse = APIResponse.<Ticket>builder()
                .message("Delete tickets retrieved by id successfully.")
                .payload(ticket1)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
