package cisc191Fall24Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * The GenreYearSelectionScreen class extends JPanel and allows users
 * to select a music genre and year before proceeding further in the application.
 */
public class GenreYearSelectionScreen extends JPanel {
    private MainFrame mainFrame;
    private JComboBox<String> genreComboBox;
    private JComboBox<String> yearComboBox;
    private JButton submitButton;
    private static IO obj = new IO();
    private HashMap<String, ArrayList<String>> data2 = IO.data;
    
    
    
    
    /**
     * Constructor sets up the panel with a reference to the main application frame
     * and initializes the user interface components.
     * @param mainFrame The main application window this panel belongs to.
     */
    public GenreYearSelectionScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        // Set layout manager to arrange child components vertically in three rows
        setLayout(new GridLayout(3, 1));
        // Initialize user interface components
        initComponents();
    }

    /**
     * Initializes combo boxes for selecting genre and year, and a submit button with event handling.
     */
    private void initComponents() {
        // Retrieve unique genres from data HashMap and convert to an array
        ArrayList<String> genreHelper = obj.getUniqueGenres(data2.get("genre"));
        String[] genre = genreHelper.toArray(new String[0]);
        
        // Retrieve unique years from the data HashMap and convert to an array
        ArrayList<String> yearHelper = obj.getUniqueYears(data2.get(("date")));
        String[] years = yearHelper.toArray(new String[0]);

        // Initialize and populate the genre selection combo box
        genreComboBox = new JComboBox<>(genre);

        // Initialize and populate the year selection combo box
        yearComboBox = new JComboBox<>(years);

        // Create the submit button and attach an action listener that triggers submitAction
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::submitAction);

        // Add a label and the genre combo box to the panel for selecting genre
        add(new JLabel("Select Genre:"));
        add(genreComboBox);

        // Add a label and the year combo box to the panel for selecting year
        add(new JLabel("Select Year:"));
        add(yearComboBox);

        // Add the submit button to the panel
        add(submitButton);
    }


    /**
     * The method checks if either selectedGenre or selectedYear is null (i.e., not selected).
     * If so, it throws an IllegalArgumentException with a message indicating that both fields are required.
     * A try-catch block captures and handles this exception, displaying an error message in a dialog box to the user.
     * @param e The event object representing the action event.
     */
    public void submitAction(ActionEvent e) {
    	 try {
             String selectedGenre = (String) genreComboBox.getSelectedItem();
             String selectedYear = (String) yearComboBox.getSelectedItem();
             if (selectedGenre == null || selectedYear == null || selectedGenre.equals("Genre") || selectedYear.equals("Year")) {
                 throw new IllegalArgumentException("Both genre and year must be selected.");
             }
             ArrayList<String> artists = getArtistsByGenreAndYear(selectedGenre, selectedYear);
             if (artists.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "No artists found for the selected genre and year.", "No Matches", JOptionPane.INFORMATION_MESSAGE);
             } else {
                 new RankedArtistsScreen(artists); // Assuming RankedArtistsScreen can accept a list of artists.
             }
         } catch (IllegalArgumentException ex) {
             JOptionPane.showMessageDialog(this, ex.getMessage(), "Selection Error", JOptionPane.ERROR_MESSAGE);
         }
     }

    /**
     * Retrieves a list of artists that match the specified genre and year.
     * @param genre The genre to match.
     * @param year The year to match.
     * @return A list of artist names that match the criteria.
     */
    private ArrayList<String> getArtistsByGenreAndYear(String genre, String year) {
        ArrayList<String> artists = new ArrayList<>();
        ArrayList<String> names = data2.get("name");
        ArrayList<String> genres = data2.get("genre");
        ArrayList<String> years = data2.get("date");
        ArrayList<String> ranks = data2.get("rank");
        ArrayList<Integer> matchedIndices = new ArrayList<>();

        for (int i = 0; i < genres.size(); i++) {
            if (genres.get(i).equals(genre) && years.get(i).contains(year)) {
                matchedIndices.add(i);
            }
        }

        for (int index : matchedIndices) {
            artists.add(names.get(index));
        }
        return artists;
    }
    
}
