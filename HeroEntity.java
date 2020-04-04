import java.util.ArrayList;
import java.util.Scanner;

// Abstract class for the HeroEntity
// Handles the entire logic and data members needed necessary for any hero
// Implements the interface Entity and Unit

abstract class HeroEntity implements Entity, Unit {

    // All the private data members used for a HeroEntity

    private String name;

    private int level, health, experience;

    private int strength, dexterity, agility;

    private int mana;
    public int MANA_CAP;

    private int money;

    private Inventory inventory = new Inventory();

    private Weapon equippedWeapon = new Weapon("Fists",0, 1, 150, 1);
    private Armor equippedArmor = new Armor("Broken_Chestplate",0,1,100);

    private int kills;

    private String indicator;

    private static Scanner input = new Scanner(System.in);

    // Constructor method for the HeroEntity

    public HeroEntity(String name, int level,int experience, int strength, int dexterity, int agility, int mana, int money) {
        this.name = name;
        this.level = level;
        this.health = 100*level;
        this.experience = experience;
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
        this.mana = mana;
        this.MANA_CAP = mana;
        this.money = money;
        this.kills = 0;
        equippedWeapon.equip();
        equippedArmor.equip();
        addWeaponToInventory(equippedWeapon);
        addArmorToInventory(equippedArmor);
        this.indicator = "HH";
    }

    // Private class for the Inventory of a hero

    private class Inventory {
        
        public ArrayList<Spell> spells = new ArrayList<Spell>();
        public ArrayList<Potion> potions = new ArrayList<Potion>();
        public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
        public ArrayList<Armor> armors = new ArrayList<Armor>();

        // Methods for displaying the contents of a hero

        public void spells() {
            System.out.println("Spells:");
            if (spells.size() == 0) {
                System.out.println("--no spells--");
            } else {
                for (int i = 0; i < spells.size(); i++) {
                    System.out.println(Integer.toString(i) + ". " + spells.get(i));
                }
            }
        }

        public void potions() {
            System.out.println("\nPotions:");
            if (potions.size() == 0) {
                System.out.println("--no potions--");
            } else {
                for (int i = 0; i < potions.size(); i++) {
                    System.out.println(Integer.toString(i) + ". " + potions.get(i));
                }
            }
        }

        public void weapons() {
            System.out.println("\nWeapons:");
            if (weapons.size() == 0) {
                System.out.println("--no weapons--");
            } else {
                for (int i = 0; i < weapons.size(); i++) {
                    System.out.println(Integer.toString(i) + ". " + weapons.get(i));
                }
            }
        }

        public void armor() {
            System.out.println("\nArmor:");
            if (armors.size() == 0) {
                System.out.println("--no armors--");
            } else {
                for (int i = 0; i < armors.size(); i++) {
                    System.out.println(Integer.toString(i) + ". " + armors.get(i));
                }
            }
        }
    }

    // All the getter methods for the class

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getExperience() {
        return experience;
    }

    public int getMana() {
        return mana;
    }

    public int getMoney() {
        return money;
    }

    public Weapon getCurrentWeapon() {
        return equippedWeapon;
    }

    public Armor getCurrentArmor() {
        return equippedArmor;
    }

    public int spellCount() {
        return inventory.spells.size();
    }

    public int weaponCount() {
        return inventory.weapons.size();
    }

    public int armorCount() {
        return inventory.armors.size();
    }

    public int potionCount() {
        return inventory.potions.size();
    }

    public int getKills() {
        return kills;
    }

    public String getIndicator() {
        return indicator;
    }

    // All the methods for modifying the private members of the class

    public void setStrength(int k) {
        strength = k;
    }

    public void setDexterity(int k) {
        dexterity = k;
    }

    public void setAgility(int k) {
        agility = k;
    }
    
    public void setLevel(int k) {
        level = k;
    }

    public void setHealth(int k) {
        health = k;
        if (health > level*100) {
            health = 100*level;
        }
    }

