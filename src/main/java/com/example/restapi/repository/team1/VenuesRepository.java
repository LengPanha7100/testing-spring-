package com.example.restapi.repository.team1;

import com.example.restapi.model.Venues;
import com.example.restapi.model.request.VenuesRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenuesRepository {
    @Results(id = "venuesId" , value = {
            @Result(property = "venueId" , column = "venues_id"),
            @Result(property = "venueName" , column = "venues_name")
    })
    @Select(""" 
    SELECT * FROM venues_db
    LIMIT #{pageSize}
    OFFSET #{pageSize} * (#{pageNo} - 1);
    """)
    List<Venues> getAllVenues(Long pageNo, Long pageSize);

    @Select("""
    SELECT * FROM venues_db WHERE venues_id = #{id};
    """)
    @ResultMap("venuesId")
    Venues getVenuesById(Long id);

    @Select("""
    INSERT INTO venues_db (venues_name, location)
    VALUES (#{venues.venueName}, #{venues.location})
    RETURNING *;
    """)
    @ResultMap("venuesId")
    Venues createVenues(@Param("venues") VenuesRequest venuesRequest);

    @Select("""
    UPDATE venues_db SET venues_name = #{venues.venueName} , location = #{venues.location}
    WHERE venues_id =#{id} RETURNING * ;
    """)
    @ResultMap("venuesId")
    Venues updateVenuesById(Long id, @Param("venues") VenuesRequest venuesRequest);

    @Select("""
    DELETE FROM venues_db WHERE venues_id = #{id};
    """)
    @ResultMap("venuesId")
    void deleteVenues(Long id);
}
