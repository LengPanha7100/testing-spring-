package com.example.restapi.model.request;

import com.example.restapi.model.Enum.TicketStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TicketRequest {
    private String passengerName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime travelDate;
    private String sourceStation;
    private Double price;
    private String destinationStation;
    private Boolean paymentStatus;
    private TicketStatusEnum ticketStatus;
    private String seatNumber;

}
