package com.example.puja.LearningRESTapi.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private String email;


    // Data annotation will generate all the getters, setters, toString, equals, and hashCode methods
    //AllArgsConstructor will generate a constructor with all fields


    // public StudentDto(long id, String name, String email) {
    //     this.id = id;
    //     this.name = name;
    //     this.email = email;
    // }

    // public long getId() {
    //     return id;
    // }

    // public void setId(long id) {
    //     this.id = id;
    // }

    // public String getName() {
    //     return name;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }
}
