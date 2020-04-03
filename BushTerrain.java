// Class for Bush type of cells, which increase a hero's dexterity by 10%
// extends the Terrain class

public class BushTerrain extends Terrain{

	private final String TYPE = "dexterity";

	public String getType(){
		return this.TYPE;
	}

}