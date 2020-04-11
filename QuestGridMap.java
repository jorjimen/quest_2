// public class for the QuestGridMap
// extends the GridMap class
public class QuestGridMap extends GridMap {

    // private data members
    private CellHeroLocation[] hero_locations = new CellHeroLocation[3];

    // QuestGridMap constructor
    public QuestGridMap(int dim) {
        super(dim);
        hero_locations[0] = new CellHeroLocation(7,0);
        hero_locations[1] = new CellHeroLocation(7,3);
        hero_locations[2] = new CellHeroLocation(7,6);
    }

    public class CellHeroLocation {
        int hero_r;
        int hero_c;

        public CellHeroLocation(int r, int c) {
            this.hero_r = r;
            this.hero_c = c;
        }

        public String toString() {
            return "(" + Integer.toString(hero_r) + "," + Integer.toString(hero_c) + ")";
        }

    }

    // methods to handle player movement
    public int moveUp(HeroTeam team, int heroIndex) {
        if (hero_locations[heroIndex].hero_r - 1 < 0 || hero_locations[heroIndex].hero_r - 1 >= super.rowCount() || hero_locations[heroIndex].hero_c < 0 || hero_locations[heroIndex].hero_c >= super.colCount()) {
            return -1;
        }
        if (super.check(hero_locations[heroIndex].hero_r-1 , hero_locations[heroIndex].hero_c)) {
            if (scans(hero_locations[heroIndex].hero_r-1, hero_locations[heroIndex].hero_c)) {
                GridMapCell cell = super.getCellAt(hero_locations[heroIndex].hero_r - 1, hero_locations[heroIndex].hero_c);
                leave(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c);
                this.getCellAt(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c).removeHero();
                hero_locations[heroIndex].hero_r -= 1;
                ((Terrain) cell.getEntity()).arrive();
                cell.placeHero(team.get(heroIndex));
                team.get(heroIndex).setLocation(cell.getRow(), cell.getColumn());
                if (cell.getEntity() instanceof Nexus) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return -2;}
        } else {
            return 0;
        }
    }

    public int moveDown(HeroTeam team, int heroIndex) {
        if (hero_locations[heroIndex].hero_r + 1 < 0 || hero_locations[heroIndex].hero_r + 1 >= super.rowCount() || hero_locations[heroIndex].hero_c < 0 || hero_locations[heroIndex].hero_c >= super.colCount()) {
            return -1;
        }
        if (super.check(hero_locations[heroIndex].hero_r+1 , hero_locations[heroIndex].hero_c)) {
            if (scans(hero_locations[heroIndex].hero_r+1, hero_locations[heroIndex].hero_c)){
            GridMapCell cell = super.getCellAt(hero_locations[heroIndex].hero_r + 1, hero_locations[heroIndex].hero_c);
            leave(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c);
            this.getCellAt(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c).removeHero();
            hero_locations[heroIndex].hero_r += 1;
            ((Terrain) cell.getEntity()).arrive();
            cell.placeHero(team.get(heroIndex));
            team.get(heroIndex).setLocation(cell.getRow(), cell.getColumn());
            if (cell.getEntity() instanceof Nexus) {
                return 1;
            } else {
                return 2;
            }
        } else {return -2;}
        } else {
            return 0;
        }
    }


    public int moveLeft(HeroTeam team, int heroIndex) {
        if (hero_locations[heroIndex].hero_r < 0 || hero_locations[heroIndex].hero_r >= super.rowCount() || hero_locations[heroIndex].hero_c - 1 < 0 || hero_locations[heroIndex].hero_c - 1 >= super.colCount()) {
            return -1;
        }
        if (super.check(hero_locations[heroIndex].hero_r , hero_locations[heroIndex].hero_c - 1)) {
            if (scans(hero_locations[heroIndex].hero_r, hero_locations[heroIndex].hero_c - 1)) {
            GridMapCell cell = super.getCellAt(hero_locations[heroIndex].hero_r, hero_locations[heroIndex].hero_c - 1);
            leave(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c);
            this.getCellAt(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c).removeHero();
            hero_locations[heroIndex].hero_c -= 1;
            cell.placeHero(team.get(heroIndex));
            ((Terrain) cell.getEntity()).arrive();
            team.get(heroIndex).setLocation(cell.getRow(), cell.getColumn());
            if (cell.getEntity() instanceof Nexus) {
                return 1;
            } else {
                return 2;
            }
        } else {return -2;}
        } else {
            return 0;
        }
    }


