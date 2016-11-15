/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model.impl;

import studentsgroups.model.Student;
import java.util.Date;

/**
 *
 * @author Эльвира
 */
public class StudentImpl implements Student{
    
    private final int idStudent;
    private String surname;
    private String name;
    private String patronymic;
    private Date enrollmentDate;

    public StudentImpl(int idStudent, String surname, String name, String patronymic, Date enrollmentDate) {
        this.idStudent = idStudent;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.enrollmentDate = enrollmentDate;
    }    

    @Override
    public int getIdStudent() {
        return idStudent;
    }
    
    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
