package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import POM.samplePOM;
import Report.Report;
import processutil.addProcess;
import processutil.deleteprocess;
import processutil.retrievedata;
import processutil.updateprocess;

public class StartExecution {
	
	//static String sconfigFile = "src/test/resources/properties/config.properties";
	
	static String sconfigFile = "src/test/resources/executionjar/properties/config.properties";
	
	
	
	static String strUrl = "";
	static String strdriverPath = "";
	
	static String stractValue = "";
	static String strexpValue = "";
	static String strHeader = "";
	static String strReportFileName = "";
	static String strinpFileName = "";
	static int numColumns;
	public static String strDeleteVerification = "";
	public static String strUpdateVerification = "";
	public static String strRetrieveVerification = "";
	public static String strinsertVerification = "";

	static WebDriver driver;
	static WebElement ele = null;
	public static String strcomputerName = "";
	public static String strDiscontinuedDate = null;
	public static String strintroducedDate = null;
	public static String strselCompanyName = "";
	public static String strupdDiscontinuedDate = "";
	public static String strupdintroducedDate = "";
	static int counter = 1;
	static samplePOM sample = null;
	static String strInput = "";
	static int totRec = 0;
	static String inputRecord = "";
	static ArrayList inputRow = null;
	static String strinputType = "";
	public static String strDBVerification = "";
	public static String strDBDriverString= "";
	public static String strDBConDetails = "";
	public static String strDBUsername = "";
	public static String strDBPassword = "";
	public static String strTableName = "";
	public static String strComputerNameColumn = "";
	
	static String strTitle = "";
	public static BufferedWriter writer = null;

	
	public static void startProcess() throws IOException, InterruptedException, InvalidFormatException, SQLException {		
		// =============================================================================================================
		// Initialize Routne and Invoke the application
		// =============================================================================================================

		getConfigParameters(sconfigFile);
		InitializeReport(strHeader, strReportFileName);
		InitializeDriver(strdriverPath);
		invokeApplication();
		VerifyTitle();
		// =============================================================================================================
		// Get the Input Rec either from Input Text File or Excel
		// =============================================================================================================
		
		if (strinputType.equals("File")) {
			totRec = fileutil.readfile.getNoOfRec(strinpFileName);
			// =============================================================================================================
			// Based on the file input run the test
			// =============================================================================================================
			
			for (int recNo = 0; recNo < totRec; recNo++) {
				getinfofromFile(recNo);
				innerProcess();			
			}
		}
		
		if (strinputType.equals("Excel")){
			
			totRec = excelutil.excelInput.numExcelRows(strinpFileName,numColumns);
			// =============================================================================================================
			// Based on the excel input run the test
			// =============================================================================================================
			
			for (int recNo = 0; recNo < totRec; recNo++) {
				getinfofromexcel(recNo);
				innerProcess();
				
			}
		}
		
		produceHTMLReport();
		driver.quit();

	}
	
	public static void innerProcess() throws IOException, InterruptedException, InvalidFormatException, SQLException {
		if (strinsertVerification.contentEquals("Y")) {
			addProcess.AddData(driver, writer, counter++);
			Thread.sleep(1000);
			addProcess.VerifyAdd(driver, writer, counter++);
		}
		
		if (strUpdateVerification.contentEquals("Y")) {
			updateprocess.updateData(driver, writer, counter++);
			updateprocess.verifyUpdateData(driver, writer, counter++);

		}
		if (strRetrieveVerification.equals("Y")) {
			System.out.println("r1=" + strRetrieveVerification);
			retrievedata.retrieveData(driver, writer, counter++);
		}
		
		// =============================================================================================================
		// DB Verification
		// =============================================================================================================

		if (strDBVerification.contentEquals("Y")) {
			dbutil.DBVerify.VerifyAdd(driver, writer, counter++);

		}
		// =============================================================================================================

		if (strDeleteVerification.contentEquals("Y")) {
			deleteprocess.deleteData(driver, writer, counter++);
			deleteprocess.verifyDeleteData(driver, writer, counter++);

		}
	}
//====================================================================================
	
