package processutil;

import java.io.BufferedWriter;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import POM.samplePOM;
import Report.Report;
import sample.StartExecution;

public class deleteprocess {

	static String strUrl = "http://computer-database.herokuapp.com/computers";
	static String strdriverPath = "C:\\class0315\\chromeDriver.exe";
	static String sconfigFile="src/test/resources/config.properties";
	static String stractValue="";
	static String strexpValue="";
	static String strHeader="";
	static String strReportFileName="";
	static String strinpFileName="";
	 
	static WebDriver driver;
     static  WebElement ele= null;
	static String strcomputerName = "RRR";
	static String strDiscontinuedDate = "";
	static String strintroducedDate = "";
	static String strselCompanyName = "";
	static String strupdDiscontinuedDate = "";
	static String strupdintroducedDate = "";
	
	static samplePOM sample=null;
	static String strInput="";
	static int totRec=0;
	
	
	static String strTitle="";
	public static BufferedWriter writer  =null;
	
	
	
	public static void deleteData(WebDriver driver,BufferedWriter writer,int counter) throws IOException, InterruptedException {

		try {
			samplePOM.clearSearchBox();
	samplePOM.setSearchBox(StartExecution.strcomputerName);
	

	samplePOM.clickFindButton();
	
	samplePOM.selectforDelete();
	
	samplePOM.clickDelete();
	strInput=StartExecution.strcomputerName;
	strexpValue="Record Deleted";
	stractValue="Record Deleted";
	 counter++;
	Report.writeDetail(strexpValue, stractValue, strInput, "Verify Delete New Computer",writer,counter);
		}
	catch(Exception Ex) {
		
		strexpValue="Record Deleted";
		stractValue="Record not Deleted";
		
		
		
		
		Report.writeDetail(strexpValue, stractValue, strInput, "Verify delete Computer",writer,counter);
	
	}
	
}
	
	
	
	public static void verifyDeleteData(WebDriver driver,BufferedWriter writer,int counter) throws IOException, InterruptedException {
	
		String updateText=samplePOM.verifyupdatedelete();
		System.out.println(updateText);
		strexpValue="Done! Computer "   + "has been deleted";
		//stractValue=updateText;
		
		
		
		Report.writeDetail(strexpValue, updateText, strInput, "Verify delete happened",writer,counter);
	    
	}
	
	
}
