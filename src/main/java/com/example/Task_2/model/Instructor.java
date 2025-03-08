package com.example.Task_2.Table;

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
}
