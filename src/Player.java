public class Player {

    private String name;
    private boolean hasLamp, key;

    public Player(String userName, Lamp lamp, boolean key) {
        name = userName;
        lamp = null;
        hasLamp = false;
        key = false;
    }

    public String getName() {
        return name;
    }

    public void getLamp(Lamp lamp) {
        lamp = new Lamp();
        hasLamp = true;
    }

    public boolean getHasLamp() {
        return hasLamp;
    }

    public void lightLamp(Lamp lamp) {
        lamp.setIsLit(true);
    }

    public void getKey() {
        key = true;
    }

    public boolean getHasKey() {
        return key;
    }

    public void openChest(boolean key, Chest chest) {
        if (key) {
            chest.openChest();
            key = false;
            chest.printChestContents();
        }
        else {
            System.out.println("You don't have a key.");
        }
    }



}
