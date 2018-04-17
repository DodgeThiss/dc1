package designchallenge1;


import sms.SMS;
import sms.SMSView;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Calendar;

@SuppressWarnings("deprecation")
public class SMSNotif implements NotifInterface {

	
	SMSView smsview = new SMSView ();
    
	public static Calendar toCalendar(Date date){ 
		System.out.println(date.getYear());
		  Calendar cal = Calendar.getInstance();
		  cal.set(date.getYear(),date.getMonth(), date.getDate());
		  return cal;
		}
    
    
	@Override
    public void sendNotif(Event event) {   
		
		  Color color;
	      	try {
	      		Field field = Class.forName("java.awt.Color").getField(event.getColor());
	      			color = (Color)field.get(null);
	      		} catch (Exception e) {
	      			color = null; // Not defined
	      			}
    	
    	Calendar newcal = toCalendar(event.getDate());
    	SMS smsnew = new SMS(event.getEventName(), newcal , color);
        smsview.sendSMS(smsnew);
    }
    
}
