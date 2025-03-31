package com.example.restapi.repository.team1;

import com.example.restapi.model.Attendee;
import com.example.restapi.model.Event;
import com.example.restapi.model.request.AttendeeRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Results(id = "attendeeId" , value = {
            @Result(property = "attendeeId" , column = "attendee_id"),
            @Result(property =  "attendeeName" , column = "attendee_name"),
            @Result(property = "eventList" , column = "attendee_id",
            many = @Many(select = "getAttendeeAndEvent")
            )
    })
    @Select("""
    SELECT * FROM attendee_db
    LIMIT #{pageSize}
    OFFSET #{pageSize} * (#{pageNo} - 1);
    """)
    List<Attendee> getAllAttendee(Long pageNo, Long pageSize);


    @Select("""
    SELECT * FROM attendee_db WHERE attendee_id = #{id};
    """)
    @ResultMap("attendeeId")
    Attendee getAttendeeById(Long id);

    @Results(id = "eventId" , value = {
            @Result(property = "eventId" , column = "event_id"),
            @Result(property = "eventName" , column = "event_name"),
            @Result(property = "eventDate" , column = "event_Date"),
            @Result(property = "venuesId" , column = "venues_id",
                    one = @One(select = "com.example.restapi.repository.team1.VenuesRepository.getVenuesById")
            )
    })
    @Select("""
    SELECT ev.event_id , ev.event_name , ev.event_date , ev.venues_id FROM events_db ev
    JOIN event_attendee_db ead ON ev.event_id = ead.event_id
    WHERE attendee_id = #{attendeeId};
    """)
    List<Event> getAttendeeAndEvent(Long attendeeId);


    @Select("""
    INSERT INTO attendee_db(attendee_name, email)
    VALUES (#{attendee.attendeeName}, #{attendee.email})
    RETURNING *;
    """)
    @ResultMap("attendeeId")
    Attendee createAttendee(@Param("attendee") AttendeeRequest attendeeRequest);

    @Select("""
    INSERT INTO event_attendee_db ( attendee_id ,event_id)
    VALUES ( #{attendeeId} ,#{eventId});
    """)
    void insertAttendeeAndEvent(Long attendeeId , Long eventId);

    @Select("""
    UPDATE attendee_db SET attendee_name = #{attendee.attendeeName} , email = #{attendee.email} WHERE attendee_id= #{id} RETURNING *;
    """)
    @ResultMap("attendeeId")
    Attendee updateAttendeeById(Long id, @Param("attendee") AttendeeRequest attendeeRequest);

    @Delete("""
    DELETE FROM attendee_db WHERE attendee_id =#{id} ;
    """)
    @ResultMap("attendeeId")
    void deleteAttendeeById(Long id);

    @Select("""
    DELETE FROM event_attendee_db WHERE attendee_id =#{attendeeId};
    """)
    void deleteAttendeeAndEvent(Long attendeeId);
}
