package main;

public class AttackPotion extends Items{

	public AttackPotion() {
		super("Attack Potion", 15, 10, "Will increase your monster's attack by 20.");
	}
	
	public void drinkPotion(Monster monster, Player player) {
		int attack = monster.getDamage();
		monster.setDamageAmount(attack + 20);
		player.removeItem(this);
	}
}
