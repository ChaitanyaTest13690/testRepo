package TestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FunctionLibrary.Global;
import FunctionLibrary.HTML_Report;
import Libraries.DateTimeClass;
import Libraries.ReadExcel;
import Libraries.WriteExcel;
import Pages.AMFI;
import Pages.Common.baseClass;

public class amcIdentification extends baseClass
{
	DateTimeClass dt;
	Global obj = new Global();
	HTML_Report htmlrep;
	int dataReaderCount=0;
	int dataReaderExcelCount=0;
	WriteExcel objExcelFile = new WriteExcel();
	boolean ldapSync=true;
	boolean multiTenant=true;
	//for parellel execution declaring new webdriver
	public WebDriver driver;
	
	
	@BeforeClass
	public void browserSettings() throws IOException, InterruptedException
	{
		driver = initDriver();
		obj.cfnRootPath();
		obj.cfnModuleRootPath("MF Stock Analyzer");
		htmlrep = new HTML_Report(driver);
		driver.get(prop.getProperty("url"));
	}
	
	@Test (priority=1, dataProvider="testData")
	public void amfiURLAccessCheck(String endType, String category, String cap, String amc) throws Exception
	{
		if (dataReaderCount==1){}
		else
		{
			htmlrep.mstrTC_Name = "To verify user can search Small Cap funds from AMFI web";
			htmlrep.mstrTC_Desc = "This test case is desiged to retrive all Small Cap funds from the AMFI web";
			htmlrep.mstrModuleName = "MF Stock Analyzer";
	
			AMFI amfi = new AMFI(driver);
			//Step 1
			htmlrep.details_append("To verify the User Can able to access AMFI Research & Information web page","","","");
			htmlrep.details_append("To verify the User Can able to access AMFI Research & Information web page",
					"User able to access the AMFI Research & Information web page ",
					"User can Access the AMFI Research & Information web page",amfi.amfiResearchInfoPageValidation());
			
			//Step 2
			
		}
		
		if (dataReaderCount==1)
		{
			dataReaderCount = dataReaderCount +1;
		}
	}
	
	@DataProvider (name="testData")
	public String[][] passDataCreateVM()
	{
		ReadExcel obj = new ReadExcel(globalPath+prop.getProperty("excelFile"));
		int rows = obj.getRowCount(0);
		System.out.println(rows);
		//int column = obj.getColumnCount(0);
		int column = 4;
		String [][]data_createProjectData = new String[rows][column];
		System.out.println("Test Data");
		for (int i=0;i<rows;i++)
		{
			System.out.println("Row:"+i+"");
			for (int j=0; j<column;j++)
			{
				data_createProjectData[i][j]=obj.getDataExcel(0, i, j);
				System.out.println(data_createProjectData[i][j]);
			}
			System.out.println("****End of Row****");
		}
		dataReaderCount=1;
		return data_createProjectData;
	}
	
	@AfterClass
	public void TearDown() throws Exception 
	{
		htmlrep.end_Report();
		driver.close();
		driver.quit();
		driver=null;
		obj = null;
		htmlrep = null;
	}
}
