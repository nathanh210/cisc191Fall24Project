package cisc191Fall24Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The GenreYearSelectionScreen class extends JPanel and allows users
 * to select a music genre and year before proceeding further in the application.
 */
public class GenreYearSelectionScreen extends JPanel {
    private MainFrame mainFrame;
    private JComboBox<String> genreComboBox;
    private JComboBox<Integer> yearComboBox;
    private JButton submitButton;

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
        // Data arrays for combo boxes
        String[] genres = {"Progressive Rock", "Hard Bop", "Death Metal", "Hard Rock", "Thrash Metal"};
        Integer[] years = {1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999};

        // Initialize combo boxes with data
        genreComboBox = new JComboBox<>(genres);
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
     * Handles the action event when the submit button is clicked. It retrieves the selected genre and year,
     * prints them, and can transition to another screen.
     * @param e The event object representing the action event.
     */
    private void submitAction(ActionEvent e) {
        // Retrieve selections from combo boxes
        String selectedGenre = (String) genreComboBox.getSelectedItem();
        Integer selectedYear = (Integer) yearComboBox.getSelectedItem();
        // Output the selected items
        System.out.println("Genre: " + selectedGenre + ", Year: " + selectedYear);
        //Transition to next screen could be put here
    }
}
