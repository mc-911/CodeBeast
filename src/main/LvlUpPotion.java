package main;
/** A subclass of Items this is the Level Up Potion that will increase the Monsters XP by 10 and decrease the gold by 25 when bought and increase gold by 20 when sold **/
public class LvlUpPotion extends Items{
	
	/** constructor for the level up potion from the items super class **/
	public LvlUpPotion() {
		super("Points Potion", 25, 20, "Will increase the points of your monster.");
	}
	
	/** a method that will allow the player to drink the potion thereby removing it from the player's inventory and adding the affects to the monster **/
	public void drinkPotion(Monster monster, Player player) {
		monster.increasePoints(25);
		player.removeItem(this);
	}

}
