// public class for the QuestGridMap
// extends the GridMap class

public class QuestGridMap extends GridMap {

    // private data members
    private int hero_r;
    private int hero_c;

    // QuestGridMap constructor
    public QuestGridMap(int dim) {
        super(dim);
        hero_r = dim - 1;
        hero_c = dim - 1;
    }

    // methods to handle player movement
    public int moveUp() {
        if (hero_r - 1 < 0 || hero_r - 1 >= super.rowCount() || hero_c < 0 || hero_c >= super.colCount()) {
            return -1;
        }
        if (super.check(hero_r-1 , hero_c)) {
            GridMapCell cell = super.getEntityAt(hero_r - 1, hero_c);
            if (cell.getEntity() instanceof Market) {
                leave(hero_r,hero_c);
                hero_r -= 1;
                ((Market) cell.getEntity()).arrive();
                return 1;
            } else {
                leave(hero_r,hero_c);
                hero_r -= 1;
                ((Terrain) cell.getEntity()).arrive();
                return 2;
            }
        } else {
            return 0;
        }
    }

    public int moveDown() {
        if (hero_r + 1 < 0 || hero_r + 1 >= super.rowCount() || hero_c < 0 || hero_c >= super.colCount()) {
            return -1;
        }
        if (super.check(hero_r+1 , hero_c)) {
            GridMapCell cell = super.getEntityAt(hero_r + 1, hero_c);
            if (cell.getEntity() instanceof Market) {
                leave(hero_r,hero_c);
                hero_r += 1;
                ((Market) cell.getEntity()).arrive();
                return 1;
            } else {
                leave(hero_r,hero_c);
                hero_r += 1;
                ((Terrain) cell.getEntity()).arrive();
                return 2;
            }
        } else {
            return 0;
        }
    }


    public int moveLeft() {
        if (hero_r < 0 || hero_r >= super.rowCount() || hero_c - 1 < 0 || hero_c - 1 >= super.colCount()) {
            return -1;
        }
        if (super.check(hero_r , hero_c - 1)) {
            GridMapCell cell = super.getEntityAt(hero_r, hero_c - 1);
            if (cell.getEntity() instanceof Market) {
                leave(hero_r,hero_c);
                hero_c -= 1;
                ((Market) cell.getEntity()).arrive();
                return 1;
            } else {
                leave(hero_r,hero_c);
                hero_c -= 1;
                ((Terrain) cell.getEntity()).arrive();
                return 2;
            }
        } else {
            return 0;
        }
    }


    public int moveRight() {
        if (hero_r < 0 || hero_r >= super.rowCount() || hero_c + 1 < 0 || hero_c + 1 >= super.colCount()) {
            return -1;
        }
        if (super.check(hero_r , hero_c + 1)) {
            GridMapCell cell = super.getEntityAt(hero_r, hero_c + 1);
            if (cell.getEntity() instanceof Market) {
                leave(hero_r,hero_c);
                hero_c += 1;
                ((Market) cell.getEntity()).arrive();
                return 1;
            } else {
                leave(hero_r,hero_c);
                hero_c += 1;
                ((Terrain) cell.getEntity()).arrive();
                return 2;
            }
        } else {
            return 0;
        }
    }

    // leave a cell
    public void leave(int r, int c) {
        GridMapCell cell = super.getEntityAt(r, c);
        if (cell.getEntity() instanceof Market) {
            ((Market) cell.getEntity()).leave();
        } else {
            ((Terrain) cell.getEntity()).leave();
        }
    }

    // enter a market 
    public void enterMarket(HeroTeam heroTeam) {
        Market market = new Market("Market");
        market.visit(heroTeam);
    }

    // to string for the QuestGridMap
    public String toString() {
        String s = super.toString();
        s += Colors.ANSI_BLUE_BACKGROUND + " " + Colors.ANSI_RESET + " : YOU || ";
        s += Colors.ANSI_YELLOW_BACKGROUND + " " + Colors.ANSI_RESET + " : MARKET || ";
        s += Colors.ANSI_WHITE_BACKGROUND + " " + Colors.ANSI_RESET + " : PLAIN TERRAIN || ";
        s += Colors.ANSI_CYAN_BACKGROUND + " " + Colors.ANSI_RESET + " : KOULOU TERRAIN || ";
        s += Colors.ANSI_BLACK_BACKGROUND + " " + Colors.ANSI_RESET + " : CAVE TERRAIN || ";
        s += Colors.ANSI_GREEN_BACKGROUND + " " + Colors.ANSI_RESET + " : BUSH TERRAIN || ";
        s += Colors.ANSI_RED_BACKGROUND + " " + Colors.ANSI_RESET + " : INACCESIBLE \n";
        return s;
    }

}