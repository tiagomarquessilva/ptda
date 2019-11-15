
package server;

import communication.ListenCommands;
import communication.SendCommand;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AmbientController extends Controller {
    
    private int currentValue;
    private int idealValue;
    private int actuatorPortNumber;

    public AmbientController(String name, int timeToWaitUntilNextCheck, int listenPortNumber, int sensorPortNumber, int actuatorPortNumber) {
        super(name, timeToWaitUntilNextCheck, listenPortNumber, sensorPortNumber);
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = (int) currentValue;
    }

    public float getIdealValue() {
        return idealValue;
    }

    public void setIdealValue(int idealValue) {
        this.idealValue = (int) idealValue;
    }
    
    public void start(){
        
        //Buscar valor do sensor e mandar alterar o mesmo se for diferente do valor ideal
        new Thread(() -> {
            SendCommand sc = new SendCommand(getName());
            while(true){
                ArrayList<Object> command = new ArrayList<>();
                
                command.add("getCurrentValue");
                setCurrentValue(Integer.parseInt((sc.sendCommand("127.0.0.1", 2000, command)).toString()));
                
                command.clear();
                if ( currentValue < idealValue ){
                    command.add("increase");
                } else if ( currentValue > idealValue ){
                    command.add("decrease");
                    sc.sendCommand("127.0.0.1", actuatorPortNumber, command);
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AmbientController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
        
        //Ouve comandos da interface
        new Thread(() -> {
            ListenCommands ls = new ListenCommands(getName(), getListenPortNumber());
            ls.startListening(getMainObject());
        }).start();
    }  
}
