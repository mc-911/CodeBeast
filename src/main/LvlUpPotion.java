package main;

public class LvlUpPotion extends Items{

	public LvlUpPotion() {
		super("Level Up Potion", 25, 20, "Will increase the level of your monster.");
	}
	
	public void buyPotion(Player player) {
		int coins = player.getGold();
		if (coins >= 25) {
			player.setGold(coins- 25);
			//add to inventory//
		} else {
			System.out.println(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", 25-coins));
		}	
	}
	
	public void sellPotion(Player player) {
		int coins = player.getGold();
		player.setGold(coins+20);
		//remove from inventory//
		
		
	}
	
	public void drinkPotion(Monster monster) {
		monster.levelUp();
		//remove from inventory//
	}

}
