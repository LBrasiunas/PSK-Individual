package com.psk.pskindividual.persistence;

import com.psk.pskindividual.entities.Student;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.Setter;

import java.util.List;

@ApplicationScoped
@Setter
public class StudentsDAO {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public List<Student> getAll(){
        return entityManager.createNamedQuery("Student.getAll", Student.class).getResultList();
    }

    @Transactional
    public void add(Student student){
        this.entityManager.persist(student);
    }

    @Transactional
    public Student getById(Integer id){
        return entityManager.find(Student.class, id);
    }

    @Transactional
    public void update(Student student){
        this.entityManager.merge(student);
    }
}
