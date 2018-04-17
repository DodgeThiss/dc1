/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author brighamdanielserrano
 */
public class EventFrame extends DefaultEventFrame{
    //private CalendarProgram calendar;
    //private int row;
    //private int col;
    
    private JTextField  nameField;
    private JTextField dateField;
    private JComboBox cmbColor;
    private JLabel nameLabel;
    private JLabel dateLabel;
    private JComboBox holidayCombo;
    
    ArrayList <JTextField> namesField;
    ArrayList <JLabel> namesLabel;
    
    private String [] dates;
    //private DateBox dateBox;

    public EventFrame(String name, CalendarProgram calendar,int row, int col){
        super(name, calendar, row, col);
        this.setSize(390, 120);
        
        dates = new String [3];
        
        JPanel eventPanel = new JPanel(null);
        
        nameLabel = new JLabel("Name: ");
        dateLabel = new JLabel("Date: ");
        
        nameField = new JTextField();
        dateField = new JTextField();
        cmbColor = new JComboBox();
        holidayCombo = new JComboBox();
        
        dateField.setText((String)((calendar.getMonthToday()+1) + "/" +  calendar.getDayBound() + "/" + calendar.getYearToday() ));
        dates = dateField.getText().split("/");
        
        addEvent();
        //nameField.addKeyListener(new field_Action(this));
        
        cmbColor.addItem("red");
        cmbColor.addItem("blue");
        cmbColor.addItem("green");
        
        holidayCombo.addItem("Event");
        holidayCombo.addItem("Holiday");
        
        dateLabel.setBounds(5, 40, 45, 30);
        nameLabel.setBounds(5, 5, 45, 30);
        nameField.setBounds(45, 5, 220, 30);
        cmbColor.setBounds(270, 5, 100, 30);
        dateField.setBounds(45, 40, 150, 30);
        holidayCombo.setBounds(270, 40, 100, 30);
        
        this.getContentPane().add(eventPanel);  
        
        eventPanel.add(nameField);
        eventPanel.add(dateField);
        eventPanel.add(cmbColor);
        eventPanel.add(dateLabel);
        eventPanel.add(nameLabel);   
        eventPanel.add(holidayCombo);
    }
    
    public EventFrame(String name, CalendarProgram calendar, DateBox dateBox, int row, int col){
        super(name, calendar, dateBox, row, col);
        //this.calendar = calendar;
        //this.dateBox = dateBox;
        //this.setName(name);
        this.setSize(390, 120);
        
        setEventFrame();
        
        dates = new String [3];
        
        //this.row = row;
        //this.col = col;
        
        JPanel eventPanel = new JPanel(null);
        
        nameLabel = new JLabel("Name: ");
        dateLabel = new JLabel("Date: ");
        
        nameField = new JTextField();
        dateField = new JTextField();
        cmbColor = new JComboBox();
        holidayCombo = new JComboBox();
        
        dateField.setText((String)((calendar.getMonthToday()+1) + "/" +  calendar.getDayBound() + "/" + calendar.getYearToday() ));
        dates = dateField.getText().split("/");
        
        addEvent();
        
        //nameField.addKeyListener(new field_Action(this));
        
        cmbColor.addItem("red");
        cmbColor.addItem("blue");
        cmbColor.addItem("green");
        
        holidayCombo.addItem("Event");
        holidayCombo.addItem("Holiday");
        
        holidayCombo.setBounds(270, 40, 100, 30);
        dateLabel.setBounds(5, 40, 45, 30);
        nameLabel.setBounds(5, 5, 45, 30);
        nameField.setBounds(60, 5, 220, 30);
        cmbColor.setBounds(280, 5, 100, 30);
        dateField.setBounds(60, 40, 150, 30);
        
        this.getContentPane().add(eventPanel);   
        
        eventPanel.add(nameField);
        eventPanel.add(dateField);
        eventPanel.add(cmbColor);
        eventPanel.add(dateLabel);
        eventPanel.add(nameLabel);
        eventPanel.add(holidayCombo);
    }
    
    @Override
    public void addEvent(){
        nameField.addKeyListener(new field_Action(this));
        cmbColor.addKeyListener(new field_Action(this));
        holidayCombo.addKeyListener(new field_Action(this));
    }

        
    
         class field_Action extends KeyAdapter{
         EventFrame frame;
         
         public field_Action(EventFrame frame){
         this.frame = frame;
         
         }
    
          public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                int day = Integer.parseInt(dates[1]);
                int month = Integer.parseInt(dates[0]) - 1;
                int year = Integer.parseInt(dates[2]);
                Event newEvent;
                
                
                
                if(((String)holidayCombo.getSelectedItem()).equalsIgnoreCase("holiday")){
                    //calendar.csvdataParser.save(dateString, newEvent.getEventName(), newEvent.getColor());
                    newEvent= new Holiday(nameField.getText(), new Date(year, month, day), (String)cmbColor.getSelectedItem()); 
                    //newEvent.isHoliday = true;
                    System.out.println("here tayo");
                    if(newEvent.isHoliday)
                        System.out.println("holiday talaga!");
                }
                else
                   newEvent= new Event(nameField.getText(), new Date(year, month, day), (String)cmbColor.getSelectedItem()); 
                //newEvent= new Event(nameField.getText(), new Date(year, month, day), (String)cmbColor.getSelectedItem());
                //calendar.events.add(newEvent);
                
                if(calendar.modelCalendarTable.getValueAt(row, col) instanceof Integer){
                    calendar.addDateBox(month, day, year, newEvent);
                    System.out.println("DAY!: " + day);
                    System.out.println("today: " + calendar.dayToday);
                }
                else if(calendar.modelCalendarTable.getValueAt(row,col) instanceof DateBox){
                    calendar.addToDateBox(month, day, year, newEvent); 
                    System.out.println("DAY!!!: " + day);
                    System.out.println("today!!!: " + calendar.dayToday);
                    
                }
                if(calendar.dayToday == day)
                    calendar.sendNotif();
                
                calendar.updateDateBoxes(month, day, year, row,col);
                frame.setVisible(false);
                String dateString = (month+1) + "/" + day + "/" + year;
                
                if(((String)holidayCombo.getSelectedItem()).equalsIgnoreCase("holiday")){
                    calendar.csvdataParser.save(dateString, newEvent.getEventName(), newEvent.getColor());
                }
                else
                    calendar.pipedataParser.save(dateString, newEvent.getEventName(), newEvent.getColor());
            }
          }

          public void keyTyped(KeyEvent e) {
          }

          public void keyPressed(KeyEvent e) {
          }
        
        
    }
    
    public void setEventFrame(){
        namesField = new ArrayList <JTextField>();
        namesLabel = new ArrayList <JLabel>();
        
        for(int i = 0; i < this.dateBox.getEvents().size(); i++){
            namesField.add(new JTextField(this.dateBox.getEvents().get(i).getEventName()));
            namesField.get(i).setBounds(60, (35 * (i + 2)) + 10, 220, 30);
            namesField.get(i).setVisible(true);
            this.add(namesField.get(i));
            
            namesLabel.add(new JLabel("Event " + (i + 1) + ":"));
            namesLabel.get(i).setBounds(5, (35 * (i + 2)) + 10, 55, 30);
            namesLabel.get(i).setVisible(true);
            this.add(namesLabel.get(i));
            
            this.setSize(390, 120 + (40 * (i + 1)));
            
        }
    }
}
