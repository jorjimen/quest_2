# Quest of Legends

### Team 14: 
### Victoria Kayola: U63800409
### Jorge Jimenez: U34800726

========================================================================================

#### README

Thank you for taking your time to read this. In this README, we will be overviewing our implementation of the Quest of Legends, the way the game logic works and doing an overview of the class structure and its logic. All the code implemented contains detailed descriptions of the game.

========================================================================================

#### HOW TO COMPILE:

The file that contains the main function that will execute the game is the CompileMe class. The sole purpose of this class is for compilation purposes.

It contains two lines of code:

`QuestOfLegends quest = new QuestOfLegends(8);`    -> Creates the Quest of Legends object. You can change the parameter in order to change the size of the Quest map.

`quest.play();`          -> This public method executes the game

In order to compile, run the following lines in Terminal:

`javac *.java`

`java CompileMe`

========================================================================================

#### DESCRIPTION OF THE QUEST OF LEGENDS

The Quest of Legends will begin with the prompting the user to create a team of 3 heroes from a list of available heroes:


```
...

2)  Dragovic Earthborn     250     930     350     650     2500     4     1     100      PALADIN
3)  Meliodas Rallathil     100     980     400     600     2500     6     1     100      PALADIN
4)  Garlin Glittergold     700     700     600     500     2500     7     1     100      SORCERER
...
```

Each hero has a number next to it. You can insert the number that you want to select the hero and add it to your team. Once you select a hero, it will be added to your team which
will be added above. The game will not allow you to have duplicate heroes in your team. Furthermore, you must create a team of three heroes.

If you wish to change the maximum amount of heroes in a team, you can change the variable
maxHeroPerTeam in the QuestOfLegends class.

Heroes will start with two default items:
   1) Fists: Has an attack damage of 100 and can be sold for 0.
   2) Broken Chestplate: Has a defense value of 100 and can be sold for 0.

Notice: The way the game works is that these items will always re-appear in your inventory if you contain no items. This is for game-balancing reasons.

Other than these two items, the heroes will not have any additional items. To buy new items, a hero must enter the Market, which is located at each Nexus cell (the bottom row of the map).

Above the map you will always be able to see some statistics of your hero team as well as the enemies currently on the board (there are more than can be viewed by inspecting).

The map has a legend at the bottom to explain the map structure itself:
  1. Pink: Hero Nexus
  2. Blue: Enemy Nexus
  3. White: Plain terrain
  4. Teal: Koulou terrain (increase the strength of any hero who is inside them by 10%)
  5. Yellow: Cave terrain (increase the agility of any hero who is inside them by 10%)
  6. Green: Bush terrain (increase the dexterity of any hero who is inside them by 10%)
  7. Red: Inaccessible space

The tile distribution is randomly generated with the 70% plain and 10% for each of the special terrains.

By default, the each hero will start in the Hero nexus, with one hero in each lane. Likewise, the enemies start in the enemy nexus.

Every time you are in a tile, you can select from 10 main options:
```
 W/w) Move Up
 A/a) Move Left
 S/s) Move Down
 D/d) Move Right 
 T/t) Teleport -> choose a cell to move to
 C/c) Attack -> can only attack if you are in the same cell as an enemy
 P/p) Cast spell -> can only cast spell if you are in the same cell as an enemy
 B/b) Return to Nexus/Market 
 I/i) Inspect Team/Use Item
 Q/q) Quit -> QUIT GAME. THIS COMMAND IS FINAL.
 ```

Any other options will prompt the user again. Important things to note:
- The game will display everything that is happening at all times. I.e if you move right, it will say "Moving right..." and then an appropriate message depending the outcome
- Obviously, game will notify you and not allow you to move to red tiles. Avoid this but if you forget, nothing bad will happen you will simply be moved back.
- The game will also handle appropriately if the user tries to move out of bound, and display the appropriate message.

========================================================================================

#### Markets

