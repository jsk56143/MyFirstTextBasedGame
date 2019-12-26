import java.util.Random;

public class Chest {

    private boolean chestIsOpened = false;
    private String[] contents = {"10 pieces of gold", "4 silver coins", "Cracking the Code Interview", "An instruction on how to download RAM"};

    public Chest() {

    }

    public void setChestIsOpened(boolean chestIsOpened) {
        this.chestIsOpened = chestIsOpened;
    }

    public boolean getChestIsOpened() {
        return chestIsOpened;
    }

    public void printChestContents() {
        System.out.println("Inside the chest you find:");
        for (String item : contents) {
            System.out.println(item);
        }
    }

    public void setChestLocation(Board board) {
        Random randLocationGen = new Random();
        int rowLocation = randLocationGen.nextInt(3);
        int colLocation = randLocationGen.nextInt(3);
        board.
    }

}
