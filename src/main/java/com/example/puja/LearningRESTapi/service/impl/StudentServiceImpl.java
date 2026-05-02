package com.example.puja.LearningRESTapi.service.impl;

import com.example.puja.LearningRESTapi.dto.CreateStudentDto;
import com.example.puja.LearningRESTapi.dto.StudentDto;
import com.example.puja.LearningRESTapi.entity.Student;
import com.example.puja.LearningRESTapi.repository.StudentRepository;
import com.example.puja.LearningRESTapi.service.StudentService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtoList = students
        .stream()
        .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()) )
        .toList();

        return studentDtoList;
    }
    
    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
        return modelMapper.map(student, StudentDto.class);
    }
    
    @Override
    public StudentDto createStudent(CreateStudentDto createStudentDto) {
        Student newStudent = modelMapper.map(createStudentDto, Student.class);
        Student savedStudent = studentRepository.save(newStudent);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
    
    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));
        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
    }


    //intended for full updates
    @Override
    public StudentDto updateStudent(Long id, CreateStudentDto createStudentDto) {
        //find the student
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));

        // check if email is being changed, dont allow
        if (!student.getEmail().equals(createStudentDto.getEmail())) {
            throw new IllegalArgumentException("Email cannot be changed");
        }
        //student.setDeletedAt(null);
        modelMapper.map(createStudentDto, student);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDto.class);
    }
    
    // intended for partial updates
    @Override
    public StudentDto updatePartialStudent(Long id, Map<Object, Object> updates) {
        //find the student
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Student not found with id: " + id));

        updates.forEach((key, value) -> {
            switch (key.toString()) {
                case "name":
                    student.setName(value.toString());
                    break;
                case "email":
                    student.setEmail(value.toString());
                    break;
            
                default:
                    throw new IllegalArgumentException("Field not supported: " + key);
            }
        });
        
        Student updatedStudent = studentRepository.save(student);
        return modelMapper.map(updatedStudent, StudentDto.class);
    }
}
