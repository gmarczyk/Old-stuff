package pl.polsl.java.marczyk.client;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import pl.polsl.java.marczyk.client.view.UserInterface;
import pl.polsl.java.marczyk.protocole.Protocole;
import pl.polsl.java.marczyk.protocole.ProtocoleUserType;

/** 
 * Class representing object of a single client with the application view.
 * 
 * @author Marczyk Grzegorz
 * @version 3.0.0
 */
public class Client extends Thread {

    /** Single client socket to communicate with one server */ 
    private Socket              socket;
    
    /** Port to which the socket is going to be connected */
    private int                 serverPort;
    
    /** Object of the view - display of the application */
    private UserInterface       view;

    /** Listens to server and gets the messages from it */
    private ResponseReceiver    clientReceiver;
    
    /** Provides procedures to send messages to server */
    private RequestSender       clientSender;
    
    /** Input stream connected with system.in for reading user input */
    private BufferedReader      keyboardInput;
    
    /** Transmission protocole */
    private final Protocole clientProtocole = new Protocole(ProtocoleUserType.CLIENT);

    /**
     * The constructor for CLient which takes one argument which is the port
     * number for connection.
     *
     * @param portNumber is the port number
     * @throws IOException if there were problems with I/O streams
     */
    public Client(int portNumber) throws IOException {
        
        this.serverPort     = portNumber;
        this.socket         = new Socket("localhost", this.serverPort);  
        
        this.clientSender   = new RequestSender(socket);
        this.view           = new UserInterface(clientSender);
        this.clientReceiver = new ResponseReceiver(socket, new ResponseHandler(view.getMainWindow()));
   
        this.keyboardInput  = 
                new BufferedReader(
                        new InputStreamReader(
                                System.in));
    }

    /** 
     * Overridden "run" method from Thread class. Starts the transmission
     * between server and client. Opens the streams.
     */
    @Override
    public void run() {
        
        // This is a separate thread, it listens for instructions/messages etc. from the server
        this.clientReceiver.start();
        
        EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                }
            });
        
        System.out.println("[CLIENT] Client started");
        
        try { 
            while (true) {   
                
                if (!socket.isConnected()) break;                // Server may close the connection or never accept it
                
                String userCommand = keyboardInput.readLine();  
                if (userCommand.equalsIgnoreCase("EXIT")) {      // Check if the user wants to stop the connection.
                    clientSender.sendMessage("EXIT");            // Server must stop his service by himself, tell him to do so
                    break;
                } else {
                    clientSender.sendMessage(userCommand);
                }
            }
        } catch (IOException e) {
                System.out.println("[CLIENT] Error in the main loop of the thread: " + e.getMessage());
        } finally {
            try {
                
                this.keyboardInput.close();
                
                this.clientReceiver.interrupt();
                
                this.socket.close(); // This will cause clientReceiver to stop "readline" in bufferedreader and throw exception
                
                System.out.println("[CLIENT] Client ended");
                
            } catch (IOException ex) {
                System.out.println("[CLIENT] Error while closing client: " + ex.getMessage());
            }
        }
    }

    /**
     * Starting the client is performed here.
     *
     * @param args program parameters
     * @throws IOException when any problem with stream will occur
     */
    public static void main(String[] args) throws IOException {
        
        Client client = new Client(1234);
        client.start(); 
    }
}
