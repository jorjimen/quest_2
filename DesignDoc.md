# Quest of Legends Design Document

### Team 14: Jorge Jimenez and Victoria Kayola

For our implementation for the Quest of Legends, we started with Jorge's code from Quest part 1 and added onto it for this game, combining it with some parts of Victoria's part 1 Quest implementation. The class structure itself follows the original pattern of Jorge's class structure, with a few additional interfaces, abstract class and regular classes. We chose to do this because Jorge's code was a bit more modular, so it seemed easier to work off of and expand into The Quest of Legends game. However, as we worked on The Quest Of Legends, we noticed that some of Victoria's components and her implementations in her class structure where better suited for this assignment, so we found a way to combine Jorge's original design with some of her components. Porting it into Jorge's designs were fairly easy due to the easy readability of Victoria's original code and the modularity in the class structure we were working with.

Most of the class structure and their implentations have been kept the same, but the new additions and new implementations are specified below with their reasoning:

##### Interfaces:
- Consumable interface: This is exactly the same from Jorge's Part 1 implementation.
- Equippable interface: This is the same from Jorge's Part 1 implementation.
- Entity interface: This is the same from Jorge's Part 1 implementation.
- Sellable interface: This is a new interface that we made; we realized that it is useful to have this interface to differentiate between objects that can and can't be sold. 
- Unit interface: This is the same from Jorge's Part 1 implementation.

##### Abstract Classes:
- CharacterEntity class: This is a new class, that is similar to the Character class from Victoria's implementation. We added this because Jorge's Part 1 implementation did not have a parent class for Heroes and Enemies. This component is crucial as it established a firmer relationship between the heroes and monsters, however, it was kept abstract because it is not necessary for this to ever actually be instantiated as a single object.
- EnemyEntity class: This is similar to the EnemyEntity class from Jorge's part 1 implementation, but now it is a child class of CharacterEntity.
- Item class: This is the same from Jorge's Part 1 implementation. Only change is that they now implement the Sellable interface, but all of the functionality had already been specified but we decided to enforce it regardless because it was an important component.
- HeroEntity class: This is similar to the HeroEntity class from Jorge's part 1 implementation, but now it is a child class of CharacterEntity.
- Spell class: This is the same from Jorge's Part 1 implementation.
- Terrain class: This is a new class that we added because there are different types of cells in this game (Cave, Bush, etc) so we wanted this to be an abstract parent class for the concrete terrain types. It adds a better class structure, as the different types of terrains are essentally the same, but their effects vary. By creating an abstract parent class, we could simply just subclass the concrete terrains that are going to be in the board.

##### Classes:
- Armor class: This is the same from Jorge's Part 1 implementation.
- BushTerrain class: This is a new class, because Bush cells did not exist in the Quest Part 1. Extends the Terrain abstract class as it is one of the terrains in the board, but having its own concrete class differences from the rest.
- CaveTerrain class: This is a new class, because Cave cells did not exist in the Quest Part 1. Extends the Terrain abstract class as it is one of the terrains in the board, but having its own concrete class differences from the rest.
- Colors class: This is the same from Jorge's Part 1 implementation.
- CompileMe class: This is the same from Jorge's Part 1 implementation.
- Demon class: This is the same from Jorge's Part 1 implementation.
- Dragon class: This is the same from Jorge's Part 1 implementation.
- Exoskeleton class: This is the same from Jorge's Part 1 implementation.
- FireSpell class: This is the same from Jorge's Part 1 implementation.
- GameObjects class: This is the same from Jorge's Part 1 implementation.
- GridMap class: This is the same from Jorge's Part 1 implementation, but the cell distributions have been changed.
- GridMapCell class: This is the same from Jorge's Part 1 implementation, but the implementation has been modified to handle the game functionality and better printing of the GridMap itself.
- HeroTeam class: This is the same from Jorge's Part 1 implementation.
- IceSpell class: This is the same from Jorge's Part 1 implementation.
- InaccessibleTerrain class: This is the same from Jorge's Part 1 implementation.
- KoulouTerrain class: This is a new class, because Koulou cells did not exist in the Quest Part 1. Extends the Terrain abstract class as it is one of the terrains in the board, but having its own concrete class differences from the rest.
- LightningSpell class: This is the same from Jorge's Part 1 implementation. 
- LightSpell class: This is the same from Jorge's Part 1 implementation.
- Lord class: This is the same from Jorge's Part 1 implementation.
- Market class: This is the same from Jorge's Part 1 implementation.
- Nexus class: This is a new class, because Nexus did not exist in Part 1.
- Paladin class: This is the same from Jorge's Part 1 implementation.
- PlainTerrain class: This is similar to the RegularTerrain class from Jorge's part 1 implementation but with a new name. Extends the Terrain abstract class as it is one of the terrains in the board, but having its own concrete class differences from the rest.
- Potion class: This is the same from Jorge's Part 1 implementation.
- QuestGridMap class: This is the same class structure from Jorge's Part 1 implementation, but the implementation has been modified to handle the different functionality of the new game.
- QuestOfLegends class: This is similar to the Quest class from Jorge's part 1, but the implementation has some functionality combined with Victoria's part 1.
- Sorcerer class: This is the same from Jorge's Part 1 implementation.
- Spirit class: This is the same from Jorge's Part 1 implementation.
- Warrior class: This is the same from Jorge's Part 1 implementation.
- Weapon class: This is the same from Jorge's Part 1 implementation.

##### REMOVED Classes:
- Fight class: Both Victoria and Jorge were in the agreement that this class should be removed. Due to the nature of the fights in this game, fights are not a seperate state in the play, therefore, it was not necessary to contain this class. Some important code from this file, however, is found in QuestOfLegends, but the class itself has been removed because it is not necessary.