package com.example.Task_2.controller;

import com.example.Task_2.Table.Instructor;
import com.example.Task_2.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
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
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return handleRequest(() -> instructorService.getAllInstructors());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Instructor> getInstructorByEmail(@PathVariable String email) {
        return handleRequest(() -> instructorService.getInstructorByEmail(email));
    }
}
