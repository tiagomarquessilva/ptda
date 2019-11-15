/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.util.Date;

/**
 *
 * @author joao_
 */
public class Alert {
    private Date date;
    private String title;
    private String message;

    public Alert(Date date, String title, String message) {
        this.date = date;
        this.title = title;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
