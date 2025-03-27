package com.example.restapi.repository;

import com.example.restapi.model.Course;
import com.example.restapi.model.Student;
import com.example.restapi.model.request.StudentRequest;
import com.example.restapi.service.serviceImp.StudentServiceImp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentRepository {
    @Results(id = "studentId" , value = {
            @Result(property = "studentId" , column = "student_id"),
            @Result(property = "studentName" , column = "student_name"),
            @Result(property = "phoneNumber" , column = "phone_number"),
            @Result(property = "courseList" , column = "student_id",
            many = @Many(select = "getAllCourseByStudentId")
            )
    })
    @Select("""
    SELECT * FROM student_db
    LIMIT #{pageSize}
    OFFSET #{pageSize} * (#{pageNo} - 1)
    """)
    List<Student> getAllStudent(Long pageNo, Long pageSize);


    @Select("""
    SELECT * FROM student_db WHERE student_id = #{id};
    """)
    @ResultMap("studentId")
    Student getStudentById(Long id);

    @Results(id = "courseId" , value = {
            @Result(property = "courseId" , column = "course_id"),
            @Result(property = "courseName" , column = "course_name"),
            @Result(property = "instructorId" , column = "instructor_id",
                    one = @One(select = "com.example.restapi.repository.InstructorRepository.getInstructorById")
            )
    })
    @Select("""
    SELECT c.course_id , c.course_name , c.instructor_id FROM course_db c
    JOIN student_course_db scd on c.course_id = scd.course_id
    WHERE student_id = #{studentId};
    """)
    List<Course> getAllCourseByStudentId(Long studentId);


    @Select("""
    INSERT INTO student_db(student_name, phone_number)
    VALUES (#{student.studentName},#{student.phoneNumber})
    RETURNING *;
    """)
    @ResultMap("studentId")
    Student createStudent(@Param("student") StudentRequest studentRequest);

    @Insert("""
    INSERT INTO student_course_db(student_id, course_id)
    VALUES (#{studentId},#{courseId});
    """)
    void insertCourseIdAndStudentId(Long studentId, Long courseId);

    @Update("""
    UPDATE student_db SET student_name = #{student.studentName} , phone_number = #{student.phoneNumber}
    WHERE student_id = #{id}
    RETURNING *;
    """)
    @ResultMap("studentId")
    Student updateStudent(@Param("student") StudentRequest studentRequest, Long id);

    @Delete("""
    DELETE FROM student_db WHERE student_id = #{id};
    """)
    void deleteStudent(Long id);

    @Delete("""
    DELETE FROM student_course_db WHERE student_id = #{id};
    """)
    void deleteByStudentByIdAndCourse(Long id);

    @Select("""
    SELECT * FROM student_db WHERE student_name = #{name};
    """)
    @ResultMap("studentId")
    List<Student> searchStudentByName( String name);
}
