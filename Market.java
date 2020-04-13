// public class for the Market
// Implements the Entity class

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Iterator;


public class Market implements Entity {

    // private data members
    private MarketInventory MarketInventory = new MarketInventory();
    private String marketName;
    private static Scanner input = new Scanner(System.in);

    private boolean containsHero = false;

    // constructor for a market
    public Market(String marketName) {
        this.marketName = marketName;
    }

    // private class for the Market's inventory
    private class MarketInventory {
        public ArrayList<Spell> spells = new ArrayList<Spell>(Arrays.asList(GameObjects.fs1,GameObjects.fs2,GameObjects.fs3,GameObjects.fs4,
        GameObjects.fs5,GameObjects.ls1,GameObjects.ls2,GameObjects.ls3,GameObjects.ls4,GameObjects.is1, 
        GameObjects.is2, GameObjects.is3, GameObjects.is4, GameObjects.is5, GameObjects.li1, GameObjects.li2, GameObjects.li3));
        public ArrayList<Potion> potions = new ArrayList<Potion>(Arrays.asList(GameObjects.po1, GameObjects.po2,
        GameObjects.po3, GameObjects.po4, GameObjects.po5, GameObjects.po6, GameObjects.po7, GameObjects.po8));
        public ArrayList<Weapon> weapons = new ArrayList<Weapon>(Arrays.asList(GameObjects.wpn1, GameObjects.wpn2,
        GameObjects.wpn3, GameObjects.wpn4, GameObjects.wpn5, GameObjects.wpn6, GameObjects.wpn7,GameObjects.wpn8, GameObjects.wpn9));
        public ArrayList<Armor> armors = new ArrayList<Armor>(Arrays.asList(GameObjects.ar1, GameObjects.ar2,
        GameObjects.ar3, GameObjects.ar4,GameObjects.ar6, GameObjects.ar7));

        public void spells() {
            System.out.println("Spells \n");
            if (spells.size() == 0) {
                System.out.println("--no spells--");
            } else {
                for (int i = 0; i < spells.size(); i++) {
                    System.out.println(Integer.toString(i) + ". " + spells.get(i));
                }
            }
        }

        public void potions() {
            System.out.println("\nPotions \n");
            if (potions.size() == 0) {
                System.out.println("--no potions--");
            } else {
                for (int i = 0; i < potions.size(); i++) {
                    System.out.println(Integer.toString(i) + ". " + potions.get(i));
                }
            }
        }

        public void weapons() {
            System.out.println("\nWeapons \n");
            if (weapons.size() == 0) {
                System.out.println("--no weapons--");
            } else {
                for (int i = 0; i < weapons.size(); i++) {
                    System.out.println(Integer.toString(i) + ". " + weapons.get(i));
                }
            }
        }

        public void armor() {
            System.out.println("\nArmor \n");
            if (armors.size() == 0) {
                System.out.println("--no armors--");
            } else {
                for (int i = 0; i < armors.size(); i++) {
                    System.out.println(Integer.toString(i) + ". " + armors.get(i));
                }
            }
        }
    }

