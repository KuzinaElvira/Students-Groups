/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller.utils;

/**
 *
 * @author Elvira
 */
public class NotValidValueException extends RuntimeException{

    public NotValidValueException(String message) {
        System.out.println(message);
    }
    
}
