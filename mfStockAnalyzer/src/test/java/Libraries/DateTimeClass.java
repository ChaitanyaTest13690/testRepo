package Libraries;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeClass 
{
	
	public String getDateAndTime() 
	{
		//Creating date format
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		//Getting current date and time
		Date dt = new Date();
		
		String dt1=	dateFormat.format(dt);
		
		return dt1;
	}
	public String getTime() 
	{
		//Creating date format
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		//Getting current date and time
		Date dt = new Date();
		
		String dt1=	dateFormat.format(dt);
		
		return dt1;
	}
}
