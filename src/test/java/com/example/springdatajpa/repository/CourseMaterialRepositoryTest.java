package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Course;
import com.example.springdatajpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial(){

        Course course = Course.builder()
                .title("Data Structures")
                .credit(6)
                .build();

        // Cascading helps you achieve this

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);
    }

    // Fetching data
    // eager -  it fetches the course dta as well when fetching data for course material
    // lazy -  fetching only the course material data

    @Test
    public void printAllCourseMaterial(){
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        System.out.println(courseMaterials);
    }

}