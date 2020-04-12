import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// Main Quest object, to handle all the main game logic
public class Quest {

    // private data members
    private QuestGridMap map;
    private HeroTeam heroTeam;
    private ArrayList<EnemyEntity> enemyTeam = new ArrayList<EnemyEntity>();
    
    private int numRounds;

    private Scanner input = new Scanner(System.in);

    // change this variable to adjust the maximum amount of heroes per team
    private final int maxHeroPerTeam = 3;

    // public constructor for the Quest
    // int dim -> specifies map dimension
    public Quest(int dim) {
        map = new QuestGridMap(dim);
        heroTeam = new HeroTeam();
    }

    // call this method to play the Quest
    public void play() {
        numRounds = 1;
        showStartMessage();
        heroTeam = buildTeam();
        buildEnemyTeam();
        System.out.println("Our group of travelers embark in their adventure...");
        mainGameHandler();
    }

    // shows initial start mention
    private void showStartMessage() {
        String s = "\nGreetings traveleres!\n\nIn a far, far, away land, mythical and legendary creatures roam the wastelands in search of mighty heroes to hunt.";
        s += "In the capital of Ansturia, King John The Mighty has allowed his royal troops every kind to hunt these creatures.";
        s += "Obviously, any man or woman who participates in this hunt will be rewardd with numerous of gold, and the ability to level up their skills as a warrior in the Kingdom of Ansturia";
        s += "\n\nYou have woken up one fine morning and received an invite from King John the Mighty himeself. It is time to rise up to the challenge and create your team of warriors to partake in this hunt.\n";
        System.out.println(s);
    }

