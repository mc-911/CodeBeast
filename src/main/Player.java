package main;

import java.lang.reflect.Array;
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Player {
	//Empty list of userMonsters
	private ArrayList<Monster> userMonsters = new ArrayList<Monster>();
	//The name of the user
	private String name;
	private int gold = 50;
	//Scale strength of enemies with points
	private int points = 0;
	private Monster activeMonster;
	//Empty list of user's items
	private ArrayList<Items> userItems = new ArrayList<Items>();
	public void increasePoints(int amount) {
		points += amount;
	}
	public int getPoints() {
		return points;
	}
	public void nextMon() {
		int i = userMonsters.indexOf(activeMonster);
		while (activeMonster.getCurrentHealth() == 0) {
			activeMonster = userMonsters.get(i);
			i++;
		}
		
	}
	public void setName(){
		/**used to get input from user and set player name**/
		String input;
		boolean valid = false;
		while (valid == false) {
			Environment.printMsg("Please input your Player name\n(Note: Must be between 3 and 15 characters and no numbers or special characters");
			input = Environment.getUserString();
			valid = checkName(input);
			name = input;
			if (valid == false) {
				Environment.printMsg("Invalid name");
			}
		}
		
	}
	public static boolean checkName(String name) {
		/**used to check if user's input from setName() is a valid name**/
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
	public  void monsterMenu() {
		int input;
		String menuString = "";
		for (int i = 0; i != userMonsters.size(); i++) {
			if (userMonsters.get(i).getCurrentHealth() != 0){
			menuString = menuString + String.format("%s. %s ", i, userMonsters.get(i).getName()) ;
			}
			else {
				menuString =  menuString + String.format("(x) Downed: %s", userMonsters.get(i).getName());
			}
		}
		Environment.printMsg("Monster Order: " + menuString);
		Environment.displayList(userMonsters.toArray(new Purchasable[userMonsters.size()]));
		Environment.printMsg("Input 0 to go back\nInput 1 to enter change order mode");
		
		input = Environment.getUserIntBounds(0, 1);
		switch (input) {
		case 0:
			break;
		case 1:
			int firstMonIndex;
			int secondMonIndex;
			Environment.printMsg("Enter the position of the first monster you'd like to swap");
			firstMonIndex = Environment.getUserIntBounds(0, userMonsters.size());
			Environment.printMsg(String.format("First Monster: %s", userMonsters.get(input).getName()));
			Environment.printMsg("Enter the position of the second monster you'd like to swap");
			secondMonIndex = Environment.getUserIntBounds(0, userMonsters.size());
			Environment.printMsg(String.format("First Monster: %s\nSecond Monster: %s", userMonsters.get(firstMonIndex).getName(), userMonsters.get(input).getName()));
			Collections.swap(userMonsters, firstMonIndex, secondMonIndex);
			activeMonster = userMonsters.get(0);
			
			
		}
		
		
	}
	public void addItem(Items item) {
		userItems.add(item);
	}
	public void removeItem(Items item) {
		userItems.remove(item);
	}
	public ArrayList<Items> getItems() {
		return userItems;
	}
	public void getInventory() {
		if (userItems.size() == 0) {
			Environment.printMsg("No items in inventory.");
			Environment.printMsg("Monsters:\n");
		}
		else {
			Environment.printMsg("Items:\n");
			for (Items item : userItems) {
				Environment.printMsg(item.toString());
			}
		}
		Environment.printMsg(stringMonsters());
		if (userItems.size() != 0) {
		Environment.printMsg("Input a number to select an item.");
		Items item = userItems.get(Environment.getUserIntBounds(0, userItems.size()));
		Environment.displayList(userMonsters.toArray(new Purchasable[userMonsters.size()]));
		Environment.printMsg("Select the monster that you would like to apply the item to.");
		item.drinkPotion(userMonsters.get(Environment.getUserIntBounds(0, userMonsters.size())), this);
		}
	}
	
	public void addMonster(Monster creep) {
		userMonsters.add(creep);
	}
	public void removeMonster(Monster creep) {
		userMonsters.remove(creep);
	}
	public String getName() {
		return name;
	}
	public void setGold(int coins) {
		gold = coins;
	}
	public int getGold() {
		return gold;
	}
	public Monster getMonster(int i) {
		return userMonsters.get(i);
	}
	public ArrayList<Monster> getMonsters() {
		return userMonsters;
	}
	public void setActiveMonster(int i){
		activeMonster = userMonsters.get(i);
	}
	public Monster getActiveMonster() {
		return activeMonster;
	}
	public String stringMonsters() {
		String strMon = "";
		if (userMonsters.size() == 0) {
			return "No userMonsters in party";
		}
		else {
			for (int i = 0; i != userMonsters.size(); i++) {
				strMon = userMonsters.get(i).getName() + " " + strMon;
			}
			return strMon;
		}
	}
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
	public String toString() {
		return String.format("Name: %s\nGold: %s\nPoints: %s\nMonsters: %s\nItems: %s", name, gold, points, stringMonsters(), stringItems());
	}
	public void sleepMon() {
		for (Monster i : userMonsters) {
			i.addHealth(i.getHealAmount());
			
		}
	}
}
