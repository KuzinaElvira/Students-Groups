/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller;

import studentsgroups.model.Faculty;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import studentsgroups.controller.utils.*;
import studentsgroups.model.Group;
import studentsgroups.model.Student;
import studentsgroups.model.impl.FacultyImpl;
import studentsgroups.model.impl.StudentImpl;

/**
 *
 * @author Elvira
 */
public class Controller {
    
    private Faculty faculty;

    public Controller(Faculty faculty) {
        this.faculty = faculty;
    }    
    
    private void isValidString(String str){
        if(str == null || str.equals("")){
            throw new NotValidValueException("Введенное значение некорректно.");
        }
    }
    
    /**
     * Сериализация
     * @param file
     * @throws IOException 
     */
    public void writeToFile(File file) throws IOException{
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(faculty);
        oos.close();
    }
    
    /**
     * Сериализация
     * @param fileName
     * @throws IOException 
     */
    public void writeToFile(String fileName) throws IOException{
        writeToFile(new File(fileName));
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
    
    /**
     * Поиск данных в соответствии с шаблоном
     * @param pattern
     * @return 
     */
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
    
    /**
     * Поиск данных в соответствии с шаблоном
     * @param pattern
     * @return 
     */
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
    
    /**
     * Добавление студента в группу
     * @param group
     * @param student 
     */
    public void addStudent(Group group, Student student){
        group.addStudent(student);
    }
    
    /**
     * Удаление студента из группы
     * @param group
     * @param exstudent 
     */
    public void deleteStudent(Group group, Student exstudent){
        group.deleteStudent(exstudent);
    }
    
    /**
     * Просмотр группы
     * @param group
     * @return 
     */
    public Student[] getStudents(Group group){
        return group.getStudents();
    }
    
    /**
     * Добавление группы
     * @param group 
     */
    public void addGroup(Group group){
        faculty.addGroup(group);
    }
    
    /**
     * Удаление группы
     * @param exgroup 
     */
    public void deleteGroup(Group exgroup){
        faculty.deleteGroup(exgroup);
    }
    
    /**
     * Просмотр групп факультета
     * @return 
     */
    public Group[] getGroups(){
        return faculty.getGroups();
    }
    
    /**
     * Изменение студента
     * @param group
     * @param idStudent
     * @param surname
     * @param name
     * @param patronymic
     * @param enrollmentDate 
     * @return  
     */
    public boolean setStudent(Group group, int idStudent, String surname, String name, String patronymic, Date enrollmentDate) {
        isValidString(surname);
        isValidString(name);
        isValidString(patronymic);
        for (Student student : group) {
            if (student.getIdStudent() == idStudent) {
                student.setSurname(surname);
                student.setName(name);
                student.setPatronymic(patronymic);
                student.setEnrollmentDate(enrollmentDate);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Запись в xml файл. ПОКА НЕ РАБОТАЕТ
     * @param file
     * @throws JAXBException
     * @throws FileNotFoundException 
     */
    public void writeToXML(File file) throws JAXBException, FileNotFoundException{
        JAXBContext context = JAXBContext.newInstance(FacultyImpl.class);
        Marshaller marshaller = context.createMarshaller();
        FileOutputStream fos = new FileOutputStream(file);
        marshaller.marshal(faculty, fos);
    }
    
    /**
     * Чтение из xml файла. ПОКА НЕ РАБОТАЕТ
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws JAXBException 
     */
    public Faculty readFromXML(File file) throws FileNotFoundException, JAXBException{
        JAXBContext context = JAXBContext.newInstance(FacultyImpl.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        FileInputStream fis = new FileInputStream(file);
        return (Faculty) unmarshaller.unmarshal(fis);
    }

    //region Need to realize
    //желательно чтобы если группа не найдена выбрасывал новую группу с пустым именем
    public Group getGroup(String numberOfgroup){
        for(Group group : faculty.getGroups()){
            if(group.getNumberOfGroup().equals(numberOfgroup))
                return group;
        }
        return null;
    }
    //просто измени сигнатуру readToFile и readFromXML метода ну и еще чтобы он овый объект считывал в себя
    public void readFile(File file) throws IOException, ClassNotFoundException{readFromFile(file);}
    public void readXML(File file) throws FileNotFoundException, JAXBException{readFromXML(file);}
    public boolean setGroupName(String oldName, String newName) throws NotValidValueException{getGroup(oldName).setNumberOfGroup(newName); return true;};
    //по сути те же методы что уже есть только возвращают массивы а не коллекции
    public Student[] getStudentByPattern(Group group, String pattern){
        Collection<Student> ret = getStudentsByPattern(pattern);
        Student[] result = new Student[ret.size()];
        return ret.toArray(result);
    }
    public Group[] getGroupByPattern(String pattern){
        Collection<Group> ret = getGroupsByPattern(pattern);
        Group[] result = new Group[ret.size()];
        return ret.toArray(result);
    }
    public Student getStudentById(Group group, int id){
        for(Student stud : group){
            if(stud.getIdStudent() == id)
                return stud;
        }
        return new StudentImpl(0, "", "", "", Calendar.getInstance().getTime());
    };
    public void addStudent(Group group, int id, String name, String surname, String patronymic, Date enrollmentDate){
        addStudent(group, new StudentImpl(id, name, surname, patronymic, enrollmentDate));
    }
    //endregion
}