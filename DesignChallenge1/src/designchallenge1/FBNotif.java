package designchallenge1;


import facebook.FBView;
import java.awt.Color;
import java.util.Date;
import java.lang.reflect.Field;


public class FBNotif implements NotifInterface {

    FBView facebook=  new FBView();
  


    @SuppressWarnings("deprecation")
	@Override
    public void sendNotif(Event event) {   
    	  Color color;
      	try {
      		Field field = Class.forName("java.awt.Color").getField(event.getColor());
      			color = (Color)field.get(null);
      		} catch (Exception e) {
      			color = null; // Not defined
      			}
          
        facebook.showNewEvent(event.getEventName(),event.getDate().getMonth()+1,event.getDate().
        		getDate(),event.getDate().getYear(),color);
        System.out.println();
    }
    
}
