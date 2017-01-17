import java.util.*;

class Game {

	int level;
	int maxLevel;
	int difficulty=99;
	Location locale;
	List<Location> trail = new ArrayList<Location>();
	Map mymap = new Map();

	public Game() {
		level = 0;
		maxLevel=0;
		locale = new Location(level,difficulty);
		trail.add(locale);
		mymap.mapAdd(locale);
	}

	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();
		System.out.println("======Square Off!======");
		System.out.println();
	}  

	public String namePrompt(){
		clearScreen();
		System.out.println("Name your hero: ");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public void diffPrompt(){
		while (difficulty==99){
			clearScreen();
			Scanner input = new Scanner(System.in);
			System.out.println("Choose your difficulty: ");
			System.out.println("  1) Easy");
			System.out.println("  2) Medium ");
			System.out.println("  3) Hard ");	
			int selection = input.nextInt();
	    	input.nextLine();

			switch (selection) {
				case 1:
					difficulty = 3;
	  				break;
				case 2:
					difficulty = 5;
	  				break;
				case 3:
	  				difficulty = 7;
	  				break;
	  			default:
					System.out.println("Invalid selection.");
					break;
	  			}
		}	
	}


	public void help(String type){
		if (type=="starter"){
			System.out.println("FRIENDLY STRANGER: Hiya there! Welcome to the world of SquareOff!\n\n"
							  +"                   If you wanna succeed here you'll need to know what you're doing.\n"
							  +"                   Most importantly, you'll need to be aware of your own health;\n" 
							  +"                   at any time you can \"Check yo'self\" to take a moment to self-reflect.\n"
					          +"                   If you're not quite satisfied with your lot, you can always take\n"
						      +"                   a look around this here town and try to pick up some nicer stuff.\n\n"
							  +"                   However, if you really wanna make a name for yourself, as well \n"
							  +"                   as acquire untold riches, you're going to have to adventure off into\n"
							  +"                   the wilderness, where you'll have to manage your health and resources\n"
							  +"                   to survive. You can head back at any time, but remember, adventure \n"
							  +"                   lies onwards, not backwards.\n\n"
							  +"                   I'd recommend checking out our healer and swordsmith before you \n"
							  +"                   head out!\n");
		} else if (type == "firsttown"){
			System.out.println("WAVING STRANGER: Congratulations! You made it to your second town!\n\n"
							  +"                 There's towns dotted pretty consistently along the road,\n"
							  +"                 so you can count on them showing up eventually! Now that\n"
							  +"                 you're here, why dontcha stay awhile?\n\n" 
							  +"                 From the blood on your sword, it looks like you encountered\n"
							  +"                 some trouble on the way; be aware that you've created a bit of a\n"
							  +"                 power vacuum out in the wilderness. Something out there just got\n"
							  +"                 a new home, and they'll be itching to pay their...uh, gratitudes.\n\n"
							  +"                 Just be careful if you decide to head back, okay?\n");

		}

	}

	public void displayTown(Hero pc){
		
		Scanner input = new Scanner(System.in);
		locale.lookAround();
		System.out.println("  1) Check yo' self ");
		System.out.println("  2) Check the map ");
		System.out.println("  3) Visit the swordsmith ");
		System.out.println("  4) Visit the healer ");
		System.out.println("  5) Venture into the wilderness  ");
		if (level == 0) {
			System.out.println("  6) Ask a friendly bystander for help");
		}else{
			System.out.println("  6) Head back");
		}
		if (level == difficulty){
			System.out.println("  7) Approach the stranger ");
		}
		
		int selection = input.nextInt();
    	input.nextLine();

		switch (selection) {
			case 1:
				clearScreen();
  				pc.heroStatus();
  				break;
  			case 2:
				clearScreen();
  				mymap.display(level);
  				break;
			case 3:
				clearScreen();
  				shopLoop(pc);
  				break;
			case 4:
  				clearScreen();
  				healerLoop(pc);
  				break;
			case 5:
				clearScreen();
				if (level == maxLevel){
					level++;
					maxLevel++;
					locale = new Location(level,difficulty);
					trail.add(locale);
					mymap.mapAdd(locale);
				}else{
					level++;
					locale=trail.get(level);
				}
				break;
			case 6:
  				if (level==0){
  					clearScreen();
  					help("starter");
  					break;
  				}else{
  					clearScreen();
					level--;
  					locale=trail.get(level);
  					locale.denizen = new Monster(level);
  					locale.monsterDead=false;
  					break;

  				}
  			case 7:
  				if (level==difficulty){
  					clearScreen();
	  				help("firsttown");
	  				break;
	  			}
			default:
				System.out.println("Invalid selection.");
				break;
		}
	}

	public void healerLoop(Hero pc){
		boolean inShop=true;
		while (inShop){

			System.out.println("You're at the healer! You have " + pc.health + " health and " + pc.gold + " gold.");
			Scanner input = new Scanner(System.in);
			System.out.println("  1) Heal " +locale.medic.cost+" HP for "+locale.medic.cost+" G ("+locale.medic.inventory+" remaining)");
			System.out.println("  2) Leave  ");
			int selection = input.nextInt();
    		input.nextLine();
			switch (selection) {
				case 1:
					clearScreen();
					locale.medic.healPlayer(pc);
  					break;
	  			case 2:
	  				inShop=false;
	  				clearScreen();
	  				break;
				default:
					clearScreen();
					System.out.println("Invalid selection.");
					break;
			}
		}
	}

	public void shopLoop(Hero pc){
		boolean inShop=true;
		while (inShop){

			System.out.println("You're in the swordsmith's! A sign hangs over the counter, reading: \n");
			locale.store.inventory();
			Scanner input = new Scanner(System.in);
			System.out.println("You have " + pc.gold + " gold. You: ");
			System.out.println("  1) Examine your current sword ");
			System.out.println("  2) Buy a new sword ");
			System.out.println("  3) Leave  ");
			int selection = input.nextInt();
    		input.nextLine();

			switch (selection) {
				case 1:
					clearScreen();
					System.out.println("===Your sword===");
  					pc.equipped.stats();
  					break;
				case 2:
					int success = locale.store.buySword(pc);
					clearScreen();
					if (success==2){
						System.out.println("You've equipped " + pc.equipped.name + "!\n");
					}else if (success==0){
						System.out.println("Invalid selection!\n");
					}else{
						System.out.println("You can't afford that sword!\n");
					}
	  				break;
	  			case 3:
	  				inShop=false;
	  				clearScreen();
	  				break;
				default:
					System.out.println("Invalid selection.");
					break;
			}
		}
	}

	public void displayWild(Hero pc){
	
		Scanner input = new Scanner(System.in);
		locale.lookAround();
		System.out.println("  1) Check yo' self ");
		System.out.println("  2) Check the map ");
		if (locale.monsterDead) {
			System.out.println("  3) Continue onwards  ");
		}else{
			System.out.println("  3) Approach the monster  ");
		}
		System.out.println("  4) Head back  ");
		int selection = input.nextInt();
    	input.nextLine();

		switch (selection) {
			case 1:
				clearScreen();
  				pc.heroStatus();;
  				break;
  			case 2:
				clearScreen();
  				mymap.display(level);
  				break;
			case 3:
				if (locale.monsterDead){
					clearScreen();
					if (level == maxLevel){
						level++;
						maxLevel++;
						locale = new Location(level,difficulty);
						trail.add(locale);
						mymap.mapAdd(locale);
					}else{
						level++;
						locale=trail.get(level);
					}
				}else{
					clearScreen();
					battleLoop(pc,locale.denizen);
				}
				break;
			case 4:
				clearScreen();
				level--;
  				locale=trail.get(level);
  				if (level<=(maxLevel-(maxLevel%difficulty))){
  					locale.denizen = new Monster(level);
  					locale.monsterDead=false;
  				}
  				break;
			default:
				clearScreen();
				System.out.println("Invalid selection.");
				break;
		}
	}


	public void battleLoop(Hero pc,Monster enemy){
		boolean inBattle=true;
		while (inBattle){
			String prep;
			String[] vowels={"a","e","i","o","u"};
			if (Arrays.asList(vowels).contains(enemy.name.substring(0,1))) {
				prep = "an ";
			}else{
				prep = "a ";
			}
			System.out.println("You're locked in battle with " + prep + enemy.name + "! You have " + pc.health + " health.\n");
			
			Scanner input = new Scanner(System.in);
			System.out.println("You: ");
			System.out.println("  1) Check yo'self ");
			System.out.println("  2) Check on the "+enemy.name);
			System.out.println("  3) Attack  ");
			System.out.println("  4) Flee  ");
			int selection = input.nextInt();
    		input.nextLine();

			switch (selection) {
				case 1:
					clearScreen();
  					pc.heroStatus();
  					break;
				case 2:
					clearScreen();
					int healthGuess = (int) ((enemy.health*.9) + (enemy.health*.2*Math.random()));
					if (enemy.health < (enemy.level * 3.3)){
						System.out.println("The " + enemy.name + " looks almost dead! You'd guess it has " + healthGuess + " health.");
					} else if (enemy.health > (enemy.level * 6.6)){
						System.out.println("The " + enemy.name + " still looks pretty strong...you'd guess it has " + healthGuess + " health.");
					}else{
						System.out.println("The " + enemy.name + "'s taken a few scratches. You'd guess it has " + healthGuess + " health.");
					}
					System.out.println();
	  				break;
	  			case 3:
	  				clearScreen();
	  				pc.heroAttack(enemy);
	  				if (enemy.alive){
	  					enemy.monsterAttack(pc);
	  				}else{
	  					locale.monsterDead=true;
	  					inBattle=false;
	  				}
	  				break;
	  			case 4:
	  				clearScreen();
	  				System.out.println("You fled!\n");
	  				inBattle=false;
	  				break;
				default:
					System.out.println("Invalid selection.");
					break;
			}
		}
	}


	public static void main(String[] args){
		Game mygame = new Game();
		mygame.diffPrompt();
		Hero pc = new Hero(mygame.namePrompt());
		mygame.clearScreen();
		while (true) {
			if (mygame.locale.type == "Town") {
				mygame.displayTown(pc);
			}else{
				mygame.displayWild(pc);
			}
		}
	
	}

}