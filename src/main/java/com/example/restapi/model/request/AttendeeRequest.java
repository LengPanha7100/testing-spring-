package com.example.restapi.model.request;

import com.example.restapi.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    private String attendeeName;
    private String email;
    private List<Long> eventList;
}
