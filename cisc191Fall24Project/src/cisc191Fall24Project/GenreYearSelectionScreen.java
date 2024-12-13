package cisc191Fall24Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;

/**
 * This class extends JPanel and allows users to select a music genre and year
 * before proceeding further in the application. It facilitates data selection
 * and interacts with other components to display results based on the
 * selections.
 */
public class GenreYearSelectionScreen extends JPanel {
    private MainFrame mainFrame; // Reference to the main application window
    private JComboBox<String> genreComboBox; // Dropdown for selecting genres
    private JComboBox<String> yearComboBox; // Dropdown for selecting years
    private JButton submitButton; // Button to submit the selection
    private static IO obj = new IO(); // IO object for data operations
    private HashMap<String, ArrayList<String>> data2 = IO.data; // Data model containing music info
    private JButton addGenreButton; // Button to trigger the addition of genres

    /**
     * Constructor sets up the panel with the main application frame and initializes
     * UI components.
     * 
     * @param mainFrame The main application window this panel belongs to.
     */
    public GenreYearSelectionScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(3, 1)); // Use GridLayout to arrange components
        initComponents(); // Initialize the components
    }

    /**
     * Initializes the UI components used on the panel.
     */
    private void initComponents() {
        // Get unique genres from data and populate the genre combo box
        ArrayList<String> genreHelper = obj.getUniqueGenres(data2.get("genre"));
        String[] genre = genreHelper.toArray(new String[0]);

        // Get unique years from data and populate the year combo box
        ArrayList<String> yearHelper = obj.getUniqueYears(data2.get("date"));
        String[] years = yearHelper.toArray(new String[0]);

        genreComboBox = new JComboBox<>(genre); // Initialize genre combo box with genres
        yearComboBox = new JComboBox<>(years); // Initialize year combo box with years
        yearComboBox.setEnabled(false); // Disable year selection initially

        submitButton = new JButton("Submit"); // Initialize the submit button
        submitButton.addActionListener(this::submitAction); // Add action listener to submit button

        addGenreButton = new JButton("Add Genre"); // Initialize the add genre button
        addGenreButton.addActionListener(this::addAction); // Add action listener to add genre button

        // Add components to the panel
        add(new JLabel("Select Genre:")); // Add genre label
        add(genreComboBox); // Add genre combo box
        add(new JLabel("Select Year:")); // Add year label
        add(yearComboBox); // Add year combo box
        add(submitButton); // Add submit button
        add(addGenreButton); // Add add genre button
    }

    /**
     * Action performed when the submit button is pressed. It validates selections
     * and processes data retrieval.
     * 
     * @param e The event that triggers this action.
     */
    public void submitAction(ActionEvent e) {
    	try {
            // Retrieve the genre selected by the user from the genreComboBox.
            String selectedGenre = (String) genreComboBox.getSelectedItem();
            // Retrieve the year selected by the user from the yearComboBox.
            String selectedYear = (String) yearComboBox.getSelectedItem();
            // Get a list of artists matching the selected genre and year from a data source.
            ArrayList<String> artists = getArtistsByGenreAndYear(selectedGenre, selectedYear);

            // Check if the list of artists is empty.
            if (artists.isEmpty()) {
                // If no artists found, show an informational message dialog to the user.
                JOptionPane.showMessageDialog(this, "No artists found for the selected genre and year.", "No Matches",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // If artists are found, save the results to a file.
                saveResultsToFile(artists);
                // Show a confirmation message dialog that the artists were successfully saved.
                JOptionPane.showMessageDialog(this, "Artists saved to file successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            // Catch any exceptions that occur during the retrieval or file saving process.
            // Show an error message dialog displaying the exception message.
            JOptionPane.showMessageDialog(this, "Error processing request: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Action to populate the year dropdown based on selected genre.
     * 
     * @param e The event that triggers this action.
     */
    private void addAction(ActionEvent e) {
        String selectedGenre = (String) genreComboBox.getSelectedItem();
        ArrayList<String> yearList = new ArrayList<>();
        ArrayList<String> uniqueYearList = new ArrayList<>();
        ArrayList<String> years = data2.get("date");
        ArrayList<String> genres = data2.get("genre");

        yearComboBox.removeAllItems(); // Clear all items in the year combo box

        for (int i = 0; i < genres.size(); i++) {
            if (genres.get(i).equals(selectedGenre)) {
                yearList.add(years.get(i));
            }
        }

        Collections.sort(yearList); // Sort the years
        for (String year : yearList) {
            if (!uniqueYearList.contains(year)) {
                uniqueYearList.add(year);
            }
        }

        for (String year : uniqueYearList) {
            yearComboBox.addItem(year); // Add year to the combo box
        }

        yearComboBox.setEnabled(true); // Enable the year combo box
    }

    /**
     * Retrieves a list of artists that match the specified genre and year.
     * 
     * @param genre The genre to match.
     * @param year  The year to match.
     * @return A list of artist names that match the criteria.
     */
    private ArrayList<String> getArtistsByGenreAndYear(String genre, String year) {
    	// Initialize an ArrayList to hold the matching artists' names.
        ArrayList<String> artists = new ArrayList<>();
        // Retrieve the list of all names from the data.
        ArrayList<String> names = data2.get("name");
        // Retrieve the list of all genres from the data.
        ArrayList<String> genres = data2.get("genre");
        // Retrieve the list of all years (or dates) from the data.
        ArrayList<String> years = data2.get("date");

        // Loop through the genres list to find matches.
        for (int i = 0; i < genres.size(); i++) {
            // Check if the current genre matches the selected genre and the year contains the selected year.
            if (genres.get(i).equals(genre) && years.get(i).contains(year)) {
                // If a match is found, add the corresponding artist's name to the artists list.
                artists.add(names.get(i));
            }
        }
        // Return the list of artists that match the given genre and year.
        return artists;
    }

    /**
     * Saves the list of matched artists to a text file and opens it in TextEdit on
     * macOS.
     * 
     * @param artists List of artists to save.
     * @throws IOException If an error occurs during file writing.
     */
    private void saveResultsAndOpenInTextEdit(ArrayList<String> artists) throws IOException {
    	// Get the home directory of the current user
        String homeDirectory = System.getProperty("user.home");
        // Construct the file path for the results file on the user's desktop
        String filename = Paths.get(homeDirectory, "Desktop", "artists_results.txt").toString();

        // Try-with-resources to automatically handle closing the PrintWriter
        try (PrintWriter writer = new PrintWriter(filename)) {
            // Iterate through each artist in the list
            for (String artist : artists) {
                // Write each artist's name followed by a newline to the file
                writer.println(artist);
            }
        }
        // After saving the file, open it using TextEdit application on macOS
        Runtime.getRuntime().exec("open -a TextEdit " + filename);
    }

    /**
     * Saves the results to a new file with a timestamp, ensuring a new file is created each time.
     * 
     * @param artists List of artists to save.
     * @throws IOException If there is an issue writing to the file.
     */
    private void saveResultsToFile(ArrayList<String> artists) throws IOException {
        // Format the current date and time to append to the file name
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // Determine the path to the user's desktop directory
        String homeDirectory = System.getProperty("user.home");
        // Create the full path for the new file using the timestamp
        String filename = Paths.get(homeDirectory, "Desktop", "artists_results_" + timestamp + ".txt").toString();

        // Use PrintWriter to write each artist's name to the new file
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (String artist : artists) {
                writer.println(artist); // Write the artist name to the file
            }
        }
        // Open the newly created file in TextEdit for viewing
        Runtime.getRuntime().exec("open -a TextEdit " + filename);
    }
}