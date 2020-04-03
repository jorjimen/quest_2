// public class for the Exoskeleton
// Subclass of the EnemyEntity

public class Demon extends EnemyEntity {

    // constructor of the Exoskeleton object
    public Demon(String name, int level, int damage, int defense, int dodge_chance) {
        super(name, level, damage, defense, dodge_chance);
    }

    public String showDetailed() {
        return super.showDetailed() + "   DEMON";
    }

    

    
}