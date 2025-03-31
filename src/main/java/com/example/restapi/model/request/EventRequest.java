package com.example.restapi.model.request;

import com.example.restapi.model.Venues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    private String eventName;
    private Timestamp eventDate;
    private Long venuesId;
}
