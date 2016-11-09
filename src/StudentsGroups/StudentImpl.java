/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentsGroups;

import java.util.Date;

/**
 *
 * @author Эльвира
 */
public class StudentImpl implements Student{
    
    String surname;
    String name;
    String patronymic;
    Group group;
    Date enrollmentDate;

    public StudentImpl(String surname, String name, String patronymic, Group group, Date enrollmentDate) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.group = group;
        this.enrollmentDate = enrollmentDate;
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
    public Group getGroup() {
        return group;
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
    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
