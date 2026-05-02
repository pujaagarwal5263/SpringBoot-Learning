package com.example.puja.LearningRESTapi.service;

import com.example.puja.LearningRESTapi.dto.CreateStudentDto;
import com.example.puja.LearningRESTapi.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    StudentDto createStudent(CreateStudentDto createStudentDto);
    void deleteStudent(Long id);
    StudentDto updateStudent(Long id, CreateStudentDto createStudentDto);
    StudentDto updatePartialStudent(Long id, Map<Object, Object> updates);
}
