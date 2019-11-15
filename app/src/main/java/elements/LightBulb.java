/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import enums.States;
import java.awt.Color;

/**
 *
 * @author joao_
 */
public class LightBulb {
    private Color color;
    private double intensity;
    private States state;

    public LightBulb(Color color, double intensity, States state) {
        this.color = color;
        this.intensity = intensity;
        this.state = state;
    }

    public Color getColor() {
        return color;
    }

    public double getIntensity() {
        return intensity;
    }

    public States getState() {
        return state;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public void setState(States state) {
        this.state = state;
    }
    
    
    
}
