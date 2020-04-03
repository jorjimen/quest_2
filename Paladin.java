// class for the Paladin object
// extends the HeroEntity abstract class

public class Paladin extends HeroEntity {

    // constructor for the Paladin class
    public Paladin(String name, int level, int experience, int strength, int dexterity, int agility,
            int mana, int money) {
        super(name, level, experience, strength, dexterity, agility, mana, money);
    }

    // implements abstract method to level up hero
    public void levelup() {

        setLevel(getLevel() + 1);
        setHealth(100*getLevel());

        MANA_CAP = (int) Math.round(MANA_CAP + MANA_CAP*0.1);

        setMana((int) Math.round(MANA_CAP + MANA_CAP*0.1));

        setAgility((int) Math.round(getAgility() * 1.05));

        setStrength((int) Math.round(getStrength() * 1.10));
        
        setDexterity((int) Math.round(getDexterity() * 1.10));

        resetExperience();

    }

    // overrides some additional methods
    public String showDetailed() {
        return super.showDetailed() + "      PALADIN";
    }

    public String showListDetailed() {
        return super.showListDetailed() + "\n  Class: PALADIN";
    }


}