package pl.polsl.java.marczyk.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import pl.polsl.java.marczyk.client.view.MainWindow;
import pl.polsl.java.marczyk.protocole.Protocole;
import pl.polsl.java.marczyk.protocole.ProtocoleUserType;

/**
 * Class represents the object which is responsible for receiving messages from
 * the server.
 *
 * @author Marczyk Grzegorz
 * @version 1.1.0
 */
public class ResponseReceiver extends Thread {

    /** Input stream connected with the socket to receive messages from server. */
    private BufferedReader  socketReceive;
    
    /** Reference to handler which does things when server gives an instruction */
    private ResponseHandler responseHandler;
    
    /** Transmission protocole */
    private Protocole       refToProtocole = new Protocole(ProtocoleUserType.CLIENT);

    /**
     * Constructor creates socketReceive input stream based on the socket. 
     *
     * @param theSocket is the client socket that is used to communicate with
     * the server
     * @param handler reference to handler
     * @throws IOException when creating input stream for socketReceive
     */
    public ResponseReceiver(Socket theSocket, ResponseHandler handler) throws IOException { 
        
        this.socketReceive = 
                new BufferedReader(
                        new InputStreamReader(
                                theSocket.getInputStream()));
        
        this.responseHandler = handler;
    }


    /**
     * Thread's overridden method. Starts the thread, listens for messages from
     * server.
     */
    @Override
    public void run() {
        
        System.out.println("[CLIENT] Receiver started");
        
        String receivedMsg;
        try {
            
            while (!Thread.interrupted()) {
                
                receivedMsg = this.socketReceive.readLine();
                if(receivedMsg != null) System.out.println("Received from [SERVER] to [CLIENT]: " + receivedMsg);
                
                  if(refToProtocole.isInstructionValid(receivedMsg)){
                      this.responseHandler.handleServersResponse(receivedMsg);
                  } else {
                       System.out.println("[PROTOCOLE] Invalid instruction received from [SERVER] : " + receivedMsg);
                       System.out.println(refToProtocole.getInvalidReason());
                  }
            }
            
        } catch (IOException e) {
            System.out.println("[CLIENT] Receiver: " + e.getMessage());
        }
        
    }
}
