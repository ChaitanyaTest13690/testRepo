/*package Libraries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.json.Json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class ConvertExcel2Json 
{
	static String excelFile = "C:\\Users\\S00297\\workspace\\airbus\\src\\test\\resources\\RestAssured.xlsx";
	public static void mian(String ar[])
	{
		File file = new File(excelFile);
		getExcelDataAsJsonObject(file);
	}
	public static JsonObject getExcelDataAsJsonObject(File excelFile) 
	{

	    JsonObject sheetsJsonObject = new JsonObject();
	    Workbook workbook = null;

	    try {
	        workbook = new XSSFWorkbook(excelFile);
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

	        JsonArray sheetArray = new JsonArray();
	        ArrayList<String> columnNames = new ArrayList<String>();
	        Sheet sheet = workbook.getSheetAt(i);
	        Iterator<Row> sheetIterator = sheet.iterator();

	        while (sheetIterator.hasNext()) {

	            Row currentRow = sheetIterator.next();
	            JsonObject jsonObject = new JsonObject();

	            if (currentRow.getRowNum() != 0) {

	                for (int j = 0; j < columnNames.size(); j++) {

	                    if (currentRow.getCell(j) != null) {
	                        if (currentRow.getCell(j).getCellTypeEnum() == CellType.STRING) {
	                            jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getStringCellValue());
	                        } else if (currentRow.getCell(j).getCellTypeEnum() == CellType.NUMERIC) {
	                            jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getNumericCellValue());
	                        } else if (currentRow.getCell(j).getCellTypeEnum() == CellType.BOOLEAN) {
	                            jsonObject.addProperty(columnNames.get(j), currentRow.getCell(j).getBooleanCellValue());
	                        } else if (currentRow.getCell(j).getCellTypeEnum() == CellType.BLANK) {
	                            jsonObject.addProperty(columnNames.get(j), "");
	                        }
	                    } else {
	                        jsonObject.addProperty(columnNames.get(j), "");
	                    }

	                }

	                sheetArray.add(jsonObject);

	            } else {
	                // store column names
	                for (int k = 0; k < currentRow.getPhysicalNumberOfCells(); k++) {
	                    columnNames.add(currentRow.getCell(k).getStringCellValue());
	                }
	            }

	        }

	        sheetsJsonObject.add(workbook.getSheetName(i), sheetArray);

	    }

	    return sheetsJsonObject;
	}
}*/