package main;


import java.util.ArrayList;

public class Monster extends Purchasable{
	
	private String name;
	private int level = 1;
	private int maxHealth;
	private int damage;
	private int healAmount;
	private int points;
	private boolean faintedToday;
	//healAmount? 
	int currentHealth;
	static String levelUpFormat = "%s is now level %s\nMax Health increased to %s\nHeal Amount increased to %s";
	public Monster(String name) {
		this.name = name;
		
	}
	public void setMaxHealth(int amount) {
		/**Sets setMaxHealth to be amount**/
		maxHealth = amount;
	}
	public void setCurrentHealth(int amount) {
		/**Sets currentHealth to be amount**/
		currentHealth = amount;
		
	}
	public void setDamageAmount(int amount){
		/**Sets damage to be amount**/
		damage = amount;
		
	}
	public void sethealAmount(int amount){
		/**Sets healAmount to be amount**/
		healAmount = amount;
		
	}
	public void addHealth(int amount) {
		/**Used to increase a monster's currentHealth by amount whilst not exceeding the monster's maxHealth**/
		currentHealth += amount;
		if (currentHealth > maxHealth) {
			currentHealth = maxHealth;
			
		}
		
		
	}
	public void setName(String name) {
		/**Used to set the monsters name to the parameter name, name is the user's input**/
		this.name = name; 
	}
	public int getHealAmount() {
		/**Returns healAmount**/
		return healAmount;
	}
	public String getName() {
		/**Returns healAmount**/
		return name;
	}
	public int getDamage() {
		/**Returns healAmount**/
		return damage;
	}
	public String toString() {
		/**Returns a string showing a monster's name, damage, currentHealth, maxHealth, damage, healAmount**/
		return String.format("Name: %s\nHealth: %s/%s\nDamage: %s\nHeal Amount: %s \nDescription: %s \n", name, currentHealth, maxHealth, damage, healAmount, super.getDescription());
		
	}
	public int getMaxHealth() {
		/**Returns the monster's maxHealth**/
		return maxHealth;
	}
	public int getCurrentHealth() {
		/**Returns the monster's currentHealth**/
		return currentHealth;
	}
	public void setHardFoe() {
		/**Used to scale an enemy's damage up, only used if the game is on hard**/
		damage = (int) Math.round(damage * 1.5);
		
	}
	
	public void attack(Monster other) {
		/**Used in Battle to have a monster attack another monster (other)**/
		int otherhealth = other.currentHealth - damage;
		if (otherhealth < 0) {
			other.setCurrentHealth(0);
		}
		else {
			other.setCurrentHealth(otherhealth);
		}
		
		
	}
	public void increasePoints(int amount) {
		/**Used to increase the amount of points a monster has and calls the method checkPoints()**/
		points += amount;
		checkPoints();
		
	}
	public void levelUp() {
		/**Increases a monsters damage, maxHealth, healAmount based on some formulas, and displays these changes to the user**/
		level++;
		points = 0;
		damage = (int) Math.round(damage * 1.5);
		maxHealth = (int) Math.round(maxHealth * 1.5);
		healAmount = (int) Math.round(healAmount * 1.5);
		System.out.println(String.format(levelUpFormat, level, maxHealth, healAmount));
		
	}
	public void checkPoints() {
		/**Checks points to see if the monster is ready to level up**/
		if (points >= 100 * level) {
			levelUp();
		}
	}

	
}
