package com.example.restapi.repository.team1;

import com.example.restapi.model.Event;
import com.example.restapi.model.request.EventRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {
    @Results(id = "eventId" , value = {
            @Result(property = "eventId" , column = "event_id"),
            @Result(property = "eventName" , column = "event_name"),
            @Result(property = "eventDate" , column = "event_Date"),
            @Result(property = "venuesId" , column = "venues_id",
            one = @One(select = "com.example.restapi.repository.team1.VenuesRepository.getVenuesById")
            )
    })

    @Select("""
    SELECT * FROM events_db
    LIMIT #{pageSize}
    OFFSET #{pageSize} * (#{pageNo} - 1);
    """)
    List<Event> getAllEvent(Long pageNo, Long pageSize);

    @Select("""
    SELECT * FROM events_db WHERE event_id = #{id};
    """)
    @ResultMap("eventId")
    Event getEventById(Long id);

    @Select("""
    INSERT INTO events_db (event_name, event_date, venues_id)
    VALUES(#{event.eventName}, #{event.eventDate}, #{event.venuesId})
    RETURNING *;
    """)
    @ResultMap("eventId")
    Event createEvent(@Param("event") EventRequest eventRequest);

    @Select("""
    UPDATE events_db SET event_name = #{event.eventName} , event_date = #{event.eventDate} ,venues_id = #{event.venuesId}
    WHERE event_id = #{id}  RETURNING *;
    """)
    @ResultMap("eventId")
    Event updateEventById(Long id, @Param("event") EventRequest eventRequest);

    @Select("""
    DELETE FROM events_db WHERE event_id = #{id};
    """)
    @ResultMap("eventId")
    Event deleteEventById(Long id);
}
