package com.example.restapi.model;

import com.example.restapi.model.Enum.TicketStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private Long ticketId;
    private String passengerName;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd")
    private LocalDate travelDate;
    private String sourceStation;
    private Double price;
    private String destinationStation;
    private Boolean paymentStatus;
    private TicketStatusEnum ticketStatus;
    private String seatNumber;

}
