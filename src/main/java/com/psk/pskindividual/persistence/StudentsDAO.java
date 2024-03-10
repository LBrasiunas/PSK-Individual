package com.psk.pskindividual.persistence;

import com.psk.pskindividual.entities.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Setter;

@ApplicationScoped
@Setter
public class StudentsDAO {

    @Inject
    private EntityManager entityManager;

    public void add(Student student){
        this.entityManager.persist(student);
    }

    public Student getById(Integer id){
        return entityManager.find(Student.class, id);
    }

    public Student update(Student student){
        return entityManager.merge(student);
    }

    public void delete(Integer id) {
        Student studentDbResponse = this.getById(id);
        this.entityManager.remove(studentDbResponse);
    }
}
