package Demo2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;

public class CandyCrushGame {

    private static final int ROWS = 6;
    private static final int COLS = 10;
    private static final int ICON_COUNT = 6;
    private static final int MAX_MOVES = 20;

    private Color[] colors = new Color[ICON_COUNT];
    private ImageIcon[] icons = new ImageIcon[ICON_COUNT];
    private URL[] iconUrls = new URL[ICON_COUNT];

    private int score = 0;
    private int moves = MAX_MOVES;
    private JButton[][] buttons = new JButton[ROWS][COLS];
    private int selectedRow = -1, selectedCol = -1;

    private JFrame frame;
    private JLabel scoreLabel;

    public CandyCrushGame() {
        loadResources();
        initializeUI();
        resetBoard();
        updateScoreLabel();
    }

    private void loadResources() {
        // Load icons and set colors
        String[] iconPaths = { "candy70x70.png", "chocolate70x70.png", "Heart70x70.png", "we70x70.png", "bomb70x70.png", "apple_red.png" };
        colors = new Color[]{Color.RED, Color.BLUE, Color.CYAN, Color.ORANGE, Color.WHITE, Color.PINK};

        for (int i = 0; i < ICON_COUNT; i++) {
            iconUrls[i] = getClass().getResource("/Demo/" + iconPaths[i]);
            icons[i] = new ImageIcon(iconUrls[i]);
        }
    }

    private void initializeUI() {
        // Set up frame and main layout
        frame = new JFrame("Candy Crush");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize score panel
        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(scoreLabel, BorderLayout.NORTH);

        // Initialize grid panel for buttons
        JPanel gridPanel = new JPanel(new GridLayout(ROWS, COLS));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JButton button = new JButton();
                button.addActionListener(new ButtonClickListener(row, col));
                button.setOpaque(true);
                button.setBorderPainted(false);
                buttons[row][col] = button;
                gridPanel.add(button);
            }
        }
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void resetBoard() {
        // Fill the board with random icons/colors
        Random random = new Random();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int iconIndex = random.nextInt(ICON_COUNT);
                buttons[row][col].setIcon(icons[iconIndex]);
                buttons[row][col].setActionCommand(String.valueOf(iconIndex));
            }
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score + " | Moves Left: " + moves);
    }

    private boolean isValidSwap(int row1, int col1, int row2, int col2) {
        return (Math.abs(row1 - row2) + Math.abs(col1 - col2)) == 1;
    }

    private void swap(int row1, int col1, int row2, int col2) {
        String tempCmd = buttons[row1][col1].getActionCommand();
        Icon tempIcon = buttons[row1][col1].getIcon();

        buttons[row1][col1].setActionCommand(buttons[row2][col2].getActionCommand());
        buttons[row1][col1].setIcon(buttons[row2][col2].getIcon());

        buttons[row2][col2].setActionCommand(tempCmd);
        buttons[row2][col2].setIcon(tempIcon);
    }

    private boolean checkAndHandleMatches() {
        boolean foundMatch = false;

        // Horizontal and vertical match checking
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String currentCmd = buttons[row][col].getActionCommand();

                if (col < COLS - 2 && currentCmd.equals(buttons[row][col + 1].getActionCommand()) &&
                        currentCmd.equals(buttons[row][col + 2].getActionCommand())) {
                    foundMatch = true;
                    clearMatchedCandies(row, col, "horizontal");
                }

                if (row < ROWS - 2 && currentCmd.equals(buttons[row + 1][col].getActionCommand()) &&
                        currentCmd.equals(buttons[row + 2][col].getActionCommand())) {
                    foundMatch = true;
                    clearMatchedCandies(row, col, "vertical");
                }
            }
        }
        return foundMatch;
    }

    private void clearMatchedCandies(int row, int col, String direction) {
        if (direction.equals("horizontal")) {
            for (int i = 0; i < 3; i++) {
                buttons[row][col + i].setIcon(null);
                buttons[row][col + i].setActionCommand("");
            }
        } else if (direction.equals("vertical")) {
            for (int i = 0; i < 3; i++) {
                buttons[row + i][col].setIcon(null);
                buttons[row + i][col].setActionCommand("");
            }
        }
        score += 15;
    }

    private void applyGravity() {
        for (int col = 0; col < COLS; col++) {
            for (int row = ROWS - 1; row >= 0; row--) {
                if (buttons[row][col].getActionCommand().equals("")) {
                    for (int k = row - 1; k >= 0; k--) {
                        if (!buttons[k][col].getActionCommand().equals("")) {
                            buttons[row][col].setActionCommand(buttons[k][col].getActionCommand());
                            buttons[row][col].setIcon(buttons[k][col].getIcon());

                            buttons[k][col].setActionCommand("");
                            buttons[k][col].setIcon(null);
                            break;
                        }
                    }
                }
            }
        }
    }

    private void refillCandies() {
        Random random = new Random();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (buttons[row][col].getActionCommand().equals("")) {
                    int iconIndex = random.nextInt(ICON_COUNT);
                    buttons[row][col].setIcon(icons[iconIndex]);
                    buttons[row][col].setActionCommand(String.valueOf(iconIndex));
                }
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private final int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedRow == -1 && selectedCol == -1) {
                selectedRow = row;
                selectedCol = col;
            } else {
                if (isValidSwap(selectedRow, selectedCol, row, col)) {
                    swap(selectedRow, selectedCol, row, col);
                    if (!checkAndHandleMatches()) {
                        swap(selectedRow, selectedCol, row, col); // Revert swap if no match
                    } else {
                        moves--;
                        applyGravity();
                        refillCandies();
                        updateScoreLabel();

                        if (moves <= 0) {
                            JOptionPane.showMessageDialog(frame, "Game Over! Your score is " + score);
                            frame.dispose();
                        }
                    }
                }
                selectedRow = -1;
                selectedCol = -1;
            }
        }
    }

    public static void main(String[] args) {
        new CandyCrushGame();
    }
}
