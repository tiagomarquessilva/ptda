/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import enums.Consumes;
import enums.States;
import communication.DBConnection;
import elements.Element;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joao_
 */
public class Zone {
    
    private int id;
    private String name;
    private States state;
    private ArrayList<Element> elements;
    
    public Zone( int id, String name, States state ) {
        this.id = id;
        this.name = name;
        this.state = state;
        elements = new ArrayList<>();
        
        //Carregar os elementos da zona da base de dados
        DBConnection db = new DBConnection();
        try {
            
            ResultSet rs = db.dbQuery( "SELECT element_id, element_name, room_id, consumes, consumption, power, ip, socketportnumber, usedonlybyadmin  \n" +
"FROM zones_elements_view WHERE zone_id = " + this.id );
            
            while( rs.next() ){
                
                elements.add(new Element(rs.getString("name"), rs.getInt("socketportnumber"), Consumes.valueOf(rs.getString("consumes")), rs.getBoolean("usedonlybyadmin")));
                
            }
            
        } catch (SQLException ex) {
           
    }

   }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public void removeElement(Element element) {
        elements.remove(element);
    }
    
}