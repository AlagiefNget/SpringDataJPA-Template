package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Course;
import com.example.springdatajpa.entity.Student;
import com.example.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void getCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Paul")
                .lastName("Coraza")
                .build();

        Course course = Course.builder()
                .credit(5)
                .title("Algorithms")
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);

        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();
        long totalElements = courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();
        int totalPages = courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();
        System.out.println(totalElements);
        System.out.println(totalPages);
        System.out.println("Pagination =====> "+courses);
    }

    @Test
    public void findAllPaginationWithSorting(){
        Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("title"));

        Pageable sortByCredit = PageRequest.of(1, 2, Sort.by("credit").descending());
        Pageable sortByTitleAndCredit = PageRequest.of(
                0,
                2,
                Sort.by("title")
                    .descending()
                    .and(Sort.by("credit")));

        List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        long totalElements = courseRepository.findAll(sortByTitle).getTotalElements();
        int totalPages = courseRepository.findAll(sortByTitle).getTotalPages();
        System.out.println(totalElements);
        System.out.println(totalPages);
        System.out.println("Pagination =====> "+courses);
    }

    @Test void printFindByTitleContaining(){
        Pageable firstTenRecords = PageRequest.of(0,10);
        List<Course> courses = courseRepository.findByTitleContaining("gorithm", firstTenRecords).getContent();
        System.out.println("******* "+courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Student student = Student.builder()
                .firstName("Jane")
                .lastName("Doe")
                .email("jdoe@miu.edu")
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Professor")
                .lastName("Renuka")
                .build();

        Course course = Course.builder()
                .title("Mobile Programming")
                .credit(5)
                .teacher(teacher)
                .build();
        course.addStudents(student);
        courseRepository.save(course);
    }
}