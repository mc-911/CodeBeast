package main;

public class MaxHealthPotion extends Items{

	public MaxHealthPotion() {
		super("Max Health Potion", 50, 25, "Will increase you max health by 50.");
	}
	
	public void drinkPotion(Monster monster, Player player) {
		int maxHealth = monster.getMaxHealth();
		monster.setMaxHealth(maxHealth + 50);
		player.removeItem(this);
	}
}
