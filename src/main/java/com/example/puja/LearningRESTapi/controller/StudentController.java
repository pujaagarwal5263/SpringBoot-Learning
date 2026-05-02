package com.example.puja.LearningRESTapi.controller;

import com.example.puja.LearningRESTapi.dto.CreateStudentDto;
import com.example.puja.LearningRESTapi.dto.StudentDto;
import com.example.puja.LearningRESTapi.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
  private final StudentService studentService;
  
  @GetMapping
  public ResponseEntity<List<StudentDto>> getAllStudents() {
    return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
  }

  @GetMapping("/{id}")
  public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id) {
    //just to see different ways to return status codes
    return ResponseEntity.ok(studentService.getStudentById(id));
  }

  @PostMapping
  public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid CreateStudentDto createStudentDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(createStudentDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long id, @RequestBody CreateStudentDto createStudentDto) {
    return ResponseEntity.ok(studentService.updateStudent(id, createStudentDto));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable("id") Long id, @RequestBody Map<Object,Object> updates) {
    return ResponseEntity.ok(studentService.updatePartialStudent(id, updates));
  }
}
