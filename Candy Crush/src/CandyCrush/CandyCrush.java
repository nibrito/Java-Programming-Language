package CandyCrush;

import java.util.Scanner;

public class CandyCrush {
    private static final int ROWS = 8;
    private static final int COLS = 8;

    private int[][] board = new int[ROWS][COLS];

    public CandyCrush(){
        fillboard();
    }
    public boolean swap (int row1, int col1, int row2, int col2){
        if (Math.abs(row1 - row2) + Math.abs(col1 - col2) != 1){
            return false;
        }

        int temp = board[row1][col1];
        board [row1][col1] = board[row2][col2];
        board [row2][col2] = temp;

        if (checkMatches()){
            return true;
        }else {
            board [row2][col2] = board [row1][col1];
            board [row1][col1] = temp;

            return false;
        }
    }

    private boolean checkMatches(){
        boolean matchFound = false;

        for (int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS - 2; col++){
                int candy = board[row][col];
                if(candy != -1 && candy == board[row][col + 1] && candy == board[row][col + 2]){
                    board[row][col] = board[row][col + 1] = board[row][col + 2] = -1;
                    matchFound = true;
                }
            }
        }

        for (int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS - 2; col++){
                int candy = board[row][col];
                if(candy != -1 && candy == board[row + 1][col] && candy == board[row + 2][col]){
                    board[row][col] = board[row + 1][col] = board[row + 2][col] = -1;
                    matchFound = true;
                }
            }
        }
        return matchFound;
    }


    private void fillboard(){
        for (int row = 0; row < ROWS; row++){
            for (int col = 0; col < COLS; col++){
                board[row][col] = (int) (Math.random() * 5);
            }
        }
    }

    private void applyGravity() {
        for (int col = 0; col < COLS; col++){
            int emptyRow = ROWS - 1;
            for (int row = ROWS - 1; row >= 0; row--){
                if (board [row][col] != -1){
                    board[emptyRow][col] = board [row][col];
                    if(emptyRow != row){
                        board[row][col] = -1;
                    }
                    emptyRow--;
                }
            }
        }
    }

    private void refillBoard(){
        for (int row = 0; row < ROWS; row++){
            for (int col = 0; col < COLS; col++){
                if (board[row][col] == -1){
                    board[row][col] = (int) (Math.random() * 5);
                }
            }
        }
    }

    public void playGame(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            dispalyBoard();
            System.out.println("Enter swap coordinates (row1 col1 row2 col2) or 'exit': ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            String[] parts = input.split(" ");

            int row1 = Integer.parseInt(parts[0]);
            int col1 = Integer.parseInt(parts[1]);
            int row2 = Integer.parseInt(parts[2]);
            int col2 = Integer.parseInt(parts[3]);

            if (swap(row1, col1, row2, col2)){
                applyGravity();
                refillBoard();
            } else {
                System.out.println("Invalid swap or no match.");
            }
        }
        scanner.close();
    }

    private void dispalyBoard(){
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                System.out.println(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}




