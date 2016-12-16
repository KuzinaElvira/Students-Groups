/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller.utils;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import studentsgroups.controller.Controller;
import studentsgroups.view.MainForm;

/**
 *
 * @author Elvira
 */
public class Client {
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Controller controller = RMIUtils.connectToServer(1099, "localhost");
        new MainForm(controller);
    }
    
}
