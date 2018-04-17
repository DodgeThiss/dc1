/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

import java.awt.Color;
import java.util.Date;

/**
 *
 * @author brighamdanielserrano
 */
public class Event extends DefaultEvent{
    String color;
    boolean isHoliday;
    
     public Event(String eventName, Date date, String color){
        super(eventName, date);
        this.color = color;
        isHoliday = false;
       
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
    
}
