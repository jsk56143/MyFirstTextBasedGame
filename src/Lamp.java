public class Lamp {

    private boolean hasLamp, isLit;

    public Lamp() {
        hasLamp = false;
        isLit = false;
    }

    public void getLamp() {
        hasLamp = true;
    }

    public void lightLamp() {
        isLit = true;
    }


}
