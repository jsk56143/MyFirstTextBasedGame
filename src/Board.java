
public class Board {

    private String[][] displayArray = new String[3][3];
    private int[][] hiddenArray = new int[3][3]; //Do I need this?
    private int currentRow, currentCol;
    private String command;

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

    protected void printBoard() {
        Game game = new Game();
        for (int i = 0; i < displayArray.length; i++) {
            for (int j = 0; j < displayArray[0].length; j++) {
                if (i == 0 && j == 0 && game.getFirstTime()) { //Initializes player's token at 0,0
                    displayArray[i][j] = "| X |";
                    game.setFirstTime(false);
                }
                if (j == 0 && !game.getFirstTime()) { //If first column and not initializing player's token
                    displayArray[i][j] = "|   |";
                } else { //If column other than 0
                    displayArray[i][j] = "   |";
                }
                hiddenArray[i][j] = 0;
            }
        }
    }















}
