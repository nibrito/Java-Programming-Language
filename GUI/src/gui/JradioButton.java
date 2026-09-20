package gui;
import javax.swing.*;

public class JradioButton {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Radio Button Example");
        JRadioButton rb1 = new JRadioButton("Option 1");
        JRadioButton rb2 = new JRadioButton("Option 2");

        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);

        rb1.addActionListener(e -> System.out.println("Option 1 Selected!"));
        rb2.addActionListener(e -> System.out.println("Option 2 Selected!"));

        JPanel panel = new JPanel();
        panel.add(rb1);
        panel.add(rb2);

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
