/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model;

import studentsgroups.model.Group;
import java.io.Serializable;
import java.util.Date;
import studentsgroups.model.Group;

/**
 *
 * @author Elvira
 */
public interface Student extends Serializable{
    
    public String getSurname();
    public String getName();
    public String getPatronymic();
    public Group getGroup();
    public Date getEnrollmentDate();
    public void setSurname(String surname);
    public void setName(String name);
    public void setPatronymic(String patronymic);
    public void setGroup(Group group);
    public void setEnrollmentDate(Date enrollmentDate);
}
