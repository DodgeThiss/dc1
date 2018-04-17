package designchallenge1;

public class NotifContext {
	private NotifInterface notif;
	
	public NotifContext (NotifInterface notif)
	{
		this.notif = notif;
	}
	
	 public void executeNotif( Event events )
	    { 
	     	notif.sendNotif(events);
	    }
	
}
