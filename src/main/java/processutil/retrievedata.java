package processutil;

import java.io.BufferedWriter;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import POM.samplePOM;
import Report.Report;
import sample.StartExecution;

public class retrievedata {
	static String strUrl = "http://computer-database.herokuapp.com/computers";
	static String strdriverPath = "C:\\class0315\\chromeDriver.exe";
	static String sconfigFile = "src/test/resources/config.properties";
	static String stractValue = "";
	static String strexpValue = "";
	static String strHeader = "";
	static String strReportFileName = "";
	static String strinpFileName = "";

	static WebDriver driver;
	static WebElement ele = null;
	static String strcomputerName = "";
	static String strDiscontinuedDate = "";
	static String strintroducedDate = "";
	static String strselCompanyName = "";
	static String strupdDiscontinuedDate = "2020-01-01";
	static String strupdintroducedDate = "2021-01-01";
	static int counter = 0;
	static samplePOM sample = null;
	static String strInput = "";
	static int totRec = 0;

	static String strTitle = "";
	public static BufferedWriter writer = null;

	public static void retrieveData(WebDriver driver, BufferedWriter writer, int counter)
			throws IOException, InterruptedException {
		strInput = "retrieving  == " + StartExecution.strcomputerName;
		try {
			samplePOM.clearSearchBox();
			samplePOM.setSearchBox(StartExecution.strcomputerName);
			samplePOM.clickFindButton();
		  Thread.sleep(6000);
			samplePOM.selectforDelete();
			Thread.sleep(6000);
			String strcomputerName = samplePOM.setComputerName().getText();
		
			
			System.out.println("r2=" +StartExecution.strcomputerName );
			
			ele = samplePOM.setintroducedDate();
			samplePOM.highlightelement(ele);
			String strintroduceddate = ele.getAttribute("value");
				
			ele = samplePOM.setDiscontinuedDate();
			
			String strupdDiscontinuedDate = ele.getAttribute("value");

			strexpValue = StartExecution.strcomputerName + "," + StartExecution.strintroducedDate + ","
					+ StartExecution.strupdDiscontinuedDate;

			stractValue = StartExecution.strcomputerName + "," + strintroduceddate + "," + strupdDiscontinuedDate;
			System.out.println("r3=" +StartExecution.strcomputerName );

			Report.writeDetail(strexpValue, stractValue, strInput, "Verify retrieved  New Computer Information", writer, counter);
		} catch (Exception Ex) {

			strexpValue = StartExecution.strcomputerName + "," + StartExecution.strintroducedDate + ","
					+ StartExecution.strupdDiscontinuedDate;

			stractValue = StartExecution.strcomputerName + "," + strintroducedDate + "," + strupdDiscontinuedDate;

			Report.writeDetail(strexpValue, stractValue, strInput, "Verify retrieved New Computer", writer, counter);

		}
		
		samplePOM.clickcancel();
	}

}
