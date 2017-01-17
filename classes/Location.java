import java.util.*;

class Location {

	String type;
	String name;
	String noun;
	String adj;
	String prep;
	String extra;
	Monster denizen;
	boolean monsterDead;
	Shop store;
	Healer medic;
	int level;
	int difficulty;

	public Location(int locLevel,int locDifficulty) {
		level = locLevel;
		difficulty=locDifficulty;
		String[] tAdj = {"cute","quaint","sprawling","dirty","proud","bustling","dilapidated","humble","nice","smelly","wonderful","busy little","busy","gorgeous"};
		String[] tNoun= {"town","hamlet","city","village","shanty-town","metropolis","settlement","burg","market"};

		String[] wAdj = {"dark","sprawling","ominous","hostile","sickly","lonely","unnerving","awful","miserable","dank","shadowy","eerie","mystical","rank"};
		String[] wNoun= {"jungle","desert","wasteland","wilderness","forest","ruin","cave","tundra","citadel","vale","clearing","abyss"};
		
		String[] sAddon={", mind you",", too",", at least",", as well"," on the corner"," the next street over"};

		String[] mAdjs= {"foul","wretched","frightening","terrible","eldritch","angry","sinewy","hideous","macabre","vicious","vile","furious","violent"};
		String[] mVerbs= {"shambles","lumbers","screams","roars","slithers","lurks","calls","watches","menaces","wanders","grunts"};
		String[] mNouns= {"mists","distance","fog","gloom","shadows","dusts"};

		String[] vowels={"a","e","i","o","u"};

		if (level%difficulty==0){
			type = "Town";
			TownName here = new TownName();
			name = here.name;
			adj = tAdj[(int) (Math.random()*tAdj.length)];
			noun= tNoun[(int) (Math.random()*tNoun.length)];
			String extraAdd=sAddon[(int) (Math.random()*sAddon.length)];
			extra = "There's a swordsmith and a healer" + extraAdd;
			store = new Shop(level+5);
			medic = new Healer(level);
		}else{
			type = "Wilderness";
			adj = wAdj[(int) (Math.random()*wAdj.length)];
			noun= wNoun[(int) (Math.random()*wNoun.length)];
			String mAdj = mAdjs[(int) (Math.random()*mAdjs.length)];
			String mVerb= mVerbs[(int) (Math.random()*mVerbs.length)];
			String mNoun= mNouns[(int) (Math.random()*mNouns.length)];
			denizen = new Monster(level);
			extra = "Something " + mAdj + " " + mVerb + " in the " + mNoun;

			if (Arrays.asList(vowels).contains(adj.substring(0,1))) {
				prep = "an";
			}else{
				prep = "a";
			}
		}
	}

	public void lookAround() {
		System.out.println("Level " + level);
		if (type == "Town") {
			if (level==difficulty){
				System.out.println("You've made it to the " + adj + " " + noun + " of " + name +".\nSomeone noticed you arrive and is waving you over. You:");
				
			} else if (level==0) {
				System.out.println("You're in your good ol' hometown of " + name + ". You:");
			} else {
				System.out.println("You made it to civilization! You're in the " + adj + " " + noun + " of " + name +". You:");
			}
		} else if (!monsterDead){
			System.out.println("You're in " + prep + " " + adj + " " + noun + ". " + extra + ". You:");
		}else{
			System.out.println("You're in " + prep + " " + adj + " " + noun + ". You:");
		}
	}

	public static void main(String[] args){
		Location here = new Location(0,5);
		here.lookAround();
		Location there = new Location(1,5);
		there.lookAround();
	}
}