    // handles all the game logic
    private void mainGameHandler() {
        int index = 0;
        String option = "";
        do {
            if (map.didMonstersReachNexus()) {
                System.out.println("Heroes... it seems a monster has reached our Nexus. Let's retreat and come back stronger next time.\n");
                System.out.println(map);
                System.out.println(Colors.ANSI_RED + "--------------------------------------- YOU LOSE ----------------------------------------------------------------------------------\n" + Colors.ANSI_RESET);
                break;
            }
            if (heroTeam.get(index).isFainted()) {
                System.out.println(heroTeam.get(index).toString() + " is fainted. It will respawn once this round ends.");
                if (index + 1 == heroTeam.count()) {
                    index = 0;
                    moveEnemies();
                    reviveFallenHeroes();
                    numRounds += 1;
                    if(numRounds == 8){
                        buildEnemyTeam();
                        System.out.println(Colors.ANSI_RED + "3 new enemies " + Colors.ANSI_RESET + "have spawned in the lanes. Beware!");
                        numRounds = 1;
                    }
                } else {
                    index += 1;
                }
                continue;
            } 
            try {
                System.out.println();
                System.out.println("HERO TEAM:");
                heroTeam.showDetailed();
                displayEnemies();
                System.out.println("\n"+ map);
                System.out.println("Move list:\n W/w) Move Up\n A/a) Move Left\n S/s) Move Down\n D/d) Move Right \n T/t) Teleport \n C/c) Attack \n P/p) Cast spell \n B/b) Return to Nexus/Market \n I/i) Inspect Team/Use Item\n Q/q) Quit\n");
                System.out.println("You are currently selecting a move for:  " + heroTeam.get(index).toString() + " ("  + heroTeam.get(index).getIndicator() + ")");
                System.out.print("Enter move: ");
                option = input.nextLine();
                int ret = -1;
                if (option.equals("W") || option.equals("w")) {
                    System.out.println("Moving up..");
                    ret = map.moveUp(heroTeam, index);
                } else if (option.equals("D") || option.equals("d")) {
                    System.out.println("Moving right...");
                    ret = map.moveRight(heroTeam, index);
                } else if (option.equals("S") || option.equals("s")) {
                    System.out.println("Moving down...");
                    ret = map.moveDown(heroTeam, index);
                } else if (option.equals("A") || option.equals("a")) {
                    System.out.println("Moving left...");
                    ret = map.moveLeft(heroTeam, index);
                } else if (option.equals("I") || option.equals("i")) {
                    inspectHandler();
                    ret = 3;
                } else if (option.equals("T") || option.equals("t")) {
                    ret = teleportHandler(heroTeam, index);
                } else if (option.equals("Q") || option.equals("q")) {
                    break;
                }else if(option.equals("C") || option.equals("c")){ // attack if there is an enemy in the same cell

                    if(!monsterInSameCell(heroTeam.get(index))){
                        System.out.println("There's no monster here to attack!");
                    } 
                    else{
                        ret = 4;
                    }

                }else if(option.equals("P") || option.equals("p")){  // cast spell if there is an enemy in the same cell

                    if(!monsterInSameCell(heroTeam.get(index))){
                        System.out.println("There's no monster here to cast a spell on!");
                    } 
                    else{
                        ret = 5;
                       
                    }

                }
                else if (option.equals("B") || option.equals("b")){
                    System.out.println("Going back to Nexus...");
                    ret = map.teleportHero(heroTeam, index, map.rowCount()-1, heroTeam.get(index).getLocation()[1]);
                } else {
                    System.out.println("I can't recognize that command hero...");
                } 

                switch(ret) {
                    case -2:
                        System.out.println("Hero, you cannot move forward without defeating a monster near you first. Try attacking it instead, or moving away from it.");
                        break;
                    case -1:
                        System.out.println("You cannot move here! Look at the map and notice your location.");
                        break;
                    case 0:
                        System.out.println("Agh! You have hit your head against a wall. Remember, look at your map. You cannot access locations marked in red. Let's back up.");
                      break;
                    case 1:
                        System.out.println("Home sweet home! You have arrived to a Nexus.");
                        map.enterMarket(heroTeam);
                        if (index + 1 == heroTeam.count()) {
                            index = 0;
                            moveEnemies();
                            reviveFallenHeroes();
                            numRounds += 1;
                            if(numRounds == 8){
                                buildEnemyTeam();
                                System.out.println(Colors.ANSI_RED + "3 new enemies " + Colors.ANSI_RESET + "have spawned in the lanes. Beware!");
                                numRounds = 1;
                            }
                        } else {
                            index += 1;
                        }
                      break;
                    case 2:
                        System.out.println("You have moved.");

                        // THIS HANDLES THE CASE THAT YOU HAVE MOVED
                        // ADD CODE HERE FOR A FIGHT

                        if (index + 1 == heroTeam.count()) {
                            index = 0;
                            moveEnemies();
                            reviveFallenHeroes();
                            numRounds += 1;
                            if(numRounds == 8){
                                buildEnemyTeam();
                                System.out.println(Colors.ANSI_RED + "3 new enemies " + Colors.ANSI_RESET + "have spawned in the lanes. Beware!");
                                numRounds = 1;
                            }
                        } else {
                            index += 1;
                        }
                        break;

                    // case 4:
                    // todo: hero attacks enemy until the enemy dies to get past 
                    case 5: // try to cast spell
                        if (!heroTeam.get(index).hasSpells()) {
                            System.out.println(heroTeam.get(index).toString() + " does not have any spells yet.");
                        }
                        else{
                            int r = heroTeam.get(index).getLocation()[0];
                            int c = heroTeam.get(index).getLocation()[1];
                            EnemyEntity currentEnemy = map.getCellAt(r, c).getEnemy();
                            heroUseSpell(heroTeam.get(index), currentEnemy);
                        }
                        break;
                    }
                    
                  
            } catch (Exception e) {
                System.out.println("Something went wrong...");
            }

  
        } while (!option.equals("Q") && !option.equals("q"));
                System.out.println("Thank you for playing!");

    } 
    


