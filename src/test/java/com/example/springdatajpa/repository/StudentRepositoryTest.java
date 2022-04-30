package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Guardian;
import com.example.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // this test impacts the database, so not ideal to use in real applications
//@DataJpaTest // this test does not impact the database, so ideal to use in real applications
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .email("anget@miu.edu")
                .firstName("Alagie F")
                .lastName("Nget")
//                .guardianName("Cherno Nget")
//                .guardianEmail("cnget@gmail.com")
//                .guardianPhone("3829938")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void SaveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("Cherno Nget")
                .email("cnget@gmail.com")
                .phone("3829938")
                .build();

        Student student = Student.builder()
                .email("nbalajo@miu.edu")
                .firstName("Natoma")
                .lastName("Balajo Nget")
                .guardian(guardian)
                .build();
        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstName(){
        List<Student> studentList = studentRepository.findByFirstName("Natoma");
        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("oma");
        System.out.println(studentList);
    }

    @Test
    public void printStudentByGuardianName(){
        List<Student> studentList = studentRepository.findByGuardianName("Cherno");
        System.out.println(studentList);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("anget@miu.edu");
        System.out.println("Student ====> "+student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String student = studentRepository.getStudentFirstNameByEmailAddress("anget@miu.edu");
        System.out.println("Student ====> "+student);
    }
    @Test
    public void printGetStudentByEmailAddressNativeQuery(){
        Student student = studentRepository.getStudentByEmailAddressNative("anget@miu.edu");
        System.out.println("Student ====> "+student);
    }

    @Test
    public void printGetStudentByEmailAddressNativeQueryNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNative("anget@miu.edu");
        System.out.println("Student ====> "+student);
    }

    @Test
    public void updateStudentNameByEmail(){
        studentRepository.updateStudentNameByEmail("Natoma mu Alagie", "nbalajo@miu.edu");
    }

}