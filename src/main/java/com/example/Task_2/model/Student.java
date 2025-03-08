package com.example.Task_2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "student_course", // Join table name
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )

    private List<Course> courses;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Student() {

    }
}
