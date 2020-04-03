// public class for the Exoskeleton
// Subclass of the EnemyEntity

public class Spirit extends EnemyEntity {

    // Constructor for the Spirit object
    public Spirit(String name, int level, int damage, int defense, int dodge_chance) {
        super(name, level, damage, defense, dodge_chance);
    }

    public String showDetailed() {
        return super.showDetailed() + "   SPIRIT";
    }

    
}