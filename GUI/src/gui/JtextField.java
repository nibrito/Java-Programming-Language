package gui;
import javax.swing.*;
import java.awt.event.*;

public class JtextField {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTextField Example");
        JTextField textField = new JTextField(20); // 20 columns wide

        // Add an ActionListener to handle "Enter" key events
        textField.addActionListener(e -> {
            String input = textField.getText(); // Get text from the field
            System.out.println("Input: " + input);
        });

        frame.add(textField);
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        textField.setBounds(50, 30, 200, 30); // Positioning the text field
        frame.setVisible(true);
    }
}
