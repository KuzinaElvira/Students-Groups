/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller.utils;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Elvira
 */
public class JAXBParser implements Parser{
    
    /**
     * Запись объекта в xml файл
     * @param o
     * @param file
     * @throws JAXBException 
     */
    @Override
    public void saveObject(Object o, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(o,file);
    }
    
    /**
     * Чтение объекта из xml файла
     * @param c
     * @param file
     * @return
     * @throws JAXBException 
     */
    @Override
    public Object getObject(Class c, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(file); 
        return object;
    } 
    
}
