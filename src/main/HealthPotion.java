package main;


public class HealthPotion extends Items{

	public HealthPotion() {
		super("Health Potion", 10, 5, "Will increse your current health by 25.");
	}

	public void drinkPotion(Monster monster, Player player) {
		int currentHealth = monster.getCurrentHealth();
		if (currentHealth == monster.getMaxHealth()) {
			System.out.println("Current health is equal to max health.");
		} else {
			monster.setCurrentHealth(currentHealth + 25);
			if (currentHealth > monster.getMaxHealth()) {
				currentHealth = monster.getMaxHealth();
				player.removeItem(this);
			}
		}
	}

}
