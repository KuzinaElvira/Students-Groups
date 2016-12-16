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
import java.util.Arrays;
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
import studentsgroups.model.comparators.GroupNameComparator;
import studentsgroups.model.comparators.StudentIDComparator;
import studentsgroups.model.comparators.StudentSurnameComparator;
import studentsgroups.model.impl.FacultyImpl;
import studentsgroups.model.impl.GroupImpl;
import studentsgroups.model.impl.StudentImpl;

/**
 *
 * @author Elvira
 */
public class ControllerImpl implements Controller{
    
    private Faculty faculty;

    public ControllerImpl(Faculty faculty) {
        this.faculty = faculty;
    }  
    
    public synchronized Faculty getFaculty(){
        return faculty;
    }
    
    private synchronized void isValidIDs(Faculty fac) throws JAXBException{
        Collection<Integer> ids = new LinkedList<>();
        for(Group group : fac){
            for(Student student :  group){
                if(ids.contains(student.getIdStudent()))
                    throw new JAXBException("Файл содержит студентов с повторяющимися ID.");
                ids.add(student.getIdStudent());
            }
        }
    }
    
    /**
     * Проверка строк на наличие значения
     * @param str 
     */
    private synchronized void isValidString(String str){
        if(str == null || str.equals("")){
            throw new NotValidValueException();
        }
    }
    
    /**
     * Получить имя факультета
     * @return 
     */
    @Override
    public synchronized String getFacultyName(){
        return faculty.getFacultyName();
    }
    
