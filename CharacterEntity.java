/*
Abstract class for a Character object, which will be extended by concrete character 
types (Heroes and Enemies). Implements the Entity and Unit interfaces.
*/

abstract class CharacterEntity implements Entity, Unit{

	// the attributes are protected, so the child classes can access them
	protected String name;
	protected int level, health;

	protected String indicator;

	protected int r, c;

	public CharacterEntity(String name, int level, String indicator){
		this.name = name;
		this.level = level;
		this.health = 100*level;
		this.indicator = indicator;
		this.r = 0;
		this.c = 0;
	}

	// getter methods

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public String getIndicator() {
        return indicator;
    }

     public int[] getLocation() {
        int[] location = new int[2];
        location[0] = r;
        location[1] = c;
        return location;
    }

    // setter methods
    public void setName(String newName) {
        name = newName;
    }

    public void setLevel(int k) {
        level = k;
    }

    public void setIndicator(String newIndicator) {
        indicator = newIndicator;
    }

    public void setLocation(int r, int c) {
        this.r = r;
        this.c = c;
    }

    // returns true if the character has died in the attack
    public boolean isFainted() {
        return health <= 0;
    }

    public abstract void takeDamage(int amountOfDamage, String type);

    public String toString() {
        String s = name + " (";
        s += Colors.ANSI_CYAN + Integer.toString(level) + Colors.ANSI_RESET  + ")";
        return s;
    }

}