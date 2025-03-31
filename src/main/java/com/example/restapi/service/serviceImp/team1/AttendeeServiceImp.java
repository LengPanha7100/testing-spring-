package com.example.restapi.service.serviceImp.team1;

import com.example.restapi.exception.BadRequestException;
import com.example.restapi.model.Attendee;
import com.example.restapi.model.request.AttendeeRequest;
import com.example.restapi.repository.team1.AttendeeRepository;
import com.example.restapi.service.team1.AttendeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AttendeeServiceImp implements AttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Attendee> getAllAttendee(Long pageNo, Long pageSize) {
        return attendeeRepository.getAllAttendee(pageNo , pageSize);
    }

    @Override
    public Attendee getAttendeeById(Long id) {
        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if(attendee == null) {
            throw new BadRequestException("Get attendee by id "+id+ "not found");
        }
        return attendee;
    }

    @Override
    public Attendee createAttendee(AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeRepository.createAttendee(attendeeRequest);
        for(Long event : attendeeRequest.getEventList()){
            attendeeRepository.insertAttendeeAndEvent(attendee.getAttendeeId(),event);
        }
        return getAttendeeById(attendee.getAttendeeId());
    }

    @Override
    public Attendee updateAttendeeById(Long id, AttendeeRequest attendeeRequest) {
        getAttendeeById(id);
        Attendee attendee = attendeeRepository.updateAttendeeById(id, attendeeRequest);
        attendeeRepository.deleteAttendeeById(id);
        for(Long event : attendeeRequest.getEventList()){
            attendeeRepository.insertAttendeeAndEvent(attendee.getAttendeeId(),event);
        }
        return getAttendeeById(attendee.getAttendeeId());
    }

    @Override
    public void deleteAttendeeById(Long id) {
        getAttendeeById(id);
        attendeeRepository.deleteAttendeeById(id);
    }
}
