package com.example.Task_2.controller;

import com.example.Task_2.model.Course;
import com.example.Task_2.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    private static <T> ResponseEntity<T> handleRequest(Supplier<T> serviceMethod, String successMessage) {
        try {
            return ResponseEntity.ok(serviceMethod.get());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private static ResponseEntity<String> handleStringRequest(Runnable serviceMethod, String successMessage) {
        try {
            serviceMethod.run();
            return ResponseEntity.ok(successMessage);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return handleRequest(() -> courseService.getAllCourses(), "Courses retrieved successfully");
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable Integer instructorId) {
        return handleRequest(() -> courseService.getCoursesByInstructorId(instructorId), "Courses for instructor retrieved successfully.");
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<String> enrollStudent(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        return handleStringRequest(() -> courseService.enrollStudent(courseId, studentId), "Student enrolled in course successfully.");
    }

    @PutMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<String> unenrollStudent(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        return handleStringRequest(() -> courseService.unenrollStudent(courseId, studentId), "Student unenrolled from course successfully.");
    }
}