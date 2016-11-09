/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentsGroups;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Эльвира
 */
public class GroupImpl implements Group{
    
    String numberOfGroup;
    String faculty;
    Collection<Student> students;

    public GroupImpl(String numberOfGroup, String faculty) {
        this.numberOfGroup = numberOfGroup;
        this.faculty = faculty;
        students = new HashSet<>();
    }   
    
    public int getSizeOfGroup() {
        return students.size();
    }

    @Override
    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    @Override
    public String getFaculty() {
        return faculty;
    }

    @Override
    public Collection<Student> getStudents() {
        return students;
    }

    @Override
    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    @Override
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public void setStudents(Collection<Student> students) {
        this.students = students;
    }    
    
    @Override
     public void addStudent(Student newStudent){
        students.add(newStudent);
    }
    
    @Override
    public void deleteStudent(Student exstudent){
        students.remove(exstudent);
    }

    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }
}