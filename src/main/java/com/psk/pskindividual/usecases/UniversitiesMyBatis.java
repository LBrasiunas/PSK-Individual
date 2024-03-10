package com.psk.pskindividual.usecases;

import com.psk.pskindividual.mybatis.model.University;
import com.psk.pskindividual.mybatis.dao.UniversityMapper;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Model
public class UniversitiesMyBatis {

    @Inject
    private UniversityMapper universityMapper;

    @Getter
    private List<University> universities;

    @Getter @Setter
    private University universityCreate = new University();

    @PostConstruct
    public void init() {
        this.loadAllUniversities();
    }

    private void loadAllUniversities() {
        this.universities = universityMapper.selectAll();
    }

    @Transactional
    public String createUniversity() {
        universityMapper.insert(universityCreate);
        return "/mybatis/universities?faces-redirect=true";
    }
}