    // handles user input logic for buying an item
    public void buy(HeroTeam heroTeam) {
        HeroEntity buyer = heroTeam.getHero();
        int option = 0;
        while(true){
            try {
                System.out.println("\nCurrently purchasing for: " + buyer.toString() + ", with a balance of " + Colors.ANSI_YELLOW + Integer.toString(buyer.getMoney()) + Colors.ANSI_RESET + " gold.");
                System.out.println("What item would you like to buy?:\n 1) Potion \n 2) Spell \n 3) Weapon\n 4) Armor\n 5) Heal Team\n 6) Back");
                System.out.print("\nEnter move: ");
                option = Integer.parseInt(input.nextLine());
                if (option > 0 && option < 7) {
                    if (option == 1) {
                        MarketInventory.potions();
                        System.out.print("\nChoose which potion (-1 to go back): ");
                        option = Integer.parseInt(input.nextLine());
                        Potion toBuy = MarketInventory.potions.get(option);
                        if (toBuy.canBeUsed(buyer) && toBuy.getPrice() <= buyer.getMoney()) {
                            System.out.println(buyer.getName() + " succesfully purchased a " + toBuy.getName()+ ".");
                            buyer.setMoney(buyer.getMoney() - toBuy.getPrice());
                            buyer.addPotion(toBuy);
                        } else {
                            System.out.println("You are unable to purchase this.");
                        }
                    } else if (option == 2) {
                        MarketInventory.spells();
                        System.out.print("\nChoose which spell (-1 to go back): ");
                        option = Integer.parseInt(input.nextLine());
                        Spell toBuy = MarketInventory.spells.get(option);
                        if (toBuy.canBeUsed(buyer) && toBuy.getPrice() <= buyer.getMoney()) {
                            System.out.println(buyer.getName() + " succesfully purchased a " + toBuy.getName()+ ".");
                            buyer.setMoney(buyer.getMoney() - toBuy.getPrice());
                            buyer.addSpell(toBuy);
                        } else {
                            System.out.println("You are unable to purchase this.");
                        }
                    } else if (option == 3) {
                        MarketInventory.weapons();
                        System.out.print("\nChoose which weapon (-1 to go back): ");
                        option = Integer.parseInt(input.nextLine());
                        Weapon toBuy = MarketInventory.weapons.get(option);
                        if (toBuy.canBeUsed(buyer) && toBuy.getPrice() <= buyer.getMoney()) {
                            System.out.println(buyer.getName() + " succesfully purchased a " + toBuy.getName() + ".");
                            buyer.addWeaponToInventory(toBuy);
                            buyer.setMoney(buyer.getMoney() - toBuy.getPrice());
                        } else {
                            System.out.println("You are unable to purchase this.");
                        }
                    } else if (option == 4) {
                        MarketInventory.armor();
                        System.out.print("\nChoose which armor (-1 to go back): ");
                        option = Integer.parseInt(input.nextLine());
                        Armor toBuy = MarketInventory.armors.get(option);
                        if (toBuy.canBeUsed(buyer) && toBuy.getPrice() <= buyer.getMoney()) {
                            System.out.println(buyer.getName() + " succesfully purchased a " + toBuy.getName()+ ".");
                            buyer.addArmorToInventory(toBuy);
                            buyer.setMoney(buyer.getMoney() - toBuy.getPrice());
                        } else {
                            System.out.println("You are unable to purchase this.");
                        }
                    } else if (option == 5) {
                        healHandler(buyer, heroTeam);
                    } else if (option == -1) {

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

    public void healHandler(HeroEntity buyer, HeroTeam heroTeam) {
        int healCount = 0;
        Iterator<HeroEntity> iter = heroTeam.heroIterator();
        while (iter.hasNext()) {
            HeroEntity curr = iter.next();
            if (curr.getHealth() != curr.getLevel() * 100) {
                healCount += 1;
            }
        }
        int moneyRequired = (healCount * 1200);
        iter = heroTeam.heroIterator();
        String intro = "";
        if (healCount == 1) {
            intro = Colors.ANSI_GREEN + Integer.toString(healCount) + Colors.ANSI_RESET + " hero requires healing. ";
        } else {
            intro = Colors.ANSI_GREEN + Integer.toString(healCount) + Colors.ANSI_RESET + " heroes require healing. ";
        }
        if (buyer.getMoney() < moneyRequired) {
            System.out.println(intro + "This action will cost you " + Colors.ANSI_YELLOW + Integer.toString(moneyRequired) 
            + Colors.ANSI_RESET + " gold, however, you do not have enough gold.");
        } else if (moneyRequired == 0) {
            System.out.println("No hero requires healing.");
        } else {
            try {
            System.out.print(intro + "Therefore, healing your team will cost you " + Colors.ANSI_YELLOW + Integer.toString(moneyRequired) 
            + Colors.ANSI_RESET + " gold. Would you like to proceed? (Type 1 to PROCEED): ");
            int option = Integer.parseInt(input.nextLine());
            if (option == 1) {
                System.out.println();
                while (iter.hasNext()) {
                    HeroEntity curr = iter.next();
                    if (curr.isFainted() || curr.getHealth() < curr.getLevel() * 100) {
                        curr.setHealth(curr.getLevel() * 100);
                        System.out.println(curr.toString() + " has been fully " + Colors.ANSI_RED + "healed" + Colors.ANSI_RESET + ".");
                    }
                }
                buyer.setMoney(buyer.getMoney() - moneyRequired);
            }
            } catch (Exception e) {
                System.out.println("Something seems to not be right... ");
            }
        }
    }

    // handles user input for selling an item
    public void sell(HeroTeam heroTeam) {
        HeroEntity buyer = heroTeam.getHero();
        int option = 0;
        while(true){
            try {
                System.out.println("\nCurrently selling for: " + buyer.toString() + ", who has " + Colors.ANSI_YELLOW + Integer.toString(buyer.getMoney()) + Colors.ANSI_RESET + " gold.");
                System.out.println("What item would you like to sell?:\n 1) Potion \n 2) Spell \n 3) Weapon\n 4) Armor\n 5) Main Menu");
                System.out.print("Enter move: ");
                option = Integer.parseInt(input.nextLine());
                if (option > 0 && option < 6) {
                    if (option == 1) {
                        if (buyer.hasPotion()) {
                            buyer.showPotion();
                            try {
                                System.out.print("Enter move: ");
                                int option_sell = 0;
                                option_sell = Integer.parseInt(input.nextLine());
                                Potion removed = buyer.removePotion(option_sell);
                                buyer.setMoney(buyer.getMoney() + (removed.getPrice() / 2));
                                System.out.println("Congratulations, you sold a " + removed.getName() + " for " + Colors.ANSI_YELLOW + Integer.toString((removed.getPrice() / 2)) + Colors.ANSI_RESET + " gold.");
                            } catch (Exception e) {
                              System.out.println("Sell unsuccesfull");  
                            }
                        } else {
                            System.out.println("You have no potions.");
                        }
                    } else if (option == 2) {
                        if (buyer.hasSpells()) {
                            buyer.showSpells();
                            try {
                                System.out.print("Enter move: ");
                                int option_sell = 0;
                                option_sell = Integer.parseInt(input.nextLine());
                                Spell removed = buyer.removeSpell(option_sell);
                                buyer.setMoney(buyer.getMoney() + (removed.getPrice() / 2));
                                System.out.println("Congratulations, you sold a " + removed.getName() + " for " + Colors.ANSI_YELLOW + Integer.toString((removed.getPrice() / 2)) + Colors.ANSI_RESET + " gold.");
                            } catch (Exception e) {
                              System.out.println("Sell unsuccesfull");  
                            }
                        } else {
                            System.out.println("You have no spells.");
                        }
                    } else if (option == 3) {
                        if (buyer.hasWeapons()) {
                            buyer.showWeapons();
                            try {
                                System.out.print("Enter move: ");
                                int option_sell = 0;
                                option_sell = Integer.parseInt(input.nextLine());
                                Weapon removed = buyer.removeWeaponFromInventory(option_sell);
                                buyer.setMoney(buyer.getMoney() + (removed.getPrice() / 2));
                                System.out.println("Congratulations, you sold a " + removed.getName() + " for " + Colors.ANSI_YELLOW + Integer.toString((removed.getPrice() / 2)) + Colors.ANSI_RESET + " gold.");
                            } catch (Exception e) {
                              System.out.println("Sell unsuccesfull");  
                            }
                        } else {
                            System.out.println("You have no weapons.");
                        }
                    } else if (option == 4) {
                        if (buyer.hasArmors()) {
                            buyer.showArmors();
                            try {
                                System.out.print("Enter move: ");
                                int option_sell = 0;
                                option_sell = Integer.parseInt(input.nextLine());
                                Armor removed = buyer.removeArmorFromInventory(option_sell);
                                buyer.setMoney(buyer.getMoney() + (removed.getPrice() / 2));
                                System.out.println("Congratulations, you sold a " + removed.getName() + " for " + Colors.ANSI_YELLOW + Integer.toString((removed.getPrice() / 2)) + Colors.ANSI_RESET + " gold.");
                            } catch (Exception e) {
                              System.out.println("Sell unsuccesfull");  
                            }
                        } else {
                            System.out.println("You have no armors.");
                        }
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

    // handles logic for getting the hero's main option
    public void getMainOption(HeroTeam heroTeam) {
        int option = 0;
        while(true){
            try {
                System.out.println("\nWelcome back to your Nexus! As always, our market is open. What would you like to do?:\n 1) Buy \n 2) Sell \n 3) Leave");
                System.out.print("\nEnter move: ");
                option = Integer.parseInt(input.nextLine());
                if (option > 0 && option < 5) {
                    if (option == 1) {
                        buy(heroTeam);
                    } else if (option == 2) {
                        sell(heroTeam);
                    } else {
                        System.out.println("Thank you for the visit!");
                        break;
                    }
                } else {
                    System.out.println("Enter a number between 1-3");
                }
            }
            catch(Exception e){
                System.out.println("This is not a valid option...");
                continue;
            }
        }
    }

    // method from interface
    public void setName(String newName) {
        marketName = newName;
    }

    public String getName() {
        return marketName;
    }

    // to string method
    public String toString() {
        return marketName;
    }

    // additional logic methods
    public boolean containsHero() {
        return containsHero;
    }

    public void arrive() {
        containsHero = true;
    }

    public void leave() {
        containsHero = false;
    }

    // visit a market
    public void visit(HeroTeam heroTeam) {
        getMainOption(heroTeam);
    }

}