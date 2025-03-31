package com.example.restapi.repository.team2;

import com.example.restapi.model.Course;
import com.example.restapi.model.request.CourseRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseRepository {
    @Results(id = "courseId" , value = {
            @Result(property = "courseId" , column = "course_id"),
            @Result(property = "courseName" , column = "course_name"),
            @Result(property = "instructorId" , column = "instructor_id",
            one = @One(select = "com.example.restapi.repository.InstructorRepository.getInstructorById")
            )
    })
    @Select("""
    SELECT * FROM course_db
    LIMIT #{pageSize}
    OFFSET #{pageSize} * (#{pageNo}-1)
    """)
    List<Course> getAllCourse(Long pageNo, Long pageSize);
    void getInstructorById();

    @Select("""
    SELECT * FROM course_db WHERE course_id = #{id};
    """)
    @ResultMap("courseId")
    Course getByIdCourse(@Param("id") Long id);

    @Select("""
    INSERT INTO course_db(course_name, instructor_id)
    VALUES (#{course.courseName},#{course.instructorId})
    RETURNING *;
    """)
    @ResultMap("courseId")
    Course createCourse(@Param("course") CourseRequest courseRequest);

    @Select("""
    
    UPDATE course_db SET course_name = #{course.courseName} , instructor_id = #{course.instructorId} 
    WHERE course_id= #{id}
    returning *;
    """)
    @ResultMap("courseId")
    Course updateCourse(@Param("course") CourseRequest courseRequest, Long id);

    @Select("""
    DELETE FROM course_db WHERE course_id = #{id} ;
    """)
    @ResultMap("courseId")
    Course deleteCourse(Long id);
}
