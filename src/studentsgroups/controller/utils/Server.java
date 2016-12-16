/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller.utils;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.Date;
import studentsgroups.controller.ControllerImpl;
import studentsgroups.model.Faculty;
import studentsgroups.model.Group;
import studentsgroups.model.Student;
import studentsgroups.model.impl.FacultyImpl;
import studentsgroups.model.impl.GroupImpl;
import studentsgroups.model.impl.StudentImpl;

/**
 *
 * @author Elvira
 */
public class Server {
 
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Faculty fac = new FacultyImpl("FIST");

        Group group = new GroupImpl("TP-666");
        Student student = new StudentImpl(1, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(2, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(3, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(4, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(5, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(6, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(7, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(8, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);
        RMIUtils.createServer(1099, new ControllerImpl(fac));
    }
    
}
