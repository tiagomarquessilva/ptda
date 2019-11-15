
package elements;

import communication.ListenCommands;
import others.Room;

public class Sensor {
   
    private String name;
    private int listenPortNumber;
    private String unit;
    private final Object mainObject;
    private Room room;

    public Sensor(String name, int listenPortNumber, Room room) {
        this.name = name;
        this.listenPortNumber = listenPortNumber;
        this.mainObject = this;
        this.room = room;
    }

    public int getCurrentTempValue() {
        return room.getTemperatureValue();
    }
    
    public int getCurrentHumValue(){
        return room.getHumidityValue();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getListenPortNumber() {
        return listenPortNumber;
    }

    public void setListenPortNumber(int listenPortNumber) {
        this.listenPortNumber = listenPortNumber;
    }
    
    public void start(){
        
        //transmite temperatura atual
          new Thread(() -> {
           
              ListenCommands ls = new ListenCommands(name, listenPortNumber);
              ls.startListening(mainObject);
              
        }).start();
          
         
    }
}
