package cisc191Fall24Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

/**
* 
 * Lead Author(s):
 * @author This is great 1
 * @author 
 * <<add additional lead authors here, with a full first and last name>>
 * 
 * Other contributors:
 * <<add additional contributors (mentors, tutors, friends) here, with contact information>>
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 * Retrieved from https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Gaddis, T. (2015). Starting out with Java: From control structures through objects. Addison-Wesley. 
 * 
 * <<add more references here>>
 *  
 * Version/date: 
 * 
 * Responsibilities of class:
 * 
 */
/**
 */
public class IO
{

	public static String readTestResults(String filename) {
		
		File file = new File(filename); 		
		Scanner scanner = null;
		String result = ""; 
		try 
		{
			scanner = new Scanner(file); 
			
			//skip the column titles
			scanner.nextLine(); //moves to actual data
			
			//read from the file line-by-line
			
			while(scanner.hasNextLine()) { //read until there are no more lines
				
				//read the next available thing from
				//the scanner 
				String line = scanner.nextLine();
				
//				String[] columns = line.split(",");
//				
//				int rank = Integer.parseInt(columns[0]);
//				String album = columns[1];
////				System.out.println(scanner.nextLine());
				
			}
			
		} 
		catch(FileNotFoundException e) 
		{
			System.out.println(e);
		}
		finally {

			//close the file
			if(scanner != null)
			{
				scanner.close();
			}
		}
		
		//return the resulting info read from the file
		
		return result; 
	}
	
	/*
	 * write to file
	 */
	public static void startTestResults(String filename, String data) {
		
			PrintWriter writer = null; 
			
		try {
			//Create a PrintWriter object to help facilitate writing to a file
			writer = new PrintWriter(filename); 
			writer.println(data);
			
		}catch(FileNotFoundException e) {
			
			
			System.out.println(e); 
			
		}finally {
			
			if(writer != null) {
				//save and close the file
				writer.close();
			}
			
			
		}
		
		
		
	}
	
	
	/*
	 * write data to the file!
	 */
	public static void appendTestResult(String filename, String data) {
		PrintWriter writer = null; 
		
		try {
			//setup the writer so that it APPENDS with the boolean argument!
			writer = new PrintWriter(new FileWriter(new File(filename), true));
			writer.println(data); 
			
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		finally
		{
			//clean-up tasks 
			if(writer!= null) {
				//save and close the file! - else it will not
				writer.close();
			}
		}
		
		
	}

	public static String readDateTime(String link) {
		
		Scanner scanner = null; 
		String content = "";
		try
		{
			URL url = new URL(link);
			scanner = new Scanner(url.openStream()); 
			
			while(scanner.hasNext()) {
				content += scanner.next();
				
			}
			
			//get the content to the proper format expected by the tester
			content = content.substring(content.indexOf("202"));
			
			//remove everything after ":00"
			content = content.substring(0, content.indexOf(":00")+3);
			
		}
		catch(Exception e) 
		{
			
		}
		finally 
		{
			//clean up 
			if(scanner != null)
			{
				scanner.close();
			}
		}
		
		System.out.println(content);
		return content;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
