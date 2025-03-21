package com.example.restapi.repository;

import com.example.restapi.model.Instructor;
import com.example.restapi.model.request.InstructorRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InstructorRepository {
    @Results(id = "instructor" , value = {
            @Result(property = "instructorId" , column = "instructor_id"),
            @Result(property = "instructorName" , column = "instructor_name"),
    })
    @Select("""
        SELECT * FROM  instructor_db
        LIMIT #{pageSize}
        OFFSET #{pageSize} * (#{pageNo}-1)
    """)
    List<Instructor> getAllInstructor(Long pageNo, Long pageSize);

    @Select("""
        SELECT * FROM instructor_db WHERE instructor_id = #{id};
    """)
    @ResultMap("instructor")
    Instructor getInstructorById(Long id);

    @Select("""
        INSERT INTO instructor_db (instructor_name, email)
        VALUES(#{instructor.instructorName}, #{instructor.email})
        RETURNING *;
    """)
    @ResultMap("instructor")
    Instructor createInstructor(@Param("instructor") InstructorRequest instructorRequest);

    @Select("""
       UPDATE instructor_db SET instructor_name = #{instructor.instructorName} , email= #{instructor.email} 
       WHERE instructor_id = #{id}
       RETURNING *;
    """)
    @ResultMap("instructor")
    Instructor updateInstructor(Long id, @Param("instructor") InstructorRequest instructorRequest);

    @Select("""
        DELETE FROM instructor_db where instructor_id = #{id};
    """)
    @ResultMap("instructor")
    Instructor deleteRepository(Long id);


}
