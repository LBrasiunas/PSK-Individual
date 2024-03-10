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
        @NamedQuery(name = "Course.getAll", query = "SELECT C FROM Course as C")
})
@Getter
@Setter
@NoArgsConstructor
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String Name;

    @ManyToMany(mappedBy = "Courses")
    private List<Student> Students = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer OptLockVersion;

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Course course = (Course) object;
        return Objects.equals(Name, course.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name);
    }
}
