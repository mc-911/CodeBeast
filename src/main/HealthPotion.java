package main;


public class HealthPotion extends Items{

	public HealthPotion(String name, int purchase, int sellBack, String description) {
		super("Health Potion", 10, 5, "Will increse your current health by 25.");
	}
	
	public void buyPotion(Player player) {
		int coins = player.getGold();
		if (coins >= 10) {
			player.setGold(coins- 10);
		} else {
			System.out.println(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", 10-coins));
		}	
	}
	
	public void sellPotion(Player player) {
		int coins = player.getGold();
		player.setGold(coins+5);
		
		
	}
	
	public void drinkPotion(Monster monster) {
		int currentHealth = monster.getCurrentHealth();
		if (currentHealth == monster.getMaxHealth()) {
			System.out.println("Current health is equal to max health.");
		} else {
			monster.setCurrentHealth(currentHealth + 25);
			if (currentHealth > monster.getMaxHealth()) {
				currentHealth = monster.getMaxHealth();
			}
		}
	}

}
