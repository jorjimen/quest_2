// Public class for Armor objects.
// Subclass of the abstract class Item
// Implements the Equippable intereface

public class Armor extends Item implements Equippable {

    // private data members

    private int reduction;
    private boolean equipped = false;

    // Constructor for the Armor object
    
    public Armor(String name, int price, int level, int reduction) {
        super(name, price, level);
        this.reduction = reduction;
    }

    // getter methods

    public int getReduction() {
        return reduction;
    }

    // setter methods

    public void setreduction(int k) {
        reduction = k;
    }

    // additional methods, including implementations of abstract and interface methods
    
    boolean canBeUsed(HeroEntity other) {
        return this.getLevel() <= other.getLevel();
    }

    public boolean isEquipped() {
        return equipped;
    }

    public void equip() {
        equipped = true;
    }

    public void unequip() {
        equipped = false;
    }

    // To string method for the ARmoro object

    public String toString() {
        String s = super.toString();
        s += "   Defense Reduction: " + Colors.ANSI_GREEN + Integer.toString(reduction) + Colors.ANSI_RESET +  "\n";
        return s;
    }

}