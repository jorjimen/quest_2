// Public class for Weapon objects.
// Subclass of the abstract class Item
// Implements the Equippable intereface

public class Weapon extends Item implements Equippable {

    // private data members
    private int damage;
    private int required_hands;
    private boolean equipped = false;

    // constructor for the Weapon object
    public Weapon(String name, int price, int level, int damage, int required_hands) {
        super(name, price, level);
        this.required_hands = required_hands;
        this.damage = damage;
    }

    // getter methods

    public int getDamage() {
        return damage;
    }

    public int getRequiredHands() {
        return required_hands;
    }

    // setter methods

    public void setDamage(int k) {
        damage = k;
    }

    public void setRequiredHands(int k) {
        required_hands = k;
    }

    // additional methods
    

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

    // to string method overriden
    public String toString() {
        String s = super.toString();
        s += "   Damage: " + Colors.ANSI_GREEN + Integer.toString(damage) + Colors.ANSI_RESET +  "\n";
        s += "   Required hands: " + Colors.ANSI_BLUE + Integer.toString(required_hands) + Colors.ANSI_RESET +  "\n";
        return s;
    }

}