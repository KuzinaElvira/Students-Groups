/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model;

import java.io.Serializable;

/**
 *
 * @author Elvira
 */
public interface Faculty  extends Iterable<Group>, Serializable{
    
    public int getSizeOfFac();
    public String getFacultyName();
    public void setFacultyName(String facultyName);
    public void addGroup(Group group);
    public void deleteGroup(Group exgroup);
    public Group[] getGroups();   
    
}
