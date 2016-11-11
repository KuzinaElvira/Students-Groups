/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentsGroups;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 *
 * @author Elvira
 */
public class Controller {

    public static boolean addGroup(String groupNumber, String groupFaculty){
        return false;
    }
    public static boolean removeGroup(String groupNumber, String groupFaculty){
        return true;
    }
    public static boolean readFromTxtFile(File file) throws IOException{
        return true;
    }
    //десериализация
    public static boolean readFromBinFile(File file) throws IOException{
        return true;
    }
    public static boolean writeToTxtFile(File file) throws IOException{
        throw  new IOException();
    }
    //сериализация
    public static boolean writeToBinFile(File file) throws IOException{
        return false;
    }

}
