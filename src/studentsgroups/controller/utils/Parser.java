/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller.utils;

import java.io.File;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Elvira
 */
public interface Parser {
    
    void saveObject(Object o, File file) throws JAXBException;
    Object getObject(Class c, File file) throws JAXBException;
    
}
