package dbutil;

import java.sql.Statement;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;

import Report.Report;
import sample.StartExecution;

public class DBVerify {
	
	public static Connection connectDB() throws SQLException, ClassNotFoundException{	
		String driverString = StartExecution.strDBDriverString; //driver url 
		
		Class.forName(driverString); //register database driver
		Connection dbCon = DriverManager.getConnection(StartExecution.strDBConDetails,StartExecution.strDBUsername,StartExecution.strDBPassword); //connect to DB
		
		return dbCon; //return db connection
	}
	
	public static void VerifyAdd(WebDriver driver, BufferedWriter writer, int counter) throws IOException, SQLException {
		Connection con = null;
		String stractValue = "database connected";
		String strexpValue;
		String strInput = StartExecution.strDBConDetails;
	 
		try {
			if(!connectDB().equals(null)){
				strexpValue = stractValue;
				con = connectDB();
				Report.writeDetail(strexpValue, stractValue, strInput, "Verify database connection", writer, counter);
			}else{
				strexpValue = stractValue;
				stractValue = "database not connected";
				strInput = "null input";
				Report.writeDetail(strexpValue, stractValue, strInput, "Verify database connection", writer, counter);
				System.out.println("Database not connected");
			}
			
			String databaseName = strInput;
			strInput = StartExecution.strTableName;
			stractValue = "table " + strInput + " exists in database " + databaseName;
			
			DatabaseMetaData metadata = con.getMetaData();
			 ResultSet rs = metadata.getTables(null,null,strInput,null);
	         if(rs.next()) {
	        	 strexpValue = stractValue;
	        	 Report.writeDetail(strexpValue, stractValue, strInput, "Verify table exists", writer, counter);
	         }else{
	        	 strexpValue = stractValue;
	 			 stractValue = "table " + strInput + " doesn't exist in database " + databaseName;
	 			 Report.writeDetail(strexpValue, stractValue, strInput, "Verify table exists", writer, counter);
	         }
			 
	       if(!stractValue.equals("table " + strInput + " doesn't exist in database " + databaseName)){ 
	         String strTableName = strInput;
	         strInput = StartExecution.strcomputerName;
	         stractValue = "computer name " + strInput +" exists in database table " + strTableName;
			 String sql = "select * from " + strTableName + " where " + StartExecution.strComputerNameColumn + " = " + strInput;
			 
			 Statement stmt = con.createStatement();
			 ResultSet rset = stmt.executeQuery(sql);

			 if(rset.next()) {
	        	 strexpValue = stractValue;
	        	 Report.writeDetail(strexpValue, stractValue, strInput, "Verify computer name exists in table", writer, counter);
	         }else{
	        	 strexpValue = stractValue;
	 			 stractValue = "Computer name " + strInput +" doesn't exist in database table " + strTableName;
	 			 Report.writeDetail(strexpValue, stractValue, strInput, "Verify table exists in table", writer, counter);
	         }
		    }
	      }catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			strexpValue = stractValue;
			stractValue = "Class not found";
			Report.writeDetail(strexpValue, stractValue, strInput, "Verify database connection", writer, counter);
			System.out.println("Class not found!");
		}
	}
}
