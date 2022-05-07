package main;

public class AttackPotion extends Items{

	public AttackPotion() {
		super("Attack Potion", 15, 10, "Will increase your monster's attack by 20.");
	}
	
	public void buyPotion(Player player) {
		int coins = player.getGold();
		if (coins >= 15) {
			player.setGold(coins- 15);
			//add from inventory//
		} else {
			System.out.println(String.format("Insufficient funds, can't buy this potion. \nNeed %s more gold.", 15-coins));
		}	
	}
	
	public void sellPotion(Player player) {
		int coins = player.getGold();
		player.setGold(coins+5);
		//remove from inventory//
		
		
	}
	
	public void drinkPotion(Monster monster) {
		int attack = monster.getDamage();
		monster.setDamageAmount(attack + 20);
		//remove from inventory//
	}
}
