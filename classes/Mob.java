class Mob {

	int level;
	int health;
	boolean alive = true;
	String name;
	Sword equipped;

	public Mob(int mobLevel,String mobName) {
		level = mobLevel;
		health = mobLevel * 10;
		if (mobName=="Monster"){
			MonsterName me = new MonsterName();
			name = me.name;
		}else{
			name = mobName;
		}
	}

	public void status() {
		System.out.println("=====" + name +"=====");
		if (alive) {
			System.out.println("STATS:");
			System.out.println("  Level:  "+level);
			System.out.println("  Health: "+health);
			System.out.println();
			System.out.println("SWORD:");
			equipped.stats();
			System.out.println();
		} else {
			System.out.println("DEAD");
			System.out.println();
		}
	}

	public void isDamagedBy(int damage) {
		health = health-damage;
		if (health<=0) {
			System.out.println(name + " died!");
			health = 0;
			alive = false;
		}
	}

	public int attack(String agent,String target) {
		//find minimum attack threshold
		int attackPower = equipped.damage;
		int thresh = (int) (attackPower*Math.pow(Math.random(),.25));
		//find random value from minimum attack to attack power
		int overThresh = (int) (Math.random()*(attackPower-thresh+1));
		int rawAttack = thresh+overThresh;
		
		//check for crits
		double roll = Math.random();
		int damageDone;
		if (roll > equipped.crit) {
			damageDone = (int) (attackPower*1.5);
			System.out.println(agent + " gets a critical hit on " + target + " for " + damageDone + " damage!");
		} else if (rawAttack == 0 || roll < .05) {
			System.out.println(agent + " missed!");
			damageDone = 0;
		} else {
			damageDone = rawAttack;
			System.out.println(name + " hits " + target + " for " + damageDone + " damage!");
		}
		System.out.println();
		return damageDone;
	}

	public static void main(String[] args){
		Mob test = new Mob(10,"Arnold");
		test.status();
	
	}

}