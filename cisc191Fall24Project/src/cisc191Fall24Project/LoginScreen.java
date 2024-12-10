package cisc191Fall24Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The LoginScreen class extends JPanel and provides a user interface
 * for entering a username before proceeding to the next part of the application.
 */
public class LoginScreen extends JPanel {
    private JTextField usernameField;
    private JButton submitButton;
    private MainFrame mainFrame;

    /**
     * Constructor initializes the LoginScreen with a reference to the MainFrame.
     * @param mainFrame The main application window this panel will interact with.
     */
    public LoginScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        // Set the layout manager to BorderLayout for arranging components
        setLayout(new BorderLayout());
        // Initialize user interface components
        initComponents();
    }

    /**
     * Initializes the user interface components for username entry.
     */
    private void initComponents() {
        // Create and configure the username input field
        usernameField = new JTextField(20);
        JPanel inputPanel = new JPanel();
        // Add a label and the username field to the input panel
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        // Add the input panel to the center of this panel
        add(inputPanel, BorderLayout.CENTER);

        // Create the submit button and set up the action listener
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::submitAction);
        // Add the submit button to the south of this panel
        add(submitButton, BorderLayout.SOUTH);
    }


    /**
     * Handles the action event when the submit button is clicked. It checks if the username is empty,
     * and if not, transitions to the next screen; otherwise, it displays an error message.
     * @param e The event object representing the action event.
     */
    private void submitAction(ActionEvent e) {
        String username = usernameField.getText().trim(); // Ensure whitespace is not considered.
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } else {
            System.out.println("Username entered: " + username);
            mainFrame.showScreen("GenreYearSelectionScreen");
        }
    }
    
}
