/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model.impl;

import studentsgroups.model.Group;
import studentsgroups.model.Student;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Эльвира
 */
public class GroupImpl implements Group{
    
    private String numberOfGroup;
    private Collection<Student> students;

    public GroupImpl(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
        students = new ArrayList<>();
    }   
    
    @Override
    public int getSizeOfGroup() {
        return students.size();
    }

    @Override
    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    @Override
    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
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
    public Student[] getStudents() {
        Student[] studentsArray = new Student[students.size()];
        studentsArray = students.toArray(studentsArray);
        return studentsArray;
    }    
    
    @Override
    public Iterator<Student> iterator() {
        return students.iterator();
    }
}