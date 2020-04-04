import java.util.ArrayList;

// public class for a cell in the GridMap

public class GridMapCell {

    // private data members
    private int r;
    private int c;

    private Entity entity;

    private ArrayList<Unit> units = new ArrayList<Unit>();

    // GridMapCell constructor
    public GridMapCell(int r, int c, Entity entity) {
        this.r = r;
        this.c = c;
        this.entity = entity;
    }
    
    // getter methods

    public int getRow() {
        return r;
    }

    public int getColumn() {
        return c;
    }

    public Entity getEntity() {
        return entity;
    }

    public int heroCount() {
        int count = 0;
        for (Unit unit : units) {
            if (unit instanceof HeroEntity) {
                count += 1;
            }
        }
        return count;
    }

    public int enemyCount() {
        int count = 0;
        for (Unit unit : units) {
            if (unit instanceof EnemyEntity) {
                count += 1;
            }
        }
        return count;
    }

    // setter methods

    public void setLocation(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    // toString method
    public String toString() {
        String toUseColor = Colors.ANSI_BLUE;
        if (this.entity instanceof Terrain) {
            if (((Terrain) this.entity).containsHero()) {
                toUseColor = Colors.ANSI_BLUE;
            } else if (this.entity instanceof PlainTerrain){
                toUseColor = Colors.ANSI_WHITE;
            }
            else if (this.entity instanceof BushTerrain){
                toUseColor = Colors.ANSI_GREEN;
            }
            else if (this.entity instanceof CaveTerrain){
                toUseColor = Colors.ANSI_BLACK;
            } else{
                toUseColor = Colors.ANSI_CYAN;
            }

        } else if (this.entity instanceof InaccesibleTerrain) {
            return Colors.ANSI_RED + "|" + Colors.ANSI_RED_BACKGROUND + "      "  + Colors.ANSI_RESET + Colors.ANSI_RED + "|" + Colors.ANSI_RESET;
        } else if (this.entity instanceof Market) {
            if (((Market) this.entity).containsHero())
                toUseColor = Colors.ANSI_BLUE;
            else {
                toUseColor = Colors.ANSI_YELLOW;
            }
        } else {
            toUseColor = Colors.ANSI_WHITE;
        }
        return toUseColor + "|" + cellContentPrintable() + "|" + Colors.ANSI_RESET;
    }

    // additional method

    public boolean placeHero(HeroEntity hero) {
        if (heroCount() == 0) {
            units.add(hero);
            return true;
        } else {
            return false;
        }
    }

    public boolean placeEnemy(EnemyEntity enemy) {
        if (enemyCount() == 0) {
            units.add(enemy);
            return true;
        } else {
            return false;
        }
    }

    public void removeHero() {
        for (var i = 0; i < units.size(); i++) {
            if (units.get(i) instanceof HeroEntity) {
                units.remove(i);
            }
        } 
    }

    public void removeEnemy() {
        for (var i = 0; i < units.size(); i++) {
            if (units.get(i) instanceof EnemyEntity) {
                units.remove(i);
            }
        } 
    }

    public String cellContentPrintable() {
        if (units.size() == 0) {
            return "      ";
        } else if (units.size() == 1) {
            return "  " + units.get(0).getIndicator() + "  ";
        } else {
            return units.get(0).getIndicator() + "  " + units.get(1).getIndicator();
        }
    }

}