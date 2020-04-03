// public class for the GridMap

public class GridMap {

    // Change this variable to change ceiling of GridMap dimension.
    final private int GridMap_DIM_MAX = 100;

    private GridMapCell[][] GridMap;

    // n*n GridMap constructor
    public GridMap(int dim) {
        if (dim < 3) {
            throw new IllegalArgumentException("Specified dimension cannot be lower than 3.");
        } else if (dim > GridMap_DIM_MAX) {
            throw new IllegalArgumentException("The specified dimension is higher than the GridMap_DIM_MAX ("
                    + Integer.toString(GridMap_DIM_MAX) + ").");
        }
        GridMap = new GridMapCell[dim][dim];
        for (int r = 0; r < GridMap.length; r++){
            for (int c = 0; c < GridMap[r].length; c++) {
                // create the inaccessible lane every two columns
                if(c % 3 == 2){
                    GridMap[r][c] = new GridMapCell(r,c,new InaccesibleTerrain());
                }
                else if(r == 0 || r == GridMap.length -1){
                    GridMap[r][c] = new GridMapCell(r,c,new Market("Market"));
                }
                else{
                    GridMap[r][c] = new GridMapCell(r,c,new RegularTerrain());  
                }
            }
        }
        GridMap[dim - 1][dim - 1] = new GridMapCell(dim-1,dim-1, new RegularTerrain());
        ((RegularTerrain) GridMap[dim - 1][dim - 1].getEntity()).arrive();
    }

    // n*m GridMap constructor
    public GridMap(int row, int col) {
        if (row < 3 || col < 3) {
            throw new IllegalArgumentException("GridMap dimensions cannot be lower than 3");
        } else if (row > GridMap_DIM_MAX || col > GridMap_DIM_MAX) {
            throw new IllegalArgumentException("The specified dimension is higher than the GridMap_DIM_MAX ("
                    + Integer.toString(GridMap_DIM_MAX) + ")");
        }
        GridMap = new GridMapCell[row][col];
        for (int r = 0; r < GridMap.length; r++) {
            for (int c = 0; c < GridMap[r].length; c++) {
                GridMap[r][c] = new GridMapCell(r,c,new InaccesibleTerrain());
            }
        }
    }

    // toString implementation for a GridMap
    public String toString() {
        String GridMap_repr = "";
        String divisor = "";
        for (int i = 0; i < GridMap[0].length; i++) {
            divisor += "+---";
        }
        divisor += "+\n";
        for (int r = 0; r < GridMap.length; r++) {
            GridMap_repr += divisor;
            for (int c = 0; c < GridMap[0].length; c++) {
                GridMap_repr += "| " + GridMap[r][c] + " ";
            }
            GridMap_repr += "|\n";
        }
        GridMap_repr += divisor;
        return GridMap_repr;
    }

    // checks if a can be moved to
    public boolean check(int row, int col) {
        return !(GridMap[row][col].getEntity() instanceof InaccesibleTerrain);
    }

    public GridMapCell getEntityAt(int row, int col) {
        return GridMap[row][col];
    }

    // swap two cell locations
    public void swapCell(int r1, int c1, int r2, int c2) {
        GridMapCell temp = GridMap[r1][c1];
        GridMap[r1][c1] = GridMap[r2][c2];
        GridMap[r2][c2] = temp;
        GridMap[r1][c1].setLocation(r1, c1);
        GridMap[r2][c2].setLocation(r2, c2);
    }

    // takes in an array representation of 1s and 0s and fills the GridMap to match
    // that array. Locations with 1s will get tiles and locations with 0s will stay
    // empty
    public void fill(int[][] arr, Entity entity) {
        for (int r = 0; r < GridMap.length; r++) {
            for (int c = 0; c < GridMap[r].length; c++) {
                if (arr[r][c] == 1) {
                    GridMap[r][c].setEntity(entity);
                }
            }
        }
    }

    // deep copy of the GridMap
    public GridMapCell[][] deep_copy() {
        GridMapCell[][] deep_copy = new GridMapCell[GridMap.length][GridMap[0].length];
        for (int r = 0; r < GridMap.length; r++) {
            for (int c = 0; c < GridMap[0].length; c++) {
                deep_copy[r][c] = GridMap[r][c];
            }
        }
        return deep_copy;
    }

    // get copy of the original GridMap array
    public GridMapCell[][] getGridMap() {
        return GridMap;
    }

    // get the GridMap dimension max (GridMap dimension ceiling)
    public int GET_GridMap_DIM_MAX() {
        return GridMap_DIM_MAX;
    }

    // get the row dimension of the GridMap
    public int rowCount() {
        return GridMap.length;
    }

    // get the column dimension of the GridMap
    public int colCount() {
        return GridMap[0].length;
    }
}