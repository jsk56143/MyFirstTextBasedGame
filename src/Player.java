/* This class represents the Player and his/her attributes. */
public class Player {

    private String name; //Stores the player's name
    private boolean hasLamp = false, key = false; //Indicates whether player has a lamp/key

    /* This constructor sets the player's name. */
    public Player(String userName, boolean key) {
        name = userName;
    }

    /* This method returns the player's name. */
    public String getName() {
        return name;
    }

    /* This method returns void and sets the player to have a lamp. */
    public void getLamp(Lamp lamp) {
        lamp = new Lamp();
        hasLamp = true;
    }

    /* This method returns true if the player has a lamp, false otherwise. */
    public boolean getHasLamp() {
        return hasLamp;
    }

    /* This method returns void and lights the lamp. */
    public void lightLamp(Lamp lamp) {
        lamp.setIsLit(true);
    }

    /* This method returns void and gives the player a key. */
    public void getKey() {
        key = true;
    }

    /* Thi method returns true if the player has a key, false otherwise. */
    public boolean getHasKey() {
        return key;
    }

    /* This method returns void, opens the chest, and prints it contents. */
    public void openChest(boolean key, Chest chest) {
        if (key) { //If the player has a key
            chest.openChest();
            key = false;
            chest.printChestContents();
        }
        else { //If the player doesn't have a key
            System.out.println("You don't have a key.");
        }
    }



}
