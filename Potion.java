// public class for a Potion
// Subclass of the item abstract class
// Implements the Consumable interface

public class Potion extends Item implements Consumable {

    // private data members
    private int attributeIncrease;
    private String attributeType;

    private boolean consumed = false;

    // 
    public Potion(String name, int price, int level, int attributeIncrease, String attributeType) {
        super(name, price, level);
        this.attributeIncrease = attributeIncrease;
        this.attributeType = attributeType;
    }

    // getter methods

    public int getAttributeIncrease() {
        return attributeIncrease;
    }

    public String getAttributeType() {
        return attributeType;
    }


    // setter methods

    public void setAttributeIncrease(int k) {
        attributeIncrease = k;
    }

    public void setType(String newType) {
        attributeType = newType;
    }

    // additional methods that must be implented by this class
    // like abstract methods and methods from the interface

    public boolean isConsumed() {
        return consumed;
    }

    public void consume() {
        consumed = true;
    }

    boolean canBeUsed(HeroEntity other) {
        if (other.getLevel() >= this.getLevel()) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String s = super.toString();
        s += "   Type: " + Colors.ANSI_BLUE + attributeType + Colors.ANSI_RESET +  "\n";
        s += "   Attribute Increase: " + Colors.ANSI_GREEN + Integer.toString(attributeIncrease) + Colors.ANSI_RESET +  "\n";
        return s;
    }

}