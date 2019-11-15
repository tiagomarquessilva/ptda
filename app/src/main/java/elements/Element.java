
package elements;

import enums.Consumes;
import enums.Power;
import communication.ListenCommands;
import communication.SendCommand;
import enums.States;
import java.util.ArrayList;

public class Element extends ListenCommands{
    
    //Atributos para utilização do equipamento
    private States state;
    private Consumes consumes;
    private Power power;
    private float consumption;
    private boolean usedOnlyByAdmin;

    public Element(String name, int socketPortNumber, Consumes consumes, boolean usedOnlyByAdmin) {
        super( name, socketPortNumber );
        this.consumes = consumes;
        this.usedOnlyByAdmin = usedOnlyByAdmin;
        SendCommand sc = new SendCommand("Get State");
        ArrayList<Object> command = new ArrayList<>();
        command.add("getState");
        Object response = sc.sendCommand("127.0.0.1", socketPortNumber, command);
        this.state = (States) response;
        sc = null;
        command = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Consumes getConsumes() {
        return consumes;
    }

    public void setConsumes(Consumes consumes) {
        this.consumes = consumes;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public boolean isUsedOnlyByAdmin() {
        return usedOnlyByAdmin;
    }

    public void setUsedOnlyByAdmin(boolean usedOnlyByAdmin) {
        this.usedOnlyByAdmin = usedOnlyByAdmin;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }
    
    
}    