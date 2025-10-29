package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // READ ONE
    public Optional<Student> getStudentById(Long id) {
        return repo.findById(id);
    }

    // UPDATE
    public Student updateStudent(Long id, Student newData) {
        return repo.findById(id).map(student -> {
            student.setName(newData.getName());
            student.setEmail(newData.getEmail());
            student.setAge(newData.getAge());
            return repo.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // DELETE
    public String deleteStudent(Long id) {
        repo.deleteById(id);
        return "Student deleted successfully!";
    }
}
