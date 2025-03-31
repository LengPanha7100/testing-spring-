package com.example.restapi.service.serviceImp.team1;

import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Event;
import com.example.restapi.model.Venues;
import com.example.restapi.model.request.EventRequest;
import com.example.restapi.repository.team1.EventRepository;
import com.example.restapi.service.team1.EventService;
import com.example.restapi.service.team1.VenuesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;
    private final VenuesService venuesService;

    @Override
    public List<Event> getAllEvent(Long pageNo, Long pageSize) {
        return eventRepository.getAllEvent(pageNo,pageSize);
    }

    @Override
    public Event getEventsById(Long id) {
        Event event = eventRepository.getEventById(id);
        if(event == null) {
            throw new BadRequestException("Get events by id "+id+" is null");
        }
        return event;
    }

    @Override
    public Event createEvents(EventRequest eventRequest) {
        Venues venues = venuesService.getVenuesById(eventRequest.getVenuesId());
        if(venues == null) {
            throw new BadRequestException("Get events by id "+eventRequest.getVenuesId()+" is null");
        }
        return eventRepository.createEvent(eventRequest);
    }

    @Override
    public Event updateEventsById(Long id, EventRequest eventRequest) {
        getEventsById(id);
        Venues venues = venuesService.getVenuesById(eventRequest.getVenuesId());
        if(venues == null) {
            throw new BadRequestException("Get events by id "+eventRequest.getVenuesId()+" is null");
        }
        return eventRepository.updateEventById(id, eventRequest);
    }

    @Override
    public Event deleteEventsById(Long id) {
        getEventsById(id);
        return eventRepository.deleteEventById(id);
    }
}
