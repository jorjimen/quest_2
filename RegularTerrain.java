// public class for RegularTerrain that can be accessed by entities
// Extends the Entity class

public class RegularTerrain implements Entity {

    String name = "O";
    boolean containsHero = false;

    public void setName(String newName) {
        newName = name;
    }

    public String getName() {
        return name;
    }

    public boolean containsHero() {
        return containsHero;
    }

    public void arrive() {
        containsHero = true;
    }

    public void leave() {
        containsHero = false;
    }


}