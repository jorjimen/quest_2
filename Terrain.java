/*
Abstract class representing Terrain, which will be extended by the 
concrete terrain types (Bush, Cave Koulou, Plain). Implements the 
Entity interface.
*/

abstract class Terrain implements Entity {

    private String name = "O";

    private boolean containsHero = false;

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