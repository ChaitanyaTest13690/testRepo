package Libraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel 
{
	XSSFWorkbook wb;
	XSSFSheet sht;
	public ReadExcel(String excelPath)
	{
		try
		{
			File file = new File(excelPath);//Path=C:\\Work\\Automation\\Practice\\AMOS\\src\\TestData\\ExcelData.xlsx
			FileInputStream fis = new FileInputStream(file);
			 wb = new XSSFWorkbook(fis);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	
	public String getDataExcel(int sheetNumber, int row, int column)
	{
		String data=null;
		sht = wb.getSheetAt(sheetNumber);
		int row_limit=getRowCount(sheetNumber);
		if(row<=row_limit)
		{
			//Added code to read int value
			/*if(sht.getRow(row).getCell(column).getCellTypeEnum()==CellType.STRING)
			{
				data = sht.getRow(row).getCell(column).getStringCellValue().toString();
			}
			else
			{*/
				//End
				data = sht.getRow(row).getCell(column).getStringCellValue().toString();
			//}
		}
		return data;
	}
	
	public int getRowCount(int sheetIndex)
	{
		int row=wb.getSheetAt(sheetIndex).getLastRowNum()+1;
		return row;
	}
	public int getColumnCount(int sheetIndex)
	{
		int col=sht.getRow(sheetIndex).getLastCellNum();
		return col;
	}
}
