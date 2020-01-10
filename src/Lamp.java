/* This class represents the Lamp that the player will use to see his/her surroundings. */
public class Lamp {

    private boolean isLit; //True if lamp is lit, false otherwise

    /* This constructor initializes the lamp to not be lit. */
    public Lamp() {
        isLit = false;
    }

    /* This setter method returns void and sets the lamp to be lit to whatever the boolean parameter is. */
   public void setIsLit(boolean isLit) {
       this.isLit = isLit;
   }

   /* This getter method returns true if the lamp is lit, false otherwise. */
   public boolean getIsLit() {
       return isLit;
   }


}
