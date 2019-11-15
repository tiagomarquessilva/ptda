/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 *
 * @author joao_
 */
public class Pool extends PercentState{
    
    private float phLevel;

    public Pool(float percentCoverClosed, float phLevel) {
        super(percentCoverClosed);
        this.phLevel = phLevel;
    }

    public float getPhLevel() {
        return phLevel;
    }

    public void setPhLevel(float phLevel) {
        this.phLevel = phLevel;
    }
      
}
