package com.example.Task_2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String code;

    private Integer credit;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Instructor instructor;


    public Course(String name, String code, int credit, Instructor instructor) {
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.credit = credit;
    }

    public Course() {

    }
}
