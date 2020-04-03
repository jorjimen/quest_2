// abstract class for all the Items in the game
// implements the Entity interface

abstract class Item implements Entity {

    // private data members

    private String name;
    private int level;

    private int price;

    // constructor
    public Item(String name, int price, int level) {
        this.name = name;
        this.level = level;
        this.price = price;
    }

    // all the getter methods


    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getPrice() {
        return price;
    }

    // attribute mutator methods

    public void setName(String newName) {
        name = newName;
    }

    public void setLevel(int k) {
        level = k;
    }

    public void setPrice(int k) {
        price = k;
    }

    // additional methods

    public String toString() {
        String s = "";
        s += name + "\n";
        s += "   Level: " + Colors.ANSI_CYAN + Integer.toString(level) + Colors.ANSI_RESET  + "\n";
        s += "   Price: " + Colors.ANSI_YELLOW + Integer.toString(price) + Colors.ANSI_RESET +  "\n";
        return s;
    }

    // equals methods for the item
    public boolean equals(Item other) {
        return this.getName() == other.getName();
    }

    // abstract method that has to be implemented by any Item object
    abstract boolean canBeUsed(HeroEntity other);

}