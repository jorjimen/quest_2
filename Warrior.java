// class for the Paladin object
// extends the HeroEntity abstract class

public class Warrior extends HeroEntity {

    // constructor for the Warrior class
    public Warrior(String name, int level, int experience, int strength, int dexterity, int agility,
            int mana, int money) {
        super(name, level, experience, strength, dexterity, agility, mana, money);
    }

    // implementation of the abstract method for leveling up
    public void levelup() {

        setLevel(getLevel() + 1);
        setHealth(100*getLevel());

        MANA_CAP = (int) Math.round(MANA_CAP + MANA_CAP*0.1);

        setMana((int) Math.round(MANA_CAP + MANA_CAP*0.1));

        setAgility((int) Math.round(getAgility() * 1.10));

        setStrength((int) Math.round(getStrength() * 1.10));
        
        setDexterity((int) Math.round(getDexterity() * 1.05));

        resetExperience();

    }

    // additional overriden method
    public String showDetailed() {
        return super.showDetailed() + "      WARRIOR";
    }

    public String showListDetailed() {
        return super.showListDetailed() + "\n  Class: WARRIOR\n";
    }


}