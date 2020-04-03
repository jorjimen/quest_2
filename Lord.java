public class Lord extends HeroEntity {

    // constructor for the Warrior class
    public Lord(String name, int level, int experience, int strength, int dexterity, int agility,
            int mana, int money) {
        super(name, level, experience, strength, dexterity, agility, mana, money);
    }

    // implementation of the abstract method for leveling up
    public void levelup() {

        setLevel(getLevel() + 1);
        setHealth(100*getLevel());

        MANA_CAP = (int) Math.round(MANA_CAP + MANA_CAP*0.1);

        setMana((int) Math.round(MANA_CAP + MANA_CAP*0.1));

        setAgility((int) Math.round(getAgility() * 1.05));

        setStrength((int) Math.round(getStrength() * 1.15));
        
        setDexterity((int) Math.round(getDexterity() * 1.05));

        resetExperience();

    }

    // additional overriden method
    public String showDetailed() {
        return super.showDetailed() + "      LORD";
    }

    public String showListDetailed() {
        return super.showListDetailed() + "\n  Class: LORD\n";
    }


}