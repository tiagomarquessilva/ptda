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
public class VacuumRobot extends BinaryState{
    
    private float battery;

    public VacuumRobot(States state, float battery) {
        super(state);
        this.battery = battery;
    }

    public float getBattery() {
        return battery;
    }
    
    public void setBattery(float battery) {
        this.battery = battery;
    }
    
    
}
