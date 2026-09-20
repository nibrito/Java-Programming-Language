package gui;
import javax.swing.*;

public class JcheckBox {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Checkbox Example");
        JCheckBox checkBox = new JCheckBox("I Agree");

        checkBox.addItemListener(e -> {
            if (checkBox.isSelected()) {
                System.out.println("Fuck u then!");
            } else {
                System.out.println("Fuck yourself then!");
            }
        });

        frame.add(checkBox);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
