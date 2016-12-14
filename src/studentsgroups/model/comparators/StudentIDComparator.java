/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model.comparators;

import java.util.Comparator;
import studentsgroups.model.Student;

/**
 *
 * @author Elvira
 */
public class StudentIDComparator implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getIdStudent() - o2.getIdStudent();
    }
    
}
