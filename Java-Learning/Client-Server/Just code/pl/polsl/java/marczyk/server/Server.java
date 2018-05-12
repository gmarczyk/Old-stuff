/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.marczyk.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import pl.polsl.java.marczyk.protocole.Protocole;
import pl.polsl.java.marczyk.protocole.ProtocoleUserType;
import pl.polsl.java.marczyk.server.model.ProbabilisticTests;

public class Server {

    /** Port on which the host works*/
    private int             PORT = 1234;
    
    /** Socket to accept transmission with client */
    private ServerSocket    serverSocket;
    
    /** Protocole of the server */
    private final Protocole serverProtocole = new Protocole(ProtocoleUserType.SERVER);
    
    /** Model for doing calculations */
    private final ProbabilisticTests model = new ProbabilisticTests();


    /**
     * Constructor, initializes all the fields needed to start the server, and 
     * then starts it. Accepts the client requests and handles it one by one.
     */
    public Server() {
        
        try {
            this.serverSocket = new ServerSocket(PORT);
            System.out.println("[SERVER] Server was created.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("[SERVER] Server started.");
        try { 
            while (true) {
                
                Socket socket = serverSocket.accept();
                try {
                    // creates new service for every connected client
                    SingleService singleService = new SingleService(socket, serverProtocole, model);
                    singleService.start();
                } catch (IOException e) {
                    socket.close();
                    System.err.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                
                serverSocket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }


    
    /**
     * Main method of the server, just starts it.
     * @param args are user's input parameters
     */
    public static void main(String[] args) {
        
        Server server = new Server();
    }
}