package com.example.puja.LearningRESTapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.puja.LearningRESTapi.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
