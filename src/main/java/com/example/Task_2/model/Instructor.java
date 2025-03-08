package com.example.Task_2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Instructor {
    @Id
    private Integer id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    public Instructor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Instructor() {

    }
}
