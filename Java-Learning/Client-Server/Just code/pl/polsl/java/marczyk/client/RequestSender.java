/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.java.marczyk.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class represents the object which is responsible for sending messages to
 * the server.
 * 
 * @author Marczyk Grzegorz
 * @version 1.0.2
 */
public class RequestSender {

    /** Output stream connected with the socket to send message to the server */
    private PrintWriter socketSend;
    

    /**
     * Constructor creates socketSend output stream based on the socket 
     * 
     * @param theSocket is the client socket that is used to communicate with
     * the server
     * @throws IOException when creating output stream for socketSend
     */
    public RequestSender(Socket theSocket) throws IOException { 
        
        this.socketSend = 
                new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        theSocket.getOutputStream())), true);
        
        System.out.println("[CLIENT] Sender started");
    }
    
    /**
     * Sends String message to server.
     * 
     * @param msgToSend the message to send
     */
    public void sendMessage(String msgToSend){
        
        this.socketSend.println(msgToSend);  
        this.socketSend.flush();
    }
}
