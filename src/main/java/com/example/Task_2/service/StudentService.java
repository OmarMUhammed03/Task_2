package com.example.Task_2.service;

import com.example.Task_2.Table.Student;
import com.example.Task_2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null)
            throw new IllegalArgumentException("Student not found");
        return student;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getStudentsByCourseId(Integer courseId) {
        return studentRepository.findStudentsByCourseId(courseId);
    }
}
