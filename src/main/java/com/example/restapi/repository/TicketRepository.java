package com.example.restapi.repository;

import com.example.restapi.model.Enum.TicketStatusEnum;
import com.example.restapi.model.Ticket;
import com.example.restapi.model.request.TicketRequest;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
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
    SELECT * FROM  tickets WHERE ticket_id = #{ticketId};
    """)
    @ResultMap("ticketId")
    Ticket getTicketById( Long ticketId);

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
    WHERE ticket_id = #{ticketId}
    returning *;
    """)
    @ResultMap("ticketId")
    Ticket updateTicketById(@Param("ticketId") Long ticketId, @Param("ticket") TicketRequest ticketRequest);

    @Select("""
    DELETE FROM tickets WHERE ticket_id = #{ticketId};
    """)
    @ResultMap("ticketId")
    Ticket deleteTicketById(Long ticketId);

    @Select("""
    SELECT * FROM tickets WHERE passenger_name = #{passengerName};
    """)
    @ResultMap("ticketId")
    Ticket searchPassengerName(String passengerName);

    @Select("""
    SELECT * FROM tickets WHERE ticket_status = #{status} AND travel_date = #{travelDate};
    """)
    @ResultMap("ticketId")
    Ticket filterStatusAndDate(TicketStatusEnum status, LocalDate travelDate );

    @Select("""
    INSERT INTO tickets (passenger_name, travel_date, source_station, destination_station, price, payment_status, ticket_status, seat_number)
    VALUES(#{ticket.passengerName} , #{ticket.travelDate} , #{ticket.sourceStation} , #{ticket.destinationStation} , #{ticket.price} , #{ticket.paymentStatus},#{ticket.ticketStatus} , #{ticket.seatNumber})
    RETURNING *;
    """)
    @ResultMap("ticketId")
    List<Ticket> createTicketList(@Param("ticket") List<TicketRequest> ticketRequestList);

    @Select("""
    UPDATE tickets 
    SET payment_status = #{paymentStatus}
    WHERE ticket_id = #{ticketId}
    returning *;
    """)
    @ResultMap("ticketId")
    Ticket updatePaymentStatus(Long ticketId , @Param("paymentStatus") Boolean paymentStatus);
}
