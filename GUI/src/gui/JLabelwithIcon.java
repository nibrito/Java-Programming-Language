package gui;
import javax.swing.*;

public class JLabelwithIcon {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Label with Image Example");
        ImageIcon icon = new ImageIcon("path_name.png"); // Replace with the actual path
        JLabel label = new JLabel("Label with Image", icon, JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        frame.add(label);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
