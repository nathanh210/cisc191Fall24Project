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
 * 
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

			// read from the file line-by-line
			while (scanner.hasNextLine()) { // read until there are no more lines

				// read the next available thing from
				// the scanner
				String line = scanner.nextLine();

				data.get("rank").add(getRank(line));

				data.get("name").add(getName(line));

				data.get("date").add(getDate(line));

				data.get("genre").add(getGenre(line));

			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			// close the file
			if (scanner != null) {
				scanner.close();
			}
		}

		// Remove duplicates from the date list based on years
		removeDuplicateYears(data.get("date"));
		removeDuplicateGenres(data.get("genre"));
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

		return sb.toString();

	}

	// method to isolate name, similar issue: does not isolate all names and includes one or two dates
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

		return sb.toString();

	}

//	// method to isolate date
//	public static String getDate(String line) {
//
//		StringBuilder sb = new StringBuilder();
//
//		// look for third comma in the string
//		int count = 3;
//
//		int i = 0;
//
//		for (i = 0; i < line.length(); i++) {
//
//			if (line.charAt(i) != ',' && count != 0) {
//
//				continue;
//
//			} else if (line.charAt(i) == ',' && line.charAt(i + 1) == ' ') {
//
//				continue;
//
//			} else if (line.charAt(i) == '"') {
//
//				continue;
//
//			} else if (line.charAt(i) == ',' && count != 0) {
//
//				count--;
//
//			} else if (line.charAt(i) != ',' && count == 0) {
//
//				sb.append(line.charAt(i));
//
//			} else if (line.charAt(i) == ',' && count == 0) {
//
//				break;
//
//			} else {
//
//				System.out.println("?");
//			}
//		}
//
//		// Reverse the string to make the year come first
//		sb.reverse();
//
//		// Extract the first four characters (now at the beginning due to reversal)
//		StringBuilder year = new StringBuilder(sb.substring(0, 4));
//
//		// Reverse the year to get it back in the correct order
//		year.reverse();
//
//		return year.toString();
//	}
	
	public static String getDate(String line) {
	    StringBuilder sb = new StringBuilder();
	    // look for third comma in the string
	    int count = 3;

	    int i = 0;
	    boolean inQuotes = false; // Keep track of whether we're inside quotes

	    for (i = 0; i < line.length(); i++) {
	        char c = line.charAt(i);

	        // Toggle inQuotes status on encountering a quote
	        if (c == '"') {
	            inQuotes = !inQuotes;
	            continue; // Skip the quote character
	        }

	        // Skip all characters inside quotes
	        if (inQuotes) {
	            continue;
	        }

	        // Processing only outside of quotes
	        if (c == ',' && count > 0) {
	            count--;
	            continue;
	        }

	        if (count == 0 && c != ',') {
	            sb.append(c);
	        } else if (count == 0 && c == ',') {
	            break; // Stop reading the date at the next comma after count has reached 0
	        }
	    }

	    // Date format is almost always consistent as "dd MMMM yyyy"
	    String[] parts = sb.toString().trim().split(" ");
	    
	    return parts[parts.length - 1]; // Return only the year part
	}
	

	public static String getYear(String date) {
		
		StringBuilder sb = new StringBuilder();
		
		// the year is always the last 4 characters of the date string
		if (date.length() >= 4) {
			
			sb.append(date.substring(date.length() - 4)); // Gets the last 4 characters
		}
		return sb.toString();
	}

	public static void removeDuplicateYears(ArrayList<String> years) {
		
	    for (int i = 0; i < years.size(); i++) {
	    	
	        String year1 = years.get(i);
	        
	        for (int j = years.size() - 1; j > i; j--) {
	        	
	            String year2 = years.get(j);
	            
	            if (year1.equals(year2)) {
	            	
	                years.remove(j); // Remove the duplicate year
	            }
	        }
	    }
	}
	
	public static void removeDuplicateGenres(ArrayList<String> genres) {
		
	    for (int i = 0; i < genres.size(); i++) {
	    	
	        String genre1 = genres.get(i);
	        
	        for (int j = genres.size() - 1; j > i; j--) {
	        	
	            String genre2 = genres.get(j);
	            
	            if (genre1 != null && genre1.equals(genre2)) {
	            	
	                genres.remove(j); // Remove the duplicate genre
	            }
	        }
	    }
	}

}