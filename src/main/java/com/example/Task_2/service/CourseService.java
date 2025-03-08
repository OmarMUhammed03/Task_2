package com.example.Task_2.service;

import com.example.Task_2.Table.Course;
import com.example.Task_2.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseByID(Integer id) {
        return courseRepository.findById(id);
    }

    public List<Course> getCoursesByInstructorId(Integer instructorId) {
        return courseRepository.findCoursesByInstructorId(instructorId);
    }

    public void enrollStudent(Integer courseId, Integer studentId) {
        courseRepository.enrollStudent(courseId, studentId);
    }

    public void unenrollStudent(Integer courseId, Integer studentId) {
        courseRepository.unenrollStudent(courseId, studentId);
    }
}
