// public class for the Exoskeleton
// Subclass of the EnemyEntity

public class Exoskeleton extends EnemyEntity {

    // constructor of the Exoskeleton object
    public Exoskeleton(String name, int level, int damage, int defense, int dodge_chance) {
        super(name, level, damage, defense, dodge_chance);
    }

    public String showDetailed() {
        return super.showDetailed() + "   EXO";
    }

    

    
}