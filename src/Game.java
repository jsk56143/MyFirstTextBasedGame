import java.util.Scanner;

//Make Game
public class Game {

    /*
     * The main method, the entry-point to my program, creates the game object,
     * prints the board out to standard output, asks the user for input, and
     * performs an action according to the input.     *
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Board board = new Board();
        board.initializeBoard();
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
