class Monster extends Mob {

	String uppername;

	public Monster(int mobLevel) {
		super(mobLevel,"Monster");
		equipped = new Sword(mobLevel);
		uppername = name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	public void monsterAttack(Hero target) {
		target.isDamagedBy(super.attack(uppername,"you"));
		if (!target.alive) {
			System.out.println("\nGAME OVER\n");
			System.exit(0);
		}
	}

	public static void main(String[] args){
		Monster jeff = new Monster(100);	
		jeff.status();
	}

}