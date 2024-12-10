package cisc191Fall24Project;

import java.util.ArrayList;
import java.util.Collections;
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
/**
 * Class IO is used for input and output operations, particularly for reading
 * from and processing a CSV file.
 */
public class IO {

	// Declares a static HashMap to store lists of strings categorized by 'rank',
	// 'name', 'date', and 'genre'.
	public static HashMap<String, ArrayList<String>> data = new HashMap<>();

	// Constructor of the IO class initializes reading of test results from a
	// specified CSV file.
	public IO() {
		readTestResults("top.csv");
	}

	// Method readTestResults attempts to read a CSV file and organize data into a
	// HashMap.
	public static HashMap<String, ArrayList<String>> readTestResults(String filename) {

		File file = new File(filename); // Creates a File object to represent the filename.
		Scanner scanner = null; // Declares a Scanner object to null, to be used for reading the file.

		// Initializes HashMap data structure with keys and corresponding new
		// ArrayLists.
		data.put("rank", new ArrayList<>());
		data.put("name", new ArrayList<>());
		data.put("date", new ArrayList<>());
		data.put("genre", new ArrayList<>());

		try {
			scanner = new Scanner(file); // Assigns the Scanner to read from the file.

			// Continuously reads each line of the file until there are no more lines.
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine(); // Reads the next line from the file.

				// Calls methods to parse the line and add results to corresponding ArrayLists
				// in the HashMap.
				data.get("rank").add(getRank(line));
				data.get("name").add(getName(line));
				data.get("date").add(getDate(line));
				data.get("genre").add(getGenre(line));
			}

		} catch (FileNotFoundException e) {
			System.out.println(e); // Catches and prints any FileNotFoundExceptions that occur during file reading.
		} finally {
			if (scanner != null) {
				scanner.close(); // Ensures the scanner is closed after the file is read.
			}
		}

		return data; // Returns the populated HashMap.
	}

	// Method getRank extracts the 'rank' from given CSV line.
	public static String getRank(String line) {
		StringBuilder sb = new StringBuilder(); // Uses StringBuilder to build the rank string.

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == ',' || c == '.') { // Checks if the current character is a comma or period to stop appending.
				break;
			} else {
				sb.append(c); // Appends each character to build the rank string until a stop condition is
								// met.
			}
		}

		return sb.toString(); // Converts StringBuilder to String and returns it.
	}

	// Method getName extracts the 'name' from a given CSV line.
	public static String getName(String line) {
		StringBuilder sb = new StringBuilder(); // Uses StringBuilder to build the name string.
		int count = 2; // Counter to track the number of commas passed.

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c != ',' && count != 0) { // Continues to skip characters until two commas have been encountered.
				continue;
			} else if (c == ',' && count > 0) { // Decreases the counter each time a comma is encountered.
				count--;
			} else if (count == 0 && c != ',') { // Starts appending characters after the second comma until the next
													// comma.
				sb.append(c);
			} else if (count == 0 && c == ',') { // Stops appending when the third comma is reached.
				break;
			}
		}

		return sb.toString(); // Converts StringBuilder to String and returns it.
	}

	// Method getGenre extracts the 'genre' from a given CSV line.
	public static String getGenre(String line) {
		StringBuilder sb = new StringBuilder(); // Uses StringBuilder to build the genre string.
		int count = 4; // Counter to track the number of commas passed.

		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			char d = line.charAt(i + 1);
			if ((c != ',' && count != 0) || c == '"') { // Skips characters and ignores quotes until four commas are
														// encountered.
				continue;

			} else if (c == ',' && (line.charAt(i + 1) == ' ' || line.charAt(i + 1) == '0')) {

				continue;

			} else if (c == ',' && count > 0) { // Decreases the counter each time a comma is encountered.
				count--;

			} else if (count == 0 && c != ',') { // Starts appending characters after the fourth comma until the next
													// comma.
				sb.append(c);
			} else if (count == 0 && c == ',') { // Stops appending when the next comma is reached after the fourth one.
				break;
			}
		}

		return sb.toString(); // Converts StringBuilder to String and returns it.
	}


