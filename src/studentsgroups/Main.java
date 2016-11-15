package studentsgroups;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import studentsgroups.controller.Controller;
import studentsgroups.model.Faculty;
import studentsgroups.model.Group;
import studentsgroups.model.Student;
import studentsgroups.model.impl.FacultyImpl;
import studentsgroups.model.impl.GroupImpl;
import studentsgroups.model.impl.StudentImpl;

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
        /*
        String pathForReading = "C:\\Users\\Elvira\\Documents\\NetBeansProjects\\Students-Group\\src\\testFac.xml";
        String pathForWriting = pathForReading;
        File fileForWriting = new File(pathForWriting);
        File fileForReading = new File(pathForReading);
        Faculty fac = new FacultyImpl("FIST");
        Group group = new GroupImpl("TP-666");
        Student student = new StudentImpl(1, "Ivanova", "Yana", "Alexandrovna", Date.from(Instant.now()));
        group.addStudent(student);
        fac.addGroup(group);
        Controller contr = new Controller(fac);
        contr.writeToXML(fileForWriting);
        */
    }
    
}