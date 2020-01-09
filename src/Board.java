
public class Board {

    private String[][] displayArray; //Stores location of person
    private String[][] descriptionArray = new String[5][5]; //Stores description of the rooms
    private int[][] hiddenArray; //Stores location of sword, lamp, chest, key, and walkable paths
    private int currentRow, currentCol;
    protected String command;

    /*
     * The constructor creates the game object and initializes the
     * user's position at currentRow = 0 and currentCol = 0 with an
     * "X". The command is entered by the user. Only move right,
     * move left, move down, and move up is currently valid.
     */
    public Board() {
        currentRow = 0; //Represents user's location in terms of the row
        currentCol = 0; //Represents user's location in terms of the column
        command = "";
    }

    public void initializeBoard() {
        displayArray = new String[5][5];
        hiddenArray = new int[5][5];
        for (int i = 0; i < displayArray.length; i++) {
            for (int j = 0; j < displayArray[1].length; j++) {
                if (i == 0 && j == 0) { //Initializes player's token at 0,0
                    displayArray[i][j] = "| X |";
                } else if (j == 0 && i > 0) { //If first column and not initializing player's token
                    displayArray[i][j] = "|   |";
                } else if (j > 0) { //If column other than 0
                    displayArray[i][j] = "   |";
                }
                hiddenArray[i][j] = 0;
                descriptionArray[i][j] = "";
            }
        }
        setItemLocation();
        setWallLocation();
    }

    public void setItemLocation() {
        hiddenArray[0][1] = 1; //Location of the lamp
        hiddenArray[2][0] = 2; //Location of they key
        hiddenArray[4][1] = 3; //Location of the chest
    }

    public void setWallLocation() {
        int[] row = {1, 1, 2, 2, 3, 4};
        int[] col = {0, 1, 1, 3, 3, 0};
        for (int i = 0; i < row.length; i++) {
            hiddenArray[row[i]][col[i]] = 5;
            if (col[i] == 0) {
                displayArray[row[i]][col[i]] = "| O |";
            } else {
                displayArray[row[i]][col[i]] = " O |";
            }
        }
    }

    public void printBoard() {
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
    public void action(String command, Player player, Lamp lamp, Chest chest) {
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
                displayArray[currentRow][currentCol + 1] = " X |";
                currentCol++;
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
                    displayArray[currentRow][currentCol - 1] = "| X |";
                } else {
                    displayArray[currentRow][currentCol] = "   |";
                    displayArray[currentRow][currentCol - 1] = " X |";
                }
                currentCol--;
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
                    displayArray[currentRow - 1][currentCol] = "| X |";
                } else {
                    displayArray[currentRow][currentCol] = "   |";
                    displayArray[currentRow - 1][currentCol] = " X |";
                }
                currentRow--;
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
                    displayArray[currentRow + 1][currentCol] = "| X |";
                } else {
                    displayArray[currentRow][currentCol] = "   |";
                    displayArray[currentRow + 1][currentCol] = " X |";
                }
                currentRow++;
            } else {
                System.out.println("Can't move in that direction.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Get Lamp")) {
            if (hiddenArray[currentRow][currentCol] == 1) {
                player.getLamp(lamp);
                hiddenArray[currentRow][currentCol] = 0;
                System.out.println(player.getName() + " has obtained a lamp. You'll be able to see in the dark.");
            } else {
                System.out.println("There is no lamp to pick up.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Light lamp")) {
            if (lamp != null) {
                player.lightLamp(lamp);
            } else {
                System.out.println("You don't have a lamp to light.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Get Key")) {
            if (hiddenArray[currentRow][currentCol] == 2) {
                player.getKey();
                hiddenArray[currentRow][currentCol] = 0;
                System.out.println(player.getName() + " has obtained a key. Let's see what it unlocks.");
            } else {
                System.out.println("There is no key to pick up.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Open Chest")) {
            if (hiddenArray[currentRow][currentCol] == 3 && player.getHasKey()) {
                System.out.println("You insert the key into the lock and it unlocks.");
                player.openChest(true, chest);
                System.out.println("Congratulations! You have found the chest and won the game.");
                System.exit(0);
            } else {
                System.out.println("There is no chest to unlock.");
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
        if (direction.equals("right") && (currentCol + 1 >= hiddenArray.length || hiddenArray[currentRow][currentCol + 1] == 5)) {
            return false;
        }
        if (direction.equals("left") && (currentCol - 1 < 0 || hiddenArray[currentRow][currentCol - 1] == 5)) {
            return false;
        }
        if (direction.equals("up") && (currentRow - 1 < 0 || hiddenArray[currentRow - 1][currentCol] == 5)) {
            return false;
        }
        if (direction.equals("down") && (currentRow + 1 >= hiddenArray.length || hiddenArray[currentRow + 1][currentCol] == 5)) {
            return false;
        }
        return true;
    }

    public void printRoomDescription() {
        if (currentRow == 0 && currentCol == 1) {
            if (hiddenArray[currentRow][currentCol] == 0) {
                descriptionArray[currentRow][currentCol] = "There is a cave entrance in front of you. It's dark inside.";
            }
        }
        else if (currentRow == 2 && currentCol == 0) {
            if (hiddenArray[currentRow][currentCol] == 0) {
                descriptionArray[currentRow][currentCol] = "The ground is bumpy. The air is cool and dry. Stalactites hang from the ceiling.";
            }
        }
        System.out.println(descriptionArray[currentRow][currentCol]);
    }

    public void setRoomDescription() {
        descriptionArray[0][0] = "There are walls around you except for one direction.";
        descriptionArray[0][1] = "There is a cave entrance in front of you. It's dark inside, but you find a lamp near the entrance.";
        descriptionArray[2][0] = "The ground is bumpy, and you see a golden key sticking out from the ground. It looks like it unlocks something valuable.";
        descriptionArray[4][1] = "This room looks different. It's smaller, and you see a large padlocked wooden chest.";
        for (int row = 0; row < descriptionArray.length; row++) {
            for (int col = 0; col < descriptionArray.length; col++) {
                if (descriptionArray[row][col].isEmpty()) {
                    descriptionArray[row][col] = "The ground is bumpy. The air is cool and dry. Stalactites hang from the ceiling.";
                }
            }
        }
    }

    public void printAvailOptions() {
        //use the moveCheck method
    }














}
