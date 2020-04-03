// abstract class for Terrain that can be accessed by entities
// will be extended by the different concrete types of terrains (Bush, Cave Koulou, Plain)

// Extends the Entity class

abstract class Terrain implements Entity {

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

    public abstract String getType();


}