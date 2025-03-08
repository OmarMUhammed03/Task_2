package com.example.Task_2.controller;

import com.example.Task_2.Table.Course;
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

    private static <T> ResponseEntity<T> handleRequest(Supplier<T> serviceMethod) {
        try {
            return ResponseEntity.ok(serviceMethod.get());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private static ResponseEntity<String> handleStringRequest(Runnable serviceMethod) {
        try {
            serviceMethod.run();
            return ResponseEntity.ok("Operation completed successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return handleRequest(() -> courseService.getAllCourses());
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable Integer instructorId) {
        return handleRequest(() -> courseService.getCoursesByInstructorId(instructorId));
    }

    @PostMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<String> enrollStudent(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        return handleStringRequest(() -> courseService.enrollStudent(courseId, studentId));
    }

    @PutMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<String> unenrollStudent(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        return handleStringRequest(() -> courseService.unenrollStudent(courseId, studentId));
    }

}
