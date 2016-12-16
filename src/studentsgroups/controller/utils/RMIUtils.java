/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsgroups.controller.utils;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import studentsgroups.controller.Controller;

/**
 *
 * @author Elvira
 */
public class RMIUtils {
    public static final String CONTROLLER = "Controller";
    
    public static void createServer(int port, Controller controller) throws RemoteException, AlreadyBoundException{
        Registry registry = LocateRegistry.createRegistry(port);
        Remote stub = UnicastRemoteObject.exportObject(controller, 0);
        registry.bind(CONTROLLER, stub);
    }
    
    public static Controller connectToServer(int port, String host) throws RemoteException, NotBoundException{
        Registry registry = LocateRegistry.getRegistry(host, port);
        Controller controller = (Controller) registry.lookup(CONTROLLER);
        return controller;
    }
    
}
