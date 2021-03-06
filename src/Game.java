import java.util.Scanner;

//This class represents the game and contains the entire game mechanics. */
public class Game {

    /* The main method, the entry-point to my program, creates the game object,
     * prints the board out to standard output, asks the user for input, and
     * performs an action according to the input.
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Lamp lamp = new Lamp();
        Chest chest = new Chest();
        Board board = new Board();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("INTRO: Welcome to this very simple text-based adventure game!");
        System.out.println("HOW TO WIN: Your goal is to find the chest and unlock it.\n");
        System.out.print("Now let us begin. What is your name? ");
        String name = input.nextLine();
        Player player = new Player(name, false); //Creates the player object
        System.out.println("Welcome " + player.getName() + "\n");
        System.out.println("LEGEND:\n X - yourself\n O - wall\n | - size of room.");
        board.initializeBoard(); //Initializes the board
        board.setRoomDescription(); //Sets the description of each room
        System.out.println("BACKGROUND: You wake up to find yourself lying on a cold stone slab raised above the ground and in the center of a room.");
        do {
            board.printRoomDescription(player, lamp); //Prints the description of the current room
            board.printBoard(); //Prints the board from the displayArray
            System.out.print("What do you want to do? \n");
            board.printAvailOptions(player, lamp); //Prints the available commands that user can input
            System.out.println("---------------------------------------------------------------------------------------");
            board.command = input.nextLine(); //Stores the input
            board.action(board.command, player, lamp, chest); //Performs the action according to the input
        }
        while (!board.command.equalsIgnoreCase("quit")); //Keep going unless player enters "quit"
        input.close(); //Closes the scanner object
    }


}
