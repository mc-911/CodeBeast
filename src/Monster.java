

import java.util.ArrayList;

public class Monster extends Purchasable{
	
	private String name;
	private int level = 1;
	private int maxHealth;
	private int damage;
	private int healAmount;
	private int points;
	//healAmount? 
	int currentHealth;
	static String levelUpFormat = "%s is now level %s\nMax Health increased to %s\nHeal Amount increased to %s";
	public Monster(String name) {
		this.name = name;
		
	}
	public void setMaxHealth(int amount) {
		maxHealth = amount;
	}
	public void setCurrentHealth(int amount) {
		currentHealth = amount;
		
	}
	public void setDamageAmount(int amount){
		damage = amount;
		
	}
	public void sethealAmount(int amount){
		healAmount = amount;
		
	}
	public void setName(String name) {
		this.name = name; 
	}
	public String getName() {
		return name;
	}
	public int getDamage() {
		return damage;
	}
	public String toString() {
		return String.format("Name: %s\nHealth: %s/%s\nDamage: %s\nHeal Amount: %s", name, currentHealth, maxHealth, damage, healAmount);
		
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setHardFoe() {
		damage = (int) Math.round(damage * 1.5);
		System.out.println(damage);
		
	}
	public void setFoe() {
		
	}
	public void attack(Monster other) {
		int otherhealth = other.currentHealth - damage;
		if (otherhealth < 0) {
			other.setCurrentHealth(0);
		}
		else {
			other.setCurrentHealth(otherhealth);
		}
		
		
	}
	public void increasePoints(int amount) {
		points += amount;
		checkPoints();
		
	}
	public void levelUp() {
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
