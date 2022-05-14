package main;
/** A subclass of Items this is the Health Potion that will increase the Monsters health by 25 and decrease the gold by 10 when bought and increase gold by 5 when sold **/

public class HealthPotion extends Items{
	
	/** constructor for the health potion from the items super class **/
	public HealthPotion() {
		super("Health Potion", 10, 5, "Will increse your current health by 25.");
	}
	
	/** a method that will allow the player to drink the potion thereby removing it from the player's inventory and adding the affects to the monster **/
	public void drinkPotion(Monster monster, Player player, GameWindow window) {
		int currentHealth = monster.getCurrentHealth();
		if (currentHealth == monster.getMaxHealth()) {
			window.printMsg("Current health is equal to max health.");
		} else {
			monster.setCurrentHealth(currentHealth + 25);
			if (currentHealth > monster.getMaxHealth()) {
				currentHealth = monster.getMaxHealth();
				player.removeItem(this);
			}
		}
	}

}
