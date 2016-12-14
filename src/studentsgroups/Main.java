package studentsgroups;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import studentsgroups.controller.ControllerImpl;
import studentsgroups.model.Faculty;
import studentsgroups.model.Group;
import studentsgroups.model.Student;
import studentsgroups.model.impl.FacultyImpl;
import studentsgroups.model.impl.GroupImpl;
import studentsgroups.model.impl.StudentImpl;
import studentsgroups.view.MainForm;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Эльвира
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException, JAXBException {
        
        
//        String pathForReading = "C:\\Users\\Elvira\\Documents\\NetBeansProjects\\Students-Groups\\src\\testFac.xml";
//        String pathForWriting = pathForReading;
//        File fileForWriting = new File(pathForWriting);
//        File fileForReading = new File(pathForReading);
//        Faculty fac = new FacultyImpl("FIST");
//        Group group = new GroupImpl("TP-666");
//        Student student = new StudentImpl(0, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
//        Student student1 = new StudentImpl(1, "Ivanova2", "Yana", "Alexandrovna", Date.from(Instant.now()));
//        Student student2 = new StudentImpl(2, "Ulyanova", "Christina", "Eduardovna", Date.from(Instant.now()));
//        Student student3 = new StudentImpl(3, "Ulyanova2", "Christina", "Eduardovna", Date.from(Instant.now()));
//        group.addStudent(student);
//        group.addStudent(student1);
//        group.addStudent(student2);
//        group.addStudent(student3);
//        fac.addGroup(group);
//        Group group2 = new GroupImpl("Agents-007");
////        Student student2 = new StudentImpl(2, "Ulyanova", "Christina", "Eduardovna", Date.from(Instant.now()));
////        Student student3 = new StudentImpl(3, "Ulyanova2", "Christina", "Eduardovna", Date.from(Instant.now()));
//        group2.addStudent(student2);
//        group2.addStudent(student3);
//        fac.addGroup(group2);
//        Controller contr = new Controller(fac);
//        Group[] groupsByPattern = contr.getGroupByPattern("6");
//        for(Group groupPat : groupsByPattern){
//            System.out.println(groupPat.getNumberOfGroup());
//        }
//        Student[] studsByPattern = contr.getStudentByPattern(group, "va");
//        for(Student studPat : studsByPattern){
//            System.out.println(studPat.getIdStudent() + studPat.getSurname());
//        }
//        contr.writeToXML(fileForWriting);
        

        //region filling
//        Faculty fac = new FacultyImpl("FIST");

        /*Group group = new GroupImpl("TP-666");
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

        group = new GroupImpl("OPC-776");
        student = new StudentImpl(9, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(10, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(11, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(12, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(13, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(14, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(15, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(16, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);

        group = new GroupImpl("BF-687");
        student = new StudentImpl(17, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(18, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(19, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(20, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(21, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(22, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(23, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(24, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);

        group = new GroupImpl("TF-546");
        student = new StudentImpl(25, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(26, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(27, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(28, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(29, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(30, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(31, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(32, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);

        group = new GroupImpl("SD-666");
        student = new StudentImpl(33, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);

        group = new GroupImpl("TP-666");
        student = new StudentImpl(34, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(35, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(36, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(37, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(38, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(39, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(40, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(41, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);

        group = new GroupImpl("TBG-666");
        student = new StudentImpl(42, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(43, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(44, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(45, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(46, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(47, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(48, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(49, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);

        group = new GroupImpl("KJ-666");
        student = new StudentImpl(50, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(51, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(52, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(53, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(54, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(55, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(56, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(57, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);

        group = new GroupImpl("XCV-666");
        student = new StudentImpl(58, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(59, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(60, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(61, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(62, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(63, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(64, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        student = new StudentImpl(65, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);*/
        //endregion

        Faculty fac = new FacultyImpl(" ");

        ControllerImpl controller = new ControllerImpl(fac);
        new MainForm(controller);

    }
    
}