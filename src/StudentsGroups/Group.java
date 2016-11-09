/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentsGroups;

import java.util.Collection;

/**
 *
 * @author Elvira
 */
public interface Group extends Iterable<Student>{
    
    public String getNumberOfGroup();
    public String getFaculty();
    public Collection<Student> getStudents();
    public void setNumberOfGroup(String numberOfGroup);
    public void setFaculty(String faculty);
    public void setStudents(Collection<Student> students);
    public void addStudent(Student newStudent);
    public void deleteStudent(Student exstudent);
}