    public void setIndicator(String newIndicator) {
        indicator = newIndicator;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setMana(int k) {
        mana = k;
        if (mana > MANA_CAP) {
            mana = MANA_CAP;
        }
    }

    public void setMoney(int k) {
        money = k;
    }

    public void incrmKills() {
        kills += 1;
    }

    // resets a hero experience

    public void resetExperience() {
        experience = experience - ((level-1) * 10);
    }

    // Additional methods

    // increases a hero's experience level, and returns true if the hero can level up
    public boolean increaseExperience(int k) {
        experience += k;
        if (experience >= level*10) {
            return true;
        } else {
            return false;
        }
    }

    // returns true if the hero has fainted
    public boolean isFainted() {
        return health <= 0;
    }

    // handles logic for hero regaining health and mana every round
    public void regain() {
        health += (int) Math.round(100*level * 0.05);
        if (health >= 100*level) {
            health = 100*level;
        }
        mana += (int) Math.round(mana * 0.05);
        if (mana >= MANA_CAP) {
            mana = MANA_CAP;
        }
    }

    // consume spell mana. mana cannot decrease by 0.
    public void consumeMana(int k) {
        mana -= k;
        if (mana < 0) {
            mana = 0;
        }
    }

    // handles logic for using a spell potion
    public boolean usePotion(Potion potion) {
        if (!potion.canBeUsed(this)) {
            return false;
        }
        String type = potion.getAttributeType();
        int incrm = potion.getAttributeIncrease();
        if (type.equals("STRENGTH")) {
            setStrength(getStrength() + incrm);
        } else if (type.equals("AGILITY")) {
            setAgility(getAgility() + incrm);
        } else if (type.equals("DEXTERITY")) {
            setDexterity(getDexterity() + incrm);
        } else if (type.equals("HEALTH")) {
            setHealth(getHealth() + incrm);
        } else if (type.equals("MANA")) {
            setMana(getMana() + incrm);
        } else if (type.equals("EXPERIENCE")) {
            boolean res = increaseExperience(incrm);
            if (res) {
                levelup();
            }
        }
        return true;
    }

    // add potion to inventory
    public void addPotion(Potion potion) {
        inventory.potions.add(potion);
    }

    // add a spell to the inventory
    public void addSpell(Spell newSpell) {
        if (!inventory.spells.contains(newSpell)) {
            inventory.spells.add(newSpell);
        }
    }

    // calculate hero's damage that it can deal with a spell
    public int calculateSpellDamage(Spell spell) {
        return (int) Math.round(spell.getDamage() + (this.dexterity/10000) * spell.getDamage());
    }

    // get a Spell object from inventory at a specific index
    public Spell getSpellFromInventory(int index) {
        return inventory.spells.get(index);
    }

    // add a Weapon object to the inventory
    public void addWeaponToInventory(Weapon weapon) {
        if (!inventory.weapons.contains(weapon)) {
            inventory.weapons.add(weapon);
        }
    }

    // remove a Weapon object from the inventory
    public Weapon removeWeaponFromInventory(int index) {
        Weapon removed = inventory.weapons.remove(index);
        if (inventory.weapons.size() == 0) {
            equippedWeapon = new Weapon("Fists",0, 1, 150, 1);
            addWeaponToInventory(equippedWeapon);
        } else if (removed == equippedWeapon) {
            equippedWeapon = inventory.weapons.get(0);
        }
        return removed;
    }

    // change the hero's equipped Weapon to a specified Weapon Object
    public void changeWeapon(Weapon newWeapon) {
        if (newWeapon.canBeUsed(this)) {
            equippedWeapon.unequip();
            equippedWeapon = newWeapon;
            newWeapon.equip();
        }
    }

    // Obtain a Weapon Object from a hero's inventory at a specific index
    public Weapon getWeaponFromInventory(int index) {
        return inventory.weapons.get(index);
    }

    // Obtain a Potion Object from a hero's nventory at a specific index
    public Potion getPotionFromInventory(int index) {
        return inventory.potions.get(index);
    }

    // Calculates the hero's attack damage
    public int calculateAttackDamage() {
        if (inventory.weapons.size() == 0) {
            return (int) Math.round((this.strength + 0)*0.05);
        }
        else {
            return (int) Math.round((this.strength + equippedWeapon.getDamage())*0.05);
        }
    }

    // Add a specific Armor object to the Hero inventory
    public void addArmorToInventory(Armor armor) {
        if (!inventory.armors.contains(armor)) {
            inventory.armors.add(armor);
        } 
    }
    
    // Remove a specific Armor object from the hero's inventory
    // DEFAULT: if a hero sells its last item, it will automaticall equip a base armor
    public Armor removeArmorFromInventory(int index) {
        Armor removed = inventory.armors.remove(index);
        if (inventory.armors.size() == 0) {
            equippedArmor = new Armor("Broken_Chestplate",0,1,100);
            addArmorToInventory(equippedArmor);
        } else if (removed == equippedArmor) {
            equippedArmor = inventory.armors.get(0);
        }
        return removed;
    }

    // change the hero's equipped armor to the specified one
    public void changeArmor(Armor newArmor) {
        if (newArmor.canBeUsed(this)) {
            equippedArmor.unequip();
            equippedArmor = newArmor;
            newArmor.equip();
        }
    }

    // get a specific armor object from the hero's inventory at an index
    public Armor getArmorFromInventory(int index) {
        return inventory.armors.get(index);
    }

    // remove a potion from the hero at a specific index
    public Potion removePotion(int index) {
        return inventory.potions.remove(index);
    }

    // remve a spell fromt he hero at a specific index
    public Spell removeSpell(int index) {
        return inventory.spells.remove(index);
    }

    // handles the logic for a hero taking damage from an enemy
    public void takeDamage(int amountOfDamage, String type) {
        Double dodge_probability = (agility * 0.02) / 100;
        if (Math.random() <= dodge_probability) {
            System.out.println(this.toString() + "has dodged the attack!");
        } else {
            if (amountOfDamage > equippedArmor.getReduction()) {
                if (inventory.armors.size() == 0) {
                    health = health - (amountOfDamage - 0);
                System.out.println(this.toString() + " takes " + Integer.toString((amountOfDamage - 0)) + " damage.");
                } else {
                    health = health - (amountOfDamage - equippedArmor.getReduction());
                    System.out.println(this.toString() + " takes " + Integer.toString((amountOfDamage - equippedArmor.getReduction())) + " damage.");
                }
            } else {
                System.out.println(this.toString() + " defense has deflected all incoming damage.");
            }
            if (health <= 0) {
                health = 0;
                System.out.println("Agh heroes! " + this.toString() + " is knocked out!");
            }
        }
    }

    // handles user input logic for displaying hero inventory
    public void showInventory() {
        int option = 0;
        while(true){
            try {
                System.out.println("Choose what to view?:\n 1) Potion \n 2) Spell \n 3) Weapon\n 4) Armor\n 5) Back");
                System.out.print("Enter move: ");
                option = Integer.parseInt(input.nextLine());
                if (option > 0 && option < 6) {
                    if (option == 1) {
                        inventory.potions();
                    } else if (option == 2) {
                        inventory.spells();
                    } else if (option == 3) {
                        inventory.weapons();
                    } else if (option == 4) {
                        inventory.armor();
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Enter a number between 1-5");
                }
            }
            catch(Exception e){
                System.out.println("Please input a number.");
                continue;
            }
        }
    }

    // show potions in the inventory
    public void showPotion() {
        inventory.potions();
    }

    // show armors in the inventory
    public void showArmors() {
        inventory.armor();
    }
    
    // show weapons in the inventory
    public void showWeapons() {
        inventory.weapons();
    }

    // show spells in the inventory
    public void showSpells() {
        inventory.spells();
    }

    // returns true if the hero has potions in the inventory
    public boolean hasPotion() {
        return inventory.potions.size() > 0;
    }

    // returns true if the hero has armor in the inventory
    public boolean hasArmors() {
        return inventory.armors.size() > 0;
    }
    
    // returns true if the hero has weapons in the inventory
    public boolean hasWeapons() {
        return inventory.weapons.size() > 0;
    }

    // returns true if the hero has spells in the inventory
    public boolean hasSpells() {
        return inventory.spells.size() > 0;
    }

    // handles user input logic to change its weapon
    public void changeWeapon() {
        System.out.println("Currently equipped weapon: " + this.getCurrentWeapon().getName());
        this.showWeapons();
        System.out.print(this.toString() + " choose which weapon you want to equip: ");
        int option = Integer.parseInt(input.nextLine());
        Weapon newWP = this.getWeaponFromInventory(option);
        if (newWP == this.getCurrentWeapon()) {
            System.out.println("You already have this weapon equipped.");
        } else {
            this.changeWeapon(newWP);
            System.out.println(this.toString() + " equipped a " + newWP.getName() + ".");
        }
    }

    // handles user input logic to change its armor
    public void changeArmor() {
        System.out.println("Currently equipped armor: " + this.getCurrentArmor().getName());
        this.showArmors();
        System.out.print("\n" + this.toString() + " choose which armor you want to equip: ");
        int option = Integer.parseInt(input.nextLine());
        Armor newWP = this.getArmorFromInventory(option);
        if (newWP == this.getCurrentArmor()) {
            System.out.println("You already have this armor equipped.");
        } else {
            this.changeArmor(newWP);
            System.out.println(this.toString() + " equipped a " + newWP.getName() + ".");
        }
    }

    // revives a hero after death
    public void revive() {
        health = (100*level) / 2;
    }

    // ABSTRACT CLASS THAT MUST BE IMPLEMENTED BY ANY SUB CLASS
    // handles logic for leveling up a hero
    abstract void levelup();

    // returns a detailed string of the hero's data in a column form
    public String showDetailed() {
        String s = "";
        s += name + "     ";
        s += Colors.ANSI_BLUE +  Integer.toString(mana) + Colors.ANSI_RESET+  "     ";
        s += Colors.ANSI_GREEN + Integer.toString(strength) + Colors.ANSI_RESET + "     ";
        s += Colors.ANSI_GREEN + Integer.toString(agility) + Colors.ANSI_RESET + "     ";
        s += Colors.ANSI_GREEN + Integer.toString(dexterity) + Colors.ANSI_RESET + "     ";
        s += Colors.ANSI_YELLOW + Integer.toString(money) + Colors.ANSI_RESET + "     "; 
        s += Colors.ANSI_PURPLE + Integer.toString(experience) + Colors.ANSI_RESET + "     ";
        s += Colors.ANSI_CYAN + Integer.toString(level) + Colors.ANSI_RESET  + "     ";
        s += Colors.ANSI_RED + Integer.toString(health) + Colors.ANSI_RESET ;
        return s;
    }

    // returns a detailed string of the hero's data in a list form
    public String showListDetailed() {
        String s = "\n";
        s += name + "\n\n";
        s += "  Experience:" + Colors.ANSI_PURPLE + Integer.toString(experience) + Colors.ANSI_RESET + "\n";
        s += "  Level:" + Colors.ANSI_CYAN + Integer.toString(level) + Colors.ANSI_RESET  + "\n";
        s += "  Health:" + Colors.ANSI_RED + Integer.toString(health) + Colors.ANSI_RESET + "\n";
        s += "  Mana:" + Colors.ANSI_BLUE +  Integer.toString(mana) + Colors.ANSI_RESET + "\n";
        s += "  Strength:" + Colors.ANSI_GREEN + Integer.toString(strength) + Colors.ANSI_RESET + "\n";
        s += "  Agility:" + Colors.ANSI_GREEN + Integer.toString(agility) + Colors.ANSI_RESET + "\n";
        s += "  Dexterity:" + Colors.ANSI_GREEN + Integer.toString(dexterity) + Colors.ANSI_RESET + "\n";
        s += "  Money:" + Colors.ANSI_YELLOW + Integer.toString(money) + Colors.ANSI_RESET + "\n";
        s += "  Enemies defeated: " + Colors.ANSI_RED+ Integer.toString(kills) + Colors.ANSI_RESET + "\n";
        s += "\n  Equipped Weapon: " + equippedWeapon.toString();
        s += "  Equipped Armor: " + equippedArmor.toString();
        return s;
    }

    // to string method for the hero
    public String toString() {
        String s = name + " (";
        s += Colors.ANSI_CYAN + Integer.toString(level) + Colors.ANSI_RESET  + ")";
        return s;
    }

}
