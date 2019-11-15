
package server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    
    private int secondsToWaitUntilNextCheck;
    private String name;
    private int listenPortNumber;
    private int sensorPortNumber;
    private final Object mainObject;
    
    public Controller(String name, int timeToWaitUntilNextCheck, int listenPortNumber, int sendPortNumber) {
        this.secondsToWaitUntilNextCheck = timeToWaitUntilNextCheck;
        this.name = name;
        this.listenPortNumber = listenPortNumber;
        this.sensorPortNumber = sendPortNumber;
        this.mainObject = this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSecondsToWaitUntilNextCheck() {
        return secondsToWaitUntilNextCheck;
    }

    public void setSecondsToWaitUntilNextCheck(int secondsToWaitUntilNextCheck) {
        this.secondsToWaitUntilNextCheck = secondsToWaitUntilNextCheck;
    }

    public int getListenPortNumber() {
        return listenPortNumber;
    } 

    public void setListenPortNumber(int listenPortNumber) {
        this.listenPortNumber = listenPortNumber;
    }

    public int getSendPortNumber() {
        return sensorPortNumber;
    }

    public void setSendPortNumber(int sendPortNumber) {
        this.sensorPortNumber = sendPortNumber;
    }

    public Object getMainObject() {
        return mainObject;
    }
    
    public String getCurrentDateAndTime(){  
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	return dateFormat.format(date);
    }
    
}
