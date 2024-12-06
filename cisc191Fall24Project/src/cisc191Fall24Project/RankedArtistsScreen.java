package cisc191Fall24Project;

import java.util.Stack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The RankedArtistsFrame class extends JFrame and is designed to display
 * the ranked artists based on the selected genre and year from the GenreYearSelectionScreen.
 */
public class RankedArtistsScreen extends JFrame {
    private JTextArea artistArea1;
    private JTextArea artistArea2;
    private JTextArea artistArea3;
    private Stack<String> genreYearStack = new Stack<>();
    private static GenreYearSelectionScreen obj2 = new GenreYearSelectionScreen(null);

    /**
     * Constructor for RankedArtistsFrame sets up the frame's properties and initializes its components.
     * @param selectedYear 
     * @param selectedGenre 
     */
    public RankedArtistsScreen(String selectedGenre, String selectedYear) {
        // Set the title of the window
        setTitle("Ranked Artists of Chosen Genre and Year");
        // Set the size of the window
        setSize(800, 600); // Adjust size as needed
        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Initialize the components of the frame
        initComponents();
        
        // Push the genre and year onto the stack
        genreYearStack.push(selectedGenre);
        genreYearStack.push(selectedYear);

        setVisible(true);
        
        System.out.println(selectedGenre + " " + selectedYear);
    }

    /**
     * Initializes the components of the frame, specifically setting up the text areas.
     */
    private void initComponents() {
        // Create a panel to hold the text areas
        JPanel panel = new JPanel(new GridLayout(3, 3)); // Use GridLayout to arrange text areas vertically
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Padding around the panel

        // Initialize the text areas
        artistArea1 = new JTextArea(3, 15);
        artistArea2 = new JTextArea(3, 15);
        artistArea3 = new JTextArea(3, 15);
        
        
        // Set line wrap and wrap style 
        artistArea1.setLineWrap(true);
        artistArea1.setWrapStyleWord(true);
        artistArea2.setLineWrap(true);
        artistArea2.setWrapStyleWord(true);
        artistArea3.setLineWrap(true);
        artistArea3.setWrapStyleWord(true);

        // Add scroll panes to each text area
        panel.add(new JScrollPane(artistArea1));
        panel.add(new JScrollPane(artistArea2));
        panel.add(new JScrollPane(artistArea3));

        // Adding the panel to the frame
        add(panel);

        // Set visibility (you might control this outside the constructor based on when it needs to be shown)
        setVisible(true);
    }
    
  

    /**
     * The main method to launch the application.
     * @param 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
//            new RankedArtistsScreen(); 
        });
    }
}



