/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


/**
 *
 * @author brighamdanielserrano
 */
public class CSVDataParser extends DataParser{
String [] text;

    public CSVDataParser(CalendarProgram calendar, String fileName, String toFileName) {
        super(calendar, fileName, toFileName);
    }

    void getData(int index){
        String line;
        System.out.println("Looping through loaded csv file");
        int i =0;
    
        try {
        while((line = buffReader.readLine()) != null && i <= index){
            
            text = line.split(",");
            
            if(i < index){
                i++; 
                continue;
            }
            
            String[] _date = text[0].split("/");
            
            int day = Integer.parseInt(_date[1]);
            int month = Integer.parseInt(_date[0]) - 1;
            int year = Integer.parseInt(_date[2]);
            
            
        }
    } catch (IOException ex) {
            System.out.println("Wrong");
        }
    }

    @Override
    void processData() {
        String line;
        System.out.println("Looping through loaded csv file");
        int i =0;
    try {
        while((line = buffReader.readLine()) != null){
            
            text = line.split(",");
            
            if(i ==0){
            sBuilder.append("Date");
            sBuilder.append(",");
            sBuilder.append("Event");
            sBuilder.append(",");
            sBuilder.append("Color");
            sBuilder.append("\n");
                i++; 
                continue;
            }
            
            String[] _date = text[0].split("/");
            
            int day = Integer.parseInt(_date[1]);
            int month = Integer.parseInt(_date[0]) - 1;
            int year = Integer.parseInt(_date[2]);
            
            
            Event newEvent = new Holiday(text[1], new Date(Integer.parseInt(_date[2]), Integer.parseInt(_date[0])-1,  Integer.parseInt(_date[1]) ), text[2]);
            if(newEvent.isHoliday)
                System.out.println("uy holiday");
            boolean beenHere = false;
            if(calendar.addToDateBox(Integer.parseInt(_date[0]) - 1, Integer.parseInt(_date[1]), Integer.parseInt(_date[2]),newEvent, beenHere))
                ;
            else
                calendar.addDateBoxEvents(newEvent);
            
            sBuilder.append(text[0]);
            sBuilder.append(",");
            sBuilder.append(text[1]);
            sBuilder.append(",");
            sBuilder.append(text[2]);
            sBuilder.append("\n");
        }
    } catch (IOException ex) {
            System.out.println("Wrong");
        }
    }

    @Override
    void save(String date, String eventName, String color) {
        String newFileName = "events.csv";
        try {
            this.printWriter = new PrintWriter(new File(newFileName));
            sBuilder.append(date);
            sBuilder.append(",");
            sBuilder.append(eventName);
            sBuilder.append(",");
            sBuilder.append(color);
            sBuilder.append("\n");
                
            this.printWriter.write(sBuilder.toString());
            this.printWriter.close(); 
         } catch (IOException ex) {
                System.out.println("Boom");
            }   
    }

    
}
