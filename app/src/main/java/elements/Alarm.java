/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import enums.States;
import java.util.ArrayList;

/**
 *
 * @author joao_
 */
public class Alarm {
    
    ArrayList<Integer> codes;
    private States state;

    public Alarm(int [] codes, States state) {
        this.codes = new ArrayList<Integer>();
        
        this.state = state;
    }

    public ArrayList<Integer> getCodes() {
        return codes;
    }

   

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }
    
   
    public void addCode(int newCode){
        codes.add(newCode);
    }
    public void removeCode(int newCode){
        codes.remove(newCode);
    }
}
