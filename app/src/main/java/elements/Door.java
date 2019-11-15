/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import enums.States_Door;
import java.util.ArrayList;

/**
 *
 * @author joao_
 */
public class Door {
    
    ArrayList<Integer> unlockcodes;
    private States_Door state;

    public Door(ArrayList<Integer> unlockcodes, States_Door state) {
        this.unlockcodes = unlockcodes;
        this.state = state;
    }

    public ArrayList<Integer> getUnlockcodes() {
        return unlockcodes;
    }
    
    public States_Door getState() {
        return state;
    }

    public void setState(States_Door state) {
        this.state = state;
    }
    
    public void addUnlockCode(int newUnlockCode){
        unlockcodes.add(newUnlockCode);
    }
    
    public void removeUnlockCode(int unlockCode){
        unlockcodes.remove(unlockCode);
    }
    
}
