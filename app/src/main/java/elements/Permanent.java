/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import others.Room;

/**
 *
 * @author Hugo Neves
 */
public class Permanent {
    public static void main(String[] args) {
    
    Room kitchen = new Room("Cozinha");
    kitchen.generateHumidity();
    kitchen.generateTemperatures();
    
    Sensor kts = new Sensor("Sensor Temperatura Cozinha", 2000, kitchen);
    Sensor khs = new Sensor("Sensor Humidade Cozinha", 2001, kitchen);
    AC kta = new AC("Atuador Temp Cozinha", 2002, kitchen);
    HumidifierDehumidifier kha = new HumidifierDehumidifier("Atuador Hum Cozinha", 2003, kitchen);
    
    kts.start();
    khs.start();
    kta.start();
    kha.start();
    
    }
}
