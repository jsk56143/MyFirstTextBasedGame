import java.util.Scanner;

//Make Game
public class Game {

    //boolean difficultyCheck = false;

    /*
     * The main method, the entry-point to my program, creates the game object,
     * prints the board out to standard output, asks the user for input, and
     * performs an action according to the input.     *
     */
    public static void main(String[] args) {
        boolean difficultyCheck = false;
        Scanner input = new Scanner(System.in);
        Lamp lamp = new Lamp();
        Key key = new Key();
        Board board = new Board();
        System.out.println("Welcome to this very simple text-based adventure game!");
        System.out.println("Your goal is to find the chest that's in a randomly generated location and unlock it.");
        System.out.print("Now let us begin. What is your name? ");
        String name = input.nextLine();
        Player player = new Player(name, lamp, key); //Creates the player object
        System.out.println("Welcome " + name);
        do {
            System.out.println("Select the difficulty of the game: \n");
            System.out.println("Easy: You start off with a lamp, sword, and key in 3x3 grid.");
            System.out.println("Medium: You start off with a lamp and sword in a 5x5 grid.");
            System.out.println("Hard: You start off with just your fists in a 5x5 grid.");
            String difficulty = input.nextLine();
            difficultyCheck = board.initializeBoard(difficulty);
        } while (!difficultyCheck);
        //Player player = new Player("Josh");

        do {
            board.printBoard();
            //Print out description of room
            System.out.print("What do you want to do? ");
            board.command = input.nextLine(); //Stores the input
            board.action(board.command); //Does the action according to the input
        }
        while (!board.command.equalsIgnoreCase("quit")); //Keep going unless player enters "quit"
        input.close();
    }


}
