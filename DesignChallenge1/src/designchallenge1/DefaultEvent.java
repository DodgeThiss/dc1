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
abstract class DefaultEvent {
    
    String eventName;
    Date date;
    
    public DefaultEvent(String eventName, Date date){
        this.eventName = eventName;
        this.date = date;
    }
    
    
}
