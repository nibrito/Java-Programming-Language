package gui;
import javax.swing.*;
import java.awt.event.*;

public class ComboBoxExample {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Choose Your Favourite Actress");

        // Create a JComboBox with initial items
        String[] options = {"Sydney Sweeney", "Ana de Armas","Scarlett Johanson"};
        JComboBox<String> comboBox = new JComboBox<>(options);

        // Add an ActionListener to handle selection changes
        comboBox.addActionListener(e -> {
            String selectedItem = (String) comboBox.getSelectedItem();
            System.out.println("You Selected: " + selectedItem);
        });

        // Add the combo box to the frame
        frame.add(comboBox);

        // Set frame properties
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);  // Use absolute positioning for simplicity
        comboBox.setBounds(50, 50, 200, 30);  // Position the combo box
        frame.setVisible(true);
    }
}
