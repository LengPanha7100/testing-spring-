package com.example.restapi.service.serviceImp;
import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Enum.TicketStatusEnum;
import com.example.restapi.model.Ticket;
import com.example.restapi.model.TicketUpdateStatus;
import com.example.restapi.model.request.TicketRequest;
import com.example.restapi.repository.TicketRepository;
import com.example.restapi.service.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
            throw new BadRequestException("Can't input negative page");
        }
        return ticketRepository.getAllTicket(page,size);
    }

    @Override
    public Ticket getTicketById(Long ticketId) {
        Ticket ticket = ticketRepository.getTicketById(ticketId);
        if(ticket == null) {
            throw new BadRequestException("Ticket by id "+ ticketId + " not found");
        }
        return ticket;
    }

    @Override
    public Ticket createTicket(TicketRequest ticketRequest) {
        return ticketRepository.createTicket(ticketRequest);
    }

    @Override
    public Ticket updateTicketById(Long ticketId, TicketRequest ticketRequest) {
        getTicketById(ticketId);
        return ticketRepository.updateTicketById(ticketId,ticketRequest);
    }

    @Override
    public Ticket deleteTickerById(Long ticketId) {
         getTicketById(ticketId);
        return ticketRepository.deleteTicketById(ticketId);
    }

    @Override
    public Ticket searchPassengerName(String passengerName) {
        return ticketRepository.searchPassengerName(passengerName);
    }

    @Override
    public Ticket filterStatusAndDate(TicketStatusEnum status, LocalDate travelDate ) {
        return ticketRepository.filterStatusAndDate(status,travelDate );
    }

    @Override
    public List<Ticket> createTicketList(List<TicketRequest> ticketRequestList) {
        List<Ticket> ticketList = new ArrayList<>();

        for (TicketRequest ticketRequest : ticketRequestList) {
            Ticket ticket = ticketRepository.createTicket(ticketRequest);
            ticketList.add(ticket);
        }

        return ticketList;
    }

    @Override
    public List<Ticket> ticketUpdatePaymentStatus(TicketUpdateStatus ticketUpdateStatus, List<Long> ticketIds, Boolean paymentStatus) {
        if(ticketIds == null || ticketIds.isEmpty()) {
            throw new BadRequestException("Can't input null or empty list of ticketIds");
        }
        List<Ticket>  ticketList = new ArrayList<>();
        for(Long ticketId  : ticketIds) {
            Ticket ticket = ticketRepository.updatePaymentStatus(ticketId,paymentStatus);
            if(ticket != null) {
                ticketList.add(ticket);
            }

        }
        return ticketList;
    }
}
