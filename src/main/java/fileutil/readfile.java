package fileutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class readfile {
	public  static HashMap<Integer, String>inputrecord = new HashMap<Integer, String>();

	
	public static int getNoOfRec(String strinpFileName) throws IOException {
		
		
		 StringBuilder sb = new StringBuilder();
         int count=0;
	        try{
	        	BufferedReader br = Files.newBufferedReader(Paths.get(strinpFileName));
	            // read line by line
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line).append("\n");
	                inputrecord.put(count,line);
	                count++;
	            }

	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	        }

	        

	        for (Object objectName : inputrecord.keySet()) {
	        	   System.out.println(objectName);
	        	   System.out.println(inputrecord.get(objectName));
	        }
		
		return count;

		
		
	}
	public static int  readRecord(String strinpFileName,int RecNo) throws IOException {
		
		
		 StringBuilder sb = new StringBuilder();
        int count=0;
	        try{
	        	BufferedReader br = Files.newBufferedReader(Paths.get(strinpFileName));
	            // read line by line
	            String line;
	            while ((line = br.readLine()) != null) {
	                sb.append(line).append("\n");
	                count++;
	            }

	        } catch (IOException e) {
	            System.err.format("IOException: %s%n", e);
	        }

	        
		
		
		return count;

		
		
	}
}
