package cisc191Fall24Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GenreYearSelectionScreen extends JPanel {
    private MainFrame mainFrame;
    private JComboBox<String> genreComboBox;
    private JComboBox<Integer> yearComboBox;
    private JButton submitButton;

    public GenreYearSelectionScreen(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(3, 1));  // Simple grid layout
        initComponents();
    }

    private void initComponents() {
        // Mock data for genre and years
        String[] genres = {"Progressive Rock", "Hard Bop", "Death Metal", "Hard Rock", "Thrash Metal"};
        Integer[] years = {1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999};

        genreComboBox = new JComboBox<>(genres);
        yearComboBox = new JComboBox<>(years);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this::submitAction);

        add(new JLabel("Select Genre:"));
        add(genreComboBox);
        add(new JLabel("Select Year:"));
        add(yearComboBox);
        add(submitButton);
    }

    private void submitAction(ActionEvent e) {
        String selectedGenre = (String) genreComboBox.getSelectedItem();
        Integer selectedYear = (Integer) yearComboBox.getSelectedItem();
        System.out.println("Genre: " + selectedGenre + ", Year: " + selectedYear);

        // Handle moving to the next screen here
       
    }
}