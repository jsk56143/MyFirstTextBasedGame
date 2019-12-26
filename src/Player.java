public class Player {

    private String name, weapon;
    private boolean hasLamp, hasKey;

    public Player(String userName) {
        setName(userName);
        setWeapon("Fist");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void getLamp(Lamp lamp) {
        lamp.setHasLamp(true);
    }

    public void lightLamp(Lamp lamp) {
        lamp.setIsLit(true);
    }

    public void getKey(Key key) {
        key.setHasKey(true);
    }

    public void openChest(Key key, Chest chest) {
        if (key.getHasKey()) {
            chest.setChestIsOpened(true);
            key.setHasKey(false);
            chest.printChestContents();
        }
        else {
            System.out.println("You don't have a key.");
        }
    }


    //public void useWeapon() {
    //enemy takes damage
    // }


    //lightLamp(), openChest(), useWeapon(),


}
