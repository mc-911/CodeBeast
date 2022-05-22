package main;

import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**A class which holds all the details of the user the name they wish to go by, their items, and monsters etc.**/
public class Player {
	/**An arraylist which holds the mosnters that the user has currently**/
	private ArrayList<Monster> userMonsters = new ArrayList<Monster>();
	/**A string variable which holds the name of the player/user**/
	private String name;
	/**A int variable, which contains the amount of gold the user has, is initialized as 50**/
	private int gold = 50;
	/**A int variable, which contains the amount of points the user has**/
	private int points = 0;
	/**An Monster variable which contains the users Active Monster which is the monster they will fight with**/
	private Monster activeMonster;
	/**An ArrayList<Items> variable, which contains an ArrayList containing the users items**/
	private ArrayList<Items> userItems = new ArrayList<Items>();
	/**An instance method used to increase points by the int parameter amount, returns void**/
	public void increasePoints(int amount) {
		points += amount;
	}
	/**An instance method, which returns points, return int**/
	public int getPoints() {
		return points;
	}
	/**An instance method, which is used to change the monster that activeMonster contains, its used when the users active monster gets downed and sets active monster to be the next monster in userMonsters that has health, returns void**/
	public void nextMon() {
		int i = userMonsters.indexOf(activeMonster);
		while (activeMonster.getCurrentHealth() == 0) {
			activeMonster = userMonsters.get(i);
			i++;
		}
		
	}
	/**An instance method, used to set the users name via setting name to the String parameter str, returns void**/
	public void setName(String str){
		name = str;
		
		
	}
	/**A static method, used to check if a String parameter name is a valid user name, returns boolean**/
	public static boolean checkName(String name) {
		boolean notValid;
		if (name.length() < 4 || name.length() > 15) {
			return false;
		}
		String[] invalidList = {"0", "1", "2", "3", "4", "5", "6","7","8", "9", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "-", "_", "+", "=", "`", "~", "{", "}", "[", "]", ",", ".", "/", "?", "<", ">", ";", ":"};
		for (int i = 0; i != name.length(); i++) {
			notValid = Arrays.asList(invalidList).contains( String.valueOf(name.charAt(i)));
			
			if (notValid == true) {
				return false;
			}
		}
		return true;
		
	}
	/**An instance method, returns a string representation of the users monsters and the order of the monsters, returns String**/
	public String getMonsterOrder() {
		String menuString = "";
		if (userMonsters.size() == 0) {
			return "No monsters";
		}
		for (int i = 0; i != userMonsters.size(); i++) {
			if (userMonsters.get(i).getCurrentHealth() != 0){
			menuString = menuString + String.format("%s. %s ", i, userMonsters.get(i).getName()) ;
			}
			else {
				menuString =  menuString + String.format("(x) Downed: %s", userMonsters.get(i).getName());
			}
		}
		return menuString;
	}
	/**An instance method, used to add an Items parameter item to the users list of items, returns void**/
	public void addItem(Items item) {
		userItems.add(item);
	}
	/**An instance method, used to remove an Items parameter item from the users list of items, returns void**/
	public void removeItem(Items item) {
		userItems.remove(item);
	}
	/**An instance method, used to get a list of the users items, returns ArrayList<Items>**/
	public ArrayList<Items> getItems() {
		return userItems;
	}

	/**An instance method, used to add an Monster parameter creep to the users list of Monsters, returns void**/
	public void addMonster(Monster creep) {
		userMonsters.add(creep);
	}
	/**An instance method, used to remove an Monster parameter creep from the users list of Monsters, returns void**/
	public void removeMonster(Monster creep) {
		userMonsters.remove(creep);
	}
	/**An instance method, used to get the users name, returns String**/
	public String getName() {
		return name;
	}
	/**An instance method, sets the users gold to the int parameter coins, returns void**/
	public void setGold(int coins) {
		gold = coins;
	}
	/**An instance method, returns the users gold, returns int**/
	public int getGold() {
		return gold;
	}
	/**An instance method, returns an instance of Monster from position int parameter i in userMonsters, returns Monster**/
	public Monster getMonster(int i) {
		return userMonsters.get(i);
	}
	/**An instance method, used to get a the list of the users Monsters, returns ArrayList<Monster>**/
	public ArrayList<Monster> getMonsters() {
		return userMonsters;
	}
	/**An instance method, used to set the users active monster to the monster at postition int parameter i in userMonsters, returns void**/
	public void setActiveMonster(int i){
		activeMonster = userMonsters.get(i);
	}
	/**An instance method, returns the users active monsters, returns Monster**/
	public Monster getActiveMonster() {
		return activeMonster;
	}
	/**An instance method, returns a string representation of userMonsters, returns String**/
	public String stringMonsters() {
		String strMon = "";
		if (userMonsters.size() == 0) {
			return "No Monsters in party";
		}
		else {
			for (int i = 0; i != userMonsters.size(); i++) {
				strMon = userMonsters.get(i).getName() + " " + strMon;
			}
			return strMon;
		}
	}
	/**An instance method, returns a string representation of userMonsters, returns String**/
	public String stringItems() {
		String strItems = "";
		if (userItems.size() == 0) {
			return "You have no items";
		}
		else {
			for (int i = 0; i != userMonsters.size(); i++) {
				strItems = userItems.get(i).getName() + " " + strItems;
			}
			return strItems;
		}
	}
	/**A toString() method, returns a String representation of this class, returns String**/
	public String toString() {
		return String.format("Name: %s\nGold: %s\nPoints: %s\nMonsters: %s\nItems: %s", name, gold, points, stringMonsters(), stringItems());
	}
	/**An instance method, used to have the users monsters sleep, returns void**/
	public void sleepMon() {
		for (Monster i : userMonsters) {
			i.addHealth(i.getHealAmount());
			if (!i.getFaintedToday()) {
				i.setDaysFainted(0);
			}
			i.setFaintedToday(false);
		}
	}
}
