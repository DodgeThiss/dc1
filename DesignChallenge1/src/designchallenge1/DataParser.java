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


/**
 *
 * @author brighamdanielserrano
 */
abstract class DataParser {
  
  StringBuilder sBuilder = new StringBuilder();
  String fileName;
  String toFileName;
  CalendarProgram calendar;
  PrintWriter printWriter;
  BufferedReader buffReader;
  
    public DataParser(CalendarProgram calendar, String fileName, String toFileName){
        this.fileName = fileName;
        this.calendar = calendar;
        this.toFileName = toFileName;
    }
  
    public  void parseDataAndGenerateOutput(){
        readData();
        processData();
        save(toFileName);
    }
    
    abstract void processData();
    abstract void save(String date, String eventName, String color);
    
    void readData() {    
            System.out.println("Reading data from csv file");
        try{    
            
            String csvFile = this.fileName;
            buffReader = new BufferedReader(new FileReader(csvFile));
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

    }
  
    
    public void save(String newFileName){
    System.out.println("Saving to CSV!");
    
        try {
            this.printWriter = new PrintWriter(new File(newFileName));
            
            System.out.println("Flip: " + sBuilder.toString());
            this.printWriter.write(this.sBuilder.toString());
            this.printWriter.close(); 
            
        } catch (IOException ex) {
            System.out.println("Boom");
        }
    }
    
    
    
    public void saveToCsv(String date, String eventName, String color){
        String newFileName = "events.csv";
        try {
            this.printWriter = new PrintWriter(new File(newFileName));
            sBuilder.append(date);
            sBuilder.append("|");
            sBuilder.append(eventName);
            sBuilder.append("|");
            sBuilder.append(color);
            sBuilder.append("\n");
                
            this.printWriter.write(sBuilder.toString());
            this.printWriter.close(); 
         } catch (IOException ex) {
                System.out.println("Boom");
            }       

        }  
    
    
    }
