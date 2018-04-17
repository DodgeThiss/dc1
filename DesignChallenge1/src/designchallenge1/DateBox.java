/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

import java.util.ArrayList;

/**
 *
 * @author brighamdanielserrano
 */
public class DateBox {
    private int day;
    private int month;
    private int year;
    private ArrayList <Event> events;
    
    public DateBox (int month, int day, int year){
        this.day = day;
        this.month = month;
        this.year = year;
        
        events = new ArrayList<>();
    }
 
    
    public void addEvent(Event event){
        events.add(event);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

}
