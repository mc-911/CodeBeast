

public class MaxHealthPotion extends Items{

	public MaxHealthPotion(String name, int purchase, int sellBack, String description) {
		super("Max Health Potion", 50, 25, "Will increase you max health by 50.");
	}
	
	public void buyPotion(Player player) {
		int coins = player.getGold();
		if (coins >= 50) {
			player.setGold(coins- 50);
		} else {
			System.out.println(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", 50-coins));
		}	
	}
	
	public void sellPotion(Player player) {
		int coins = player.getGold();
		player.setGold(coins+25);
	}
	
	public void drinkPotion(Monster monster) {
		int maxHealth = monster.getMaxHealth();
		monster.setMaxHealth(maxHealth + 50);
	}
}
