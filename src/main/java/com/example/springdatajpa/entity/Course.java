package com.example.springdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
    private Long courseId;
    private String title;
    private Integer credit;

    // Bidirectional relationship between course and course material
    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne( cascade = CascadeType.ALL) // allows you to save both objects
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    // For many-to-many relation between courses and students
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course_table",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"

            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

    public void addStudents(Student student){
        students = new ArrayList<>();
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }
}