Markets are where the different heroes are allowed to buy and sell their items, and you can also heal your team. Every hero has their own wallet, which means every hero has their own inventory. A hero cannot purchase items for other heroes. Markets also have a fixed inventory (which means every market has the same inventory and buying an item doesn't remove it from the Market's inventory). Markets are located at each cell of the Hero nexus.

The Market's interface is pretty self-explanatory. Any time you choose to either buy or sell, you have to select which hero you are going to purchase to or which hero you are 
selling from. The market will display your current selected hero and their wallet balance.

##### Buying:
- Game will not allow you to buy items you do not have gold for.
- Game will not allow you to buy items which you do not have the level or mana requirement for.
- You can obviously buy multiple versions of an item.
- Under the buying option, you can also choose to heal your team.

##### Healing:
- Healing is an additional mechanic I have implemented to the game. I felt it was a great addition and it helps the players alot on their journey.
- The way that healing works is that one hero can opt to pay gold to heal the entire team.
- It will cost you 1200 gold PER HERO THAT DOES NOT HAVE FULL HEALTH. Healing is expensive, so it is not recommended to do so early in the game.
- For example, if you need to heal 2 heroes, it will cost 3600 gold to perform this action.
- One hero has to pay for the entire sum. It cannot be split between heroes.
- You cannot perform this move if your team is fully healed or if the selected hero does not have the required funds.

##### Selling:
- Selling is very straightforward (you choose with item, it sells it, gives you the gold and removes it from your inventory)
- Selling your current equipped item will replace your current equipped item by the first item in the inventory.

You can freely leave a Market. You can return to the Nexus and enter the Market at any time.

========================================================================================

#### Leveling Up

Leveling up follows the specified details in the PDF. Hero's stats are increased by a certain percentage, more depending on the type of hero (their favored skills). Heroes health is also reset and calculated as follows: `100 * level`.

========================================================================================

#### Inspecting

If you do not select a command to move in the map, you can inspect a hero to view a more detailed version of the player or use on of their items. An example of this would be:

```
Merlin Myrddin W.

  Experience:8
  Level:1
  Health:100
  Mana:1200
  Strength:800
  Agility:1500
  Dexterity:1200
  Money:1800
  Enemies Defeated: 0

  Equipped Weapon: Fists
   Level: 1
   Price: 0
   Damage: 150
   Required hands: 1
  Equipped Armor: Broken_Chestplate
   Level: 1
   Price: 0
   Defense Reduction: 100

  Class: SORCERER
  ```

As you can see, the game is keeping track of how many enemies defeated in combat a hero has (this number is only increased when a specific hero lands the KILLING BLOW).

You can also select a few commands:

What would you like to do?:
 1) View Inventory  -> View your hero's inventory
 2) Change Weapon -> Change your current equipped weapon
 3) Change Armor  -> Change your armor
 4) Use Potion    -> Use a potion on a hero
 5) Go Back To Map  -> Go back to the map

========================================================================================
#### Gameplay

- During each round, each Hero will make a move. Once all of the Heros have moved, each Enemy will move forward as well. A hero cannot pass an Enemy without defeating it, and likewise an Enemy will attack a Hero when it is next to a Hero. To defeat an Enemy, a hero can attack or cast a spell. To aid in defeating an Enemy, a Hero can buy items (armor, weapons, potions, spells) from the market. If a Hero is killed by an Enemy, it will respawn on its Nexus at the start of the next round. 
- Every 8 rounds, 3 new Enemies will spawn on the Enemy nexus. 
- At the start of every round, the heroes regain 10% of their hp and 10% of their mana.

========================================================================================
#### Ending the Game
The game ends when a Hero reached the Enemy Nexus, or vice versa. If in the same round, a Hero and an Emeny both reach the opposing team's Nexus, the Heroes win.

###### THE GOAL OF THE GAME IS TO LEVEL UP, BEAT THE ENEMIES AND REACH THE ENEMY NEXUS. FIGHT WELL BUT FIGHT WISELY. TRY TO COLLECT THE BEST WEAPONS AND ARMORS BUT MOST IMPORTANTLY... HAVE FUN!!!!!!!!!!!!
========================================================================================

#### Class Structure

