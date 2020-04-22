package Report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sample.StartExecution;

public class Report {
	static int totFail = 0;

	static BufferedWriter writer = null;

	public static BufferedWriter writeHeader(String strHeader, String strReportFileName) throws IOException {
		// =============================================================================================
		//             This is creating the Header of the Report
		//              called 1 time 
		// ============================================================================================
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("MMddyyyyHHmmss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

		strReportFileName = strReportFileName + dtf1.format(now) + ".html";

		BufferedWriter writer = new BufferedWriter(new FileWriter(strReportFileName));
		writer.write("<h2>Test - Computer Database (CRUD Opertion) " + dtf.format(now)
				+ "</h2> ");
		
		// =============================================================================================
		//             Here the Options are Printed
		//              called 1 time 
		// ============================================================================================
			
		
		
		writer.write("<h5>DeleteVerification:&nbsp;" + StartExecution.strDeleteVerification + "&nbsp;UpdateVerification:&nbsp;" + StartExecution.strUpdateVerification + "</h5>");
		writer.write("<h5>RetrieveVerificatio:&nbsp;" +StartExecution.strRetrieveVerification+ "&nbsp;insertVerification:&nbsp;" + StartExecution.strinsertVerification + "</h5>");
		writer.write("<h5>DBVerification:&nbsp;" + StartExecution.strDBVerification + "</h5><br>");
		
		writer.write("<table border='20'><tr bgcolor='pink'><td>" + " ScenarioNo "
				+ "</td><td>ScenarioDescription</td><td>" + "input" + "</td><td>" + "expectedValue" + "</td><td>"
				+ "actualValue" + "</td><td >" + "status" + "</td></tr>");
		
		  

		return writer;

	}

	public static void writeDetail(String strexpValue, String stractValue, String strinput,
			String strScenarioDescription, BufferedWriter writer, int counter) throws IOException {
		String ScenarioNo = "Scenario-" + counter;

 // System.out.println(strexpValue   + "=" +       stractValue    + "=" +     ScenarioNo    + "=" +    strScenarioDescription  + "=" +    strinput      );

  
		if (strexpValue.contentEquals(stractValue))
			writer.write("<tr><td bgcolor='lightblue'>" + ScenarioNo + "</td><td>" + strScenarioDescription + "</td><td>" + strinput
					+ "</td><td>" + strexpValue + "</td><td>" + stractValue
					+ "</td><td bgcolor='lightgreen'>passed</td></tr>");
		else {
			totFail = totFail + 1;

			writer.write("<tr><td  bgcolor='lightblue'>" + ScenarioNo + "</td><td>" + strScenarioDescription + "</td><td>" + strinput
					+ "</td><td>" + strexpValue + "</td><td>" + stractValue + "</td><td bgcolor='red'>fail</td></tr>");
			
			
			
		}
		

	}

	public static void closeFile(BufferedWriter writer, int counter) throws IOException {
		int totPass = counter - totFail;

		writer.write("</table>");
		writer.write("<center><table border='2'><tr bgcolor='grey'><td>" + " Total No. of Test Cases=>" + "</td><td>" + counter
				+ "</td></tr>");
		writer.write(
				"<tr bgcolor='red'><td>" + " Total No. of Failed Test Cases=>" + "</td><td>" + totFail + "</td></tr>");
		writer.write("<tr bgcolor='green'><td>" + " Total No. of Passed Test Cases=>" + "</td><td>" + totPass
				+ "</td></tr>");

		writer.close();
	}
}
