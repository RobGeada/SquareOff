class Sword {
	int level;
	int damage;
	String name;
	double crit;
	int price;

	public Sword(int swordLevel) {
		level = swordLevel;
		if (level == 0){
			level=0;
			damage=10;
			crit=1;
			name = "Mom's Cleaver";
		}else{
			damage = Math.max(1,(int) (1.5 * swordLevel * Math.pow(Math.random(),.33)));
			crit = (.8 + .2 * Math.pow(Math.random(),.33));
			SwordName nameObj = new SwordName();
			name = "The " + nameObj.name;

			double itemAvg   = ((crit*damage)+(1-crit)*(damage*1.5));
			itemAvg = (level+itemAvg)/2;
			price = Math.max(level,(int) ((itemAvg*.9) + (itemAvg * .2 * Math.random())));
			price = price*10;
		}
	}

	public void stats() {
		if (level == 0) {
			System.out.println("A cleaver you borrowed from your mom");
			System.out.println("  Level:  1");
			System.out.println("  Damage: 10");
			System.out.println("  Crit:   0%\n");	
		} else {
			String critDisplay = String.format("%.1f",100-(crit*100));
			System.out.println(name);
			System.out.println("  Level:  " + level);
			System.out.println("  Damage: " + damage);
			System.out.println("  Crit:   " + critDisplay +"%\n");	
		}
	}

	public void display(int index) {
		if (level == 0) {
			System.out.println("N/A");
		} else {
			String critDisplay = String.format("%.1f",100-(crit*100));
			System.out.println(index + ") " + name + ": " + price + " G");
			System.out.println("  Level:  " + level);
			System.out.println("  Damage: " + damage);
			System.out.println("  Crit:   " + critDisplay +"%\n");	
		}
	}

	public static void main(String[] args){
		Sword excalibur = new Sword(100);
		excalibur.stats();
	}

}