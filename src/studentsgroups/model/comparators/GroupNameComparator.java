/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.model.comparators;

import java.util.Comparator;
import studentsgroups.model.Group;

/**
 *
 * @author Elvira
 */
public class GroupNameComparator implements Comparator<Group>{

    @Override
    public int compare(Group o1, Group o2) {
        return o1.getNumberOfGroup().compareTo(o2.getNumberOfGroup());
    }
    
}
