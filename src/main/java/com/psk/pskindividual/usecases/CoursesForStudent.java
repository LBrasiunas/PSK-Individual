package com.psk.pskindividual.usecases;

import com.psk.pskindividual.entities.Course;
import com.psk.pskindividual.entities.Student;
import com.psk.pskindividual.entities.University;
import com.psk.pskindividual.interceptors.Log;
import com.psk.pskindividual.persistence.CoursesDAO;
import com.psk.pskindividual.persistence.StudentsDAO;
import com.psk.pskindividual.persistence.UniversitiesDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
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

    @Getter @Setter
    private List<Course> courses = new ArrayList<>();

    private Map<String, String> requestParameters;

    @PostConstruct
    public void init() {
        this.courses = coursesDAO.getAll();
        requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
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

    @Transactional
    @Log
    public String updateStudentNumber() {
        try{
            studentsDAO.update(this.student);
        }
        catch (OptimisticLockException exception) {
            System.out.println("OptimisticLockException occurred (1): " + exception.getMessage());
            return "/studentCourses.xhtml?faces-redirect=true&amp;universityId="
                    + requestParameters.get("universityId")
                    + "&amp;studentId=" + requestParameters.get("studentId")
                    + "&amp;error=optimistic-lock-exception";
        }
        return "/students.xhtml?universityId=" + this.student.getUniversity().getId() + "&faces-redirect=true";
    }

    @Transactional
    @Log
    public String updateWithOptLockEx() {
        try {
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/PSK/api/students/" + this.student.getId()))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString("{\n" +
                            "    \"name\": \"OptimisticName\",\n" +
                            "    \"surname\": \"LockSurname\",\n" +
                            "    \"studentNumber\": 1111111,\n" +
                            "    \"universityId\": 1\n" +
                            "}"))
                    .build();
            try {
                HttpResponse<?> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
                System.out.println("Response code: " + response.statusCode());
            }
            catch (Exception exception) {
                System.out.println("Exception occurred while sending the update request: " + exception.getMessage());
            }
            studentsDAO.update(this.student);
        }
        catch (OptimisticLockException exception) {
            System.out.println("OptimisticLockException occurred (2): " + exception.getMessage());
            return "/studentCourses.xhtml?faces-redirect=true&amp;universityId="
                    + requestParameters.get("universityId")
                    + "&amp;studentId=" + requestParameters.get("studentId")
                    + "&amp;error=optimistic-lock-exception";
        }
        catch (Exception exception){
            System.out.println("Normal exception caught: " + exception.getMessage());
        }
        return "/students.xhtml?universityId=" + this.student.getUniversity().getId() + "&faces-redirect=true";
    }
}
