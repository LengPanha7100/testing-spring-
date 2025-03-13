package com.example.restapi.service;

import com.example.restapi.model.Ticket;
import com.example.restapi.model.request.TicketRequest;

import java.util.List;


public interface TicketService {
    List<Ticket> getAllTicket(Integer page, Integer size);

    Ticket getTicketById(Long id);

    Ticket createTicket(TicketRequest ticketRequest);

    Ticket updateTicketById(Long id, TicketRequest ticketRequest);

    Ticket deleteTickerById(Long id);
}
