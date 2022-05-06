package main;



public class MaxHealthPotion extends Items{

	public MaxHealthPotion(String name, int purchase, int sellBack, String description) {
		super("Max Health Potion", 50, 25, "Will increase you max health by 50.");
	}
	
	public void buyPotion(Player player) {
		int coins = player.getGold();
		if (coins >= 50) {
			player.setGold(coins- 50);
			//add to inventory//
		} else {
			System.out.println(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", 50-coins));
		}	
	}
	
	public void sellPotion(Player player) {
		int coins = player.getGold();
		player.setGold(coins+25);
		//remove from inventory//
	}
	
	public void drinkPotion(Monster monster) {
		int maxHealth = monster.getMaxHealth();
		monster.setMaxHealth(maxHealth + 50);
		//remove from inventory//
	}
}
