package cisc191Fall24Project;

import java.util.ArrayList;
import java.util.HashMap;

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
 * 
 */
/**
 */
public class IO {
	
	// Create a HashMap object
	public static HashMap<String, ArrayList<String>> data = new HashMap<>();
	
	
	public IO() {
		readTestResults("top.csv"); 
		
	}

	public static HashMap<String, ArrayList<String>> readTestResults(String filename) {

		File file = new File(filename);
		Scanner scanner = null;
		String result = "";


		data.put("rank", new ArrayList<>());
		data.put("name", new ArrayList<>());
		data.put("date", new ArrayList<>());
		data.put("genre", new ArrayList<>()); 

		try {
			scanner = new Scanner(file);

//				if(scanner.hasNextLine()){
//					
//					String line = scanner.nextLine(); 
//					
//					
//					
//				} else {
//					
//					scanner.close();
//				}

			// read from the file line-by-line
			while (scanner.hasNextLine()) { // read until there are no more lines

				// read the next available thing from
				// the scanner
				String line = scanner.nextLine();

//				String[] columns = line.split(",");

//				String album = columns[1];

				data.get("rank").add(getRank(line));

				data.get("name").add(getName(line));

				data.get("date").add(getDate(line));

				data.get("genre").add(getGenre(line));

//						System.out.println(line);

			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			// close the file
			if (scanner != null) {
				scanner.close();
			}
		}

//		System.out.println(data);

		return data;
	}

	// method to isolate rank
	public static String getRank(String line) {

		// look for first comma in the string

		StringBuilder sb = new StringBuilder();

		int i = 0;

		for (i = 0; i < line.length(); i++) {

			if (line.charAt(i) == ',' || line.charAt(i) == '.') {
				break;
			} else {
				sb.append(line.charAt(i));
			}
		}

//		System.out.println(sb);

		return sb.toString();

	}
	


	// method to isolate name
	public static String getName(String line) {

		StringBuilder sb = new StringBuilder();

		// look for second comma in the string
		int count = 2;

		int i = 0;

		for (i = 0; i < line.length(); i++) {

			if (line.charAt(i) != ',' && count != 0) {

				continue;
				
			} else if (line.charAt(i) == ',' && line.charAt(i + 1) == ' ') {

				continue;
				
			} else if (line.charAt(i) == '"') {

				continue;

			} else if (line.charAt(i) == ',' && count != 0) {

				count--;

			} else if (line.charAt(i) != ',' && count == 0) {

				sb.append(line.charAt(i));

			} else if (line.charAt(i) == ',' && count == 0) {

				break;

			} else {

				System.out.println("?");

			}
		}

//		System.out.println(sb);

		return sb.toString();

	}

	// method to isolate genre
	public static String getGenre(String line) {

		StringBuilder sb = new StringBuilder();

		// look for fourth comma in the string
		int count = 4;

		int i = 0;

		for (i = 0; i < line.length(); i++) {

			if ((line.charAt(i) != ',' && count != 0) || line.charAt(i) == '"') {

				continue;
			
			} else if (line.charAt(i) == '"') {

				continue;

			} else if (line.charAt(i) == ',' && count != 0) {

				count--;

			} else if (line.charAt(i) != ',' && count == 0) {

				sb.append(line.charAt(i));

			} else if (line.charAt(i) == ',' && count == 0) {

				break;

			} else {

				System.out.println("?");
			}
		}

//		System.out.println(sb);

		return sb.toString();

	}

	// method to isolate date
	public static String getDate(String line) {

		StringBuilder sb = new StringBuilder();

		// look for third comma in the string
		int count = 3;

		int i = 0;

		for (i = 0; i < line.length(); i++) {

			if (line.charAt(i) != ',' && count != 0) {

				continue;
			
			} else if (line.charAt(i) == ',' && line.charAt(i + 1) == ' ') {

				continue;
				
			} else if (line.charAt(i) == '"') {

				continue;


			} else if (line.charAt(i) == ',' && count != 0) {

				count--;

			} else if (line.charAt(i) != ',' && count == 0) {

				sb.append(line.charAt(i));

			} else if (line.charAt(i) == ',' && count == 0) {

				break;

			} else {

				System.out.println("?");
			}
		}

//		System.out.println(sb);

		return sb.toString();

	}

	/*
	 * write to file
	 */
	public static void startTestResults(String filename, String data) {

		PrintWriter writer = null;

		try {
			// Create a PrintWriter object to help facilitate writing to a file
			writer = new PrintWriter(filename);
			writer.println(data);

		} catch (FileNotFoundException e) {

			System.out.println(e);

		} finally {

			if (writer != null) {
				// save and close the file
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
			// setup the writer so that it APPENDS with the boolean argument!
			writer = new PrintWriter(new FileWriter(new File(filename), true));
			writer.println(data);

		} catch (IOException e) {
			System.out.println(e);
		} finally {
			// clean-up tasks
			if (writer != null) {
				// save and close the file! - else it will not
				writer.close();
			}
		}

	}

	public static String readDateTime(String link) {

		Scanner scanner = null;
		String content = "";
		try {
			URL url = new URL(link);
			scanner = new Scanner(url.openStream());

			while (scanner.hasNext()) {
				content += scanner.next();

			}

			// get the content to the proper format expected by the tester
			content = content.substring(content.indexOf("202"));

			// remove everything after ":00"
			content = content.substring(0, content.indexOf(":00") + 3);

		} catch (Exception e) {

		} finally {
			// clean up
			if (scanner != null) {
				scanner.close();
			}
		}

		System.out.println(content);
		return content;
	}

}
