package com.psk.pskindividual.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Student.getAll", query = "SELECT S FROM Student as S")
})
@Getter
@Setter
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "STUDENT_NUMBER", nullable = false)
    private Long StudentNumber;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String Surname;

    @ManyToOne
    @JoinColumn(name = "UNIVERSITY_ID")
    private University University;

    @ManyToMany
    private List<Course> Courses = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer OptLockVersion;

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Student student = (Student) object;
        return Objects.equals(StudentNumber, student.StudentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StudentNumber);
    }
}
