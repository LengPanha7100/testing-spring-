package com.example.restapi.service;

import com.example.restapi.model.Enum.TicketStatusEnum;
import com.example.restapi.model.Ticket;
import com.example.restapi.model.request.TicketRequest;

import java.time.LocalDate;
import java.util.List;


public interface TicketService {
    List<Ticket> getAllTicket(Integer page, Integer size);

    Ticket getTicketById(Long ticketId);

    Ticket createTicket(TicketRequest ticketRequest);

    Ticket updateTicketById(Long ticketId, TicketRequest ticketRequest);

    Ticket deleteTickerById(Long ticketId);

    Ticket searchPassengerName(String passengerName);

    Ticket filterStatusAndDate(TicketStatusEnum status, LocalDate travelDate );
}
