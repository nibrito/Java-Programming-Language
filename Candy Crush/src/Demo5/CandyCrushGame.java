package Demo5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;

public class CandyCrushGame {
    private static final int ROWS = 6;
    private static final int COLS = 10;
    private static final int ICON_COUNT = 6;
    private static final int MAX_MOVES = 20;
    private static final int ANIMATION_DELAY = 5; // milliseconds per frame
    private static final int ANIMATION_STEPS = 20; // number of steps for smooth animation

    private Color[] colors = new Color[ICON_COUNT];
    private ImageIcon[] icons = new ImageIcon[ICON_COUNT];
    private URL[] iconUrls = new URL[ICON_COUNT];

    private int score = 0;
    private int moves = MAX_MOVES;
    private AnimatedButton[][] buttons = new AnimatedButton[ROWS][COLS];
    private int selectedRow = -1, selectedCol = -1;

    private JFrame frame;
    private JLabel scoreLabel;
    private boolean isAnimating = false;

    // Custom button class to handle animations
    private class AnimatedButton extends JButton {
        private Point currentPosition;
        private Point targetPosition;
        private Timer animationTimer;
        private double progress = 0;

        public AnimatedButton() {
            super();
            currentPosition = new Point();
            targetPosition = new Point();
        }

        public void startAnimation(Point target, ActionListener onComplete) {
            if (animationTimer != null && animationTimer.isRunning()) {
                animationTimer.stop();
            }

            currentPosition = getLocation();
            targetPosition = target;
            progress = 0;

            animationTimer = new Timer(ANIMATION_DELAY, e -> {
                progress += 1.0 / ANIMATION_STEPS;
                if (progress >= 1.0) {
                    setLocation(targetPosition);
                    ((Timer) e.getSource()).stop();
                    if (onComplete != null) {
                        onComplete.actionPerformed(null);
                    }
                } else {
                    int newX = (int) (currentPosition.x + (targetPosition.x - currentPosition.x) * progress);
                    int newY = (int) (currentPosition.y + (targetPosition.y - currentPosition.y) * progress);
                    setLocation(newX, newY);
                }
            });
            animationTimer.start();
        }

        public void cancelAnimation() {
            if (animationTimer != null) {
                animationTimer.stop();
            }
        }
    }

    public CandyCrushGame() {
        loadResources();
        initializeUI();
        resetBoard();
        updateScoreLabel();
    }

    private void loadResources() {
        String[] iconPaths = { "candy70x70.png", "chocolate70x70.png", "Heart70x70.png", "we70x70.png", "bomb70x70.png", "apple_red.png" };
        colors = new Color[]{Color.RED, Color.BLUE, Color.CYAN, Color.ORANGE, Color.WHITE, Color.PINK};

        for (int i = 0; i < ICON_COUNT; i++) {
            iconUrls[i] = getClass().getResource("/Demo/" + iconPaths[i]);
            icons[i] = new ImageIcon(iconUrls[i]);
        }
    }

    private void initializeUI() {
        frame = new JFrame("Candy Crush");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(scoreLabel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(null); // Using null layout for custom positioning
        gridPanel.setPreferredSize(new Dimension(1000, 600));

        int buttonSize = 70; // Size of each candy button
        int startX = (1000 - (COLS * buttonSize)) / 2;
        int startY = (600 - (ROWS * buttonSize)) / 2;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                AnimatedButton button = new AnimatedButton();
                button.addActionListener(new ButtonClickListener(row, col));
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBounds(startX + col * buttonSize, startY + row * buttonSize, buttonSize, buttonSize);
                buttons[row][col] = button;
                gridPanel.add(button);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void resetBoard() {
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
        scoreLabel.setText("Score: " + score + " | Moves left: " + moves);
    }

    private boolean isValidSwap(int row1, int col1, int row2, int col2) {
        return (Math.abs(row1 - row2) == 1 && col1 == col2) || (Math.abs(col1 - col2) == 1 && row1 == row2);
    }

    private boolean checkAndHandleMatches() {
        boolean hasMatches = false;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 2; j++) {
                String actionCmd = buttons[i][j].getActionCommand();
                if (actionCmd.equals("-1")) continue;

                int type = Integer.parseInt(actionCmd);
                if (type == Integer.parseInt(buttons[i][j + 1].getActionCommand()) &&
                        type == Integer.parseInt(buttons[i][j + 2].getActionCommand())) {
                    hasMatches = true;
                    clearCandy(i, j);
                    clearCandy(i, j + 1);
                    clearCandy(i, j + 2);
                    score += 15;

                    if (j + 3 < COLS && type == Integer.parseInt(buttons[i][j + 3].getActionCommand())) {
                        clearCandy(i, j + 3);
                        score += 10;

                        if (j + 4 < COLS && type == Integer.parseInt(buttons[i][j + 4].getActionCommand())) {
                            clearCandy(i, j + 4);
                            score += 10;
                        }
                    }
                }
            }
        }

        for (int j = 0; j < COLS; j++) {
            for (int i = 0; i < ROWS - 2; i++) {
                String actionCmd = buttons[i][j].getActionCommand();
                if (actionCmd.equals("-1")) continue;

                int type = Integer.parseInt(actionCmd);
                if (type == Integer.parseInt(buttons[i + 1][j].getActionCommand()) &&
                        type == Integer.parseInt(buttons[i + 2][j].getActionCommand())) {
                    hasMatches = true;
                    clearCandy(i, j);
                    clearCandy(i + 1, j);
                    clearCandy(i + 2, j);
                    score += 15;

                    if (i + 3 < ROWS && type == Integer.parseInt(buttons[i + 3][j].getActionCommand())) {
                        clearCandy(i + 3, j);
                        score += 10;

                        if (i + 4 < ROWS && type == Integer.parseInt(buttons[i + 4][j].getActionCommand())) {
                            clearCandy(i + 4, j);
                            score += 10;
                        }
                    }
                }
            }
        }

        if (hasMatches) {
            animateGravity();
            updateScoreLabel();
            checkAndHandleMatches(); // Recursively call to handle any new matches
        }

        return hasMatches;
    }

