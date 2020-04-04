import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// public class for the HeroTeam

public class HeroTeam {
    

    // private data members

    private ArrayList<HeroEntity> heroes;
    private static Scanner input = new Scanner(System.in);

    // public constructor for the HeroTeam
    public HeroTeam() {
        heroes = new ArrayList<HeroEntity>();
    }

    // get a hero at a specific index
    public HeroEntity get(int index) {
        return heroes.get(index);
    }

    // add a new heroEntity
    public void add(HeroEntity newHero) {
        if (!heroes.contains(newHero)) {
            heroes.add(newHero);
            newHero.setIndicator("H" + Integer.toString(heroes.size()- 1));
        } else {System.out.println("This hero is already here.");}
    }

    // returns an iterator for the heroteam
    public Iterator<HeroEntity> heroIterator() {
        return heroes.iterator();
    }

    // handles logic for getting a hero
    public HeroEntity getHero() {
        System.out.print("\nChoose which hero you want:\n");
        System.out.println(this);
        int option = 0;
        while(true){
            try {
                System.out.print("Enter number: ");
                option = Integer.parseInt(input.nextLine());
                return heroes.get(option);
            }
            catch(Exception e){
                System.out.println("Please input a valid number.");
                continue;
            }
        }
    }

    // returns amount of heroes in the team
    public int count() {
        return heroes.size();
    }

    // returns the max level in the team
    public int getMaxLevel() {
        int max = -1;
        for (HeroEntity heroEntity : heroes) {
            if (heroEntity.getLevel() > max) {
                max = heroEntity.getLevel();
            }
        }
        return max;
    }

    public void roundRegain() {
        for (int i = 0; i < heroes.size(); i++) {
           if (!heroes.get(i).isFainted()) {
               heroes.get(i).regain();
           }
        }
    }

    // to string method
    public String toString() {
        String s = "";
        for (int i = 0; i < heroes.size(); i++) {
            s += Integer.toString(i) + ". " + heroes.get(i) + '\n';
        }
        return s;
    }

    // returns true if the hero team can continue fighting
    public boolean canContinue() {
        for (int i = 0; i < heroes.size(); i++) {
            if (!heroes.get(i).isFainted()) {
                return true;
            }
        }
        return false;
    }

    // heroes that are not fainted gain gold and exp
    public void gain(int multiplier) {
        for (int i = 0; i < heroes.size(); i++) {
            HeroEntity current = heroes.get(i);
            if (!current.isFainted()) {
                System.out.println();
                System.out.println(current.toString() + " gains " + Colors.ANSI_YELLOW + Integer.toString(150*multiplier) + Colors.ANSI_RESET + " gold.");
                current.setMoney(current.getMoney() + 150*multiplier);
                System.out.println(current.toString() + " gains " + Colors.ANSI_PURPLE + Integer.toString(2*multiplier) + Colors.ANSI_RESET + " exp.");
                if (current.increaseExperience(2*multiplier)) {
                    current.levelup();
                    System.out.println(Colors.ANSI_CYAN + current.toString() + Colors.ANSI_CYAN + " leveled up!" + Colors.ANSI_RESET);
                }
            }
        }
    }

    // revives any hero that has fainted
    public void postMatchRevive() {
        for (int i = 0; i < heroes.size(); i++) {
            HeroEntity current = heroes.get(i);
            if (current.isFainted()) {
                current.revive();
                System.out.println(current.toString() + " has been " + Colors.ANSI_RED + "revived" + Colors.ANSI_RESET + ".");
            }
        }
    }

    // detailed toString method of the heroteam
    public void showDetailed() {
        System.out.println("         name              mana    str     agi     dex     money   exp   lvl    hp      loc      type");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println(Integer.toString(i) + ")  " + heroes.get(i).showDetailed());
        }
    }

}