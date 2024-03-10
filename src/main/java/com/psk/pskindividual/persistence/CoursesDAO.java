package com.psk.pskindividual.persistence;

import com.psk.pskindividual.entities.Course;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Setter;

@ApplicationScoped
@Setter
public class CoursesDAO {

    @Inject
    private EntityManager entityManager;

    public void add(Course course){
        this.entityManager.persist(course);
    }

    public Course getById(Integer id){
        return entityManager.find(Course.class, id);
    }

    public Course update(Course course){
        return entityManager.merge(course);
    }
}
