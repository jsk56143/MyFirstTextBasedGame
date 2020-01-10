/* This class represents the Chest that the player will try to find. */
public class Chest {

    private boolean chestIsOpened; //True if chest is opened, false otherwise
    private String[] contents = {" - 10 bars of gold", " - 4 silver coins", " - Cracking the Code Interview Book", " - An instruction on how to download RAM"};

    /* This constructor initializes the chest to be closed. */
    public Chest() {
        chestIsOpened = false;
    }

    /* This setter method returns void and sets the value of the Chest to being open. */
    public void openChest() {
        this.chestIsOpened = true;
    }

    /* This getter method returns the value of the chest being open or not. */
    public boolean getChestIsOpened() {
        return chestIsOpened;
    }

    /* This method returns void and prints the chest's contents. */
    public void printChestContents() {
        System.out.println("Inside the chest you find:");
        for (String item : contents) {
            System.out.println(item);
        }
    }


}
