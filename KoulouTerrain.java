// Class for Koulou type of cells, which increase a hero's strength by 10%
// extends the Terrain class

public class KoulouTerrain extends Terrain{

	private final String TYPE = "strength";

	public String getType(){
		return this.TYPE;
	}

}