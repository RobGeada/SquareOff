import java.util.*;

class Hero extends Mob {
	int gold;
	int monstersSlain;

	public Hero(String mobName) {
		super(5,mobName);
		gold= 100;
		monstersSlain = 0;
		equipped= new Sword(0);
	}

	public static void clearScreen() {  
		System.out.print("\033[H\033[2J");  
		System.out.flush();
		System.out.println("======Square Off!======");
		System.out.println();
	}  

	public void heroAttack(Mob target) {
		target.isDamagedBy(super.attack("You",target.name));
		if (!target.alive) {
			System.out.println();
			monstersSlain++;
			int goldReceived = Math.max(1,(int) (target.level * (Math.random()*20)));
			double dropRand = Math.random();
			if (dropRand <= .33){
				System.out.println("You found a healing potion on the monster's body.");
				int heal = Math.max(150,target.level*10);
				System.out.println("Your health increased by " + heal + "!\n");	
				health = health+heal;

			} else if (dropRand>.9){
				boolean validSelection = false;
				int lootLevel = Math.max(1,(int) (target.level*.9 + target.level*1.1 * Math.random()));
				Sword lootSword = new Sword(lootLevel);
				while (!validSelection){
					System.out.println("You found a sword on the monster's body:");
					lootSword.stats();
					System.out.println("Your current sword:");
					equipped.stats();
					Scanner input = new Scanner(System.in);
					System.out.println("You: ");
					System.out.println("  1) Equip the new sword. ");
					System.out.println("  2) Keep your old sword. ");
					int selection = input.nextInt();
	    			input.nextLine();

					switch (selection) {
						case 1:
							equipSword(lootSword);
							clearScreen();
							System.out.println("You've equipped " + equipped.name + "!\n");
							validSelection=true;
		  					break;
						case 2:
							clearScreen();
							validSelection=true;
			  				break;
			  			default:
			  			    clearScreen();
			  				System.out.println("Invalid selection.");
							break;
			  			}
			  		}
			  	}
			dropRand = Math.random();
			if (dropRand>.2){
				System.out.println("You found "+ goldReceived + " gold on the monster's body!\n");
				gold = gold + goldReceived;
			}
		}
	}

	public void equipSword(Sword newSword) {
		equipped = newSword;
	}

	public void heroStatus() {
		System.out.println("=====" + name +"=====");
		if (alive) {
			System.out.println("STATS:");
			System.out.println("  Gold: "+gold);
			System.out.println("  Health: "+health);
			System.out.println("  Monsters Slain: "+monstersSlain);
			System.out.println();
			System.out.println("SWORD:");
			equipped.stats();
			System.out.println();
		} else {
			System.out.println("DEAD");
			System.out.println();
		}
	}

	public static void main(String[] args){
		Hero rob = new Hero("Rob");
		rob.heroStatus();
		rob.equipSword(new Sword(100));
		rob.heroStatus();
	}

}