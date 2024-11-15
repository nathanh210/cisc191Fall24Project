package cisc191Fall24Project;

import javax.swing.*;
import java.awt.*;

/**
 * The MainFrame class extends JFrame and serves as the primary window
 * for the Music Finder app. It manages different screens using
 * a CardLayout to facilitate navigation between the Login and GenreYearSelection screens.
 */
public class MainFrame extends JFrame {
    private CardLayout cardLayout;  // Layout manager to allow switching between panels
    private JPanel cardPanel;       // Panel that holds different screens

    /**
     * Constructor for MainFrame sets up the frame's properties and initializes its components.
     */
    public MainFrame() {
        // Set the title of the window
        setTitle("Music Finder");
        // Set the size of the window
        setSize(600, 400);
        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Initialize the components and layout of the frame
        initComponents();
    }

    /**
     * Initializes the components of the frame, setting up the CardLayout and adding screens.
     */
    private void initComponents() {
        // Initialize CardLayout
        cardLayout = new CardLayout();
        // Create a panel with CardLayout
        cardPanel = new JPanel(cardLayout);
        
        // Create instances of the screens
        LoginScreen loginScreen = new LoginScreen(this);
        GenreYearSelectionScreen genreYearSelectionScreen = new GenreYearSelectionScreen(this);

        // Add the screens to the cardPanel with identifiers
        cardPanel.add(loginScreen, "LoginScreen");
        cardPanel.add(genreYearSelectionScreen, "GenreYearSelectionScreen");

        // Adding the cardPanel to the frame
        add(cardPanel);
        
        // Note: Adding loginScreen appears 
        LoginScreen loginScreen1 = new LoginScreen(this);
        cardPanel.add(loginScreen1, "LoginScreen"); // Potentially problematic
    }

    /**
     * Shows a screen based on the provided name.
     * @param name The name of the screen to be displayed.
     */
    public void showScreen(String name) {
        // Use CardLayout to show the panel associated with 'name'
        cardLayout.show(cardPanel, name);
    }

    /**
     * The main method to launch the application.
     * @param args 
     */
    public static void main(String[] args) {
//        // Make the frame visible
//        SwingUtilities.invokeLater(() -> {
//            new MainFrame().setVisible(true);
//        });
//        
        System.out.println(IO.readTestResults("top.csv"));
        
    }
}
