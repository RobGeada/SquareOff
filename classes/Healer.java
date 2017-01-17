import java.util.*;

class Healer {

	int level;
	int cost;
	int inventory;

	public Healer(int healLevel) {
		level = healLevel;
		cost  = Math.min(50,(level*2) + 10);
		inventory=5;
	}

	public void healPlayer(Hero pc) {
		if (inventory<=0){
			System.out.println("No potions remaining!\n");
		} else if (pc.gold >= cost){
			pc.gold = pc.gold - cost;
			inventory--;
			pc.health = pc.health + cost;
		}else{
			System.out.println("You don't have the gold!\n");
		}
	}

	public static void main(String[] args){}

}