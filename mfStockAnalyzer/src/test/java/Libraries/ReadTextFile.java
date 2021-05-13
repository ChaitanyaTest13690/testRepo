package Libraries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadTextFile 
{
	public ReadTextFile(String filePath) throws IOException
	{
		ArrayList<String> linuxData=new ArrayList<String>();
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while ((st = br.readLine()) != null)
		{
			linuxData.add(st);
			System.out.println(st);
		}
	}
}
