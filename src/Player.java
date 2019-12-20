public class Player {

    private String name, weapon;
    private boolean hasLamp, hasKey;

    public Player(String userName) {
        setName(userName);
        setWeapon("Fist");
        hasLamp = false;
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

    //public void useWeapon() {
    //enemy takes damage
    // }


    //lightLamp(), openChest(), useWeapon(),


}
