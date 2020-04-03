// public class for a cell in the GridMap

public class GridMapCell {

    // private data members
    private int r;
    private int c;

    private Entity entity;

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
        if (this.entity instanceof Terrain) {
            if (((Terrain) this.entity).containsHero()) {
                return Colors.ANSI_BLUE_BACKGROUND + " " + Colors.ANSI_RESET; 
            } else if (this.entity instanceof PlainTerrain){
                return Colors.ANSI_WHITE_BACKGROUND + " " + Colors.ANSI_RESET;
            }
            else if (this.entity instanceof BushTerrain){
                return Colors.ANSI_GREEN_BACKGROUND + " " + Colors.ANSI_RESET;
            }
            else if (this.entity instanceof CaveTerrain){
                return Colors.ANSI_BLACK_BACKGROUND + " " + Colors.ANSI_RESET;
            }else{
                return Colors.ANSI_CYAN_BACKGROUND + " " + Colors.ANSI_RESET;
            }

        }else if (this.entity instanceof InaccesibleTerrain) {
            return Colors.ANSI_RED_BACKGROUND + " " + Colors.ANSI_RESET;
        } else if (this.entity instanceof Market) {
            if (((Market) this.entity).containsHero())
                return Colors.ANSI_BLUE_BACKGROUND + " " + Colors.ANSI_RESET;
            else {
                return Colors.ANSI_YELLOW_BACKGROUND + " " + Colors.ANSI_RESET;
            }
        } else {
            return Colors.ANSI_WHITE_BACKGROUND + " " + Colors.ANSI_RESET;
        }
    }


}