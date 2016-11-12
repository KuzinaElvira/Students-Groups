/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller;

import studentsgroups.model.Faculty;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.LinkedList;
import studentsgroups.controller.utils.CheckMatching;
import studentsgroups.model.Group;
import studentsgroups.model.Student;

/**
 *
 * @author Elvira
 */
public class Controller {
    
    private Faculty faculty;

    public Controller(Faculty faculty) {
        this.faculty = faculty;
    }
    
    /**
     * Сериализация
     * @param file
     * @param faculty
     * @throws IOException 
     */
    public static void writeToFile(File file, Faculty faculty) throws IOException{
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(faculty);
        oos.close();
    }
    /**
     * Сериализация
     * @param fileName
     * @param faculty
     * @throws IOException 
     */
    public static void writeToFile(String fileName, Faculty faculty) throws IOException{
        writeToFile(new File(fileName), faculty);
    }
    /**
     * Десериализация
     * @param file
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static Faculty readFromFile(File file) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Faculty faculty =  (Faculty) ois.readObject();
        ois.close();
        return faculty;
    }
    
    /**
     * Десериализация
     * @param fileName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static Faculty readFromFile(String fileName) throws IOException, ClassNotFoundException{
        return readFromFile(new File(fileName));
    }

    public Faculty getFaculty() {
        return faculty;
    }
    
    public Collection<Group> getGroupsByPattern(String pattern){
        Collection<Group> groupsByPattern = new LinkedList<>();
        CheckMatching checker = new CheckMatching(pattern);
        for(Group group : faculty){
            if(checker.isMatches(group.getNumberOfGroup())){
                groupsByPattern.add(group);
            }
        }
        return groupsByPattern;
    }
    
    public Collection<Student> getStudentsByPattern(String pattern){
        Collection<Student> studentsByPattern = new LinkedList<>();
        CheckMatching checker = new CheckMatching(pattern);
        for(Group group : faculty){            
            for(Student student : group){
                if(checker.isMatches(student.getSurname()) || checker.isMatches(student.getName()) || checker.isMatches(student.getPatronymic())){
                    studentsByPattern.add(student);
                }
            }
        }
        return studentsByPattern;
    } 
    
    public void addStudent(Group group, Student student){
        group.addStudent(student);
    }
    
    
}