##### Interfaces:
- Consumable interface: Interface for anything in the game that can be consumed.
- Equippable interface: Interface for anything in the game that can be equipped.
- Sellable interface: Interface for anything in the game that can be sold in the market. 
- Entity Interface: Interface for all the Entities in the game. This interface is implemented by every physical entity in the game.
- Unit Interface: Interface for all the Units in the game (Heroes and Enemies).

##### Abstract Classes:
- CharacterEntity class: Abstract class for a Character object, which will be extended by concrete character types (Heroes and Enemies). Implements the Entity and Unit interfaces.
- EnemyEntity class: Abstract class for enemy objects. Extends the CharacterEntity class. Contains the majority of the data and logic for an enemy.
- Item class: Abstract class representing item objects. Implements the Entity interface and Sellable interface. Contains the majority of the data and logic for items.
- HeroEntity class: Abstract class for the hero objects. Extends the CharacterEntity class. Contains the majority of the data and logic for a hero.
  * Contains private class Inventory
- Spell class: Abstract class for spell objects. Extends the Item class. Contains the majority of the data and logic for the spells.
- Terrain class: Abstract class representing Terrain, which will be extended by the concrete terrain types (Bush, Cave Koulou, Plain). Implements the Entity interface.

##### Classes:
- Armor class: Class for the Armor object. Implements the Equippable interface. Extends the Item abstract class.
- BushTerrain class: Class for BushTerrain object. Implements the Entity interface.
- CaveTerrain class: Class for CaveTerrain object. Implements the Entity interface.
- Colors class: Class for the ASCII Colors. All members are static.
- CompileMe class: Endpoint that begins the game.
- Dragon class: Class for the Dragon object. Extends the EnemyEntity abstract class.
- Exoskeleton class: Class for the Exoskeleton object. Extends the EnemyEntity abstract class.
- FireSpell class: Class for the FireSpell object. Extends the Spell abstract class.
- GameObjects class: Class for the GameObject class. Contains all the instantiations of the objects of the game (heroes, enemies, market items, etc). All members are static.
- GridMapCell class: Class for a GridMapCell object. 
- GridMap class: Class for a GridMap object.
- HeroTeam class: Class for the HeroTeam object. Contains all the data and logic for a team of heroes.
- IceSpell class: Class for the IceSpell object. Extends the Spell abstract class.
- InaccessibleTerrain class: Class for InaccesibleTerrain object. Implements the Entity interface.
- KoulouTerrain class: Class for KoulouTerrain object. Implements the Entity interface.
- LightningSpell class: Class for the LightningSpell object. Extends the Spell abstract class.
- Market class: Class for the Market objects. Contains all the data and logic for a Market. Implements the Entity interface.
  * Contains private class MarketInventory
- Nexus class: Class representing the tiles on the board that are Nexus spaces. Extends the Terrain class.
- Paladin class: Class for the Paladin object. Extends the HeroEntity abstract class.
- PlainTerrain class: Class for PlainTerrain object. Implements the Entity interface.
- Potion class: Class for the Potion object. Implements the Consumable interface. Extends the Item abstract class.
- QuestGridMap class: Class for a QuestGridMap object. Extends the GridMap class.
- QuestOfLegends class: Class for the QuestOfLegends object. Contains all the data and logic for the Quest of Legends game.
- Sorcerer class: Class for the Sorcerer object. Extends the HeroEntity abstract class.
- Spirit class: Class for the Spirit object. Extends the EnemyEntity abstract class.
- Warrior class: Class for the Warrior object. Extends the HeroEntity abstract class.
- Weapon class: Class for the Weapon object. Implements the Equippable interface. Extends the Item abstract class.

##### Classes that were made for additional game purpose.

- Lord class: Lords are another type of hero I made. They are only favored in STRENGTH (15% increase). Extends the HeroEntity class.
- Demon class: Demons are another type of monster I made. They are only favored in DAMAGE. Extends the EnemyEntity class.
- LightSpell class: Light Spells are a new class of spells. They reduced all of the opponents skills by 5%. Extends the Spell class.

