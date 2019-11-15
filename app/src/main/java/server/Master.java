/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import elements.Element;
import enums.Consumes;

/**
 *
 * @author Hugo Neves
 */
public class Master {
    public static void main(String[] args) {
//        Controller kitchenTemperatureController = new Controller("Kitchen Temperature Controller", 1, 4000, 2000);
//        kitchenTemperatureController.start();

    Element e = new Element("teste0", 2000, Consumes.WATER, true);
    System.out.println(e.getState());
    
    AmbientController kct = new AmbientController("Controlador Temp Cozinha", 1, 3000, 2000, 2002);
    AmbientController kch = new AmbientController("Controlador Hum Cozinha", 1, 3001, 2001, 2003);
    
    kct.start();
    kch.start();
    }
}
