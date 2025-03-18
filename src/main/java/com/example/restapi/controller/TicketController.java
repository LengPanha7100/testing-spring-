package com.example.restapi.controller;

import com.example.restapi.model.APIResponse;
import com.example.restapi.model.Enum.TicketStatusEnum;
import com.example.restapi.model.Ticket;
import com.example.restapi.model.TicketUpdateStatus;
import com.example.restapi.model.request.TicketRequest;
import com.example.restapi.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
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
    @GetMapping("/{ticketId}")
    public ResponseEntity<APIResponse<Ticket>> getTicketById(@PathVariable Long ticketId) {
        Ticket ticket1   = ticketService.getTicketById(ticketId);
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

    @PutMapping("/{ticketId}")
    public ResponseEntity<APIResponse<Ticket>> updateTicketById(@PathVariable Long ticketId , @RequestBody TicketRequest ticketRequest) {
        Ticket ticket1   = ticketService.updateTicketById(ticketId , ticketRequest);
        APIResponse<Ticket> apiResponse = APIResponse.<Ticket>builder()
                .message("Update tickets retrieved by id successfully.")
                .payload(ticket1)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<APIResponse<Ticket>> deleteTickerById(@PathVariable Long ticketId ) {
        Ticket ticket1   = ticketService.deleteTickerById(ticketId );
        APIResponse<Ticket> apiResponse = APIResponse.<Ticket>builder()
                .message("Delete tickets retrieved by id successfully.")
                .payload(ticket1)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<Ticket>> searchPassengerName(@RequestParam String passengerName) {
        Ticket ticket = ticketService.searchPassengerName(passengerName);
        APIResponse<Ticket> apiResponse = APIResponse.<Ticket>builder()
                .message("Search tickets retrieved by name successfully.")
                .status(HttpStatus.OK)
                .payload(ticket)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/filter")
    public ResponseEntity<APIResponse<Ticket>> filterStatusAndDate(@RequestParam TicketStatusEnum status, @RequestParam LocalDate travelDate ) {
        Ticket ticket = ticketService.filterStatusAndDate(status, travelDate );
        APIResponse<Ticket> apiResponse = APIResponse.<Ticket>builder()
                .message("Filter tickets retrieved by status successfully.")
                .status(HttpStatus.OK)
                .payload(ticket)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/bulk")
    public ResponseEntity<APIResponse<List<Ticket>>> createTicketList(@RequestBody List<TicketRequest> ticketRequestList) {
        List<Ticket> ticket = ticketService.createTicketList(ticketRequestList);
        APIResponse<List<Ticket>> apiResponse = APIResponse.<List<Ticket>>builder()
                .message("Created tickets successfully.")
                .status(HttpStatus.OK)
                .payload(ticket)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping
    public ResponseEntity<APIResponse<List<Ticket>>> ticketUpdatePaymentStatus(@RequestBody TicketUpdateStatus ticketUpdateStatus){
        List<Ticket> ticket = ticketService.ticketUpdatePaymentStatus(ticketUpdateStatus, ticketUpdateStatus.getTicketIds() , ticketUpdateStatus.getPaymentStatus());
        APIResponse<List<Ticket>> apiResponse = APIResponse.<List<Ticket>>builder()
                .message("Update tickets retrieved by id successfully.")
                .status(HttpStatus.OK)
                .payload(ticket)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
