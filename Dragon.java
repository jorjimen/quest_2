// public class for the Dragon
// Subclass of the EnemyEntity

public class Dragon extends EnemyEntity {

    // Constructor for the Dragon object

    public Dragon(String name, int level, int damage, int defense, int dodge_chance) {
        super(name, level, damage, defense, dodge_chance);
    }

    // Overrides super showDetailed methods

    public String showDetailed() {
        return super.showDetailed() + "   DRAGON";
    }

    
}