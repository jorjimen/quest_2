Quest of Legends

Team 14: Jorge Jimenez and Victoria Kayola

================================================================================================================================================================================

README

Thank you for taking your time to read this. In this README, we will be overviewing our implementation of the Quest of Legends, the way the game logic works and doing an overview of the class
structure and its logic. All the code implemented contains detailed descriptions of the game.

================================================================================================================================================================================

HOW TO COMPILE:

The file that contains the main function that will execute the game is the CompileMe class. This classes sole purpose is for compilation purposes.

It contains two lines of code:

QuestOfLegends quest = new QuestOfLegends(8);    -> Creates the Quest of Legends object. You can change the parameter in order to change the size of the Quest map.
quest.play()		       -> This public method executes the game

In order to compile, run the following lines in Terminal:
'javac *.java'
'java CompileMe'

================================================================================================================================================================================

DESCRIPTION OF THE QUEST

The Quest will begin with the prompting the user to create a hero team from a list of available heroes:

...

2)  Dragovic Earthborn     250     930     350     650     2500     4     1     100      PALADIN
3)  Meliodas Rallathil     100     980     400     600     2500     6     1     100      PALADIN
4)  Garlin Glittergold     700     700     600     500     2500     7     1     100      SORCERER
...

Each hero has a number next to it. You can insert the number that you want to select the hero and add it to your team. Once you select a hero, it will be added to your team which
will be added above. The game will not allow you to have duplicate heroes in your team. Furthermore, you can only have up to three heroes (the game will commence once you add the
third hero). If you wish to have one or two heroes only in your team, you can add one or two heroes and then use -1 to finish the building process. If you create a team with no heroes, the game will automatically end.


If you wish to change the maximum amount of heroes in a team, you can change the variable
maxHeroPerTeam in the Quest class

Heroes will start with two default items:
   1) Fists: Has an attack damage of 100 and can be sold for 0.
   2) Broken Chestplate: Has a defense value of 100 and can be sold for 0.

Notice: The way the game works is that these items will always re-appear in your inventory if you contain no items. This is for game-balancing reasons.

Other than these two items, the heroes will not have any additional items. Your goal now is to make it to the nearest Market. 

Above the map you will always be able to see some statistics of your hero team (there are more than can be viewed by inspecting).

The map has a legend at the bottom to explain the map structure itself:
  1) Blue Tile: Your current location
  2) Yellow Tile: The location of a Market
  3) Red Tile: Inaccessible location
  4) White Tile: Regular location which can be used for encountering monsters.

The tile distribution is randomly generated with the recommended probability distribution. IF YOU ARE SURROUNDED BY RED TILES, JUST RUN THE GAME AGAIN. =(

By default, the player will start in the bottom most tile.

Every time you are in a tile, you can select from 6 main options:
 W/w) Move Up		-> Move to the tile above you
 A/a) Move Left         -> Move to the tile to your left
 S/s) Move Down         -> Move to the tile under you
 D/d) Move Right	-> Move to the tile to the right
 I/i) Inspect Team	-> Inspect your hero team to view details, change armor/weapon and use potions.
 Q/q) Quit		-> QUIT GAME. THIS COMMAND IS FINAL.

Any other options will prompt the user again. Important things to note:
- The game will display everything that is happening at all times. I.e if you move right, it will say "Moving right..." and then an appropriate message depending the outcome
- Obviously, game will notify you and not allow you to move to red tiles. Avoid this but if you forget, nothing bad will happen you will simply be moved back.
- The game will also handle appropriately if the user tries to move out of bound, and display the appropriate message.

================================================================================================================================================================================

MARKETS

Markets are where the different heroes are allowed to buy and sell their items, and you can also heal your team. Every hero has their own wallet, which means every hero has their own inventory. A hero cannot
purchase items for other heroes. Markets also have a fixed inventory (which means every market has the same inventory and buying an item doesn't remove it from the Market's
inventory).

Moving into a yellow tile will prompt the user with the option to either enter or not visit the market
- Once you either LEAVE or DECIDE NOT TO VISIT THE MARKET you cannot visit it again UNLESS YOU MOVE OUT OF THE LOCATION AND MOVE BACK INTO IT!!!!

The Market's interface is pretty self-explanatory. Any time you choose to either buy or sell, you have to select which hero you are going to purchase to or which hero you are 
selling from. Obviously, it will display your current selected hero and its wallet balance.

BUYING:
- Game will not allow you to buy items you do not have gold for.
- Game will not allow you to buy items which you do not have the level requirement for.
- You can obviously buy multiple versions of an item.
- Under the buying option, you can also choose to heal your team.

