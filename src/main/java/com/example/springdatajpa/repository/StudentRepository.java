package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // building custom methods, you just give the definition not the implementation
    public List<Student> findByFirstName(String firstName);
    Student findByFirstNameAndLastName(String firstName, String LastName);
    public List<Student> findByFirstNameContaining(String name);
    public List<Student> findByLastNameNotNull();
    public List<Student> findByGuardianName(String name);

    //JPQL
    @Query("Select s from Student s where s.email = ?1") // takes the class not the table and it's attributes
    Student getStudentByEmailAddress(String email);

    //JPQL
    @Query("Select s.firstName from Student s where s.email = ?1") // takes the class not the table and it's attributes
    String getStudentFirstNameByEmailAddress(String email);

    // Native query
    @Query(value = "SELECT * from student_table s where s.email_address = ?1", nativeQuery = true) // takes the class not the table and it's attributes
    Student getStudentByEmailAddressNative(String email);

    // Native query with named param
    @Query(value = "SELECT * from student_table s where s.email_address = :email", nativeQuery = true) // takes the class not the table and it's attributes
    Student getStudentByEmailAddressNativeNamedParam(@Param("email") String email);

    // Method to update record

    @Modifying // since modifying, there must be a transactional
    @Transactional  //should be in the service layer
    @Query(value = "update student_table set first_name = ?1 where email_address = ?2", nativeQuery = true) // takes the class not the table and it's attributes
    int updateStudentNameByEmail(String firstName, String email);
}
