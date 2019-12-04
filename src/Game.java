import java.util.Scanner;

//Mae Game
public class Game {

    //private String[][] displayArray = new String[3][3];
    //private int[][] hiddenArray = new int[3][3];
    private int currentRow, currentCol;
    private String command;

    private Game() {
        currentRow = 0;
        currentCol = 0;
        command = "";
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Game game = new Game();
        do {
            System.out.print("What do you want to do? ");
            game.command = input.nextLine();
            game.action(game.command);
            System.out.println("Row: " + game.currentRow);
            System.out.println("Col: " + game.currentCol);
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
        if (command.equalsIgnoreCase("Move Right")) {
            moveCondition = moveCheck("right");
            if (moveCondition) {
                currentCol++;
            }
        }
        if (command.equalsIgnoreCase("Move Left")) {
            moveCondition = moveCheck("left");
            if (moveCondition) {
                currentCol--;
            }
        }
        if (command.equalsIgnoreCase("Move Up")) {
            moveCondition = moveCheck("up");
            if (moveCondition) {
                currentRow--;
            }
        }
        if (command.equalsIgnoreCase("Move Down")) {
            moveCondition = moveCheck("down");
            if (moveCondition) {
                currentRow++;
            }
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
