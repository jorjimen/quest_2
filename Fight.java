import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// public class for the Fight object
// This is used to handle the logic of any fight between an enemy team and a hero team

// Contains one constructor and only one public function, fight(), which is used to execute the fight
// between the hero team and the enemy team

// enemy team is always randomly generated with the following constraints:
// -Highest enemy level does not exceed the highest hero level
// - # of enemies = # of heroes

public class Fight {

    // private members for the fight
    
    private ArrayList<EnemyEntity> enemyTeam;
    private HeroTeam heroTeam;
    private int round = 1;
    private final Scanner input = new Scanner(System.in);

    // constructor for the fight. takes in as argument a hero team and generates a random enemy team

    public Fight(HeroTeam heroTeam) {

        this.heroTeam = heroTeam;
        
        // Add enemies here if you wish to expand the list of available enemies

        ArrayList<EnemyEntity> possibleEnemies = new ArrayList<EnemyEntity>(Arrays.asList(GameObjects.d1,GameObjects.d2,
        GameObjects.d3,GameObjects.d4,GameObjects.d5,GameObjects.d6,GameObjects.d7,GameObjects.d8,GameObjects.d9,GameObjects.exo1,
        GameObjects.exo2,GameObjects.exo3,GameObjects.exo4,GameObjects.exo5,GameObjects.exo6,GameObjects.exo7,GameObjects.exo8,
        GameObjects.exo9,GameObjects.spr1,GameObjects.spr2,GameObjects.spr3,GameObjects.spr4,GameObjects.spr5,GameObjects.spr6,
        GameObjects.spr7,GameObjects.spr8,GameObjects.spr9,GameObjects.spr10, GameObjects.d10, GameObjects.dem1, GameObjects.d11, GameObjects.dem2,GameObjects.dem3, GameObjects.dem4, GameObjects.spr11,
        GameObjects.spr12,GameObjects.dem2,GameObjects.dem3,GameObjects.dem4,GameObjects.dem5));

        int maxLevel = heroTeam.getMaxLevel();

        possibleEnemies.removeIf(a -> a.getLevel() > maxLevel);

        while (possibleEnemies.size() > heroTeam.count()) {
            possibleEnemies.remove((int) (Math.random() * (possibleEnemies.size())));
        }

        enemyTeam = possibleEnemies;

    }

    // fight: executes the fight between the hero and entity. 
    // call this method to do the fight
    public void fight() {
        int outcome;
        do {
            displayRoundStatus();
            System.out.println();
            outcome = executeHeroRound();
            round += 1;
        } while (heroTeam.canContinue() && enemyCanContinue());
        if (outcome == 1) {
            System.out.println("These monsters were strong, but you were stronger! It was a long battle, but now, its time to enjoy your rewards. \n");
            heroTeam.gain(enemyTeam.size());
            heroTeam.postMatchRevive();
            System.out.println(Colors.ANSI_GREEN + "--------------------------------------- YOU WIN ---------------------------------------------------------------\n" + Colors.ANSI_RESET);
        } else {
            System.out.println("It seems you cannot continue to fight again... The journey was long but you were unable to make it to the end...\n");
            System.out.println(Colors.ANSI_RED + "--------------------------------------- YOU LOSE ---------------------------------------------------------------\n" + Colors.ANSI_RESET);
        }
        for (int i = 0; i < enemyTeam.size(); i++) {
            enemyTeam.get(i).reset();
        }
    }

    // private method -> displays status of the round
    private void displayRoundStatus() {
        System.out.println("--------------------------------------- ROUND " + Integer.toString(round) + "---------------------------------------------------------------\n");
        System.out.println(Colors.ANSI_BLUE + "HEROES:" + Colors.ANSI_RESET);
        System.out.println();
        heroTeam.showDetailed();
        System.out.println();
        System.out.println(Colors.ANSI_RED + "ENEMIES:" + Colors.ANSI_RESET);
        System.out.println();
        System.out.println("         name       lvl     hp    dmg    def     dodge     type");
            System.out.println("--------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < enemyTeam.size(); i++) {
            System.out.println(Integer.toString(i) + ")  " + enemyTeam.get(i).showDetailed());
        }
        System.out.println("\nMOVES:\n 1) ATTACK ENEMY\n 2) CAST SPELL\n 3) USE POTION\n 4) CHANGE WEAPON\n 5) CHANGE ARMOR");
    }

