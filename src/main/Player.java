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
	public void setName(String str){
		/**used to get input from user and set player name**/
		name = str;
		
		
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
	
	public void addItem(Items item) {
		userItems.add(item);
	}
	public void removeItem(Items item) {
		userItems.remove(item);
	}
	public ArrayList<Items> getItems() {
		return userItems;
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