    private int heroUseSpell(HeroEntity hero, EnemyEntity currentEnemy) {
    
        while(true){
            try {
                hero.showSpells();
                System.out.print(hero.toString() + " choose which spell you want to cast: ");
                int option = Integer.parseInt(input.nextLine());
                Spell spell = hero.getSpellFromInventory(option);
                if (spell.getManaCost() > hero.getMana()) {
                    System.out.println(hero.toString() + " does not have enough mana to cast this spell.");
                    return 0;
                } else {
                    int damage = hero.calculateSpellDamage(spell);
                    System.out.println(hero.toString() + " casts a " + spell.getName() + " for " + Integer.toString(spell.getManaCost()) + " mana.");
                    if (spell instanceof LightningSpell) {
                        currentEnemy.takeDamage(damage, "l");
                    } else if (spell instanceof FireSpell ) {
                        currentEnemy.takeDamage(damage, "f");
                    } else if (spell instanceof LightSpell) {
                        currentEnemy.takeDamage(damage, "li");
                    } else {
                        currentEnemy.takeDamage(damage, "i");
                    }
                    hero.consumeMana(spell.getManaCost());

                    if(currentEnemy.isFainted()){

                        map.getCellAt(hero.getLocation()[0], hero.getLocation()[1]).removeEnemy();
                        enemyTeam.remove(currentEnemy);
                        System.out.println("The enemy is defeated!");

                    }
                    return 1;
                }
            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }
    }

    private void moveEnemies(){
        for(int i = 0; i < enemyTeam.size(); i++){
            // should probs do some checking here to make sure it doesnt go out of bounds.
            // however, it can't go out of bounds bc it only moves down,
            // so the only check necessary is if it reaches the hero nexus,
            // at which point the game ends and mosters win
            EnemyEntity enemy = enemyTeam.get(i);
            if (!heroInRange(enemy)) {
                int row = enemy.getLocation()[0]+1;
                int col = enemy.getLocation()[1];
                if (map.getCellAt(row, col).enemyCount() == 0) {
                    map.leave(row-1, col);
                    map.getCellAt(row-1, col).removeEnemy();
                    enemy.setLocation(row,col);
                    map.getCellAt(row,col).placeEnemy(enemy);
                    if (row == map.rowCount() - 1) {
                        System.out.println(enemy.toString() + " does a game winning move...");
                    } else {
                        System.out.println(enemy.toString() + " moves forward.");
                    }
                } else {
                    System.out.println(enemy.toString() + " cannot move forward because there is another enemy blocking it.");
                }
            }
        }
    }

    // handles user input logic if user wants to inspect their hero team
    private void inspectHandler() {
        int option = 0;
        while(true){
            try {
                System.out.println("HERO TEAM:");
                heroTeam.showDetailed();
                System.out.print("\nChoose which hero you would like to inspect (-1 to cancel): ");
                option = Integer.parseInt(input.nextLine());
                if (option == -1) {
                    break;
                }
                HeroEntity inspecting = heroTeam.get(option);
                System.out.println(inspecting.showListDetailed());
                System.out.println("\nWhat would you like to do?:\n 1) View Inventory\n 2) Change Weapon\n 3) Change Armor\n 4) Use Potion\n 5) Go Back To Map\n ");
                System.out.print("Choose your move: ");
                int option2 = Integer.parseInt(input.nextLine());
                if (option2 == 1) {
                    inspecting.showInventory();
                } else if (option2 == 2) {
                    inspecting.changeWeapon();
                } else if (option2 == 3) {
                    inspecting.changeArmor();
                } else if (option2 == 4) {
                    if (inspecting.hasPotion()) {
                        potionUse(inspecting);
                    } else {
                        System.out.println("You do not have any potions.\n");
                    }
                } else {
                    break;
                }
            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }

    }

    // handles teleporting

    private int teleportHandler(HeroTeam heroTeam, int index) {
        while(true){
            try {
                System.out.print("Enter location (r,c): ");
                String moveString = input.nextLine();
                String arr[] = moveString.split(",");
                return map.teleportHero(heroTeam, index, Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }
    }

    // handles user input logic for using a potion out of combat
    private void potionUse(HeroEntity hero) {
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

    // displays available hero objects at the start of the game
    private HeroEntity[] displayAvailable() {

        // If you wish to add more heroes, adjust this here.
        
        HeroEntity[] heroes = new HeroEntity[16];

        heroes[0] = GameObjects.p1;
        heroes[1] = GameObjects.p2;
        heroes[2] = GameObjects.p3;
        heroes[3] = GameObjects.p4;

        heroes[4] = GameObjects.s1;
        heroes[5] = GameObjects.s2;
        heroes[6] = GameObjects.s3;
        heroes[7] = GameObjects.s4;

        heroes[8] = GameObjects.w1;
        heroes[9] = GameObjects.w2;
        heroes[10] = GameObjects.w3;
        heroes[11] = GameObjects.w4;

        heroes[12] = GameObjects.w5;
        heroes[13] = GameObjects.s5;
        heroes[14] = GameObjects.lord1;
        heroes[15] = GameObjects.lord2;

 
        System.out.println("         name              mana    str     agi     dex     money   exp   lvl    hp      loc      type");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        
        for (int i = 0; i < heroes.length; i++) {
            if (i < 10) {
                System.out.println(Integer.toString(i) + ")  " + heroes[i].showDetailed());
            } else {
                System.out.println(Integer.toString(i) + ") " + heroes[i].showDetailed());
            }
        }
        System.out.println();

        return heroes;
    }

    // handles user input logic for building a team
    // RESTRICTION: max amount of heroes per team is 3 and no repeated hero
    private HeroTeam buildTeam() {
        System.out.println("Let's start by building your team!");
        HeroTeam hero = new HeroTeam();
        int option = 0;
        while(true){
            if (hero.count() == maxHeroPerTeam) {
                System.out.println("You reached the maximum of " + Integer.toString(maxHeroPerTeam) + " players! Your team is done!");
                break;
            }
            System.out.println("Your team:");
            System.out.println(hero);
            System.out.println();
            try {
                System.out.println("Choose a possible hero to add:\n  ");
                HeroEntity[] h = displayAvailable();
                System.out.print("Enter move: ");
                option = Integer.parseInt(input.nextLine());
                hero.add(h[option]);
            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }

        hero.get(0).setLocation(7, 0);
        map.getCellAt(7, 0).placeHero(hero.get(0));

        hero.get(1).setLocation(7, 3);
        map.getCellAt(7, 3).placeHero(hero.get(1));

        hero.get(2).setLocation(7, 6);
        map.getCellAt(7, 6).placeHero(hero.get(2));

        return hero;
    }

    private void buildEnemyTeam(){

        ArrayList<EnemyEntity> possibleEnemies = new ArrayList<EnemyEntity>(Arrays.asList(GameObjects.d1,GameObjects.d2,
        GameObjects.d3,GameObjects.d4,GameObjects.d5,GameObjects.d6,GameObjects.d7,GameObjects.d8,GameObjects.d9,GameObjects.exo1,
        GameObjects.exo2,GameObjects.exo3,GameObjects.exo4,GameObjects.exo5,GameObjects.exo6,GameObjects.exo7,GameObjects.exo8,
        GameObjects.exo9,GameObjects.spr1,GameObjects.spr2,GameObjects.spr3,GameObjects.spr4,GameObjects.spr5,GameObjects.spr6,
        GameObjects.spr7,GameObjects.spr8,GameObjects.spr9,GameObjects.spr10, GameObjects.d10, GameObjects.dem1, GameObjects.d11, GameObjects.dem2,GameObjects.dem3, GameObjects.dem4, GameObjects.spr11,
        GameObjects.spr12,GameObjects.dem2,GameObjects.dem3,GameObjects.dem4,GameObjects.dem5));

        int maxLevel = heroTeam.getMaxLevel();

        possibleEnemies.removeIf(a -> a.getLevel() > maxLevel);

        while (possibleEnemies.size() > 3) {
            possibleEnemies.remove((int) (Math.random() * (possibleEnemies.size())));
        }

        EnemyEntity e1 = copyEnemy(possibleEnemies.get(0));
        e1.setLocation(0, 0);
        map.getCellAt(0,0).placeEnemy(e1);
        enemyTeam.add(e1);
        e1.setIndicator("E" + Integer.toString(enemyTeam.size()));

        EnemyEntity e2 = copyEnemy(possibleEnemies.get(1));
        e2.setLocation(0, 3);
        map.getCellAt(0,3).placeEnemy(e2);
        enemyTeam.add(e2);
        e2.setIndicator("E" + Integer.toString(enemyTeam.size()));

        EnemyEntity e3 = copyEnemy(possibleEnemies.get(2));
        e3.setLocation(0, 6);
        map.getCellAt(0,6).placeEnemy(e3);
        enemyTeam.add(e3);
        e3.setIndicator("E" + Integer.toString(enemyTeam.size()));
    }

    // performs a deep copy on an enemyentity object
    public EnemyEntity copyEnemy(EnemyEntity enemy) {
        if (enemy instanceof Exoskeleton) {
            return new Exoskeleton(enemy.getName(), enemy.getLevel(), enemy.getDamage(), enemy.getDefense(), enemy.getDodgeChance());
        } else if (enemy instanceof Dragon) {
            return new Dragon(enemy.getName(), enemy.getLevel(), enemy.getDamage(), enemy.getDefense(), enemy.getDodgeChance());
        } else if (enemy instanceof Demon) {
            return new Demon(enemy.getName(), enemy.getLevel(), enemy.getDamage(), enemy.getDefense(), enemy.getDodgeChance());
        } else {
            return new Spirit(enemy.getName(), enemy.getLevel(), enemy.getDamage(), enemy.getDefense(), enemy.getDodgeChance());
        } 
    }

    // when a hero tries to attack or use a spell, use this to check if there is a monster in the same cell
    // if there is, the attack/spell can proceed otherwise, can't use those actions
    public boolean monsterInSameCell(HeroEntity hero){
        int r = hero.getLocation()[0];
        int c = hero.getLocation()[1];

        return map.getCellAt(r, c).enemyCount() > 0;
    }

    public boolean heroInRange(EnemyEntity enemy) {
        int r = enemy.getLocation()[0];
        int c = enemy.getLocation()[1];
        if (r != map.getGridMap().length - 1) {
            if (c % 3 == 0) {
                if (map.getCellAt(r, c).heroCount() == 1) {
                    HeroEntity toAttack = getHeroWithLocation(r, c);
                    return attack(enemy, toAttack, enemy.getDamage());
                } else if (map.getCellAt(r+1,c).heroCount() == 1) {
                    HeroEntity toAttack = getHeroWithLocation(r+1, c);
                    return attack(enemy, toAttack, enemy.getDamage());
                } else if (map.getCellAt(r+1,c+1).heroCount() == 1) {
                    HeroEntity toAttack = getHeroWithLocation(r+1, c+1);
                    return attack(enemy, toAttack, enemy.getDamage());
                } else if (map.getCellAt(r, c+1).heroCount() == 1) {
                    HeroEntity toAttack = getHeroWithLocation(r, c+1);
                    return attack(enemy, toAttack, enemy.getDamage());
                } else {return false;}
            } else {
                if (map.getCellAt(r, c).heroCount() == 1) {
                    HeroEntity toAttack = getHeroWithLocation(r, c);
                    return attack(enemy, toAttack, enemy.getDamage());
                } else if (map.getCellAt(r+1,c).heroCount() == 1) {
                    HeroEntity toAttack = getHeroWithLocation(r+1, c);
                    return attack(enemy, toAttack, enemy.getDamage());
                } else if (map.getCellAt(r+1,c-1).heroCount() == 1) {
                    HeroEntity toAttack = getHeroWithLocation(r+1, c-1);
                    return attack(enemy, toAttack, enemy.getDamage());
                } else if (map.getCellAt(r, c-1).heroCount() == 1) {
                    HeroEntity toAttack = getHeroWithLocation(r, c-1);
                    return attack(enemy, toAttack, enemy.getDamage());
                } else {return false;} 
            }
        } else {
            return false;
        }
    }

    // displays enemies

    public void displayEnemies() {
        System.out.println("\nENEMIES ON THE BOARD: ");
        System.out.println("         name       lvl     hp    dmg    def     d%    loc    type");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < enemyTeam.size(); i++) {
            System.out.println(Integer.toString(i) + ")  " + enemyTeam.get(i).showDetailed());
        }
        System.out.println();
    }

    // get hero at location

    public HeroEntity getHeroWithLocation(int r, int c) {
        for (int i = 0; i < heroTeam.count(); i++) {
            if (map.getLocations()[i].hero_r == r && map.getLocations()[i].hero_c == c) {
                return heroTeam.get(i);
            }
        }
        return heroTeam.get(0);
    }

    // executes a monster attacking a hero
    // public boolean monsterAttacksHero(EnemyEntity enemy, HeroEntity hero) {
    //     if (!hero.isFainted()) {
    //         System.out.println(enemy.toString() + " attacks " + hero.toString() + " for " + Integer.toString(enemy.getDamage()) + " damage.");
    //         hero.takeDamage(enemy.getDamage(), "w"); 
    //         if (hero.isFainted()) {
    //             return false;
    //         } else {
    //             return true;
    //         }
    //     } else {
    //         return false;
    //     }
    // }



    // handles when some character attacks another one
    // the damage that an enemy inflicts is found with enemy.getDamage()
    // the damage that a hero inflicts if found with hero.calculateAttackDamage()
    public boolean attack(CharacterEntity attacker, CharacterEntity reciever, int damage){
        if (!reciever.isFainted()) {
            System.out.println(attacker.toString() + " attacks " + reciever.toString() + " for " + damage + " damage.");
            reciever.takeDamage(damage, "w"); 
            if (reciever.isFainted()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    // revives dead heroes at the end of each round
    public void reviveFallenHeroes() {
        for (int i = 0; i < heroTeam.count(); i++) {
            if (heroTeam.get(i).isFainted()) {
                heroTeam.get(i).revive();
                System.out.println(heroTeam.get(i).toString() + " has been " + Colors.ANSI_RED + "revived" + Colors.ANSI_RESET + ".");
                if (heroTeam.get(i).getLocation()[0] != map.rowCount() - 1) {
                    map.teleportHero(heroTeam, i, map.rowCount() - 1, heroTeam.get(i).getLocation()[1]);
                }
            }
        }
    }

}