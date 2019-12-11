import java.util.Scanner;

//Mae Game
public class Game {

    private String[][] displayArray = new String[3][3];
    private int[][] hiddenArray = new int[3][3]; //Do I need this?
    private int currentRow, currentCol;
    private String command;

    private Game() {
        currentRow = 0;
        currentCol = 0;
        command = "";
        for (int i = 0; i < displayArray.length; i++) {
            for (int j = 0; j < displayArray[0].length; j++) {
                if (j == 0) {
                    displayArray[i][j] = "|   |";
                    if (i == 0) {
                        displayArray[i][j] = "| X |";
                    }
                } else {
                    displayArray[i][j] = "   |";
                }
                hiddenArray[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Game game = new Game();
        do {
            for (int i = 0; i < game.displayArray.length; i++) {
                for (int j = 0; j < game.displayArray[0].length; j++) {
                    System.out.print(game.displayArray[i][j]);
                }
                System.out.println();
            }
            System.out.print("What do you want to do? ");
            game.command = input.nextLine();
            game.action(game.command);
        }
        while (!game.command.equalsIgnoreCase("quit"));
        input.close();
    }

    /*
     * A method that determines if the user inputted a valid command,
     * checks the if the command can be executed, and executes the
     * command if it can be done.
     */
    private void action(String command) {
        boolean moveCondition;
        int count = 0; //Determines if user entered a valid command
        if (command.equalsIgnoreCase("Move Right")) {
            moveCondition = moveCheck("right");
            if (moveCondition) {
                if (currentCol == 0) {
                    displayArray[currentRow][currentCol] = "|   |";
                } else {
                    displayArray[currentRow][currentCol] = "   |";
                }
                hiddenArray[currentRow][currentCol] = 0;
                hiddenArray[currentRow][currentCol++] = 1;
                displayArray[currentRow][currentCol] = " X |";
            } else {
                System.out.println("Can't move in that direction.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Move Left")) {
            moveCondition = moveCheck("left");
            if (moveCondition) {
                if (currentCol - 1 == 0) {
                    displayArray[currentRow][currentCol] = "   |";
                    hiddenArray[currentRow][currentCol] = 0;
                    hiddenArray[currentRow][currentCol--] = 1;
                    displayArray[currentRow][currentCol] = "| X |";
                } else {
                    displayArray[currentRow][currentCol] = "   |";
                    hiddenArray[currentRow][currentCol] = 0;
                    hiddenArray[currentRow][currentCol--] = 1;
                    displayArray[currentRow][currentCol] = " X |";
                }
            } else {
                System.out.println("Can't move in that direction.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Move Up")) {
            moveCondition = moveCheck("up");
            if (moveCondition) {
                if (currentCol == 0) {
                    displayArray[currentRow][currentCol] = "|   |";
                    hiddenArray[currentRow][currentCol] = 0;
                    hiddenArray[currentRow--][currentCol] = 1;
                    displayArray[currentRow][currentCol] = "| X |";
                } else {
                    displayArray[currentRow][currentCol] = "   |";
                    hiddenArray[currentRow][currentCol] = 0;
                    hiddenArray[currentRow--][currentCol] = 1;
                    displayArray[currentRow][currentCol] = " X |";
                }
            } else {
                System.out.println("Can't move in that direction.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Move Down")) {
            moveCondition = moveCheck("down");
            if (moveCondition) {
                if (currentCol == 0) {
                    displayArray[currentRow][currentCol] = "|   |";
                    hiddenArray[currentRow][currentCol] = 0;
                    hiddenArray[currentRow++][currentCol] = 1;
                    displayArray[currentRow][currentCol] = "| X |";
                } else {
                    displayArray[currentRow][currentCol] = "   |";
                    hiddenArray[currentRow][currentCol] = 0;
                    hiddenArray[currentRow++][currentCol] = 1;
                    displayArray[currentRow][currentCol] = " X |";
                }
            } else {
                System.out.println("Can't move in that direction.");
            }
            count++;
        }
        if (count == 0 && !command.equalsIgnoreCase("quit")) {
            System.out.println("Invalid command.");
        }
    }

    /*
     * A  method that checks if the user can proceed in the
     * specified direction and returns true if it can, false
     * otherwise.
     */
    private boolean moveCheck(String direction) {
        if (direction.equals("right") && currentCol + 1 >= 3) {
            return false;
        }
        if (direction.equals("left") && currentCol - 1 < 0) {
            return false;
        }
        if (direction.equals("up") && currentRow - 1 < 0) {
            return false;
        }
        if (direction.equals("down") && currentRow + 1 >= 3) {
            return false;
        }
        return true;
    }

}
