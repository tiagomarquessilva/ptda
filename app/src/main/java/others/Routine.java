/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import enums.States;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author joao_
 */
public class Routine {
    private String name;
    private States state;
    ArrayList<Date> schedule;

    public Routine(String name, States state, ArrayList<Date> schedule) {
        this.name = name;
        this.state = state;
        this.schedule = schedule;
    }
    

   

    public String getName() {
        return name;
    }

    public States getState() {
        return state;
    }

    public ArrayList<Date> getSchedule() {
        return schedule;
    }

    

    public void setName(String name) {
        this.name = name;
    }

    public void setState(States state) {
        this.state = state;
    }
    
    public void addEvent(Date newSchedule){
       schedule.add(newSchedule);
    }
    
    public void removeEvent(Date schedule){
       this.schedule.remove(schedule);
    }
    
}
