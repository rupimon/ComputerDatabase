package processutil;

import java.io.BufferedWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import POM.samplePOM;
import Report.Report;
import sample.StartExecution;

public class addProcess {
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
	static String strupdDiscontinuedDate = "";
	static String strupdintroducedDate = "";
	
	static samplePOM sample = null;
	static String strInput = "";
	static int totRec = 0;

	static String strTitle = "";
	public static BufferedWriter writer = null;

	public static void AddData(WebDriver driver, BufferedWriter writer, int counter)
			throws IOException, InterruptedException {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='searchsubmit']")));

			samplePOM.clickAddButton();

			ele = samplePOM.setComputerName();
			samplePOM.highlightelement(ele);

			ele.sendKeys(StartExecution.strcomputerName);
			;
			ele=samplePOM.setintroducedDate();
			ele.sendKeys(StartExecution.strintroducedDate.toString());
			samplePOM.highlightelement(ele);

			
			;
			ele=samplePOM.setDiscontinuedDate();
			
			samplePOM.highlightelement(ele);

			ele.sendKeys(StartExecution.strDiscontinuedDate.toString());
			;
			samplePOM.selectCompanyName(StartExecution.strselCompanyName);
		
			samplePOM.createComputer();

			strInput = StartExecution.strcomputerName + "<br>" + StartExecution.strintroducedDate + "<br>"
					+ StartExecution.strDiscontinuedDate + "<br>" + StartExecution.strselCompanyName;

			strexpValue = "Record Added";
			stractValue = "Record Added";

			Report.writeDetail(strexpValue, stractValue, strInput, "Verify Add A New Computer", writer, counter);
		} catch (Exception Ex) {

			strexpValue = "Record Updated";
			stractValue = "Record not Updated";

			
			
			Report.writeDetail(strexpValue, stractValue, strInput, "Verify update New Computer", writer, counter);

		}
	}

	public static void VerifyAdd(WebDriver driver, BufferedWriter writer, int counter)
			throws InterruptedException, IOException {

		String addText = samplePOM.verifyupdatedelete();
		
		strexpValue = "Done! Computer " + StartExecution.strcomputerName + " has been created";
		stractValue = addText;
		strInput = StartExecution.strcomputerName;

		Report.writeDetail(strexpValue, stractValue, strInput, "Verify Add happened", writer, counter);

	}

}