HEALING:
- Healing is an additional mechanic I have implemented to the game. I felt it was a great addition and it helps the players alot on their journey.
- The way that healing works is that one hero can opt to pay gold to heal the entire team.
- It will cost you 1200 gold PER HERO THAT DOES NOT HAVE FULL HEALTH. Healing is expensive, so it is not recommended to do so early in the game.
- For example, if you need to heal 2 heroes, it will cost 3600 gold to perform this action.
- One hero has to pay for the entire sum. It cannot be split between heroes.
- You cannot perform this move if your team is fully healed or if the selected hero does not have the required funds.

SELLING:
- Selling is very straightforward (you choose with item, it sells it, gives you the gold and removes it from your inventory)
- Selling your current equipped item will replace your current equipped item by the first item in the inventory.

You can freely leave a Market. Again, if you leave, you won't be able to re-enter unless you walk back into the market.

================================================================================================================================================================================

REGULAR TERRAIN

There is a 75% chance for you to encounter a fight in a regular terrain. The game will notify you if you were lucky to not encounter enemies, or a fight will commence if 
you weren't that lucky...

================================================================================================================================================================================

FIGHT

If a regular terrain generates a fight, the fight will automatically commence.

You will fight an team of monsters, that can include exoskeletons, spirits or dragons:
- Enemy team is randomly generated every fight. If you fight the same exact enemy team more than once it means that you were lucky.
- The amount of enemy monsters that you will go against will be equal to the amount of heroes you have in your team. I.e 3v3, 2v2 or 1v1.
- The HIGHEST LEVEL of the enemy monster will ALWAYS be equal or less than the highest level of your hero. This means you will never fight enemies higher level than your hero.

The game will display statistics of the hero and the enemies every round. The fights are also extremely user friendly: the game will notify you of virtually anything that happens.
For example, it will notify you who you are attack, how much damage you deal, if you can do or cannot do a move, if a hero dies, if a monster dies, if you cannot attack due to the
hero being dead, etc... So if you ever get lost just make sure you just read what the game outputting because I have coded it for there to be an explanation to mostly anything so 
it is easy for everyone to follow along

The way the fight works is first, the Heroes can execute their moves. Heroes that are fainted cannot do anything in a round.

They can choose from a set of moves, and they can ONLY EXECUTE ONE MOVE PER ROUND:

MOVES:
 1) ATTACK ENEMY	-> ATTACK AN ENEMY WITH YOUR EQUIPPED WEAPON
 2) CAST SPELL		-> CAST A SPELL TO YOUR ENEMY. YOU WILL NOT BE ALLOWED TO DO THIS IF YOU DO NOT HAVE ANY SPELLS
 3) USE POTION		-> USE A POTION ON YOUR TEAM. YOU WILL NOT BE ALLOWED TO DO THIS IF YOU DO NOT HAVE ANY POTIONS
 4) CHANGE WEAPON	-> CHANGE YOUR WEAPON. YOU WILL NOT BE ALLOWED TO DO THIS IF YOU ONLY HAVE ONE WEAPON IN YOUR INVENTORY
 5) CHANGE ARMOR	-> CHANGE YOUR ARMOR. YOU WILL NOT BE ALLOWED TO DO THIS IF YOU ONLY HAVE ONE ARMOR IN YOUR INVENTORY

Attacking works the following way:
- Hero will always prioritize the enemy at their same index. For example, the first hero will attack the first enemy.
- If the enemy at the hero's index is dead, it will prioritize the first hero that is not dead, starting from the first to the last.
- Enemies have a dodge chance, so the hero can possibly miss.
- Game will notify you if your attack did nothing due to the enemy having high defense.
- Game will notify you if your attack was successful and for how much damage.
- Game will notify you if your attack missed.
- Game will notify you if your attack killed the enemy.

Casting a spell:
- Casting a spell works similar to attacking
- Spells can also be dodged so watch out for this.
- Depending on the spell you used, the game will output the type of affliction it did, and to how much the enemies skill was reduced to.
- Spells use mana, so it will notify you how much mana it used. Furthermore, you will be notified if you do not have enough mana to cast a spell.
- This is only allowed if you have spells in your inventory.
- ADDITIONAL SPELL: Light Spell. They reduce all of the opponents' skills by 5%. 

Using a potion:
- You can use a potion from your inventory. A hero can use it either on itself or on another hero.
- This is only allowed if you have potions in your inventory.

Change weapon or armor:
- You can change your weapon or armor in your inventory.
- You can only do this if you have more than one weapon or more than one armor in your inventory.

Once your hero inputs all their moves, the enemy team will attack:
- Attacking works exactly the same for enemies as it does for heroes.
- Enemies who are fainted cannot attack (obviously).

Landing a killing blow counts as a kill for a hero. This data is stored in the hero and can be viewed.

The fight will end once either all the heroes are dead or all the enemies are dead.

================================================================================================================================================================================

WINNING A FIGHT

If you win a fight, the following will happen:

Any hero that survived (is alive) will gain 2 experience and 150 gold. This value is multiplied by how many enemies were defeated. For example you will receive 6 experience and
450 gold if you win a 3v3. The game will output which heroes receive gold and exp. The game will also notify you if a hero levels up!

