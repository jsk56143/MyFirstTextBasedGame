public class Chest {

    private boolean chestIsOpened;
    private String[] contents = {"10 bars of gold", "4 silver coins", "Cracking the Code Interview Book", "An instruction on how to download RAM"};

    public Chest() {
        chestIsOpened = false;
    }

    public void openChest() {
        this.chestIsOpened = true;
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


}