    /**
     * Сериализация
     * @param file
     * @throws IOException 
     */
    @Override
    public synchronized void writeToFile(File file) throws IOException{
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
    @Override
    public synchronized void writeToFile(String fileName) throws IOException{
        writeToFile(new File(fileName));
    }
    /**
     * Десериализация
     * @param file
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @Override
    public synchronized void readFile(File file) throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Faculty fac =  (Faculty) ois.readObject();
        ois.close();
        try{
            isValidIDs(fac);
        }catch(JAXBException ex){
            throw new ClassNotFoundException();
        }
        faculty = fac;
    }
    
    /**
     * Десериализация
     * @param fileName
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @Override
    public synchronized void readFile(String fileName) throws IOException, ClassNotFoundException{
        readFile(new File(fileName));
    }
    
    /**
     * Поиск данных в соответствии с шаблоном
     * @param pattern
     * @return 
     */
    @Override
    public synchronized Collection<Group> getGroupsByPattern(String pattern){
        Collection<Group> groupsByPattern = new LinkedList<>();
        CheckMatching checker = new CheckMatching(pattern);
        for(Group group : faculty){
            if(checker.doMatch(pattern, group.getNumberOfGroup())){
                groupsByPattern.add(group);
            }
        }
        return groupsByPattern;
    }
    
    /**
     * Поиск групп в соответствии с шаблоном
     * @param pattern
     * @return 
     */
    @Override
    public synchronized Group[] getGroupByPattern(String pattern){
        Collection<Group> groups = getGroupsByPattern(pattern);
        Group[] groupss = new Group[groups.size()];        
        return groups.toArray(groupss);
    }
    
    /**
     * Поиск студентов в соответствии с шаблоном
     * @param pattern
     * @return 
     */
    @Override
    public synchronized Collection<Student> getStudentsByPattern(String pattern){
        Collection<Student> studentsByPattern = new LinkedList<>();
        CheckMatching checker = new CheckMatching(pattern);
        for(Group group : faculty){            
            for(Student student : group){
                if(checker.doMatch(pattern, Integer.toString(student.getIdStudent()), student.getSurname(), student.getName(), student.getPatronymic())){
                studentsByPattern.add(student);
                }
            }
        }
        return studentsByPattern;
    }
    
     /**
     * Поиск студентов в соответствии с шаблоном
     * @param group
     * @param pattern
     * @return 
     */
    @Override
    public synchronized Student[] getStudentByPattern(Group group, String pattern) {
        Collection<Student> studentsByPattern = new LinkedList<>();
        CheckMatching checker = new CheckMatching(pattern);
        for (Student student : group) {
            if(checker.doMatch(pattern, student.getSurname(), student.getName(), student.getPatronymic())){
                studentsByPattern.add(student);
                }
        }
        Student[] studs = new Student[studentsByPattern.size()];
        return studentsByPattern.toArray(studs);
    }   
    
    /**
     * Добавление студента в группу
     * @param group
     * @param student 
     */
    @Override
    public synchronized void addStudent(Group group, Student student) {
        for (Group grp : faculty) {
            for (Student stud : grp) {
                if (stud.getIdStudent() == student.getIdStudent()) {
                    throw new ObjectExistsException();
                }
            }
        }
        group.addStudent(student);
    }

    /**
     * Добавление студента в группу
     * @param group
     * @param id
     * @param name
     * @param surname
     * @param patronymic
     * @param enrollmentDate 
     */
    @Override
    public synchronized void addStudent(Group group, int id, String name, String surname, String patronymic, Date enrollmentDate){
        Student newStudent = new StudentImpl(id, surname, name, patronymic, enrollmentDate);
        addStudent(group, newStudent);
    }
    
    /**
     * Удаление студента из группы
     * @param group
     * @param exstudent 
     */
    @Override
    public synchronized void deleteStudent(Group group, Student exstudent){
        group.deleteStudent(exstudent);
    }
    
    /**
     * Получение студента по его ID
     * @param group
     * @param id
     * @return 
     */
    @Override
    public synchronized Student getStudentById(Group group, int id){
        for (Student stud : group) {
            if (stud.getIdStudent() == id) {
                return stud;
            }
        }
        throw new ObjectNotFoundException();
    }
    
    /**
     * Просмотр группы
     * @param group
     * @return 
     */
    @Override
    public synchronized Student[] getStudents(Group group){
        return group.getStudents();
    }
    
    /**
     * Добавление группы
     * @param group 
     */
    @Override
    public synchronized void addGroup(Group group){
        for(Group groupp : faculty){
            if(group.getNumberOfGroup().equals(groupp.getNumberOfGroup())){
                        throw new ObjectExistsException();
                    }
        }
        faculty.addGroup(group);
    }
    
    /**
     * Удаление группы
     * @param exgroup 
     */
    @Override
    public synchronized void deleteGroup(Group exgroup){
        faculty.deleteGroup(exgroup);
    }
    
    /**
     * Поиск группы по ее номеру
     * @param numberOfgroup
     * @return 
     */
    @Override
    public synchronized Group getGroup(String numberOfgroup){
        for(Group group : faculty){
            if(group.getNumberOfGroup().equals(numberOfgroup)){
                return group;
            }
        }
        return new GroupImpl(numberOfgroup);
    }
    
    /**
     * Просмотр групп факультета
     * @return 
     */
    @Override
    public synchronized Group[] getGroups(){
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
    @Override
    public synchronized boolean setStudent(Group group, int idStudent, String surname, String name, String patronymic, Date enrollmentDate) {
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
        throw new ObjectNotFoundException();
    }
    
    /**
     * Изменение имени группы
     * @param oldName
     * @param newName
     * @return
     * @throws NotValidValueException 
     */
    @Override
    public synchronized boolean setGroupName(String oldName, String newName) throws NotValidValueException{
        isValidString(newName);
        for(Group group : faculty){
            if(group.getNumberOfGroup().equals(oldName)){
                for(Group groupp : faculty){
                    if(groupp.getNumberOfGroup().equals(newName)){
                        throw new ObjectExistsException();
                    }
                }
                group.setNumberOfGroup(newName);
                return true;
            }
        }
        throw new ObjectNotFoundException();
    }
    
    /**
     * Запись в xml файл.
     * @param file
     * @throws JAXBException
     * @throws FileNotFoundException 
     */
    @Override
    public synchronized void writeToXML(File file) throws JAXBException, FileNotFoundException{
        JAXBContext context = JAXBContext.newInstance(FacultyImpl.class);
        Marshaller marshaller = context.createMarshaller();
        FileOutputStream fos = new FileOutputStream(file);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(faculty, fos);
    }
    
    /**
     * Чтение из xml файла.
     * @param file
     * @throws FileNotFoundException
     * @throws JAXBException 
     */
    @Override
    public synchronized void readXML(File file) throws FileNotFoundException, JAXBException{
        JAXBContext context = JAXBContext.newInstance(FacultyImpl.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        FileInputStream fis = new FileInputStream(file);
        Faculty facultyFromXML = (Faculty) unmarshaller.unmarshal(fis);
        isValidIDs(facultyFromXML);
        faculty = facultyFromXML;
    }
    
    /**
     * Сортировка студентов по Фамилии
     * @param studs
     * @return 
     */
    @Override
    public synchronized Student[] sortStudentsBySurname(Student[] studs){
        StudentSurnameComparator surnameComp = new StudentSurnameComparator();
        Arrays.sort(studs, surnameComp);
        return studs;
    }
    
    /**
     * Сортировка студентов по ID
     * @param studs
     * @return 
     */
    @Override
    public synchronized Student[] sortStudentsById(Student[] studs){
        StudentIDComparator idComp = new StudentIDComparator();
        Arrays.sort(studs, idComp);
        return studs;
    }
    
    /**
     * Сортировка групп по имени(номеру) группы
     * @param groups
     * @return 
     */
    @Override
    public synchronized Group[] sortGroupsByName(Group[] groups){
        GroupNameComparator groupComp = new GroupNameComparator();
        Arrays.sort(groups, groupComp);
        return groups;
    }
}