    // private method -> handles the logic of executing a round of the fight
    private int executeHeroRound() {
        int count = 0;
        int option = 0;
        while(count < heroTeam.count()){
            if (!enemyCanContinue()) {
                return 1;
            }
            try {
                if (heroTeam.get(count).isFainted()) {
                    System.out.println(heroTeam.get(count).toString() + " cannot fight, he is fainted."); 
                    count += 1;
                    continue;
                } 
                System.out.print(heroTeam.get(count).toString() + " choose your move: ");
                option = Integer.parseInt(input.nextLine());
                HeroEntity currentHero = heroTeam.get(count);
                if (option == 1) { 
                    damageFirstEnemy(currentHero, currentHero.calculateAttackDamage(), count, "w");
                    System.out.println();
                    count += 1;
                } else if (option == 2) {
                    if (!currentHero.hasSpells()) {
                        System.out.println(currentHero.toString() + " does not have any spells yet.");
                    } else {
                        int usage = heroUseSpell(currentHero,count);
                        if (usage == 1) {
                            count += 1;
                        }
                    }
                } else if (option == 3) {
                    if (!currentHero.hasPotion()) {
                        System.out.println(currentHero.toString() + " does not have any potions yet.");
                    } else {
                        heroUsePotion(currentHero,count);
                        count += 1;
                    }
                } else if (option == 4) {
                    if (currentHero.weaponCount() > 1) {
                        changeWeapon(currentHero, count);
                        count += 1;
                    } else {
                        System.out.println("Not enough weapons in the inventory to do this.");
                    }

                } else if (option == 5) {
                    if (currentHero.armorCount() > 1) {
                        changeArmor(currentHero, count);
                        count += 1;
                    } else {
                        System.out.println("Not enough armor in the inventory to do this.");
                    }
                } else {
                    System.out.println("Please select a move from 1 to 5.");
                }
            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }
        enemyAttackRound();
        heroTeam.roundRegain();
        if (enemyCanContinue()) {
            return 0;
        } else {
            return 1;
        }
    }

    // private method -> executes logic if a hero decides to use a spell
    private int heroUseSpell(HeroEntity hero, int index) {
        int option = 0;
        while(true){
            try {
                hero.showSpells();
                System.out.print(hero.toString() + " choose which spell you want to cast: ");
                option = Integer.parseInt(input.nextLine());
                Spell spell = hero.getSpellFromInventory(option);
                if (spell.getManaCost() > hero.getMana()) {
                    System.out.println(hero.toString() + " does not have enough mana to cast this spell.");
                    return 0;
                } else {
                    int damage = hero.calculateSpellDamage(spell);
                    System.out.println(hero.toString() + " casts a " + spell.getName() + " for " + Integer.toString(spell.getManaCost()) + " mana.");
                    if (spell instanceof LightningSpell) {
                        damageFirstEnemy(hero,damage,index, "l");
                    } else if (spell instanceof FireSpell ) {
                        damageFirstEnemy(hero,damage,index, "f");
                    } else if (spell instanceof LightSpell) {
                        damageFirstEnemy(hero,damage,index,"li");
                    } else {
                        damageFirstEnemy(hero,damage,index, "i");
                    }
                    hero.consumeMana(spell.getManaCost());
                    return 1;
                }
            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }
    }

    // private method -> executes the logic if the hero decides to change his weapon
    private void changeWeapon(HeroEntity hero, int index) {
        int option = 0;
        while(true){
            try {
                System.out.println("Currently equipped weapon: " + hero.getCurrentWeapon().getName());
                hero.showWeapons();
                System.out.print("\n" + hero.toString() + " choose which weapon you want to equip: ");
                option = Integer.parseInt(input.nextLine());
                Weapon newWP = hero.getWeaponFromInventory(option);
                if (newWP == hero.getCurrentWeapon()) {
                    System.out.println("You already have this weapon equipped.");
                } else {
                    hero.changeWeapon(newWP);
                    System.out.println(hero.toString() + " equipped a " + newWP.getName() + ".");
                    break;
                }

            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }
    }

    // private method -> executes the logic if the hero decides to change his armor
    private void changeArmor(HeroEntity hero, int index) {
        int option = 0;
        while(true){
            try {
                System.out.println("Currently equipped armor: " + hero.getCurrentArmor().getName());
                hero.showArmors();;
                System.out.print("\n" + hero.toString() + " choose which armor you want to equip: ");
                option = Integer.parseInt(input.nextLine());
                Armor newWP = hero.getArmorFromInventory(option);
                if (newWP == hero.getCurrentArmor()) {
                    System.out.println("You already have this armor equipped.");
                } else {
                    hero.changeArmor(newWP);
                    System.out.println(hero.toString() + " equipped a " + newWP.getName() + ".");
                    break;
                }

            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }
    }

    // private method -> executed the logic if the hero decides to use a potion
    private void heroUsePotion(HeroEntity hero, int index) {
        int option = 0;
        while(true){
            try {
                hero.showPotion();
                System.out.print("\n"+ hero.toString() + " choose which potion you want to use: ");
                option = Integer.parseInt(input.nextLine());
                Potion potion = hero.getPotionFromInventory(option);
                System.out.println(heroTeam);
                System.out.print("\n" + hero.toString() + " choose on which hero you would like to use the potion: ");
                int option2 = Integer.parseInt(input.nextLine());
                HeroEntity getter = heroTeam.get(option2);
                getter.usePotion(potion);
                if (getter == hero) {
                    System.out.println(hero.toString() + " uses a " + potion.getName() + " on itself.");
                    hero.removePotion(option);
                } else {
                    System.out.println(hero.toString() + " uses a " + potion.getName() + " on " + getter.toString() + ".");
                    hero.removePotion(option);
                }
                break;
            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }
    }

    // private method -> Given a hero index and a heroEntity, it damages the first available enemy
    private void damageFirstEnemy(HeroEntity heroEntity, int damage, int index, String type) {
        if (enemyTeam.get(index).isFainted()) {
            for (int i = 0; i < enemyTeam.size(); i++) {
                EnemyEntity toAttack = enemyTeam.get(i);
                if (!toAttack.isFainted()) {
                    System.out.println(heroEntity.toString() + " attacks " + toAttack.toString() + " for " + Integer.toString(damage) + " damage.");
                    toAttack.takeDamage(damage, type);
                    if (toAttack.isFainted()) {
                        heroEntity.incrmKills();
                    }
                    break;
                }
            }
        } else {
            System.out.println(heroEntity.toString() + " attacks " + enemyTeam.get(index).toString() + " for " + Integer.toString(damage) + " damage.");
            enemyTeam.get(index).takeDamage(damage, type);
            if (enemyTeam.get(index).isFainted()) {
                heroEntity.incrmKills();
            }
        }
    }

    // private method -> executes the logic for the enemy to attack the heroes
    private void enemyAttackRound() {
        if (!enemyCanContinue()) {
            System.out.println("The enemy team has no strength left!");
        } else {
        for (int i = 0; i < enemyTeam.size(); i++) {
            if (!heroTeam.canContinue()) {
                System.out.println("Everything is getting faded... Heroes... we lost this one... ");
                break;
            }
            EnemyEntity attacking = enemyTeam.get(i);
            if (!attacking.isFainted()) {
                HeroEntity toAttack = heroTeam.get(i);
                if (!toAttack.isFainted()) {
                    System.out.println(attacking.toString() + " attacks " + toAttack.toString() + " for " + Integer.toString(attacking.getDamage()) + " damage.");
                    toAttack.takeDamage(attacking.getDamage(), "w"); 
                } else {
                    for (int j = 0; i < heroTeam.count(); j++) {
                        if (!heroTeam.get(j).isFainted()) {
                            System.out.println(attacking.toString() + " attacks " + heroTeam.get(j).toString() + " for " + Integer.toString(attacking.getDamage()) + " damage.");
                            heroTeam.get(j).takeDamage(attacking.getDamage(), "w");
                            break;
                        }
                    }
                }
            } else {
                System.out.println(attacking.toString() + " cannot attack because it has fainted");
            }
            System.out.println();
        }}
    }

    // private method -> returns true if all the enemies are not fainted.
    private boolean enemyCanContinue() {
        for (int i = 0; i < enemyTeam.size(); i++) {
            if (!enemyTeam.get(i).isFainted()) {
                return true;
            }
        }
        return false;
    }
}