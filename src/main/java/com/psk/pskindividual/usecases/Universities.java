package com.psk.pskindividual.usecases;

import com.psk.pskindividual.entities.University;
import com.psk.pskindividual.persistence.UniversitiesDAO;
import lombok.Getter;
import lombok.Setter;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

@Model
public class Universities implements Serializable {

    @Inject
    private UniversitiesDAO universitiesDAO;

    @Getter @Setter
    private University universityCreate = new University();

    @Getter
    private List<University> universities;

    @PostConstruct
    public void init(){
        loadAllUniversities();
    }

    @Transactional
    public void createUniversity(){
        this.universitiesDAO.add(universityCreate);
    }

    private void loadAllUniversities(){
        this.universities = universitiesDAO.getAll();
    }
}
