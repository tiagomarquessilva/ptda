
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class ListenCommands {
        
    //Atributo para comunicação com sockets
    public String name;
    private int socketPortNumber;
    private Socket socketConnection;
    private ObjectOutputStream sentData;
    private ObjectInputStream receivedData;

    public ListenCommands(String name, int socketPortNumber) {
        this.name = name;
        this.socketPortNumber = socketPortNumber;
    }

    public int getSocketPortNumber() {
        return socketPortNumber;
    }

    public void setSocketPortNumber(int socketPortNumber) {
        this.socketPortNumber = socketPortNumber;
    }

    public Socket getSocketConnection() {
        return socketConnection;
    }

    public void setSocketConnection(Socket socketConnection) {
        this.socketConnection = socketConnection;
    }

    public ObjectOutputStream getSentData() {
        return sentData;
    }

    public void setSentData(ObjectOutputStream sentData) {
        this.sentData = sentData;
    }

    public ObjectInputStream getReceivedData() {
        return receivedData;
    }

    public void setReceivedData(ObjectInputStream receivedData) {
        this.receivedData = receivedData;
    }
    
    //Métodos para comunicação com sockets
    public void startListening(Object objectToApplyCommand) {
            
            try {
                    
		ServerSocket socketListen = new ServerSocket( socketPortNumber, 100 );
                
                    while( true ) {
                        
			try {
                            //Espera por conexão
                            System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Waiting for connection on port" + socketPortNumber + "..." );
                            socketConnection = socketListen.accept();
                            System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Connected to " + socketConnection.getInetAddress().getHostName() + " on port " + socketPortNumber );
                            
                            //Instacia vias de comunicação
                            sentData = new ObjectOutputStream( socketConnection.getOutputStream() );
                            sentData.flush();
                            System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Now possible to send data" );
                            receivedData = new ObjectInputStream( socketConnection.getInputStream() );
                            System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Now possible to receive data" );
                            
                            //Recebe comando
                                ArrayList<Object> command = (ArrayList<Object>) receivedData.readObject();
             			System.out.println( "Thread: " + Thread.currentThread().getId() + " " + socketConnection.getInetAddress().getHostName() + " sent the command :: " + command + " :: to " + name );
                                
                                try {
                                //Executa o comando
                                Method methodToExecute = objectToApplyCommand.getClass().getMethod( command.get(0).toString());
                                switch(command.size()){
                                    case 1:
                                         Object response;
                                         response = methodToExecute.invoke( objectToApplyCommand );
                                         //Envia resposta
                                         if( command.get( 0 ).toString().contains( "get" ) ){
                                            sentData.writeObject( response );
                                            sentData.flush();
                                           }
                                         break;
                                    case 2:
                                         methodToExecute.invoke( objectToApplyCommand, command.get(0), command.get(1) );
                                         break;
                                    case 3:
                                         methodToExecute.invoke( objectToApplyCommand, command.get(0), command.get(1), command.get(2) );
                                         break;
                                    case 4:
                                         methodToExecute.invoke( objectToApplyCommand, command.get(0), command.get(1), command.get(2), command.get(3) );
                                         break;
                                    case 5:
                                         methodToExecute.invoke( objectToApplyCommand, command.get(0), command.get(1), command.get(2), command.get(3), command.get(4) );
                                         break;
                                    case 6:
                                         methodToExecute.invoke( objectToApplyCommand, command.get(0), command.get(1), command.get(2), command.get(3), command.get(4), command.get(5) );
                                         break;     
                                    case 7:
                                         methodToExecute.invoke( objectToApplyCommand, command.get(0), command.get(1), command.get(2), command.get(3), command.get(4), command.get(5), command.get(6) );
                                         break;
                                    case 8:
                                         methodToExecute.invoke( objectToApplyCommand, command.get(0), command.get(1), command.get(2), command.get(3), command.get(4), command.get(5), command.get(6), command.get(7) );
                                         break;
                                    case 9:
                                         methodToExecute.invoke( objectToApplyCommand, command.get(0), command.get(1), command.get(2), command.get(3), command.get(4), command.get(5), command.get(6), command.get(7), command.get(8) );
                                         break;     
                                }
                                
                                } catch ( NoSuchMethodException ex ) {
                                    System.out.println( "ERROR: " + socketConnection.getInetAddress().getHostName() + " has sent an unknown command" );
                                
                                } catch ( SecurityException ex ) {
                                    System.out.println("ERROR: SE");
                                
                                } catch (IllegalAccessException ex) {
                                    System.out.println("ERROR: IAcessE");
                                
                                } catch (IllegalArgumentException ex) {
                                    System.out.println("ERROR: IArgumentE");
                                
                                } catch (InvocationTargetException ex) {
                                    System.out.println("ERROR: ITE");
                                
                                }
                               
                            } catch ( ClassNotFoundException classNotFoundException ){
				System.out.println( "ERROR: " + socketConnection.getInetAddress().getHostName() + " has sent an unknown command" );
                                
                            } catch ( IOException ioException ) {
                                ioException.printStackTrace();
                                
			} finally {
                            //Fecha a conexão
                            System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Closing connections...");
                            
                            try {
                                sentData.close(); 
                                receivedData.close(); 
                                socketConnection.close();
                                System.out.println( "Thread: " + Thread.currentThread().getId() + " " + name + ": Connections closed" );

                            } catch ( IOException ioException ) {
                                ioException.printStackTrace();
                            }
			}
                    }
                    
            } catch ( IOException ioException ){
		ioException.printStackTrace();
                
            }
	}
}
