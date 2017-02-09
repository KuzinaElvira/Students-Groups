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
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Эльвира
 */
@XmlType(name = "group", propOrder = {"numberOfGroup", "students"})
public class GroupImpl implements Group{
    
    private String numberOfGroup;
    @XmlElementWrapper(name = "students")
    @XmlElements({
        @XmlElement(type = StudentImpl.class, name = "student")
    })
    private Collection<Student> students;

    public GroupImpl(){
        
    }
    
    public GroupImpl(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
        students = new ArrayList<>();
    }   
    
    @Override
    public int getSizeOfGroup() {
        return students.size();
    }

    @Override
    @XmlAttribute
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

    @Override
    public String toString() {
        return "GroupImpl{" + "numberOfGroup=" + numberOfGroup + ", students=" + students.toString() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.numberOfGroup);
        hash = 23 * hash + Objects.hashCode(this.students);
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
        final GroupImpl other = (GroupImpl) obj;
        if (!Objects.equals(this.numberOfGroup, other.numberOfGroup)) {
            return false;
        }
        if (!Objects.equals(this.students, other.students)) {
            return false;
        }
        return true;
    }
    
    
}