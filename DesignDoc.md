# Quest of Legends Design Document

### Team 14: Jorge Jimenez and Victoria Kayola

For our implementation for the Quest of Legends, we started with Jorge's code from Quest part 1 and added onto it for this game, combining it with some parts of Victoria's part 1 Quest implementation. We chose to do this because Jorge's code was a bit more modular, so it seemed easier to work off of and expand into The Quest of Legends game. 

##### Interfaces:
- Consumable interface: This is exactly the same from Jorge's Part 1 implementation.
- Equippable interface: This is the same from Jorge's Part 1 implementation.
- Entity interface: This is the same from Jorge's Part 1 implementation.
- Sellable interface: This is a new interface that we made; we realized that it is useful to have this interface to differentiate between objects that can and can't be sold.
- Unit interface: This is the same from Jorge's Part 1 implementation.

##### Abstract Classes:
- CharacterEntity class: This is a new class, that is similar to the Character class from Victoria's implementation. We added this because Jorge's Part 1 implementation did not have a parent class for Heroes and Enemies.
- EnemyEntity class: This is similar to the EnemyEntity class from Jorge's part 1 implementation, but now it is a child class of CharacterEntity.
- Item class: This is the same from Jorge's Part 1 implementation.
- HeroEntity class: This is similar to the HeroEntity class from Jorge's part 1 implementation, but now it is a child class of CharacterEntity.
- Spell class: This is the same from Jorge's Part 1 implementation.
- Terrain class: This is a new class that we added because there are different types of cells in this game (Cave, Bush, etc) so we wanted this to be an abstract parent class for the concrete terrain types.

##### Classes:
- Armor class: This is the same from Jorge's Part 1 implementation.
- BushTerrain class: This is a new class, because Bush cells did not exist in the Quest Part 1.
- CaveTerrain class: This is a new class, because Cave cells did not exist in the Quest Part 1.
- Colors class: This is the same from Jorge's Part 1 implementation.
- CompileMe class: This is the same from Jorge's Part 1 implementation.
- Demon class: This is the same from Jorge's Part 1 implementation.
- Dragon class: This is the same from Jorge's Part 1 implementation.
- Exoskeleton class: This is the same from Jorge's Part 1 implementation.
- FireSpell class: This is the same from Jorge's Part 1 implementation.
- GameObjects class: This is the same from Jorge's Part 1 implementation.
- GridMap class: This is the same from Jorge's Part 1 implementation.
- GridMapCell class: This is the same from Jorge's Part 1 implementation.
- HeroTeam class: This is the same from Jorge's Part 1 implementation.
- IceSpell class: This is the same from Jorge's Part 1 implementation.
- InaccessibleTerrain class: This is the same from Jorge's Part 1 implementation.
- KoulouTerrain class: This is a new class, because Koulou cells did not exist in the Quest Part 1.
- LightningSpell class: This is the same from Jorge's Part 1 implementation. 
- LightSpell class: This is the same from Jorge's Part 1 implementation.
- Lord class: This is the same from Jorge's Part 1 implementation.
- Market class: This is the same from Jorge's Part 1 implementation.
- Nexus class: This is a new class, because Nexus did not exist in Part 1.
- Paladin class: This is the same from Jorge's Part 1 implementation.
- PlainTerrain class: This is similar to the RegularTerrain class from Jorge's part 1 implementation, but now it is a child class of Terrain.
- Potion class: This is the same from Jorge's Part 1 implementation.
- QuestGridMap class: This is the same from Jorge's Part 1 implementation.
- QuestOfLegends class: This is similar to the Quest class from Jorge's part 1, but the implementation has some functionality combined with Victoria's part 1.
- Sorcerer class: This is the same from Jorge's Part 1 implementation.
- Spirit class: This is the same from Jorge's Part 1 implementation.
- Warrior class: This is the same from Jorge's Part 1 implementation.
- Weapon class: This is the same from Jorge's Part 1 implementation.