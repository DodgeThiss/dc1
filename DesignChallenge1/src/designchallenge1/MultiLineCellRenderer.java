package designchallenge1;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
  
/**
 * @version 1.0 11/09/98
 */
  
public class MultiLineCellRenderer extends JPanel implements TableCellRenderer {
  JTextArea textArea;
  JLabel tLabel;
  public MultiLineCellRenderer() {
    
    
    setOpaque(true);
    textArea = new JTextArea();
    tLabel = new JLabel();
    
   
    this.add(textArea);
    this.add(tLabel);
    
    
  }
  
  public Component getTableCellRendererComponent(JTable table, Object value,
               boolean isSelected, boolean hasFocus, int row, int column) {
      
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(table.getBackground());
    }
    setFont(table.getFont());
    if (hasFocus) {
      setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
      if (table.isCellEditable(row, column)) {
        setForeground( UIManager.getColor("Table.focusCellForeground") );
        setBackground( UIManager.getColor("Table.focusCellBackground") );
      }
    } else {
      setBorder(new EmptyBorder(1, 2, 1, 2));
    }
    
    if (column == 0 || column == 6)
                    setBackground(Color.LIGHT_GRAY);
            else
                    setBackground(Color.WHITE);
    
    tLabel.setText((value == null) ? "" : value.toString());
    
    if(value instanceof DateBox){
        DateBox dBoxValue = (DateBox)value;
        String html ="<html>"+dBoxValue.getDay()+"<br>";
        
        boolean noSpace = false;
        int j = 0;  
        
        for(int i = 0; i < dBoxValue.getEvents().size(); i++){  
            if(i < 2){
                html += "<font color="+"\"" + dBoxValue.getEvents().get(i).getColor() +"\">" +dBoxValue.getEvents().get(i).getEventName()+"</font><br>"; 
            }
            else{
                j++;
                noSpace = true;
            }
        }
        if(noSpace)
            html += j + " more..."; 
        
        html+= "</html>";
        tLabel.setText(html);
    }
    
        if(value instanceof Event){
        Event eventValue = (Event)value;
        String html ="<html>"+eventValue.getDate().getDate()+"<br>";
        
        boolean noSpace = false;
        int j = 0;  
        
        
            
                html += "<font color="+"\"" + eventValue.getColor() +"\">" +eventValue.getEventName()+"</font><br>"; 
           
        
        if(noSpace)
            html += j + " more..."; 
        
        html+= "</html>";
        tLabel.setText(html);
    }
   
    
    return this;
  }
}