Any hero that is fainted will NOT receive experience and gold. However, they will be revived with: (100 * level) / 2 health.

================================================================================================================================================================================

LOSING A FIGHT

If you lose the fight, the game ends. Therefore, make sure you equip the correct items, use your potions wisely and think during fights!!!!

================================================================================================================================================================================

LEVELING UP

Leveling up follows the specified details in the PDF. Hero's stats are increased by a certain percentage, more depending on the type of hero (their favored skills). Heroes health
is also reset and calculated as follows: 100 * level.

Note: EXPERIENCE GAIN IS CUMULATIVE

================================================================================================================================================================================

INSPECTING

If you do not select a command to move in the map, you can inspect a player to view a more detailed version of the player. An example of this would be:

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

As you can see, the game is keeping track of how many enemies defeated in combat a hero has (this number is only increased when a specific hero lands the KILLING BLOW).

You can also select a few commands:

What would you like to do?:
 1) View Inventory 	-> View your hero's inventory
 2) Change Weapon	-> Change your current equipped weapon
 3) Change Armor	-> Change your armor
 4) Use Potion		-> Use a potion on a hero
 5) Go Back To Map	-> Go back to the map

 

================================================================================================================================================================================

THE GOAL OF THE GAME IS TO LEVEL UP, SURVIVE AND HAVE FUN. FIGHT WELL BUT FIGHT WISELY. TRY TO COLLECT THE BEST WEAPONS AND ARMORS BUT MOST IMPORTANTLY... HAVE FUN!!!!!!!!!!!!

================================================================================================================================================================================

CLASS STRUCTURE

Interfaces:

- Entity Interface: Interface for all the Entities in the game.
- Unit Interface: Interface for all the Units in the game (Heroes and Enemies).
- Equippable Interface: Interface for anything in the game that can be equipped.
- Consumable Interface: Interface for anything in the game that can be consumed.

Abstract Classes:

- HeroEntity class: Abstract class for the heroes. Implements both the Unit and Entity interface. Contains the majority of the data and logic for a hero.
	* Contains private class Inventory

- EnemyEntity class: Abstract class for the enemies. Implements both the Unit and Entity interface. Contains the majority of the data and logic for an enemy.
- Item class: Abstract class for items. Implements the Entity interface. Contains the majority of the data and logic for items.
- Spell class: Abstract class for spell. Extends the Item class. Contains the majority of the data and logic for the spells.

Classes:

- Paladin class: Class for the Paladin object. Extends the HeroEntity abstract class.
- Sorcerer class: Class for the Sorcerer object. Extends the HeroEntity abstract class.
- Warrior class: Class for the Warrior object. Extends the HeroEntity abstract class.

- Dragon class: Class for the Dragon object. Extends the EnemyEntity abstract class.
- Exoskeleton class: Class for the Exoskeleton object. Extends the EnemyEntity abstract class.
- Spirit class: Class for the Spirit object. Extends the EnemyEntity abstract class.

- Potion class: Class for the Potion object. Implements the Consumable interface. Extends the Item abstract class.

- Weapon class: Class for the Weapon object. Implements the Equippable interface. Extends the Item abstract class.
- Armor class: Class for the Armor object. Implements the Equippable interface. Extends the Item abstract class.

- FireSpell class: Class for the FireSpell object. Extends the Spell abstract class.
- LightningSpell class: Class for the LightningSpell object. Extends the Spell abstract class.
- IceSpell class: Class for the IceSpell object. Extends the Spell abstract class.

- GridMapCell class: Class for a GridMapCell object. 
- GridMap class: Class for a GridMap object.
- QuestGridMap class: Class for a QuestGridMap object. Extends the GridMap class.

- RegularTerrain class: Class for RegularTerrain object. Implements the Entity interface.
- InaccesibleTerrain class: Class for InaccesibleTerrain object. Implements the Entity interface.

- Market class. Class for the Market objects. Contains all the data and logic for a Market. Implements the Entity interface.
	* Contains private class MarketInventory

- Fight class. Class for the Fight objects. Contains all the data and logic for a Market. Has only one public method: fight().

- HeroTeam class. Class for the HeroTeam object. Contains all the data and logic for a team of heroes.

- Quest class. Class for the Quest object. Contains all the data and logic for a team of heroes.

- Colors class. Class for the ASCII Colors. All members are static.

- GameObjects class. Class for the GameObject class. Contains all the instantiations of the objects of the game. All members are static.

CLASSES THAT WERE MADE FOR ADDITIONAL GAME PURPOSE

- Lord: Lords are another type of hero I made. They are only favored in STRENGTH (15% increase). Extends the HeroEntity class.
- Demon: Demons are another type of monster I made. They are only favored in DAMAGE. Extends the EnemyEntity class.
- Light Spell: Light Spells are a new class of spells. They reduced all of the opponents skills by 5%. Extends the Spell class.

