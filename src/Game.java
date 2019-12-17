import java.util.Scanner;

//Make Game
public class Game {

    protected Game() {
        boolean firstTime = true;
    }

    /*
     * The main method, the entry-point to my program, creates the game object,
     * prints the board out to standard output, asks the user for input, and
     * performs an action according to the input.     *
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Board board = new Board();
        do {
            board.printBoard();
            /*
            for (int i = 0; i < board.displayArray.length; i++) {
                for (int j = 0; j < board.displayArray[0].length; j++) {
                    System.out.print(game.displayArray[i][j]);
                }
                System.out.println();
            }
            */

            //Print out description of room
            System.out.print("What do you want to do? ");
            game.command = input.nextLine(); //Stores the input
            game.action(game.command); //Does the action according to the input
        }
        while (!game.command.equalsIgnoreCase("quit")); //Keep going unless player enters "quit"
        input.close();
    }

    public boolean getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean condition) {
        this.firstTime = condition;
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
