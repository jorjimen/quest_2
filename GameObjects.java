// public class GameObjects containing all the required objects for the Quest
// contains all the objects specified in the text file
// contains additional objects created by mysel

/// all these objects are static and used across the game

public class GameObjects {

    static Warrior w1 = new Warrior("Gaerdal Ironhanden", 1, 7, 1250, 700, 600, 100, 1354);
    static Warrior w2 = new Warrior("Sehanine Monnbowes", 1, 8, 1100, 800, 450, 600, 2500);
    static Warrior w3 = new Warrior("Muamman Duathallen", 1, 6, 1200, 500, 500, 300, 2546);
    static Warrior w4 = new Warrior("Jovian Steelarmsen", 1, 7, 1100, 650, 650, 200, 2500);

    static Sorcerer s1 = new Sorcerer("Garlin Glittergold", 1, 7, 700, 500, 600, 700, 2500);
    static Sorcerer s2 = new Sorcerer("Rillifane Rallath", 1, 9, 850, 450, 500, 1300, 2500);
    static Sorcerer s3 = new Sorcerer("Segojant Earthcall", 1, 5, 890, 650, 500, 900, 2500);
    static Sorcerer s4 = new Sorcerer("Skoraeuse Stonebon", 1, 6, 910, 450, 600, 800, 2500);

    static Paladin p1 = new Paladin("Solonor Thelandira", 1, 7, 900, 750, 700, 300, 2500);
    static Paladin p2 = new Paladin("Vladimir Highbanes", 1, 7, 880, 750, 700, 300, 2500);
    static Paladin p3 = new Paladin("Dragovic Earthborn", 1, 4, 930, 650, 350, 250, 2500);
    static Paladin p4 = new Paladin("Meliodas Rallathil", 1, 6, 980, 600, 400, 100, 2500);

    static Potion po1 = new Potion("Healing Potion", 200, 1, 100, "HEALTH");
    static Potion po2 = new Potion("Strength Potion", 200, 1, 75, "STRENGTH");
    static Potion po3 = new Potion("Magic Potion", 350, 2, 65, "DEXTERITY");
    static Potion po4 = new Potion("Luck Elixir", 500, 4, 65, "AGILITY");
    static Potion po5 = new Potion("Mermaid Tears", 850, 5, 100, "MANA");
    static Potion po6 = new Potion("Ambrosia", 1000, 8, 150," EXPERIENCE");

    static FireSpell fs1 = new FireSpell("Flame Tornado", 700, 4, 840, 300);
    static FireSpell fs2 = new FireSpell("Breath_of Fire", 160, 1, 150, 100);
    static FireSpell fs3 = new FireSpell("Heat Wave ", 450, 2, 600, 150);
    static FireSpell fs4 = new FireSpell("Lava Commet", 800, 7, 1000, 550);

    static LightningSpell ls1 = new LightningSpell("Lightning Dagger", 125, 1, 500, 150);
    static LightningSpell ls2 = new LightningSpell("Thunder Blast", 750, 4, 950, 400);
    static LightningSpell ls3 = new LightningSpell("Electric Arrows", 550, 5, 650, 200);
    static LightningSpell ls4 = new LightningSpell("Spark Needles", 500, 2, 600, 200);

    static IceSpell is1 = new IceSpell("Snow_Canon", 180, 2, 650, 250);
    static IceSpell is2 = new IceSpell("Ice Blade", 150, 1, 150, 100);
    static IceSpell is3 = new IceSpell("Frost Blizzard", 750, 5, 850, 350);
    static IceSpell is4 = new IceSpell("Arctic Storm", 700, 6, 800, 300);

    static Weapon wpn1 = new Weapon("Sword", 500, 1, 800, 1);
    static Weapon wpn2 = new Weapon("Bow", 300, 2, 500, 2);
    static Weapon wpn3 = new Weapon("Scythe" , 1000, 6, 1100, 2);
    static Weapon wpn4 = new Weapon("Axe", 550, 5, 850, 1);
    static Weapon wpn5 = new Weapon("Shield", 400, 1, 100, 1);
    static Weapon wpn6 = new Weapon("TSwords", 1400, 8, 1600, 2);
    static Weapon wpn7 = new Weapon("Dagger", 200, 1, 250, 1);
    
    static Armor ar1 = new Armor("Platinum_Shield", 150, 1, 200);
    static Armor ar3 = new Armor("Breastplate", 350, 3, 600);
    static Armor ar4 = new Armor("Full_Boyd Armor",1000,8,1100);
    static Armor ar5 = new Armor("Wizard Shield",1200,10,1500);
    static Armor ar6 = new Armor("Speed Boots", 550, 4, 600);

