package com.example.restapi.repository;

import com.example.restapi.model.Ticket;
import com.example.restapi.model.request.TicketRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketRepository {
    @Results(id = "ticketId",value = {
            @Result(property = "ticketId",column = "ticket_id"),
            @Result(property = "passengerName",column = "passenger_name"),
            @Result(property = "travelDate",column = "travel_date"),
            @Result(property = "sourceStation",column = "source_station"),
            @Result(property = "price",column = "price"),
            @Result(property = "destinationStation",column = "destination_station"),
            @Result(property = "paymentStatus",column = "payment_status"),
            @Result(property = "ticketStatus",column = "ticket_status"),
            @Result(property = "seatNumber",column = "ticket_status"),
            @Result(property = "seatNumber",column = "seat_number"),
    })

    @Select("""
    SELECT * FROM tickets
    LIMIT #{size}
    OFFSET #{size} * (#{page}-1);
    """)
    List<Ticket> getAllTicket(Integer page, Integer size);

    @Select("""
    SELECT * FROM  tickets WHERE ticket_id = #{ticket.id};
    """)
    @ResultMap("ticketId")
    Ticket getTicketById(@Param("ticket") Long id);

    @Select("""
    INSERT INTO tickets (passenger_name, travel_date, source_station, destination_station, price, payment_status, ticket_status, seat_number)
    VALUES(#{ticket.passengerName} , #{ticket.travelDate} , #{ticket.sourceStation} , #{ticket.destinationStation} , #{ticket.price} , #{ticket.paymentStatus},#{ticket.ticketStatus} , #{ticket.seatNumber})
    RETURNING *;
    """)
    @ResultMap("ticketId")
    Ticket createTicket(@Param("ticket") TicketRequest ticketRequest);

    @Select("""
    UPDATE tickets 
    SET passenger_name = #{ticket.passengerName}, travel_date = #{ticket.travelDate} ,source_station =  #{ticket.sourceStation} , destination_station =  #{ticket.destinationStation} ,price = #{ticket.price} ,
    payment_status = #{ticket.paymentStatus}, ticket_status = #{ticket.ticketStatus} , seat_number =  #{ticket.seatNumber}
    WHERE ticket_id = #{ticket.id}
    returning *;
    """)
    @ResultMap("ticketId")
    Ticket updateTicketById(Long id, @Param("ticket") TicketRequest ticketRequest);

    @Select("""
    DELETE FROM tickets WHERE ticket_id = #{ticket.id};
    """)
    @ResultMap("ticketId")
    Ticket deleteTicketById(Long id);
}
