// public interface for any Unit in the game

public interface Unit {

    void setLevel(int k);
    
    void takeDamage(int amountOfDamage, String type);

    boolean isFainted();
    
    int getHealth();

    String showDetailed();

    void setIndicator(String newIndicator);

    String getIndicator();

} 