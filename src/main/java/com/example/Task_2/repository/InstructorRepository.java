package com.example.Task_2.repository;

import com.example.Task_2.Table.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    @Query("SELECT instructor FROM Instructor instructor where instructor.email=:email")
    Instructor findInstructorByEmail(@Param("email") String email);
}