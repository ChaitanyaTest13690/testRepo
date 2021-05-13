/*package Libraries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTest extends AllConfigurations
{
	String filePath=logFilePath;
	DateTimeClass dt = new DateTimeClass();
	String fileExtension=".txt";
	String s = dt.getDateAndTime();
	//System.out.println(s);
	//FileWriter
	FileWriter fw=new FileWriter(filePath+s+fileExtension);
	public LogTest() throws IOException
	{
		
	}
	public void logWriter(String details) throws IOException
	{
		String s = dt.getDateAndTime()+"->"+details;
		System.out.println(s);
		fw.write(s);
		fw.write("\r\n");
	}
	public void fileClose() throws IOException
	{
		fw.close();
	}
}*/