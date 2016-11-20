/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model.impl;

import studentsgroups.model.Faculty;
import studentsgroups.model.Group;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Elvira
 */
@XmlRootElement
@XmlType(name = "faculty", propOrder = {"facultyName", "groups"})
public class FacultyImpl implements Faculty{
        
    private String facultyName;
    @XmlElementWrapper(name = "groups")
    @XmlElements({
        @XmlElement(type = GroupImpl.class, name = "group")
    })
    private Collection<Group> groups;
    
    public FacultyImpl(){
        
    }
    
    public FacultyImpl(String facultyName){
        this.facultyName = facultyName;
        groups = new ArrayList<>();
    }
    
    @Override
    public int getSizeOfFac() {
        return groups.size();
    }

    @Override
    @XmlAttribute
    public String getFacultyName() {
        return facultyName;
    }

    @Override
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public void addGroup(Group group) {
        groups.add(group);
    }

    @Override
    public void deleteGroup(Group exgroup) {
        groups.remove(exgroup);
    }

    @Override
    public Group[] getGroups() {
        Group[] groupsArray = new Group[groups.size()];
        groupsArray = groups.toArray(groupsArray);
        return groupsArray;
    }    
    
    @Override
    public Iterator<Group> iterator() {
        return groups.iterator();
    }
}
