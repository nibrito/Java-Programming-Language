package gui;
import javax.swing.*;

public class Label {
    public static void main(String[] args) {
        JFrame frame = new JFrame("MD. Ishfar Khan Arabi");
        JLabel label = new JLabel("He is a very Kamla boy");
        frame.add(label);
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
