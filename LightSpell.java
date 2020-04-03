// public class for the LightSpell
// Subclass of Spell class
public class LightSpell extends Spell {

    public LightSpell(String name, int price, int level, int base_damage, int mana_cost) {
        super(name, price, level, base_damage, mana_cost);
    }

    public String toString() {
        return super.toString() + "   Type: " + Colors.ANSI_YELLOW + "Light Spell" + Colors.ANSI_RESET +  "\n";
    }

}