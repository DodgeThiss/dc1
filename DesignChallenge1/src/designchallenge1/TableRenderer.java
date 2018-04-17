/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge1;

import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Arturo III
 */
public class TableRenderer extends DefaultTableCellRenderer 
{
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6)
                    setBackground(Color.LIGHT_GRAY);
            else
                    setBackground(Color.WHITE);

            //setForeground(((String)value).contains("blue")? Color.BLUE : Color.RED);
    
            setBorder(null);
            setForeground(Color.black);
            return this;  
    }
    
}
