/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model;

import java.io.Serializable;
import java.util.Comparator;

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
