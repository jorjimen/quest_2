// abstract class for all the Items in the game
// implements the Entity interface
// Extends the Item interface

abstract class Spell extends Item {
    
    int base_damage;
    int mana_cost;

    public Spell(String name, int price, int level, int base_damage, int mana_cost) {
        super(name,price,level);
        this.base_damage = base_damage;
        this.mana_cost = mana_cost;
    }

    // getter methods

    public int getDamage() {
        return base_damage;
    }

    public int getManaCost() {
        return mana_cost;
    }

    // setter

    public void setDamage(int k) {
        base_damage = k;
    }

    public void setManaCost(int k) {
        mana_cost = k;
    }

    // additional methods

    public boolean canBeUsed(HeroEntity other) {
        if (other.getLevel() >= this.getLevel()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Spell other) {
        return (this.getName() == other.getName());
    }

    public String toString() {
        String s = super.toString();
        s += "   Damage: " + Colors.ANSI_GREEN + Integer.toString(base_damage) + Colors.ANSI_RESET +  "\n";
        s += "   Mana Cost: " + Colors.ANSI_BLUE + Integer.toString(mana_cost) + Colors.ANSI_RESET +  "\n";
        return s;
    }

}