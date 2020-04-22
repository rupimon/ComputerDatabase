package excelutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelInput {

	
	public static ArrayList<ArrayList<String>> readExcel(String excelFilePath, int numColumns) throws IOException{
		  
		FileInputStream file = new FileInputStream(new File(excelFilePath));
		
		//ArrayList of ArrayList is like a dynamic matrix
		ArrayList<ArrayList<String>> testCases = new ArrayList<ArrayList<String>>();
		
		//Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        //Get first/desired sheet from the workbook
        Sheet sheet = workbook.getSheetAt(0);
        
        //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) 
        {
        	ArrayList<String> dataRow = new ArrayList<String>();
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();        
            
            for(int colNum = 0; colNum < numColumns; colNum++){
            	dataRow.add(row.getCell(colNum,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString()); //add cell value to dataRow
            }
        	
            testCases.add(dataRow); // add test case dataRow to testCases
        }
	   return testCases;
	}
	
	public static int numExcelRows(String excelFile, int numColumns) throws InvalidFormatException, IOException{
		int rowCount = readExcel(excelFile, numColumns).size();
		return rowCount;
	}

}
