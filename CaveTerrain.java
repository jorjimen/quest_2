// Class for Cave type of cells, which increase a hero's agility by 10%
// extends the Terrain class

public class CaveTerrain extends Terrain{

	private final String TYPE = "agility";

	public String getType(){
		return this.TYPE;
	}

}