package com.psk.pskindividual.persistence;

import com.psk.pskindividual.entities.University;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Setter;

import java.util.List;

@ApplicationScoped
@Setter
public class UniversitiesDAO {

    @Inject
    private EntityManager entityManager;

    public List<University> getAll(){
        return entityManager.createNamedQuery("University.getAll", University.class).getResultList();
    }

    public void add(University university){
        this.entityManager.persist(university);
    }

    public University getById(Integer id) { return entityManager.find(University.class, id); }
}
