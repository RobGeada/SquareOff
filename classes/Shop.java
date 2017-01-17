import java.util.*;

class Shop {
	int level;
	int inventorySize;
	List<Sword> inventory = new ArrayList<Sword>();

	public Shop(int shopLevel) {
		level = shopLevel;
		inventorySize = Math.max(1,(int) (5*Math.random()));
		int swordLevel;
		for (int i = 0; i<inventorySize; i++) {
			swordLevel = Math.max(1,(int) (level*.9 + level*1.1 * Math.random()));
			inventory.add(new Sword(swordLevel));
		}
	}

	public void inventory() {
		System.out.println("=====Swords of the Day=====");
		if (inventory.size() == 0) {
			System.out.println("Out of stock, sorry!\n");
		} else {
			int i = 1;
			for( Sword item : inventory) {
				item.display(i);
				i++;
			}
		}
	}

	public int buySword(Hero pc) {
		Scanner reader = new Scanner(System.in);
		System.out.println("Which number sword would you like? ");
		int n = reader.nextInt();

		if (n > inventorySize){
			System.out.println("Invalid selection! ");
			return 0;
		} else {
			Sword targetSword = inventory.get(n-1);
			if (pc.gold>=targetSword.price) {
				inventory.remove(targetSword);
				inventorySize--;
				pc.equipSword(targetSword);
				pc.gold = pc.gold-targetSword.price;
				return 2;
			}else{
				System.out.println("Can't afford " + targetSword.name);
				return 1;
			}
		}
	}

	public static void main(String[] args){}

}