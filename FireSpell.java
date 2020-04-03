// public class for the FireSpell
// Subclass of Spell class

public class FireSpell extends Spell {

    public FireSpell(String name, int price, int level, int base_damage, int mana_cost) {
        super(name, price, level, base_damage, mana_cost);
    }

    public String toString() {
        return super.toString() + "   Type: " + Colors.ANSI_RED + "Fire Spell" + Colors.ANSI_RESET +  "\n";
    }
    
}