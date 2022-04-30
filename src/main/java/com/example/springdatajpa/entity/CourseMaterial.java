package com.example.springdatajpa.entity;


import lombok.*;

import javax.persistence.*;

// Belongs to a course
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "course") // to exclude printing out the course since we are not loading it - org.hibernate.LazyInitializationException: could not initialize proxy [com.example.springdatajpa.entity.Course#1] - no Session

public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_material_sequence")
    private Long courseMaterialId;
    private String url;

    // Fetching data
    // eager -  it fetches the course dta as well when fetching data for course material
    // lazy -  fetching only the course material data

    // Bidirectional/Unidirectional relationship between course and course material
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false // must have a course added before it can be saved
    )
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;
}
