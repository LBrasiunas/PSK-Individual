package com.psk.pskindividual.usecases;

import com.psk.pskindividual.entities.Student;
import com.psk.pskindividual.interceptors.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OptLockService {

    @Inject
    private EntityManager entityManager;

    @Transactional
    @Log
    public void updateStudentNumber(Integer studentId, Long studentNumber) {
        Student student = entityManager.find(Student.class, studentId);
        student.setStudentNumber(studentNumber);

        try{
            entityManager.merge(student);
            entityManager.flush();
        }
        catch (OptimisticLockException exception) {
            System.out.println("OptimisticLockException occurred: " + exception.getMessage());
            throw exception;
        }
    }

    // In order to throw an OptLockException firstly call this method and then the regular update method without sleep
    @Transactional
    @Log
    public void updateStudentNumberWithOptLockEx(Integer studentId, Long studentNumber) throws InterruptedException {
        Student student = entityManager.find(Student.class, studentId);
        student.setStudentNumber(studentNumber);

        Thread.sleep(6000);

        try{
            entityManager.merge(student);
            entityManager.flush();
        }
        catch (OptimisticLockException exception) {
            System.out.println("OptimisticLockException occurred: " + exception.getMessage());
            throw exception;
        }
    }
}
