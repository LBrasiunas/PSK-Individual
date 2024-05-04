package com.psk.pskindividual.api.controllers;

import com.psk.pskindividual.DTOs.StudentRequest;
import com.psk.pskindividual.DTOs.StudentResponse;
import com.psk.pskindividual.entities.Student;
import com.psk.pskindividual.persistence.StudentsDAO;
import com.psk.pskindividual.persistence.UniversitiesDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/students")
public class StudentsController {
    @Inject
    private StudentsDAO studentsDAO;

    @Inject
    private UniversitiesDAO universitiesDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Student student = studentsDAO.getById(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setStudentNumber(student.getStudentNumber());
        studentResponse.setName(student.getName());
        studentResponse.setSurname(student.getSurname());
        studentResponse.setUniversityId(student.getUniversity().getId());

        return Response.ok(studentResponse).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(StudentRequest studentRequest) {
        Student studentToCreate = new Student();
        try {
            studentToCreate.setStudentNumber(studentRequest.getStudentNumber());
            studentToCreate.setName(studentRequest.getName());
            studentToCreate.setSurname(studentRequest.getSurname());
            studentToCreate.setUniversity(universitiesDAO.getById(studentRequest.getUniversityId()));
            studentsDAO.add(studentToCreate);
            return Response.ok().build();
        }
        catch (Exception exception) {
            System.out.println("AN ERROR OCCURRED: " + exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(@PathParam("id") final Integer studentId, StudentRequest studentRequest) {
        try {
            Student studentToUpdate = studentsDAO.getById(studentId);
            if (studentToUpdate == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            studentToUpdate.setStudentNumber(studentRequest.getStudentNumber());
            studentToUpdate.setName(studentRequest.getName());
            studentToUpdate.setSurname(studentRequest.getSurname());
            studentToUpdate.setUniversity(universitiesDAO.getById(studentRequest.getUniversityId()));
            studentsDAO.update(studentToUpdate);
            return Response.ok().build();
        }
        catch (Exception exception) {
            System.out.println("AN ERROR OCCURRED: " + exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
