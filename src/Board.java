
public class Board {

    private String[][] displayArray = new String[3][3];
    private int[][] hiddenArray = new int[3][3]; //Do I need this?
    private int currentRow, currentCol;
    protected String command;

    /*
     * The constructor creates the game object and initializes the
     * user's position at currentRow = 0 and currentCol = 0 with an
     * "X". The command is entered by the user. Only move right,
     * move left, move down, and move up is currently valid.
     */
    protected Board() {
        currentRow = 0; //Represents user's location in terms of the row
        currentCol = 0; //Represents user's location in terms of the column
        command = "";
    }

    protected void initializeBoard() {
        for (int i = 0; i < displayArray.length; i++) {
            for (int j = 0; j < displayArray[0].length; j++) {
                if (i == 0 && j == 0) { //Initializes player's token at 0,0
                    displayArray[i][j] = "| X |";
                } else if (j == 0 && i > 0) { //If first column and not initializing player's token
                    displayArray[i][j] = "|   |";
                } else if (j > 0) { //If column other than 0
                    displayArray[i][j] = "   |";
                }
                hiddenArray[i][j] = 0;
            }
        }
    }

    protected void printBoard() {
        for (int i = 0; i < displayArray.length; i++) {
            for (int j = 0; j < displayArray[0].length; j++) {
                System.out.print(displayArray[i][j]);
            }
            System.out.println();
        }
    }

    /*
     * A method that determines if the user inputted a valid command,
     * checks the if the command can be executed, and executes the
     * command if it can be done.
     */
    protected void action(String command) {
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
