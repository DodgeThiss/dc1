/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

/**
 *
 * @author Arturo III
 */

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CalendarProgram{
	
        /**** Day Components ****/
	public int yearBound, monthBound, dayBound, yearToday, monthToday, dayToday;

        /**** Swing Components ****/
        public JLabel monthLabel, yearLabel;
	public JButton btnPrev, btnNext, btnToday;
        public JComboBox cmbYear;
	public JFrame frmMain;
        public DefaultEventFrame eventFrame;
	public Container pane;
	public JScrollPane scrollCalendarTable;
	public JPanel calendarPanel;
        public JButton quickEventBtn;
        public JTextField quickEventTextField;
        
        /**** Calendar Table Components ***/
	public JTable calendarTable;
        public DefaultTableModel modelCalendarTable;
        
        //public ArrayList <Event> events;
        public ArrayList <DateBox> dateBoxes;
        
        public Font font;
        public DataParser csvdataParser;
        public DataParser pipedataParser;
        
        boolean isOpen = false;
        
        public CalendarProgram()
        {
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception e) {}
                
                int fontSize = 14;
                font = new Font(Font.SANS_SERIF, Font.PLAIN, fontSize);
                
//                events = new ArrayList<>();
                dateBoxes = new ArrayList<>();
                
                
                csvdataParser = new CSVDataParser(this, "events.csv", "events.csv");
                csvdataParser.parseDataAndGenerateOutput();
                
                pipedataParser = new PSVDataParser(this, "piped-events.csv", "piped-events.csv");
                pipedataParser.parseDataAndGenerateOutput();
                
                //addDateBoxEvents(events);
                
		frmMain = new JFrame ("Calendar Application"); //change variable name
                frmMain.setSize(660, 750);
                
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                quickEventBtn = new JButton("+");
                quickEventTextField = new JTextField();

		monthLabel = new JLabel ("January");
		yearLabel = new JLabel ("Change year:");
		cmbYear = new JComboBox();
		btnPrev = new JButton ("<");
		btnNext = new JButton (">");
                btnToday = new JButton ("Today");
		modelCalendarTable = new DefaultTableModel()
                {
                    @Override
                    public boolean isCellEditable(int rowIndex, int mColIndex)
                    {
                        return false;
                    }
                    
                    @Override
                    public Class getColumnClass(int columnIndex) {
                        return String.class;
                      }
                   
                };
                
		calendarTable = new JTable(modelCalendarTable);
                calendarTable.addMouseListener(new calendarTable_Action(this));
                calendarTable.addMouseListener(new MouseAdapter()   
                {  
                    @Override
                    public void mouseClicked(MouseEvent evt)  
                    {  
                        int col = calendarTable.getSelectedColumn();  
                        int row = calendarTable.getSelectedRow();
                    }
                    
                });
                
		scrollCalendarTable = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);

		calendarPanel.setBorder(BorderFactory.createTitledBorder("Calendar"));
		
		btnPrev.addActionListener(new btnPrev_Action());
		btnNext.addActionListener(new btnNext_Action());
                btnToday.addActionListener(new btnToday_Action());
		cmbYear.addActionListener(new cmbYear_Action());
                quickEventBtn.addActionListener(new btnQuickEvent_Action());
		
		pane.add(calendarPanel);
		calendarPanel.add(monthLabel);
		calendarPanel.add(yearLabel);
		calendarPanel.add(cmbYear);
		calendarPanel.add(btnPrev);
		calendarPanel.add(btnNext);
                calendarPanel.add(btnToday);
		calendarPanel.add(scrollCalendarTable); //change variable name
                calendarPanel.add(quickEventBtn);
                calendarPanel.add(quickEventTextField);
                
                calendarPanel.setBackground(Color.white);
                frmMain.setBackground(Color.white);
                pane.setBackground(Color.white);
                
                btnToday.setFont(font);
                monthLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize + 2));
                btnPrev.setFont(font);
                btnNext.setFont(font);
                  
                calendarPanel.setBounds(5, 0, 650, 720);
                monthLabel.setBounds(20, 55, 150, 50);
		yearLabel.setBounds(360, 610, 160, 40);
		cmbYear.setBounds(460, 610, 160, 40);
		btnPrev.setBounds(480, 70, 30, 20);
		btnNext.setBounds(580, 70, 30, 20);
                btnToday.setBounds(520, 70, 50, 20);
		scrollCalendarTable.setBounds(20, 100, 600, 500);
                quickEventBtn.setBounds(180, 70, 30, 20);
                quickEventTextField.setBounds(180, 70, 150, 20);
                
		frmMain.setResizable(false);
		frmMain.setVisible(true);
                
		GregorianCalendar cal = new GregorianCalendar();
                
                dayToday = cal.get(GregorianCalendar.DAY_OF_MONTH); //current day
		dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH); //current day
		monthBound = cal.get(GregorianCalendar.MONTH); //current month
		yearBound = cal.get(GregorianCalendar.YEAR); // current year
		monthToday = monthBound; 
		yearToday = yearBound;
		
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
                    for (int i=0; i<7; i++){
                            modelCalendarTable.addColumn(headers[i]);
                    }
		
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background

		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);

		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		calendarTable.setRowHeight(76);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);
		
		for (int i = yearBound-100; i <= yearBound+100; i++)  // yearbound is the current year
                {
                    cmbYear.addItem(String.valueOf(i)); // adds year to combo box
                }	
		refreshCalendar (monthBound, yearBound); //Refresh calendar
                sendNotif();
	}
        
        public void refreshCalendar(int month, int year)
        {
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j; 
			
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
                quickEventTextField.setVisible(false);
                
		if (month == 0 && year <= yearBound-10) 
                    btnPrev.setEnabled(false);
		if (month == 11 && year >= yearBound+100)
                    btnNext.setEnabled(false);
                
		monthLabel.setText(months[month] + " " + year);
		monthLabel.setBounds(monthLabel.getBounds());
                
		cmbYear.setSelectedItem(""+year); // change variable name
		
                for (i = 0; i < 6; i++)
                    for (j = 0; j < 7; j++)
                        modelCalendarTable.setValueAt(null, i, j);
                            
		GregorianCalendar cal = new GregorianCalendar(year, month, 1); //change cal to calendar
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
		for (i = 1; i <= nod; i++)
                {
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
                        
			modelCalendarTable.setValueAt(i, row, column);
                        updateDateBoxes(monthToday, i, yearToday, row, column); 
                        updateHolidays(monthToday, i, row, column);
		}
		//calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
                calendarTable.setDefaultRenderer(String.class, new MultiLineCellRenderer());
                
	}
        
        
	class calendarTable_Action extends MouseAdapter{
            String description;
            CalendarProgram calendar;
            
            
            public calendarTable_Action(CalendarProgram calendar){
                this.calendar = calendar;
            }
            public void mouseClicked(MouseEvent evt)  
                    {  
                        String [] dateInfo;
                        int col = calendarTable.getSelectedColumn();  
                        int row = calendarTable.getSelectedRow();
                        String info;
                        
                        if(modelCalendarTable.getValueAt(row, col) != null){

                            if(modelCalendarTable.getValueAt(row, col) instanceof DateBox){
                                dayBound = ((DateBox)modelCalendarTable.getValueAt(row, col)).getDay();
                                eventFrame = new EventFrame("New Event", calendar,(DateBox)modelCalendarTable.getValueAt(row, col), row, col);
                                
                            }
                            else if(modelCalendarTable.getValueAt(row,col) instanceof Integer){
                                info = modelCalendarTable.getValueAt(row, col).toString();
                                dateInfo = info.split("\n");
                                dayBound = Integer.parseInt(dateInfo[0]);

                                eventFrame = new EventFrame("New Event", calendar, row, col);
                            }
                            else if(modelCalendarTable.getValueAt(row,col) instanceof Event){
                                dayBound = ((Event)modelCalendarTable.getValueAt(row, col)).getDate().getDate();
                                eventFrame = new EventFrame("New Event", calendar, row, col);
                            }

                            if(evt.getClickCount() == 2){
                                eventFrame.setVisible(true);
                                eventFrame.setBounds(evt.getX(), evt.getY(), eventFrame.getWidth(), eventFrame.getHeight());   
                            }
                        }
                    }     
        }
        
    class btnQuickEvent_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
                    if(isOpen){
                        closeQuickEvent();
                    }
                    else
                        openQuickEvent();
		}
	}
        
        public void openQuickEvent(){
            quickEventTextField.setVisible(true);
            quickEventTextField.addKeyListener(new field_Action());
            quickEventBtn.setBounds((quickEventTextField.getWidth() + quickEventBtn.getX() + 5), 70, 30, 20);
            quickEventBtn.setText("-");
            isOpen = true;
        }
        
        class field_Action extends KeyAdapter{
            public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                String info = quickEventTextField.getText();
                String[] firstSplit = info.split(" on ");
                
                String eventName = firstSplit[0];
                String dates [] = firstSplit[1].split("/");
                Date thisDate = new Date(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]));
                String thisColor = "red";
                
                addToDateBox(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]), Integer.parseInt(dates[2]),new Event(eventName, thisDate, thisColor));
                
                GregorianCalendar cal = new GregorianCalendar(Integer.parseInt(dates[2]), Integer.parseInt(dates[1]), 1); //change cal to calendar
                int nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
                int som = cal.get(GregorianCalendar.DAY_OF_WEEK);
                
                for (int i = 1; i <= nod; i++)
                {
                int row = new Integer((i+som-2)/7);
                int column  =  (i+som-2)%7;
                
                //modelCalendarTable.setValueAt(i, row, column);
                updateDateBoxes(thisDate.getMonth(), thisDate.getDate(), thisDate.getYear(), row, column);
                }
                
                }
            }
        }
        
        public void closeQuickEvent(){
            quickEventTextField.setVisible(false);
            quickEventBtn.setBounds(180, 70, 30, 20);
            quickEventBtn.setText("+");
            isOpen = false;
            
        }
        
        public void createQuickEvent(){
            String quickEvent = quickEventTextField.getText();
            String eventName = "";
            Date eventDate;
            
            if(quickEvent.contains(" on ")){
                eventName = (quickEvent.split(" on "))[0];   
            }
                
        }
	
	class btnPrev_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (monthToday == 0)
                        {
				monthToday = 11;
				yearToday -= 1;
			}
			else
                        {
				monthToday -= 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
	class btnNext_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (monthToday == 11)
                        {
				monthToday = 0;
				yearToday += 1;
			}
			else
                        {
				monthToday += 1;
			}
			refreshCalendar(monthToday, yearToday);
		}
	}
        
        class btnToday_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
                   monthToday = monthBound;
                   yearToday = yearBound;
                   refreshCalendar(monthToday, yearToday);
		}
	}
        
	class cmbYear_Action implements ActionListener
        {
		public void actionPerformed (ActionEvent e)
                {
			if (cmbYear.getSelectedItem() != null)
                        {
				String b = cmbYear.getSelectedItem().toString();
				yearToday = Integer.parseInt(b);
				refreshCalendar(monthToday, yearToday);
			}
		}
	}
        
        
        /* public void addEvents(Event event){
        events.add(event);
        }*/

        /*public ArrayList<Event> getEvents() {
        return events;
        }*/

    public JLabel getMonthLabel() {
        return monthLabel;
    }

    public JLabel getYearLabel() {
        return yearLabel;
    }

    public int getYearToday() {
        return yearToday;
    }

    public int getMonthToday() {
        return monthToday;
    }

    public int getDayBound() {
        return dayBound;
    }
    
    public void updateDateBoxes(int month, int day, int year, int row, int col){
        for(int i = 0; i < dateBoxes.size(); i++){ 
                if(dateBoxes.get(i).getMonth() == month &&
                    dateBoxes.get(i).getYear() == year &&
                    dateBoxes.get(i).getDay() == day){ 
                    //System.out.println("herenaman");
                        modelCalendarTable.setValueAt(dateBoxes.get(i), row, col);        
                }

        }      
    }
    
    public void updateHolidays(int month, int day, int row, int col){
        //System.out.println("---------------");
        
        for(int i = 0; i < dateBoxes.size(); i++){ 
            //System.out.println("-----------" );
                if(dateBoxes.get(i).getMonth() == month &&
                    dateBoxes.get(i).getDay() == day){
                        for(int j = 0; j < dateBoxes.get(i).getEvents().size(); j++){
                            if(dateBoxes.get(i).getEvents().get(j).isHoliday){
                            modelCalendarTable.setValueAt(dateBoxes.get(i).getEvents().get(j), row, col);
                            System.out.println("holiday");
                            }
                    }
                }

        }  
        
    }
        
    public void addDateBox(int month, int day, int year, Event newEvent){
        dateBoxes.add(new DateBox(month, day, year));
        for(int i = 0; i < dateBoxes.size(); i++){
            if(dateBoxes.get(i).getMonth() == month &&
                dateBoxes.get(i).getYear() == year &&
                dateBoxes.get(i).getDay() == day){ 
                    dateBoxes.get(i).addEvent(newEvent);
                    System.out.println(dateBoxes.get(i).getDay() + " = " + day);
                }
            
        }
    }
    
    
    public void addDateBoxEvents(Event newEvent){
        
            dateBoxes.add(new DateBox(newEvent.getDate().getMonth(), newEvent.getDate().getDate(), newEvent.getDate().getYear()));
            dateBoxes.get(dateBoxes.size() - 1).addEvent(newEvent);
            
            System.out.println("Triggeroing");
        
    }
    
    public void addToDateBox(int month, int day, int year, Event newEvent){
        for(int i = 0; i < dateBoxes.size(); i++){
            if(dateBoxes.get(i).getMonth() == month &&
                dateBoxes.get(i).getYear() == year &&
                dateBoxes.get(i).getDay() == day){ 
                    dateBoxes.get(i).addEvent(newEvent);
                }
            
        }
    }
    
    public boolean addToDateBox(int month, int day, int year, Event newEvent, boolean beenHere){
        for(int i = 0; i < dateBoxes.size(); i++){
            if(dateBoxes.get(i).getMonth() == month &&
                dateBoxes.get(i).getYear() == year &&
                dateBoxes.get(i).getDay() == day){ 
                    dateBoxes.get(i).addEvent(newEvent);
                    return beenHere = true;
                }
            
        }
        return beenHere = false;
    }
    
    
    public void sendNotif ()
    { 
    	
    	//System.out.println("DAY IS" + dateBoxes.get(5).getEvents().get(0).getDate().getDay());
    for(int i = 0; i < dateBoxes.size(); i++)
    		{

        if(dateBoxes.get(i).getMonth() == monthToday  &&
      		  dateBoxes.get(i).getYear() == yearToday &&
      				  dateBoxes.get(i).getDay() == dayToday)
        			{ 
      	  	 		NotifContext notif = new NotifContext(new FBNotif());
      	  		   for(int g = 0; g < dateBoxes.get(i).getEvents().size(); g++){
      	  			   notif.executeNotif(dateBoxes.get(i).getEvents().get(g));
                                   //System.out.println("Day Dito!: " + dateBoxes.get(i).getEvents().get(g).getDate().getDate());
                           }
      	  		   
                                notif = new NotifContext(new SMSNotif());
      	  		   for(int g = 0; g < dateBoxes.get(i).getEvents().size(); g++)
      	  			   notif.executeNotif(dateBoxes.get(i).getEvents().get(g));
        			}

    		}
     
    }
    
    
    
}
