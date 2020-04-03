// public class for the IceSpell
// Subclass of Spell class
public class LightningSpell extends Spell {

    public LightningSpell(String name, int price, int level, int base_damage, int mana_cost) {
        super(name, price, level, base_damage, mana_cost);
    }

    public String toString() {
        return super.toString() + "   Type: " + Colors.ANSI_BLUE + "Lightning Spell" + Colors.ANSI_RESET +  "\n";
    }

}