    private void clearCandy(int row, int col) {
        buttons[row][col].setActionCommand("-1"); // Placeholder for empty
        buttons[row][col].setIcon(null);
    }

    private void animateSwap(int row1, int col1, int row2, int col2, Runnable onComplete) {
        isAnimating = true;
        Point pos1 = buttons[row1][col1].getLocation();
        Point pos2 = buttons[row2][col2].getLocation();

        buttons[row1][col1].startAnimation(pos2, e -> {
            buttons[row2][col2].startAnimation(pos1, e2 -> {
                String tempCmd = buttons[row1][col1].getActionCommand();
                Icon tempIcon = buttons[row1][col1].getIcon();

                buttons[row1][col1].setActionCommand(buttons[row2][col2].getActionCommand());
                buttons[row1][col1].setIcon(buttons[row2][col2].getIcon());

                buttons[row2][col2].setActionCommand(tempCmd);
                buttons[row2][col2].setIcon(tempIcon);

                buttons[row1][col1].setLocation(pos1);
                buttons[row2][col2].setLocation(pos2);

                isAnimating = false;
                if (onComplete != null) {
                    onComplete.run();
                }
            });
        });
    }

    private void animateGravity() {
        isAnimating = true;
        Timer gravityTimer = new Timer(50, null);
        gravityTimer.addActionListener(new ActionListener() {
            private int currentCol = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentCol >= COLS) {
                    gravityTimer.stop();
                    isAnimating = false;
                    refillCandies();
                    return;
                }

                boolean columnAnimated = false;
                for (int row = ROWS - 1; row >= 0; row--) {
                    if (buttons[row][currentCol].getActionCommand().equals("")) {
                        for (int k = row - 1; k >= 0; k--) {
                            if (!buttons[k][currentCol].getActionCommand().equals("")) {
                                Point targetPos = buttons[row][currentCol].getLocation();
                                int finalRow = row;
                                int finalK = k;
                                buttons[k][currentCol].startAnimation(targetPos, evt -> {
                                    buttons[finalRow][currentCol].setActionCommand(buttons[finalK][currentCol].getActionCommand());
                                    buttons[finalRow][currentCol].setIcon(buttons[finalK][currentCol].getIcon());

                                    buttons[finalK][currentCol].setActionCommand("");
                                    buttons[finalK][currentCol].setIcon(null);
                                });
                                columnAnimated = true;
                                break;
                            }
                        }
                    }
                }
                if (!columnAnimated) {
                    currentCol++;
                }
            }
        });
        gravityTimer.start();
    }

    private void refillCandies() {
        Random random = new Random();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (buttons[row][col].getActionCommand().equals("")) {
                    int iconIndex = random.nextInt(ICON_COUNT);
                    buttons[row][col].setActionCommand(String.valueOf(iconIndex));
                    buttons[row][col].setIcon(icons[iconIndex]);
                }
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private final int row;
        private final int col;
        private final int selectedRow;
        private final int selectedCol;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
            this.selectedRow = -1;
            this.selectedCol = -1;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isAnimating) return;

            if (CandyCrushGame.this.selectedRow == -1 && CandyCrushGame.this.selectedCol == -1) {
                CandyCrushGame.this.selectedRow = this.row;
                CandyCrushGame.this.selectedCol = this.col;
                buttons[this.row][this.col].setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
            } else {
                buttons[CandyCrushGame.this.selectedRow][CandyCrushGame.this.selectedCol].setBorder(null);
                if (isValidSwap(CandyCrushGame.this.selectedRow, CandyCrushGame.this.selectedCol, this.row, this.col)) {
                    animateSwap(CandyCrushGame.this.selectedRow, CandyCrushGame.this.selectedCol, this.row, this.col, () -> {
                        if (!checkAndHandleMatches()) {
                            animateSwap(this.row, this.col, CandyCrushGame.this.selectedRow, CandyCrushGame.this.selectedCol, null);
                        }
                        CandyCrushGame.this.moves--;
                        updateScoreLabel();
                        if (CandyCrushGame.this.moves <= 0) {
                            JOptionPane.showMessageDialog(frame, "Game Over! Your score: " + CandyCrushGame.this.score);
                            resetBoard();
                            CandyCrushGame.this.moves = MAX_MOVES;
                            CandyCrushGame.this.score = 0;
                            updateScoreLabel();
                        }
                    });
                }
                CandyCrushGame.this.selectedRow = -1;
                CandyCrushGame.this.selectedCol = -1;
            }
        }
    }

    public static void main(String[] args) {
        new CandyCrushGame();
    }
}