import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// Main Quest object, to handle all the main game logic
public class Quest {

    // private data members
    private QuestGridMap map;
    private HeroTeam heroTeam;
    private ArrayList<EnemyEntity> enemyTeam;
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
        if(numRounds == 8){
            buildEnemyTeam();
            numRounds = 1;
        }
        String option = "";
        do {
            if (!heroTeam.canContinue()) {
                break;
            }
            try {
                System.out.println("HERO TEAM:");
                heroTeam.showDetailed();
                System.out.println("\n"+ map);
                System.out.println("Move list:\n W/w) Move Up\n A/a) Move Left\n S/s) Move Down\n D/d) Move Right \n T/t) Teleport \n B/b) Return to Nexus/Market \n I/i) Inspect Team\n Q/q) Quit\n");
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
                } else if (option.equals("Q") || option.equals("q")) {break;
                } else if (option.equals("B") || option.equals("b")){
                    System.out.println("Going back to Nexus...");
                    ret = map.teleportHero(heroTeam, index, map.rowCount()-1, heroTeam.get(index).getLocation()[1]);
                } else {
                    System.out.println("I can't recognize that command hero...");
                } 

                switch(ret) {
                    case -1:
                        System.out.println("Hero, you cannot move here! Look at the map and notice your location.");
                        break;
                    case 0:
                        System.out.println("Agh! You have hit your head against a wall. Remember, look at your map. You cannot access locations marked in red. Let's back up.");
                      break;
                    case 1:
                        System.out.println("Home sweet home! You have arrived to a Nexus.");
                        if (index + 1 == heroTeam.count()) {
                            index = 0;
                        } else {
                            index += 1;
                        }
                        map.enterMarket(heroTeam);
                      break;
                    case 2:
                        System.out.println("You moved into wild grass");
                        if (randomEncounterProbability()) {
                            System.out.println("A random monster appears! Prepare for battle hero, this one might be tough.");
                            // Fight fight = new Fight(heroTeam);
                            // fight.fight();
                        } else {
                            System.out.println("No monsters have appeared. You are safe for now.");
                        }
                        if (index + 1 == heroTeam.count()) {
                            index = 0;
                            moveEnemies();
                            numRounds +=1;
                        } else {
                            index += 1;
                        }
                  }
            } catch (Exception e) {System.out.println("Something went wrong...");}
  
        } while (!option.equals("Q") && !option.equals("q"));
        System.out.println("Thank you for playing!");
    }
    private void moveEnemies(){
        for(int i = 0; i < enemyTeam.size(); i++){
            // should probs do some checking here to make sure it doesnt go out of bounds.
            // however, it can't go out of bounds bc it only moves down,
            // so the only check necessary is if it reaches the hero nexus,
            // at which point the game ends and mosters win

            EnemyEntity enemy = enemyTeam.get(i);

            int row = enemy.getLocation()[0]+1;
            int col = enemy.getLocation()[1];
            map.leave(row-1, col);

            enemy.setLocation(row,col);
            map.getCellAt(row,col).placeEnemy(enemy);

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

        this.enemyTeam = new ArrayList<EnemyEntity>();

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


        possibleEnemies.get(0).setLocation(0,0);
        map.getCellAt(0,0).placeEnemy(possibleEnemies.get(0));
        enemyTeam.add(possibleEnemies.get(0));

        possibleEnemies.get(1).setLocation(0,3);
        map.getCellAt(0,3).placeEnemy(possibleEnemies.get(1));
        enemyTeam.add(possibleEnemies.get(1));

        possibleEnemies.get(2).setLocation(0,6);
        map.getCellAt(0,6).placeEnemy(possibleEnemies.get(2));
        enemyTeam.add(possibleEnemies.get(2));


    }

    // returns true if a random encounter has been found
    private boolean randomEncounterProbability() {
        int random = (int) (Math.random() * (101));
        return random < 75;
    }
}