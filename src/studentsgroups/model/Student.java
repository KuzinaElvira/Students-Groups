/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Elvira
 */
public interface Student extends Serializable{
    
    public String getSurname();
    public String getName();
    public String getPatronymic();
    public Date getEnrollmentDate();
    public void setSurname(String surname);
    public void setName(String name);
    public void setPatronymic(String patronymic);
    public void setEnrollmentDate(Date enrollmentDate);
}
