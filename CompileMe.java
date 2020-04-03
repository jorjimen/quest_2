// Public class for compilation
// Run the main() function in order to play the game

public class CompileMe {
    
    public static void main(String[] args) {
        // Change the dimension in the Quest() argument to change the grid's dimension
        // Set to 8 by default.
        Quest quest = new Quest(8);
        quest.play();
    }

}

