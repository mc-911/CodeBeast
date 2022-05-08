package main;

public class LvlUpPotion extends Items{

	public LvlUpPotion() {
		super("Level Up Potion", 25, 20, "Will increase the level of your monster.");
	}
	
	public void drinkPotion(Monster monster, Player player) {
		monster.levelUp();
		player.removeItem(this);
	}

}
