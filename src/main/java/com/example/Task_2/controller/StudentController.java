package com.example.Task_2.controller;

import com.example.Task_2.Table.Student;
import com.example.Task_2.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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

    @GetMapping("/")
    public ResponseEntity<List<Student>> getStudents() {
        return handleRequest(() -> studentService.getAllStudents());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getUserById(@PathVariable Integer studentId) {
        return handleRequest(() -> studentService.getStudentById(studentId));
    }

    @PostMapping("/")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return handleRequest(() -> studentService.saveStudent(student));
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {
        Student newStudent = studentService.getStudentById(studentId);
        newStudent.setId(studentId);
        return handleRequest(() -> studentService.saveStudent(student));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer studentId) {
        return handleStringRequest(() -> studentService.deleteStudent(studentId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Student>> getStudentByCourseId(@PathVariable Integer courseId) {
        return handleRequest(() -> studentService.getStudentsByCourseId(courseId));
    }

}