	public static void getConfigParameters(String sconfigFile) {

		try{
			InputStream input = new FileInputStream(sconfigFile);
			Properties prop = new Properties();

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			strTitle = prop.getProperty("title");
			strdriverPath = prop.getProperty("driverPath");
			strUrl = prop.getProperty("url");
			strHeader = prop.getProperty("Header");
			strReportFileName = prop.getProperty("ReportFileName");
			strinpFileName = prop.getProperty("inputFileName");
			strinputType = prop.getProperty("inputType");

			strDBVerification = prop.getProperty("DBVerification");
			strDeleteVerification = prop.getProperty("DeleteOpt");
			strUpdateVerification = prop.getProperty("UpdateOpt");
			strRetrieveVerification = prop.getProperty("RetrieveOpt");
			strinsertVerification = prop.getProperty("InsertOpt");
			
			if (strinputType.equals("Excel")){
				numColumns = Integer.valueOf(prop.getProperty("numColumns"));
			}
			
			if (strDBVerification.contentEquals("Y")) {
				strDBDriverString =  prop.getProperty("strDBDriverString");
				strDBConDetails = prop.getProperty("strDBConDetails");
				strDBUsername = prop.getProperty("strDBUsername");
				strDBPassword = prop.getProperty("strDBPassword");
				strTableName = prop.getProperty("strTableName");
				strComputerNameColumn = prop.getProperty("strTableName");
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void getinfofromFile(int recNo){		
		inputRecord = fileutil.readfile.inputrecord.get(recNo);
		
		strcomputerName = inputRecord.split(",")[0];
		strDiscontinuedDate = inputRecord.split(",")[1];
		strintroducedDate = inputRecord.split(",")[2];
		strselCompanyName = inputRecord.split(",")[3];
		strupdDiscontinuedDate = inputRecord.split(",")[1];
		strupdintroducedDate = inputRecord.split(",")[2];
	}

	public static void getinfofromexcel(int recNo) throws IOException, InvalidFormatException {
		inputRow = excelutil.excelInput.readExcel(strinpFileName, numColumns).get(recNo);
		
		strcomputerName = (String) inputRow.get(0);
		strDiscontinuedDate = (String) inputRow.get(1);
		System.out.println(strDiscontinuedDate);
		strintroducedDate = (String) inputRow.get(2);
		strselCompanyName = (String) inputRow.get(3);
		strupdDiscontinuedDate = (String) inputRow.get(1);
		strupdintroducedDate = (String) inputRow.get(2);
		
		//String strexpValue = "";
		String stractValue = "Row " + recNo + " of excel file retrieved successfully!";
		if(!strcomputerName.equals(null) && !strDiscontinuedDate.equals(null) && !strintroducedDate.equals(null) && !strselCompanyName.equals(null)){
			strexpValue = stractValue;
			Report.writeDetail(strexpValue, stractValue, strInput, "Verify excel row read", writer, counter++);
		}else{
			strexpValue = "Row " + recNo + " of excel file has all non-null va!ues";;
			stractValue = "Row " + recNo + " of excel file doesn't have all non-null va!ues";
			String fileName = "sample.xlsx";

			Report.writeDetail(strexpValue, stractValue, fileName, "Verify excel row read", writer, counter++);
		}
	}

	
	public static void InitializeReport(String strHeader, String strReportFileName) throws IOException {
		writer = Report.writeHeader(strHeader, strReportFileName);

	}

	public static void InitializeDriver(String strdriverPath) {

		System.setProperty("webdriver.chrome.driver", strdriverPath);

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		samplePOM samplePOM = new samplePOM(driver);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// use executeScript() method and pass the arguments
		// Here i pass values based on css style. Yellow background color with solid red
		// color border. 0

	}

	public static void invokeApplication() {

		driver.get(strUrl);

	}

	public static void VerifyTitle() throws IOException {
		stractValue = driver.getTitle();

		Report.writeDetail(strTitle, stractValue, strUrl, "Title Verification", writer, counter++);

	}

	public static void produceHTMLReport() throws IOException {
		Report.closeFile(writer, counter);
	}

}



