package com.example.springdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

// Didn't use entity because don't want to save it in the db
@Embeddable // to be embedded in the student class
@Data
@AllArgsConstructor
@NoArgsConstructor
// Map to data/columns in the db
@Builder
@AttributeOverrides({
        @AttributeOverride(
                name="name",
                column = @Column(name = "guardian_name")
        ),
        @AttributeOverride(
                name="email",
                column = @Column(name = "guardian_email")
        ),
        @AttributeOverride(
                name="phone",
                column = @Column(name = "guardian_phone")
        )
})
public class Guardian {
    private String name;
    private String email;
    private String phone;
}
