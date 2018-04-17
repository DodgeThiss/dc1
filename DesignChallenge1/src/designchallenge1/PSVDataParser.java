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
public class PSVDataParser extends DataParser{
String [] text;

    public PSVDataParser(CalendarProgram calendar, String fileName, String toFileName) {
        super(calendar, fileName, toFileName);
    }
   

    @Override
    void processData() {
        String line;
        System.out.println("Looping through loaded csv file");
        int i =0;
    try {
        while((line = buffReader.readLine()) != null){
            System.out.println(line);
            text = line.split(" \\| ");
            
            if(i ==0){
            sBuilder.append("Event");
            
            sBuilder.append("\n");
                i++; 
                continue;
            }
            
            String[] _date = text[1].split("/");
            
            boolean beenHere = false;
            if(calendar.addToDateBox(Integer.parseInt(_date[0]) - 1, Integer.parseInt(_date[1]), Integer.parseInt(_date[2]),new Event(text[0], new Date(Integer.parseInt(_date[2]), Integer.parseInt(_date[0])-1,  Integer.parseInt(_date[1]) ), text[2]), beenHere))
                System.out.println("here");
            else;
            calendar.addDateBoxEvents(new Event(text[0], new Date(Integer.parseInt(_date[2]), Integer.parseInt(_date[0])-1,  Integer.parseInt(_date[1]) ), text[2]));
            
            //calendar.addEvents(new Event(text[0], new Date(Integer.parseInt(_date[2]), Integer.parseInt(_date[0])-1,  Integer.parseInt(_date[1]) ), text[2]));
            sBuilder.append(text[0]);
            sBuilder.append(" | ");
            sBuilder.append(text[1]);
            sBuilder.append(" | ");
            sBuilder.append(text[2]);
            sBuilder.append("\n");
        }
    } catch (IOException ex) {
            System.out.println("Wrong");
        }
    }

    @Override
    void save(String date, String eventName, String color) {
                String newFileName = "piped-events.csv";
        try {
            this.printWriter = new PrintWriter(new File(newFileName));
            sBuilder.append(eventName);
            sBuilder.append(" | ");
            sBuilder.append(date);
            sBuilder.append(" | ");
            sBuilder.append(color);
            sBuilder.append("\n");
                
            this.printWriter.write(sBuilder.toString());
            this.printWriter.close(); 
         } catch (IOException ex) {
                //System.out.println("Boom");
            }   
    }

    
}
