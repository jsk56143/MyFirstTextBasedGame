/* This class is responsible for everything tht happens on this board
 * from the moving the player to printing the board.
 */
public class Board {

    private String[][] displayArray; //The board that's printed out to standard output
    private String[][] descriptionArray = new String[5][5]; //The board that stores the description of each rooms
    private int[][] hiddenArray; //The board that tracks the location of the lamp, chest, key, and walkable/not walkable paths
    private int currentRow, currentCol; //Stores the player's current location in terms of the row and column
    private boolean printOtherDescrip = false; //Doesn't print the description of each room if the lamp is not lit
    protected String command; //Stores the player's commands

    /* The constructor creates the game object and initializes the
     * user's position at currentRow = 0 and currentCol = 0 with an
     * "X". The command is entered by the user. Only move right,
     * move left, move down, and move up is currently valid.
     */
    public Board() {
        currentRow = 0;
        currentCol = 0;
        command = "";
    }

    /* This method returns void and initializes what the board is going to look like
     * in terms of the displayArray and hiddenArray. This method also sets the location
     * of the lamp, key, chest, and walls.
     */
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
        setItemLocation(); //Sets the location of the lamp, chest, and key
        setWallLocation(); //Sets the location of the walls
    }

    /* This method returns void and sets the location of the lamp, chest, and key. */
    public void setItemLocation() {
        hiddenArray[0][1] = 1; //1 = lamp
        hiddenArray[2][0] = 2; //2 = key
        hiddenArray[4][1] = 3; //3 = chest
    }

    /* This method returns void and sets the location of the walls. This method is
     * more complex than the previous since it also needs to be displayed to the user.
     */
    public void setWallLocation() {
        int[] row = {1, 1, 2, 2, 3, 4}; //Sets the specified locations of the walls in terms of the row
        int[] col = {0, 1, 1, 3, 3, 0}; //Sets the specified location of the walls in terms of the column
        for (int i = 0; i < row.length; i++) {
            hiddenArray[row[i]][col[i]] = 5; //Represents the walls in the hiddenArray
            if (col[i] == 0) { //If this is the first column
                displayArray[row[i]][col[i]] = "| O |"; //Represents the walls in the displayArray
            } else {
                displayArray[row[i]][col[i]] = " O |";
            }
        }
    }

    /* This method returns void and prints the board to standard output. */
    public void printBoard() {
        for (int i = 0; i < displayArray.length; i++) {
            for (int j = 0; j < displayArray[0].length; j++) {
                System.out.print(displayArray[i][j]);
            }
            System.out.println(); //Goes to next line after printing each row
        }
    }

    /* This method determines if the user inputted a valid command, checks the if the
     * command can be executed, and executes the command if it can be done.
     */
    public void action(String command, Player player, Lamp lamp, Chest chest) {
        boolean moveCondition; //True if the player can move in that direction, false otherwise
        int count = 0; //If incremented, this variable determines if user entered a valid command
        if (command.equalsIgnoreCase("Move Right")) {
            moveCondition = moveCheck("right"); //Checks if the player can move in that direction
            if (moveCondition) { //If player can move in that direction
                if (hiddenArray[currentRow][currentCol] == 1) { //If didn't pick up lamp
                    System.out.println("You'll probably need that lamp. Who knows what lurks in the darkness.");
                    return; //Acts as a break statement (break is supposedly bad programming style)
                }
                if (currentCol == 0) { //Empties the current location in order to show that the player has moved
                    displayArray[currentRow][currentCol] = "|   |";
                } else {
                    displayArray[currentRow][currentCol] = "   |";
                }
                displayArray[currentRow][currentCol + 1] = " X |"; //Updates location of the player in displayArray
                currentCol++; //Updates the location of the player in the hiddenArray
            } else { //If player can't move in that direction
                System.out.println("Can't move in that direction.");
            }
            count++; //Increments count if the player inputted a valid command
        }
        if (command.equalsIgnoreCase("Move Left")) { //Refer to comments above
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
        if (command.equalsIgnoreCase("Move Up")) { //Refer to in the first if statement
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
        if (command.equalsIgnoreCase("Move Down")) { //Refer to comments in the first if statement
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
            if (hiddenArray[currentRow][currentCol] == 1) { //If there is a lamp in the player's current location
                player.getLamp(lamp); //Gives the player a lamp
                hiddenArray[currentRow][currentCol] = 0; //Removes the lamp from the board
                System.out.println(player.getName() + " has obtained a lamp. You'll be able to see in the dark.");
            } else { //If there is no lamp in the player's current location
                System.out.println("There is no lamp to pick up.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Light lamp")) {
            if (lamp != null) { //If the player has a lamp
                player.lightLamp(lamp); //Lights the lamp
                printOtherDescrip = true; //Since the player can see his/her surroundings, the board prints out the description of each room
            } else { //If the player doesn't have a lamp
                System.out.println("You don't have a lamp to light.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Get Key")) {
            if (hiddenArray[currentRow][currentCol] == 2) { //If there is a key in the player's current location
                player.getKey(); //Gives the player a key
                hiddenArray[currentRow][currentCol] = 0; //Removes the key from the board
                System.out.println(player.getName() + " has obtained a key. Let's see what it unlocks.");
            } else { //If there is no key in the player's current location
                System.out.println("There is no key to pick up.");
            }
            count++;
        }
        if (command.equalsIgnoreCase("Open Chest")) {
            //If there is a chest in the player's current location and the player has a key
            if (hiddenArray[currentRow][currentCol] == 3 && player.getHasKey()) {
                System.out.println("You insert the key into the lock and it unlocks.");
                player.openChest(true, chest); //Opens the chest and removes the key from the player
                System.out.println("Congratulations! You have found the chest and won the game.");
                System.exit(0); //Exits the program successfully
            } else { //If there is no chest in the player's current location and the player doesn't have a key
                System.out.println("The chest is padlocked. You need to find a key.");
            }
            count++;
        }
        //If the player doesn't input any of the above commands and is not a "quit" command, the board prompts the user for a valid command
        if (count == 0 && !command.equalsIgnoreCase("quit")) {
            System.out.println("Invalid command.");
        }
    }

    /* This  method that checks if the user can proceed in the specified direction by checking if there is
     * a wall in the proposed directionand returns true if it can, false otherwise.
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

    /* This method returns void and prints the description of the current room. It also updates the
     * descriptionArray if the player has picked up a lamp or key or has lit the lamp.
     */
    public void printRoomDescription(Player player, Lamp lamp) {
        System.out.print("ROOM DESCRIPTION: ");
        //If the player has a lamp and the lamp is not lit and the current location in inside the cave
        if (player.getHasLamp() && !lamp.getIsLit() && !(currentRow == 0 && (currentCol == 1 || currentCol == 0))) {
            System.out.println("It's too dark to see anything.");
        }
        if (currentRow == 0 && currentCol == 1) { //If the player is in front of the cave
            if (hiddenArray[currentRow][currentCol] == 0) {  //If the player picked up the lamp
                descriptionArray[currentRow][currentCol] = "There is a cave entrance in front of you. It's dark inside.";
            }
        }
        else if (currentRow == 2 && currentCol == 0 && printOtherDescrip) { //If the player has lit the lamp and picked up the key
            if (hiddenArray[currentRow][currentCol] == 0) {
                descriptionArray[currentRow][currentCol] = "The ground is bumpy. The air is cool and dry. Stalactites hang from the ceiling.";
            }
        }
        if (lamp.getIsLit() || (currentRow == 0 && (currentCol == 0 || currentCol == 1))) { //If the lamp is lit inside the cave or the player is outside the cave
            System.out.println(descriptionArray[currentRow][currentCol]);
        }
    }

    /* This method returns void and sets the description of each room. */
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

    /* This method returns void and prints the available commands that the user can input depending on whether
     * that command is valid in relation to the player's location.
     */
    public void printAvailOptions(Player player, Lamp lamp) {
        if (hiddenArray[currentRow][currentCol] == 1) { //If there is a lamp at the player's current location
            System.out.println(" - get lamp");
        }
        if (hiddenArray[currentRow][currentCol] == 2 && lamp.getIsLit()) { //If there is a key at the current location and the lamp is lit
            System.out.println(" - get key");
        }
        if (hiddenArray[currentRow][currentCol] == 3 && lamp.getIsLit()) { //If there is a chest at the current location and the lamp is lit
            System.out.println(" - open chest");
        }
        //If the player has a lamp and the lamp is not lit and the player is inside the cave
        if (player.getHasLamp() && !lamp.getIsLit() && !(currentRow == 0 && currentCol == 1)) {
            System.out.println(" - light lamp");
        }
        if (currentCol + 1 < hiddenArray.length && hiddenArray[currentRow][currentCol + 1] != 5) { //If the player can move right
            System.out.println(" - move right");
        }
        if (currentCol - 1 >= 0 && hiddenArray[currentRow][currentCol - 1] != 5) { //If the player can move left
            System.out.println(" - move left");
        }
        if (currentRow - 1 >= 0 && hiddenArray[currentRow - 1][currentCol] != 5) { //If the player can move up
            System.out.println(" - move up");
        }
        if (currentRow + 1 < hiddenArray.length && hiddenArray[currentRow + 1][currentCol] != 5) { //If the player can move down
            System.out.println(" - move down");
        }
        System.out.println(" - quit: Exits the game.");
    }














}
