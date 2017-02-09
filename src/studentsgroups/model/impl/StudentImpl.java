/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model.impl;

import studentsgroups.model.Student;
import java.util.Date;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Эльвира
 */
@XmlType(propOrder = {"idStudent", "surname", "name", "patronymic", "enrollmentDate"})
public class StudentImpl implements Student{
    
    private int idStudent;
    private String surname;
    private String name;
    private String patronymic;
    private Date enrollmentDate;

    public StudentImpl(){
        
    }
    
    public StudentImpl(int idStudent, String surname, String name, String patronymic, Date enrollmentDate) {
        this.idStudent = idStudent;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.enrollmentDate = enrollmentDate;
    }    

    @Override
    @XmlAttribute(name = "id")
    public int getIdStudent() {
        return idStudent;
    }

    private void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    } 
    
    @Override
    @XmlAttribute
    public String getSurname() {
        return surname;
    }

    @Override
    @XmlAttribute
    public String getName() {
        return name;
    }

    @Override
    @XmlAttribute
    public String getPatronymic() {
        return patronymic;
    }

    @Override
    @XmlAttribute
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

    @Override
    public String toString() {
        return "StudentImpl{" + "idStudent=" + idStudent + ", surname=" + surname + ", name=" + name + ", patronymic=" + patronymic + ", enrollmentDate=" + enrollmentDate + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idStudent;
        hash = 41 * hash + Objects.hashCode(this.surname);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.patronymic);
        hash = 41 * hash + Objects.hashCode(this.enrollmentDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentImpl other = (StudentImpl) obj;
        if (this.idStudent != other.idStudent) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.patronymic, other.patronymic)) {
            return false;
        }
        if (!Objects.equals(this.enrollmentDate, other.enrollmentDate)) {
            return false;
        }
        return true;
    }
    
    
    
}
