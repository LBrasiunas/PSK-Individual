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
        @NamedQuery(name = "University.getAll", query = "SELECT U FROM University as U")
})
@Getter
@Setter
@NoArgsConstructor
public class University implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String Name;

    @OneToMany(mappedBy = "University")
    private List<Student> Students;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer OptLockVersion;

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        University university = (University) object;
        return Objects.equals(Name, university.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name);
    }
}
