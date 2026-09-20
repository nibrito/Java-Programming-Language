package gui;

import javax.swing.*;
import java.awt.*;

public class TextAreaExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTextArea Example");
        JTextArea textArea = new JTextArea(5, 20); // 5 rows, 20 columns

        // Enable line wrapping
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Add a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        frame.add(scrollPane);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}