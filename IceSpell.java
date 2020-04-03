// public class for the IceSpell
// Subclass of Spell class

public class IceSpell extends Spell {

    public IceSpell(String name, int price, int level, int base_damage, int mana_cost) {
        super(name, price, level, base_damage, mana_cost);
    }

    public String toString() {
        return super.toString() + "   Type: " + Colors.ANSI_CYAN + "Ice Spell" + Colors.ANSI_RESET +  "\n";
    }
    
}