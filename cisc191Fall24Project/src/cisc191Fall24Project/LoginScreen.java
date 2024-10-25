package cisc191Fall24Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginScreen extends JPanel {
    private JTextField usernameField;
    private JButton submitButton;
    private MainFrame mainFrame;

    public LoginScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        // Username field
        usernameField = new JTextField(20);
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        add(inputPanel, BorderLayout.CENTER);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::submitAction);
        add(submitButton, BorderLayout.SOUTH);
    }

    private void submitAction(ActionEvent e) {
        String username = usernameField.getText();
        // Show entered username
        System.out.println("Username entered: " + username);
        
        // Move to next screen 
        mainFrame.showScreen("");
    }
}    