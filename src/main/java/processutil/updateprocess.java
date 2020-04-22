package processutil;

import java.io.BufferedWriter;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import POM.samplePOM;
import Report.Report;
import sample.StartExecution;

public class updateprocess {
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
	static String strDiscontinuedDate = "2010-01-01";
	static String strintroducedDate = "2011-01-01";
	static String strselCompanyName = "RCA";
	static String strupdDiscontinuedDate = "2020-01-01";
	static String strupdintroducedDate = "2021-01-01";
	static int counter=0;
	static samplePOM sample=null;
	static String strInput="";
	static int totRec=0;
	
	
	static String strTitle="";
	public static BufferedWriter writer  =null;
	
	public static void updateData(WebDriver driver,BufferedWriter writer,int counter) throws IOException, InterruptedException {
		strInput="updating == " + StartExecution.strcomputerName;
		try {
			samplePOM.clearSearchBox();
		samplePOM.setSearchBox(StartExecution.strcomputerName);
	
		samplePOM.clickFindButton();
		samplePOM.selectforDelete();
	
		samplePOM.clearAll();
		
		ele=samplePOM.setintroducedDate();
		samplePOM.highlightelement(ele);
		ele.sendKeys(StartExecution.strupdintroducedDate);
	    ele=samplePOM.setDiscontinuedDate();
	    ele.sendKeys(StartExecution.strupdDiscontinuedDate);
	    samplePOM.selectforupdate();
	    strexpValue="Record Updated";
		stractValue="Record Updated";
		
		
		
		
		Report.writeDetail(strexpValue, stractValue, strInput, "Verify update New Computer",writer,counter);
		}
		catch(Exception Ex) {
			
			strexpValue="Record Updated";
			stractValue="Record not Updated";
			
			
		
			
			Report.writeDetail(strexpValue, stractValue, strInput, "Verify update New Computer",writer,counter);
		
		}
	}

	public static void verifyUpdateData(WebDriver driver,BufferedWriter writer,int counter) throws IOException, InterruptedException {
	//	Thread.sleep(1000);
		String updateText=samplePOM.verifyupdatedelete();
		System.out.println(updateText);
		strexpValue="Done! Computer " +  StartExecution.strcomputerName  + " has been updated";
		stractValue=updateText;
		
		
		
		Report.writeDetail(strexpValue, stractValue, strInput, "Verify update happened",writer,counter);
	    
	}

}
