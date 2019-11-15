/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import enums.States;

/**
 *
 * @author joao_
 */
public class EntertainmentSystem {
    private float volume;
    private States state;

    public EntertainmentSystem(float volume, States state) {
        this.volume = volume;
        this.state = state;
    }

    public float getVolume() {
        return volume;
    }

    public States getState() {
        return state;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setState(States state) {
        this.state = state;
    }
    
    
}
