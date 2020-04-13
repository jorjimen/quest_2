// Class representing the tiles on the board that are Nexus spaces. Extends the Terrain class.

public class Nexus extends Terrain {

    private String TYPE;

    public Nexus(String type) {
        TYPE = type;
    }

    public String getType(){
		return this.TYPE;
	}
    
}