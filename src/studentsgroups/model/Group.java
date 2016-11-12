/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model;

import studentsgroups.model.Student;
import java.io.Serializable;
import studentsgroups.model.Student;

/**
 *
 * @author Elvira
 */
public interface Group extends Iterable<Student>, Serializable{
    
    public int getSizeOfGroup();
    public String getNumberOfGroup();
    public void setNumberOfGroup(String numberOfGroup);
    public void addStudent(Student newStudent);
    public void deleteStudent(Student exstudent);
    public Student[] getStudents();
    
}
