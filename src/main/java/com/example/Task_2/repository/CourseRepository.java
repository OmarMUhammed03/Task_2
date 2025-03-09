package com.example.Task_2.repository;

import com.example.Task_2.model.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {


    List<Course> findCoursesByInstructorId(Integer instructorId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO student_course (student_id, course_id) VALUES (:studentId, :courseId)", nativeQuery = true)
    void enrollStudent(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM student_course where student_id = :studentId AND course_id = :courseId", nativeQuery = true)
    void unenrollStudent(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);
}
