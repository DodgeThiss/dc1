/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

import java.util.Date;

/**
 *
 * @author brighamdanielserrano
 */
public class Holiday extends Event{
    
    public Holiday(String eventName, Date date, String color) {
        super(eventName, date, color);
        isHoliday = true;
    }
    
    
    
}
