package com.psk.pskindividual.usecases;

import com.psk.pskindividual.entities.Student;
import com.psk.pskindividual.entities.University;
import com.psk.pskindividual.persistence.StudentsDAO;
import com.psk.pskindividual.persistence.UniversitiesDAO;
import lombok.Getter;
import lombok.Setter;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class StudentsInUniversity implements Serializable {

    @Inject
    private UniversitiesDAO universitiesDAO;

    @Inject
    private StudentsDAO studentsDAO;

    @Getter @Setter
    private University university;

    @Getter @Setter
    private Student studentCreate = new Student();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer universityId = Integer.parseInt(requestParameters.get("universityId"));
        this.university = universitiesDAO.getById(universityId);
    }

    @Transactional
    public void createStudent() {
        studentCreate.setUniversity(this.university);
        studentsDAO.add(studentCreate);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        studentsDAO.delete(id);
    }
}
