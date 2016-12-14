/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.JAXBException;
import studentsgroups.controller.utils.NotValidValueException;
import studentsgroups.model.Group;
import studentsgroups.model.Student;

/**
 *
 * @author Elvira
 */
public interface Controller extends Remote{
    
    public String getFacultyName() throws RemoteException;
    public void writeToFile(File file) throws IOException, RemoteException;
    public void writeToFile(String fileName) throws IOException, RemoteException;
    public void readFile(File file) throws IOException, ClassNotFoundException, RemoteException;
    public void readFile(String fileName) throws IOException, ClassNotFoundException, RemoteException;
    public Collection<Group> getGroupsByPattern(String pattern) throws RemoteException;
    public Group[] getGroupByPattern(String pattern) throws RemoteException;
    public Collection<Student> getStudentsByPattern(String pattern) throws RemoteException;
    public Student[] getStudentByPattern(Group group, String pattern) throws RemoteException;
    public void addStudent(Group group, Student student) throws RemoteException;
    public void addStudent(Group group, int id, String name, String surname, String patronymic, Date enrollmentDate) throws RemoteException;
    public void deleteStudent(Group group, Student exstudent) throws RemoteException;
    public Student getStudentById(Group group, int id) throws RemoteException;
    public Student[] getStudents(Group group) throws RemoteException;
    public void addGroup(Group group) throws RemoteException;
    public void deleteGroup(Group exgroup) throws RemoteException;
    public Group getGroup(String numberOfgroup) throws RemoteException;
    public Group[] getGroups() throws RemoteException;
    public boolean setStudent(Group group, int idStudent, String surname, String name, String patronymic, Date enrollmentDate) throws RemoteException;
    public boolean setGroupName(String oldName, String newName) throws NotValidValueException, RemoteException;
    public void writeToXML(File file) throws JAXBException, FileNotFoundException, RemoteException;
    public void readXML(File file) throws FileNotFoundException, JAXBException, RemoteException;
    public Student[] sortStudentsBySurname(Student[] studs);
    public Student[] sortStudentsById(Student[] studs);
    public Group[] sortGroupsByName(Group[] groups);
}
