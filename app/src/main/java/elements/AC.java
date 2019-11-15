
package elements;

import communication.ListenCommands;
import others.Room;

public class AC {
   
    private String name;
    private int listenPortNumber;
    private String unit;
    private final Object mainObject;
    private Room room;

    public AC(String name, int listenPortNumber, Room room) {
        this.name = name;
        this.listenPortNumber = listenPortNumber;
        this.mainObject = this;
        this.room = room;
    }

    public void increaseValue(int temp) {
         room.setDestinyTemp(temp);
         room.setIncreaseTemp(true);
    }
     
    public void decreaseValue(int temp){
         room.setDestinyTemp(temp);
         room.setDecreaseTemp(true);
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
