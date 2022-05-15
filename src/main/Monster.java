package main;


import java.util.ArrayList;
/**An instance of this class is a monster, but in this game only intances of this classes sub classes are ever made**/
public class Monster extends Purchasable{
	
	/**A string variable which holds the name of this monster**/
	private String name;
	/**A int variable contains this monsters level**/
	private int level = 1;
	/**A int variable contains the maximum amount of health this monster can have**/
	private int maxHealth;
	/**A int variable contains the amount of damage this monster deals**/
	private int damage;
	/**A int variable used to contain how much this monster heals overnight**/
	private int healAmount;
	/**A int variable, used to contain how many points this monster has, used to check if this monster is ready to level up**/
	private int points;
	/**An int variable contains how many days in a row this monster has fainted in battle**/
	private int daysFainted;
	/**A boolean variable which is used to check if this monster has fainted today (i.e true == monster has fainted today)**/
	private boolean faintedToday;
	/**A int variable contains this monsters current health, used to see if a monster has fainted or not**/
	private int currentHealth;
	/**A static String variable which contains the format of the message shown to the user, when one of their monsters levels up**/
	static String levelUpFormat = "%s is now level %s\nMax Health increased to %s\nHeal Amount increased to %s";
	private boolean brought = false;
	/**A constructor that takes a String variable name as its parameter, used so that the name of the monster may be set when an Monster object is created**/
	public Monster(String name) {
		this.name = name;
		
	}
	/**An instance method, used to change the max health of an instance of Monster, takes an int variable amount as its parameter and sets maxHealth to be amount, returns void**/
	public void setMaxHealth(int amount) {
		
		maxHealth = amount;
	}
	/**An instance method, used to change the current health of an instance of Monster, takes an int variable amount as its parameter and sets currentHealth to be amount, returns void**/
	public void setCurrentHealth(int amount) {
		currentHealth = amount;
		
	}
	/**An instance method, used to change the damage of an instance of Monster, takes an int variable amount as its parameter and sets damage to be amount, returns void**/
	public void setDamageAmount(int amount){
		
		
		damage = amount;
		
		
	}
	/**An instance method, used to change the healAmount of an instance of Monster, takes an int variable amount as its parameter and sets healAmount to be amount, returns void**/
	public void sethealAmount(int amount){
		healAmount = amount;
		
	}
	/**An instance method which used to set daysFainted to the methods parameter amount, returns void**/
	public void setDaysFainted(int amount) {
		daysFainted = amount;
	}
	/**An instance method used to set faintedToday to the methods parameter bool, takes a variable bool of data type boolean as its parameter, returns void**/
	public void setFaintedToday(boolean bool) {
		faintedToday = bool;
	}
	/**An instance method, used to increase the current health of this monster whilst not exceeding the maximum amount of health this monster can have, takes an int variable amount as its parameter, returns void**/
	public void addHealth(int amount) {
		/**Used to increase a monster's currentHealth by amount whilst not exceeding the monster's maxHealth**/
		currentHealth += amount;
		if (currentHealth > maxHealth) {
			currentHealth = maxHealth;
			
		}
		
		
	}
	/**An instance method, used to set the monsters name to the parameter name, takes a String variable name as its parameter, returns void**/
	public void setName(String name) {
		
		this.name = name; 
	}
	/**An instance method, used to scale an this instance of Monster damage up, only used if the game is on hard and is only used on monsters that the user will fight against, returns void**/
	public void setHardFoe() {
		
		damage = (int) Math.round(damage * 1.5);
		
	}
	/**An instance method which returns faintedToday, returns boolean**/
	public boolean getFaintedToday() {
		return faintedToday;
	}
	/**An instance method which returns this instance of Monster healAmount, returns int**/
	public int getHealAmount() {
	
		return healAmount;
	}
	/**An instance method, used to get the name of this instance of Monster it returns name, returns String**/
	public String getName() {
	
		return name;
	}
	/**An instance method, used to get the amount of days this instance of Monster has fainted in a row, returns int**/
	public int getdaysFainted() {
		return daysFainted;
	}
	/**An instance method, used to get damage this instance of Monster deals**/
	public int getDamage() {
		return damage;
	}
	/**An instance method, returns a string showing a monster's name, damage, currentHealth, maxHealth, damage, healAmount, returns String**/
	public String toString() {
		return String.format("Name: %s\nHealth: %s/%s\nDamage: %s\nHeal Amount: %s\nPrice: %s \nSell Price: %s \nDescription: %s \nLevel: %s", name, currentHealth, maxHealth, damage, healAmount, super.getPurchasePrice(), super.getSellBackPrice(), super.getDescription(), level);
		
	}
	/**An instance method, used to get the maximum current health this instance of Monster can have, returns int**/
	public int getMaxHealth() {
		return maxHealth;
	}
	/**An instance method, used to get the current health an instance of Monster, returns int**/
	public int getCurrentHealth() {
		return currentHealth;
	}
	/**An instance method, used to get the level of an instance of Monster, returns int**/
	public int getLevel() {
		return level;
	}
	
	/**An instance method, used in Battle to have a monster attack another monster (other), takes a Monster variable as its parameter, returns void**/
	public void attack(Monster other) {
		
		int otherhealth = other.currentHealth - damage;
		if (otherhealth < 0) {
			other.setCurrentHealth(0);
		}
		else {
			other.setCurrentHealth(otherhealth);
		}
		
		
	}
	/**An instance method, Used to increase the amount of points a monster and to check if the monster is ready to level up, takes an int variable amount as its parameter, returns void**/
	public void increasePoints(int amount, GameWindow window) {
		points += amount;
		checkPoints(window);
		
	}
	/**An instance variable, its used to "level up" a monster what this does is it increases an instance of Monsters damage, maxHealth, healAmount based on some formulas, and displays these changes to the user, returns void**/
	public void levelUp(GameWindow window) {
		level++;
		points = 0;
		damage = (int) Math.round(damage * 1.5);
		maxHealth = (int) Math.round(maxHealth * 1.5);
		healAmount = (int) Math.round(healAmount * 1.5);
		currentHealth = maxHealth;
		window.printMsg(String.format(levelUpFormat, name, level, maxHealth, healAmount));
		
	}
	/**An instance method used to check if an instance of Monster is ready to level up by comparing its points to a formula if it is it calls levelUp(), returns void**/
	public void checkPoints(GameWindow window) {
		
		if (points >= 100 * level) {
			levelUp(window);
		}
	}
	public void setBrought(boolean bool) {
		brought = bool;
	}
	public boolean getBrought() {
		return brought;
	}

	
}
