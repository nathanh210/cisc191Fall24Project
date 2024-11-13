package cisc191Fall24Project;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainFrame() {
        setTitle("Music Finder");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center on screen
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        LoginScreen loginScreen = new LoginScreen(this);
        GenreYearSelectionScreen genreYearSelectionScreen = new GenreYearSelectionScreen(this);

        cardPanel.add(loginScreen, "LoginScreen");
        cardPanel.add(genreYearSelectionScreen, "GenreYearSelectionScreen");

        add(cardPanel);
        
        // Initialize panels
        LoginScreen loginScreen1 = new LoginScreen(this);
        cardPanel.add(loginScreen1, "LoginScreen");
        
        // Add more panels 
        add(cardPanel);
    }

    public void showScreen(String name) {
        cardLayout.show(cardPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}