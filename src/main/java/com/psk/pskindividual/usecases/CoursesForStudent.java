package com.psk.pskindividual.usecases;

import com.psk.pskindividual.entities.Course;
import com.psk.pskindividual.entities.Student;
import com.psk.pskindividual.entities.University;
import com.psk.pskindividual.persistence.CoursesDAO;
import com.psk.pskindividual.persistence.StudentsDAO;
import com.psk.pskindividual.persistence.UniversitiesDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Model
public class CoursesForStudent implements Serializable {

    @Inject
    private CoursesDAO coursesDAO;

    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private UniversitiesDAO universitiesDAO;

    @Getter @Setter
    private Integer courseId;

    @Getter @Setter
    private University university;

    @Getter @Setter
    private Student student;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer universityId = Integer.parseInt(requestParameters.get("universityId"));
        this.university = universitiesDAO.getById(universityId);
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentsDAO.getById(studentId);
    }

    @Transactional
    public void assignCourse(){
        Course course = coursesDAO.getById(this.courseId);
        course.getStudents().add(this.student);
        coursesDAO.update(course);
        student.getCourses().add(course);
        studentsDAO.update(student);
    }
}
