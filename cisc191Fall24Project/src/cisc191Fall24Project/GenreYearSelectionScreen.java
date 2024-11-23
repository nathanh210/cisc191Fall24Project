package cisc191Fall24Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
    	ArrayList<String> genreHelper = data2.get("genre");
    	
    	String[] genre = genreHelper.toArray(new String[0]);
    	
    	ArrayList<String> yearHelper = data2.get("date");
    	
    	String[] years = yearHelper.toArray(new String[0]);
   
        // Initialize combo boxes with data
        genreComboBox = new JComboBox<>(genre);
        yearComboBox = new JComboBox<>(years);

        // Setup submit button and attach action listener
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::submitAction);

        // Add components to this panel
        add(new JLabel("Select Genre:"));
        add(genreComboBox);
        add(new JLabel("Select Year:"));
        add(yearComboBox);
        add(submitButton);
    }

    /**
     * The method checks if either selectedGenre or selectedYear is null (i.e., not selected). 
     * If so, it throws an IllegalArgumentException with a message indicating that both fields are required.
     * A try-catch block captures and handles this exception, displaying an error message in a dialog box to the user.    
     *  * @param e The event object representing the action event.
     */
    private void submitAction(ActionEvent e) {
        try {
            String selectedGenre = (String) genreComboBox.getSelectedItem();
            Integer selectedYear = (Integer) yearComboBox.getSelectedItem();
            if (selectedGenre == null || selectedYear == null) {
                throw new IllegalArgumentException("Both genre and year must be selected.");
                
            }
            System.out.println("Genre: " + selectedGenre + ", Year: " + selectedYear);
            // Proceed to next screen if both selections are valid
            // mainFrame.showScreen("NextScreenName"); // Uncomment or modify as needed
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
