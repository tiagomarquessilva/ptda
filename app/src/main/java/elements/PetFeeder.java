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
public class PetFeeder {
    private States stateOfWaterEjector;
    private States stateOfFoodEjector;
    private float availableFood;
    private float availableWater;
    private float amountOfWaterToEject;
    private float amountOfFoodToEject;

    public PetFeeder(States stateOfWaterEjector, States stateOfFoodEjector, float availableFood, float availableWater, float amountOfWaterToEject, float amountOfFoodToEject) {
        this.stateOfWaterEjector = stateOfWaterEjector;
        this.stateOfFoodEjector = stateOfFoodEjector;
        this.availableFood = availableFood;
        this.availableWater = availableWater;
        this.amountOfWaterToEject = amountOfWaterToEject;
        this.amountOfFoodToEject = amountOfFoodToEject;
    }

    public States getStateOfWaterEjector() {
        return stateOfWaterEjector;
    }

    public States getStateOfFoodEjector() {
        return stateOfFoodEjector;
    }

    public float getAvailableFood() {
        return availableFood;
    }

    public float getAvailableWater() {
        return availableWater;
    }

    public float getAmountOfWaterToEject() {
        return amountOfWaterToEject;
    }

    public float getAmountOfFoodToEject() {
        return amountOfFoodToEject;
    }

    public void setStateOfWaterEjector(States stateOfWaterEjector) {
        this.stateOfWaterEjector = stateOfWaterEjector;
    }

    public void setStateOfFoodEjector(States stateOfFoodEjector) {
        this.stateOfFoodEjector = stateOfFoodEjector;
    }

    public void setAvailableFood(float availableFood) {
        this.availableFood = availableFood;
    }

    public void setAvailableWater(float availableWater) {
        this.availableWater = availableWater;
    }

    public void setAmountOfWaterToEject(float amountOfWaterToEject) {
        this.amountOfWaterToEject = amountOfWaterToEject;
    }

    public void setAmountOfFoodToEject(float amountOfFoodToEject) {
        this.amountOfFoodToEject = amountOfFoodToEject;
    }
    
    
}
