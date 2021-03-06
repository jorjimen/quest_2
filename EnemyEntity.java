// abstract class for the EnemyEntity (Objects used to represent the logic and functionality of monsters)
// Extends the CharacterEntity class

abstract class EnemyEntity extends CharacterEntity{

    // private data members for the enemy entity
    private int damage, defense, dodge_chance;


    // This is used to reset enemies to their originals state
    private int[] original = new int[5];

    // constructors for the EnemyEntity object

    public EnemyEntity(String name, int level, int damage, int defense, int dodge_chance) {
        super(name, level, "EE");

        this.damage = damage;
        this.defense = defense;
        this.dodge_chance = dodge_chance;
        original[0] = level;
        original[1] = damage;
        original[2] = defense;
        original[3] = dodge_chance;
        original[4] = health;

    }

    //getter methods

    public int getDamage() {
        return damage;
    }

    public int getDefense() {
        return defense;
    }

    public int getDodgeChance() {
        return dodge_chance;
    }

    // take damage, takes in a damage numebr and the type of damage
    // inflicts damage to the enemy entity. enemy can dodge it.
    public void takeDamage(int amountOfDamage, String type) {
        Double dodge_probability = dodge_chance / (double) 100;
        if (Math.random() <= dodge_probability) {
            System.out.println(this.toString() + " has dodged the attack!");
        } else {
            if (amountOfDamage >= defense) {
                health = health - (amountOfDamage - defense);
                System.out.println(this.toString() + " takes " + Integer.toString((amountOfDamage - defense)) + " damage.");
            } else {
                System.out.println(this.toString() + " defense has deflected all incoming damage.");
            }
            if (health < 0) {
                System.out.println("Great kill. " + this.toString() + " is knocked out!");
                health = 0;
            } else {
                if (!type.equals("w")) {
                    afflictSpell(type);
                }
            }
        }
    }

    // reset object to its original state
    public void reset() {
        level = original[0];
        damage = original[1];
        defense = original[2];
        dodge_chance = original[3];
        health = original[4];
    }


    // afflict spell damage to the enemy entity if it is afflicted by a spell.
    public void afflictSpell(String type) {
        if (type.equals("i")) {
            int reduction = (int) Math.round(dodge_chance * 0.9);
            System.out.println("Freeze! " + name + " was afflicted by an " + Colors.ANSI_BLUE +  "Ice Spell" + Colors.ANSI_RESET + ". It's dodge chance was reduced to " + Colors.ANSI_GREEN + Integer.toString(reduction) + Colors.ANSI_RESET +".");
            dodge_chance = reduction;
        } else if (type.equals("f")) {
            int reduction = (int) Math.round(defense * 0.9);
            System.out.println("Burned! " + name + " was afflicted by a " + Colors.ANSI_RED +  "Fire Spell" + Colors.ANSI_RESET + ". It's defense was reduced to " + Colors.ANSI_GREEN + Integer.toString(reduction)+ Colors.ANSI_RESET + ".");
            defense = reduction;
        } else if (type.equals("li")) {
            int new_def = (int) Math.round(defense * 0.95);
            int new_dodge = (int) Math.round(dodge_chance * 0.95);
            int new_att = (int) Math.round(damage * 0.95);
            System.out.println("The heavens! " + name + " was afflicted by a " + Colors.ANSI_YELLOW +  "Light Spell" + Colors.ANSI_RESET + ". All of its stats were reduced by 5%.");
            defense = new_def;
            dodge_chance = new_dodge;
            damage = new_att;
        } else {
            int reduction = (int) Math.round(damage * 0.9);
            System.out.println("Zap! " + name + " was afflicted by a " + Colors.ANSI_RED +  "Lightning Spell" + Colors.ANSI_RESET + ". It's damage was reduced to " + Colors.ANSI_GREEN + Integer.toString(reduction)+ Colors.ANSI_RESET + ".");
            damage = reduction;
        }
    }


    // displays a detailed description of the enemy entity
    public String showDetailed() {
        String s = "";
        s += name + "     ";
        s += Colors.ANSI_CYAN + Integer.toString(level) + Colors.ANSI_RESET  + "     ";
        s += Colors.ANSI_RED + Integer.toString(health) + Colors.ANSI_RESET + "     ";
        s += Colors.ANSI_GREEN + Integer.toString(damage) + Colors.ANSI_RESET + "     ";
        s += Colors.ANSI_GREEN + Integer.toString(defense) + Colors.ANSI_RESET + "     ";
        s += Colors.ANSI_GREEN + Integer.toString(dodge_chance) + Colors.ANSI_RESET + "     ";
        s += Colors.ANSI_WHITE + Integer.toString(r) + "," + Integer.toString(c) + Colors.ANSI_RESET;
        return s;
    }


}