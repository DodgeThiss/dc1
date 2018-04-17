/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

import javax.swing.JFrame;

/**
 *
 * @author brighamdanielserrano
 */
abstract class DefaultEventFrame extends JFrame{
    CalendarProgram calendar;
    int row;
    int col;
    DateBox dateBox;
    
    public DefaultEventFrame(String name, CalendarProgram calendar,int row, int col){
        this.calendar = calendar;
        this.setName(name);
        this.row = row;
        this.col = col;
    }
    
    public DefaultEventFrame(String name, CalendarProgram calendar, DateBox dateBox, int row, int col){
        this.calendar = calendar;
        this.setName(name);
        this.row = row;
        this.col = col;
        this.dateBox = dateBox;
        
    }
    
    abstract void addEvent();
}
