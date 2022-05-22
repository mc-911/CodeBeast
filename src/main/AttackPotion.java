package main;
/** A subclass of Items this is the attack potion that will increase the monsters attack by 20 and decrease the gold by 15 when bought and increase gold by 10 when sold **/
public class AttackPotion extends Items{
	
	/** constructor for the attack potion from the items super class **/
	public AttackPotion() {
		super("Attack Potion", 15, 10, "Will increase your monster's attack by 20.");
	}
	
	/** a method that will allow the player to drink the potion thereby removing it from the player's inventory and adding the affects to the monster, takes an Monster parameter monster, Player parameter as its parameters, returns void**/
	public void drinkPotion(Monster monster, Player player) {
		int attack = monster.getDamage();
		monster.setDamageAmount(attack + 20);
		player.removeItem(this);
	}
}
