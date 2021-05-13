/*package Libraries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogTestRest extends AllConfigurations
{
	String filePathRest=logFilePathRest+"/Airbus/";
	DateTimeClass dt = new DateTimeClass();
	String fileExtension=".txt";
	//FileWriter
	FileWriter fw;
	public LogTestRest(String fileName) throws IOException
	{
		fw = new FileWriter(filePathRest+fileName+fileExtension);
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