package com.psk.pskindividual.persistence;

import com.psk.pskindividual.entities.Course;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.Setter;

import java.util.List;

@ApplicationScoped
@Setter
public class CoursesDAO {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public List<Course> getAll() {
        return entityManager.createNamedQuery("Course.getAll", Course.class).getResultList();
    }

    @Transactional
    public void add(Course course){
        this.entityManager.persist(course);
    }

    @Transactional
    public Course getById(Integer id){
        return entityManager.find(Course.class, id);
    }

    @Transactional
    public void update(Course course){
         this.entityManager.merge(course);
    }
}