//	// method to isolate date
	public static String getDate(String line) {
		StringBuilder sb = new StringBuilder(); // Initialize StringBuilder to construct the date string.

		int count = 3; // This counter will track the number of commas to skip (first 3 commas).
		int i = 0; // Variable to iterate through each character in the line.

		for (i = 0; i < line.length(); i++) {
			char c = line.charAt(i); // Get the current character at index i.

			if (c != ',' && count != 0) {
				continue; // Skip all characters until a comma is found when count is not zero.
			} else if (c == ',' && line.charAt(i + 1) == ' ') {
				continue; // Skip commas that are followed directly by a space
			} else if (c == '"') {
				continue; // Skip all quote characters, assuming date values are not quoted.
			} else if (c == ',' && count != 0) {
				count--; // Decrement count each time a comma is encountered, until we reach the third
							// one.
			} else if (c != ',' && count == 0) {
				sb.append(c); // Append characters to the StringBuilder after the third comma is passed.
			} else if (c == ',' && count == 0) {
				break; // Break the loop once we reach the comma following the date segment.
			} else {
				System.out.println("?"); // Handle unexpected characters or conditions (optional, primarily for
											// debugging).
			}
		}

		// Reverse the string to make the year come first
		sb.reverse(); // Reverses the entire content of StringBuilder to access the year easily if
						// it's at the end of the date.

		// Extract the first four characters (now at the beginning due to reversal)
		StringBuilder year = new StringBuilder(sb.substring(0, 4)); // Extracts the first four characters, which should
																	// now represent the year.

		// Reverse the year to get it back in the correct order
		year.reverse(); // Reverses the year to correct the order after initial reversal.

		return year.toString(); // Return the correctly formatted year as a string.
	}

	// Method that isolates duplicate years from the old array list and adds the
	// data to a new one
	public ArrayList<String> getUniqueYears(ArrayList<String> years) {

		// Create a new array list of strings to hold to the unique data
		ArrayList<String> uniqueYear = new ArrayList<String>();

		// For loop that iterates through the entire ArrayList starting at the first
		// index
		for (int i = 0; i < years.size(); i++) {

			// Get the string from the index of the dataset, and assign it to a helper
			// variable
			String element = years.get(i);

			// Get the first character of the string, and assign to a helper variable
			char ch = element.charAt(0);

			// If the new array list does not already have the same year as the old array
			// list
			// and if the character at the index is a digit, add it to the new ArrayList
			// Otherwise, continue
			if ((!uniqueYear.contains(years.get(i))) && Character.isDigit(ch)) {

				uniqueYear.add(years.get(i));

			} else {

				continue;
			}

		}

		// Sort the year in chronological order
		Collections.sort(uniqueYear);

		uniqueYear.add(0, "Year");

		// Return the new ArrayList
		return uniqueYear;

	}

	// Method that isolate duplicate years from the old array list and adds the data
	// to a new one
	public ArrayList<String> getUniqueGenres(ArrayList<String> genre) {

		// Create a new array list of strings to hold to the unique data
		ArrayList<String> uniqueGenre = new ArrayList<String>();

		for (int i = 0; i < genre.size(); i++) {

			// If the new array list does not already have the same genre as the old array
			// list, add it to the new ArrayList.
			// Otherwise, continue

			if ((!uniqueGenre.contains(genre.get(i)))) {

				uniqueGenre.add(genre.get(i));

			} else {

				continue;

			}

		}

		// Sort the genres in alphabetical order
		Collections.sort(uniqueGenre);
		
		// Put "Genre" as the very first option in the dropdown
		uniqueGenre.add(0, "Genre");
		
		// Return the new ArrayList
		return uniqueGenre;
	}

}