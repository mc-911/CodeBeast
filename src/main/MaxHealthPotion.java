package main;
/** A subclass of Items this is the Max Health Potion that will increase the Monsters max health by 50 and decrease the gold by 50 when bought and increase gold by 25 when sold **/
public class MaxHealthPotion extends Items{
	
	/** constructor for the max health potion from the items super class **/
	public MaxHealthPotion() {
		super("Max Health Potion", 50, 25, "Will increase you max health by 50.");
	}
	
	/** a method that will allow the player to drink the potion thereby removing it from the player's inventory and adding the affects to the monster **/
	public void drinkPotion(Monster monster, Player player) {
		int maxHealth = monster.getMaxHealth();
		monster.setMaxHealth(maxHealth + 50);
		player.removeItem(this);
	}
}
