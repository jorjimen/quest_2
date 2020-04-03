// class for the Sorcerer object
// extends the HeroEntity abstract class
public class Sorcerer extends HeroEntity {

    // constructor for the Sorcerer class
    public Sorcerer(String name, int level, int experience, int strength, int dexterity, int agility,
            int mana, int money) {
        super(name, level, experience, strength, dexterity, agility, mana, money);
    }

    // implements abstract method to level up hero
    public void levelup() {

        setLevel(getLevel() + 1);
        setHealth(100*getLevel());

        MANA_CAP = (int) Math.round(MANA_CAP + MANA_CAP*0.1);

        setMana((int) Math.round(MANA_CAP + MANA_CAP*0.1));

        setAgility((int) Math.round(getAgility() * 1.10));

        setStrength((int) Math.round(getStrength() * 1.05));
        
        setDexterity((int) Math.round(getDexterity() * 1.10));

        resetExperience();

    }

    // override some additional methods
    public String showDetailed() {
        return super.showDetailed() + "      SORCERER";
    }

    public String showListDetailed() {
        return super.showListDetailed() + "\n  Class: SORCERER";
    }


}