    public int moveRight(HeroTeam team, int heroIndex) {
        if (hero_locations[heroIndex].hero_r < 0 || hero_locations[heroIndex].hero_r >= super.rowCount() || hero_locations[heroIndex].hero_c + 1 < 0 || hero_locations[heroIndex].hero_c + 1 >= super.colCount()) {
            return -1;
        }
        if (super.check(hero_locations[heroIndex].hero_r , hero_locations[heroIndex].hero_c + 1)) {
            if (scans(hero_locations[heroIndex].hero_r, hero_locations[heroIndex].hero_c+1)) {
            GridMapCell cell = super.getCellAt(hero_locations[heroIndex].hero_r, hero_locations[heroIndex].hero_c + 1);
            leave(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c);
            this.getCellAt(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c).removeHero();
            hero_locations[heroIndex].hero_c += 1;
            ((Terrain) cell.getEntity()).arrive();
            cell.placeHero(team.get(heroIndex));
            team.get(heroIndex).setLocation(cell.getRow(), cell.getColumn());
            if (cell.getEntity() instanceof Nexus) {
                return 1;
            } else {
                return 2;
            } } else {
                return -2;
            }
        } else {
            return 0;
        }
    }
    // teleports a hero

    public int teleportHero(HeroTeam team, int heroIndex, int r, int c) {
        if (r < 0 || r >= super.rowCount() || c < 0 || c >= super.colCount()) {
            return -1;
        } else if (r == 0) {
            System.out.println("Moving to the enemy Nexus is not allowed.");
            return -1;
        } else if (hero_locations[heroIndex].hero_r == r && hero_locations[heroIndex].hero_c == c) {
            System.out.println("Teleporting to the same location is not allowed.");
            return -1;
        }
        if (super.check(r , c)) {
            if (scans(r,c)) {
                GridMapCell cell = super.getCellAt(r,c);
                leave(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c);
                this.getCellAt(hero_locations[heroIndex].hero_r,hero_locations[heroIndex].hero_c).removeHero();
                hero_locations[heroIndex].hero_r = r;
                hero_locations[heroIndex].hero_c = c;
                ((Terrain) cell.getEntity()).arrive();
                cell.placeHero(team.get(heroIndex));
                team.get(heroIndex).setLocation(cell.getRow(), cell.getColumn());
                if (cell.getEntity() instanceof Nexus) {
                    return 1;
                } else {
                    return 2;
                }
            } else {
                return -2;
            }
        } else {
            return 0;
        }
    }

    // row scan to see if it is possible to move forward 

    public boolean scans(int r, int c) {
        boolean scans;
        if (c % 3 == 0) {
            scans = rowScan(r,c) && rowScan(r,c+1);
        } else {
            scans = rowScan(r,c) && rowScan(r,c-1);
        }
        return scans;
    }

    // scans a column for an enemy
    public boolean rowScan(int r, int c) {
        for (int r_check = super.rowCount() - 1; r_check > 0; r_check--) {
            GridMapCell current = super.getCellAt(r_check, c);
            if (current.enemyCount() > 0 && r_check > r) {
                return false;
            }
        }
        return true;
    }

    // leave a cell
    public void leave(int r, int c) {
        GridMapCell cell = super.getCellAt(r, c);
        ((Terrain) cell.getEntity()).leave();
    }

    // enter a market 
    public void enterMarket(HeroTeam heroTeam) {
        Market market = new Market("Market");
        market.visit(heroTeam);
    }

    // returns true if there are monsters in the nexus

    public boolean didMonstersReachNexus() {
        for (int c = 0; c < super.colCount(); c++) {
            if (getCellAt(super.rowCount() - 1, c).enemyCount() > 0) {
                return true;
            }
        }
        return false;
    }

    // returns locations

    public CellHeroLocation[] getLocations() {
        return hero_locations;
    }

    // to string for the QuestGridMap
    public String toString() {
        String s = super.toString();
        s += Colors.ANSI_PURPLE_BACKGROUND + " " + Colors.ANSI_RESET + " : HERO NEXUS || ";
        s += Colors.ANSI_BLUE_BACKGROUND + " " + Colors.ANSI_RESET + " : ENEMY NEXUS || ";
        s += Colors.ANSI_WHITE_BACKGROUND + " " + Colors.ANSI_RESET + " : PLAIN TERRAIN || ";
        s += Colors.ANSI_CYAN_BACKGROUND + " " + Colors.ANSI_RESET + " : KOULOU TERRAIN || ";
        s += Colors.ANSI_YELLOW_BACKGROUND + " " + Colors.ANSI_RESET + " : CAVE TERRAIN || ";
        s += Colors.ANSI_GREEN_BACKGROUND + " " + Colors.ANSI_RESET + " : BUSH TERRAIN || ";
        s += Colors.ANSI_RED_BACKGROUND + " " + Colors.ANSI_RESET + " : INACCESIBLE \n";
        return s;
    }

}