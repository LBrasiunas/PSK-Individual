package com.psk.pskindividual.usecases;

import com.psk.pskindividual.entities.Course;
import com.psk.pskindividual.persistence.CoursesDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Model
public class Courses implements Serializable {

    @Inject
    private CoursesDAO coursesDAO;

    @Getter @Setter
    private Course courseCreate = new Course();

    @Getter
    private List<Course> courses;

    @PostConstruct
    public void init() {
        loadAllCourses();
    }

    @Transactional
    public void createCourse(){
        this.coursesDAO.add(courseCreate);
    }

    private void loadAllCourses(){
        this.courses = coursesDAO.getAll();
    }
}
