package gui;
import javax.swing.*;
import java.awt.event.*;


public class Jlist {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JList Example");

        // Create a list of items
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        JList<String> list = new JList<>(items);

        // Enable single selection
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add a button to show the selected item
        JButton button = new JButton("Show Selected Item");
        JLabel label = new JLabel("Selected item will appear here.");

        button.addActionListener(e -> {
            String selectedItem = list.getSelectedValue(); // Get selected value
            label.setText("Selected: " + selectedItem);   // Display the selected item
        });

        // Layout components in a panel
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(list)); // Add scroll pane for large lists
        panel.add(button);
        panel.add(label);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