    static Dragon d1 = new Dragon("Desghidorrah", 3, 300, 70, 35);
    static Dragon d2 = new Dragon("Chrysophylax", 2, 150, 50, 20);
    static Dragon d3 = new Dragon("BunsenBurner", 3, 300, 100, 45);
    static Dragon d4 = new Dragon("Natsunomeryu", 1, 75, 25, 10);
    static Dragon d5 = new Dragon("TheScaleless", 7, 700, 140, 75);
    static Dragon d6 = new Dragon("Alexstraszan", 10, 1000, 250, 55);
    static Dragon d7 = new Dragon("Phaarturnax", 6, 600, 150, 60);
    static Dragon d8 = new Dragon("D-Maleficent", 8, 900, 200, 85);
    static Dragon d9 = new Dragon("The Weatherbe", 8, 800, 210, 80);

    static Exoskeleton exo1 = new Exoskeleton("Cyrrollalee", 7, 700, 120, 75);
    static Exoskeleton exo2 = new Exoskeleton("Brandobaris", 3, 350, 50, 30);
    static Exoskeleton exo3 = new Exoskeleton("Big Bad Wolf", 1, 110, 10, 15);
    static Exoskeleton exo4 = new Exoskeleton("WickedWitch", 2, 165, 45, 25);
    static Exoskeleton exo5 = new Exoskeleton("Aasterninan", 4, 400, 60, 45);
    static Exoskeleton exo6 = new Exoskeleton("Chronephsish", 6, 650, 60 , 60);
    static Exoskeleton exo7 = new Exoskeleton("St Shargaas", 5, 550, 80, 55);
    static Exoskeleton exo8 = new Exoskeleton("Merrshaullk", 10, 1000, 180, 55);
    static Exoskeleton exo9 = new Exoskeleton("St Meenoghu", 9, 950, 160, 90);

    static Spirit spr1 = new Spirit("Andrealphus",2,185,25,40);
    static Spirit spr2 = new Spirit("Aim Haborym",1,130,10,35);
    static Spirit spr3 = new Spirit("Chiang shih",4,600,40,40);
    static Spirit spr4 = new Spirit("FallenAngel",5,800,100,50);
    static Spirit spr5 = new Spirit("Melchiresas",7,350,130,75);
    static Spirit spr6 = new Spirit("Ereshkigall",6,950,120,35);
    static Spirit spr7 = new Spirit("Jormunngand",8,600,150,20);
    static Spirit spr8 = new Spirit("Rakkshassas",9,550,160,35);
    static Spirit spr9 = new Spirit("Taltecuhtli",10,1000,175,50);

    // additional objects made by myself for game balancing purpose

    static Weapon wpn8 = new Weapon("Double-Edged Sword", 850, 2, 300, 2);
    static Weapon wpn9 = new Weapon("Excalibur", 10000, 10, 2000, 1);

    static Armor ar2 = new Armor("Golden Shield", 300, 2, 400);
    static Armor ar7 = new Armor("Helm of Saint-14", 10000, 11, 1800);

    static Warrior w5 = new Warrior("Arthurio Pendragon", 1, 9, 1400, 800, 1400, 600, 2000);
                                
    static Sorcerer s5 = new Sorcerer("Merlin Myrddin W.", 1, 8, 800, 1200, 1500, 1200, 1800);
                                      
    static Spirit spr10 = new Spirit("The No Face",1,150,15,25);
    static Spirit spr11 = new Spirit("Hashladune",5,750,150,40);
    static Spirit spr12 = new Spirit("Iota Curse",2,180,35,20);

    static Demon dem1 = new Demon("Youngblood", 1, 140, 20, 20);
    static Demon dem2 = new Demon("Demon Lord", 10, 3300, 1000, 50);
    static Demon dem3 = new Demon("Blood Demon", 5, 200, 25, 25);
    static Demon dem4 = new Demon("High Demon", 7, 1000, 500, 50);
    static Demon dem5 = new Demon("Weak Demon", 5, 230, 50, 25);

    static Dragon d10 = new Dragon("Baby Deathwing", 2, 200, 30, 15);
    static Dragon d11 = new Dragon("The World-Ender", 11, 3000, 2000, 85);

    static FireSpell fs5 = new FireSpell("Pillars of Magma", 500, 2, 300, 400);

    static IceSpell is5 = new IceSpell("Iced Shuriken", 500, 2, 350, 500);

    static Potion po7 = new Potion("Weak Mana Gem", 200, 1, 100, "MANA");
    static Potion po8 = new Potion("Syrup of Vitality", 1500, 6, 750, "HEALTH");

    static Lord lord1 = new Lord("Lord of the Heaven", 1, 9, 1700, 800, 800, 100, 2500);
    static Lord lord2 = new Lord("Lord of the Lights", 1, 9, 1600, 850, 900, 80, 2400);

    static LightSpell li1 = new LightSpell("Beam of Light", 100, 1, 50, 200);
    static LightSpell li2 = new LightSpell("Heavenly Aura", 500, 5, 400, 500);
    static LightSpell li3 = new LightSpell("Blinding Gaze", 1000, 10, 1000, 1000);

}

