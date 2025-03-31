package com.example.restapi.service.team1;

import com.example.restapi.model.Attendee;
import com.example.restapi.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendee(Long pageNo, Long pageSize);

    Attendee getAttendeeById(Long id);

    Attendee createAttendee(AttendeeRequest attendeeRequest);

    Attendee updateAttendeeById(Long id, AttendeeRequest attendeeRequest);

    void deleteAttendeeById(Long id);
}
