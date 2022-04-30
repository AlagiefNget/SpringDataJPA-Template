package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Course;
import com.example.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        // ideal way is to create method to add to the list of courses
        Course course1 = Course.builder()
                .title("EA")
                .credit(5)
                .build();
        Course course2 = Course.builder()
                .title("Java")
                .credit(6)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Alagie F")
                .lastName("Nget")
//                .courses(List.of(course1, course2))
                .build();

        teacherRepository.save(teacher);
    }

}