package main;


public class Jerry extends Monster{
	public Jerry(String name){
		super(name);
		setter();
	}
	public Jerry() {
		super("Jerry");
		setter();
	}
	public void setter() {
		super.setCurrentHealth(150);
		super.setDamageAmount(55);
		super.setMaxHealth(150);
		super.sethealAmount(15);
		super.setDescription("A classic white guy.");
	}	
	
	public void drinkPotion(Monster monster, Player player) {
		int maxHealth = monster.getMaxHealth();
		monster.setMaxHealth(maxHealth + 50);
		player.removeMonster(this);
	}

}
