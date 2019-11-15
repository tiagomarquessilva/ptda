/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import communication.DBConnection;
import elements.Element;
import enums.Consumes;
import gui.kitchenDevices;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao_
 */
public class Room {
    
    private String name;
    private int temperatureValue;
    private int humidityValue;
    private boolean increaseTemp;
    private boolean decreaseTemp;
    private int destinyTemp;
    private boolean increaseHum;
    private boolean decreaseHum;
    private int destinyHum;

    public Room(String name) {
        this.name = name;
        this.temperatureValue = (int) generateRandomValue( 0, 30, 0, 30);
        this.humidityValue = (int) generateRandomValue( 0, 30, 0, 30);
        this.decreaseTemp = false;
        this.increaseTemp = false;
    }

    public boolean isIncreaseTemp() {
        return increaseTemp;
    }

    public void setIncreaseTemp(boolean increaseTemp) {
        this.increaseTemp = increaseTemp;
    }

    public boolean isDecreaseTemp() {
        return decreaseTemp;
    }

    public void setDecreaseTemp(boolean decreaseTemp) {
        this.decreaseTemp = decreaseTemp;
    }

    public int getDestinyTemp() {
        return destinyTemp;
    }

    public void setDestinyTemp(int destinyTemp) {
        this.destinyTemp = destinyTemp;
    }

    public boolean isIncreaseHum() {
        return increaseHum;
    }

    public void setIncreaseHum(boolean increaseHum) {
        this.increaseHum = increaseHum;
    }

    public boolean isDecreaseHum() {
        return decreaseHum;
    }

    public void setDecreaseHum(boolean decreaseHum) {
        this.decreaseHum = decreaseHum;
    }

    public int getDestinyHum() {
        return destinyHum;
    }

    public void setDestinyHum(int destinyHum) {
        this.destinyHum = destinyHum;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(int temperatureValue) {
        
        this.temperatureValue = temperatureValue;
    }

    public int getHumidityValue() {
        return humidityValue;
    }

    public void setHumidityValue(int humidityValue) {
        this.humidityValue = humidityValue;
    }

    public void loadPermanentItems(){
        DBConnection db = new DBConnection();
        ArrayList<Element> ke = new ArrayList<>(); 
        
        try {
            ResultSet rs = db.dbQuery("SELECT * FROM elements WHERE room_id = 1");
            while ( rs.next() ) {
                ke.add(new Element( rs.getString("name"), rs.getInt("socketportnumber"), Consumes.valueOf(rs.getString("consumes")), rs.getBoolean("usedonlybyadmin")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(kitchenDevices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generateTemperatures(){
        new Thread(() -> {
            
        
        while(true){
                 
           if(increaseTemp){
               try {
                   increaseValueByTheMinute(destinyTemp);
                    increaseTemp = false;
                    continue;
               } catch (InterruptedException ex) {
                   Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
               }
              
           
           } else if(decreaseTemp){
                     try {
                         decreaseValueByTheMinute(destinyTemp);
                         decreaseTemp = false;
                         continue;
                     } catch (InterruptedException ex) {
                    Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
                 
                 setTemperatureValue(generateRandomValue(0, 20, 0, 20));
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }).start();
    };
    
    public void generateHumidity(){
        new Thread(() -> {
             
        while(true){
                 
           if(increaseHum){
               try {
                   increaseValueByTheMinute(destinyHum);
                   increaseHum = false;
                   continue;
               } catch (InterruptedException ex) {
                   Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
               }
               
           
           } else if(decreaseHum){
                     try {
                         decreaseValueByTheMinute(destinyHum);
                         decreaseHum = false;
                         continue;
                     } catch (InterruptedException ex) {
                    Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
                 
                 setHumidityValue(generateRandomValue(0, 100, 0, 100));
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException ex) {
                     Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }).start();
    };
         
    /**
     * 
     * @param minValueToGenerate
     * @param maxValueToGenerate
     * @param minValuePossible
     * @param maxValuePossible
     * @return 
     * 
     * Gera valor aleatorio para o elemento instaciado
     */
    public int generateRandomValue( int minValueToGenerate, int maxValueToGenerate, int minValuePossible, int maxValuePossible ){
        
        if( minValueToGenerate < minValuePossible ){
            minValueToGenerate = minValuePossible;
        }
        
        if( maxValueToGenerate > maxValuePossible ){
            maxValueToGenerate = maxValuePossible;
        }
       
        return (int) ThreadLocalRandom.current().nextInt(minValueToGenerate, maxValueToGenerate + 1);
             
    }
    
    /**
     * 
     * @param valueWhereToStop
     * @param increaseValueBy
     * @throws InterruptedException 
     * 
     * Aumenta o valor do elemento (temperatura, humidade, ...) "increaseValueBy" unidades a cada minuto até o valor "valueWhereToStop" ser atingido
     */
    public void increaseValueByTheMinute( int valueWhereToStop, int increaseValueBy ) throws InterruptedException{
       
          
                    
              while( temperatureValue < valueWhereToStop ){
                   
                   setTemperatureValue((temperatureValue + increaseValueBy));
                   try {
                       Thread.sleep( 1000 );
                   } catch (InterruptedException ex) {
                       Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               
               setTemperatureValue(valueWhereToStop);
               setIncreaseTemp(false);         
           
       }
                
    /**
     * 
     * @param valueWhereToStop
     * @throws InterruptedException
     * 
     * Aumenta o valor do elemento (temperatura, humidade, ...) 1 unidade a cada minuto até o valor "valueWhereToStop" ser atingido
     */
    public void increaseValueByTheMinute( int valueWhereToStop ) throws InterruptedException{
         
        increaseValueByTheMinute(valueWhereToStop, 1);
        
     }
    
    /**
     * 
     * @param valueWhereToStop
     * @param decreaseValueBy
     * @throws InterruptedException 
     * 
     * Diminui o valor do elemento (temperatura, humidade, ...) "decreaseValueBy" unidades a cada minuto até o valor "valueWhereToStop" ser atingido
     */
    public void decreaseValueByTheMinute( int valueWhereToStop, int decreaseValueBy ) throws InterruptedException {
        
        
            
            while( temperatureValue > valueWhereToStop ){
            
                setTemperatureValue((temperatureValue - decreaseValueBy));
                Thread.sleep( 1 * 60 * 1000 );
                System.out.println( getTemperatureValue() );
            
            }
        
            setTemperatureValue(valueWhereToStop);
        
    }
    
    /**
     * 
     * @param valueWhereToStop
     * @throws InterruptedException
     * 
     * Diminui o valor do elemento 1 unidade a cada minuto até o valor "valueWhereToStop" ser atingido
     */
    public void decreaseValueByTheMinute( int valueWhereToStop ) throws InterruptedException{
        
         decreaseValueByTheMinute(valueWhereToStop, 1);
         
     } 
    
}
