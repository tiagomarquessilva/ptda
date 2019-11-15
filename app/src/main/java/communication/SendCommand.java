/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Hugo Neves
 */
public class SendCommand {
    
    //Atributos para a utilização das sockets 
    private String name;
    private ObjectOutputStream sentData;
    private ObjectInputStream receivedData;
    private Socket connection;

    public SendCommand(String name) {
        this.name = name;
    }
   
    
    /**
     * 
     * @param listenerIP
     * @param listenerPortNumber
     * @param command
     * @return 
     * 
     * Manda comando a sensor/atuador e retorna uma resposta do mesmo
     */
    public Object sendCommand( String listenerIP, int listenerPortNumber, ArrayList<Object> command){
        
            Object response = null;
        
            try {
                
                //Efetuar Conexão
		System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Attempting connection to " + listenerIP + " on port " + listenerPortNumber );
		connection = new Socket(listenerIP, listenerPortNumber);
		System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Connected to: " + connection.getInetAddress().getHostName() );
                
                //Instanciar vias de comunicação
                sentData = new ObjectOutputStream( connection.getOutputStream() );
		sentData.flush();
                System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Now possible to send data" );
		receivedData = new ObjectInputStream( connection.getInputStream() );
                System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Now possible to receive data" );
                
                //Enviar comando
                try {
                    sentData.writeObject( command );
                    sentData.flush();
                    System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + " sent the command :: " + command + " :: to " + connection.getInetAddress().getHostName() );
                    
                    response = receivedData.readObject();
                    
		} catch( IOException ioException ){
                    System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": ERROR I/O" );
                        
		} catch (ClassNotFoundException ex) {
                    System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": ERROR Class Not Found" );
                }
                               
            } catch ( EOFException eofException ) {
                
                System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Terminated connection with " + connection.getInetAddress().getHostName() );
                
            } catch ( IOException ioException ) {
                
		ioException.printStackTrace();
                
            } finally {
                
                //Fecha conexões
		try {
                    
                    sentData.close();
                    receivedData.close();
                    connection.close();
                        
		} catch ( IOException ioException ){
                    
                    ioException.printStackTrace();
                        
		}
                
            }    
            
            return response;
            
        }
    
}
