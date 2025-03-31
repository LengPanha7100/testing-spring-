package com.example.restapi.service.team1;

import com.example.restapi.model.Event;
import com.example.restapi.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvent(Long pageNo, Long pageSize);

    Event getEventsById(Long id);

    Event createEvents(EventRequest eventRequest);

    Event updateEventsById(Long id, EventRequest eventRequest);

    Event deleteEventsById(Long id);
}
