package com.example.restapi.service.serviceImp;
import com.example.restapi.model.Ticket;
import com.example.restapi.model.request.TicketRequest;
import com.example.restapi.repository.TicketRepository;
import com.example.restapi.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImp implements TicketService {
    private final TicketRepository ticketRepository;

    public TicketServiceImp(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getAllTicket(Integer page, Integer size) {
        if(page < 1) {
//            throw new BadRequestException("Can't input negative page");
            System.out.println("page is less than 1");
        }
        return ticketRepository.getAllTicket(page,size);
    }

    @Override
    public Ticket getTicketById(Long id) {
        Ticket ticket = ticketRepository.getTicketById(id);
        if(ticket == null) {
//            throw new BadRequestException("Ticket by id "+ id + " not found");
            System.out.println("page is less than 1");
        }
        return ticket;
    }

    @Override
    public Ticket createTicket(TicketRequest ticketRequest) {
        return ticketRepository.createTicket(ticketRequest);
    }

    @Override
    public Ticket updateTicketById(Long id, TicketRequest ticketRequest) {
        getTicketById(id);
        return ticketRepository.updateTicketById(id,ticketRequest);
    }

    @Override
    public Ticket deleteTickerById(Long id) {
         getTicketById(id);
        return ticketRepository.deleteTicketById(id);
    }
}
