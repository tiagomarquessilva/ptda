
package elements;

import communication.ListenCommands;
import enums.States;

public class BinaryState extends ListenCommands{

   private States state;

    public BinaryState(States state) {
        super("Tora", 2000);
        this.state = state;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }
  
}
