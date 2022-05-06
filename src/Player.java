

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Player {
	//Empty list of monsters
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	//The name of the user
	private String name;
	private int gold = 50;
	//Scale strength of enemies with points
	private int points = 0;
	private Monster activeMonster;
	//Empty list of items
	
	public void setName(){
		/**used to get input from user and set player name**/
		String input;
		boolean valid = false;
		Scanner scanner = new Scanner(System.in);
		while (valid == false) {
			System.out.println("Please input your Player name\n(Note: Must be between 3 and 15 characters and no numbers or special characters");
			input = scanner.nextLine();
			valid = checkName(input);
			name = input;
			if (valid == false) {
				System.out.println("Invalid name");
			}
		}
		
	}
	public boolean checkName(String name) {
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
		for (int i = 0; i != monsters.size(); i++) {
			if (monsters.get(i).getCurrentHealth() != 0){
			menuString = String.format("(%s) %s ", i, monsters.get(i).getName()) + menuString;
			}
			else {
				menuString = String.format("(x) Downed: %s", monsters.get(i).getName()) + menuString;
			}
		}
		System.out.println("To switch monsters input the number next to your desired monster");
		System.out.println(menuString);
		boolean picked = false;
		while (picked == false) {
			input = Environment.getUserInt();
			if (input > monsters.size() - 1|| input < 0 || monsters.get(input).currentHealth == 0) {
				System.out.println(String.format("Input must be between %s and %s and you cannot select a downed monster", -1, monsters.size()));
			}
			else {
				setActiveMonster(input);
				picked = true;
			}
			
		}
		
	}
	public void addMonster(Monster creep) {
		monsters.add(creep);
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
		return monsters.get(i);
	}
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	public void setActiveMonster(int i){
		activeMonster = monsters.get(i);
	}
	public Monster getActiveMonster() {
		return activeMonster;
	}
	public String stringMonsters() {
		String strMon = "";
		if (monsters.size() == 0) {
			return "No Monsters in party";
		}
		else {
			for (int i = 0; i != monsters.size(); i++) {
				strMon = monsters.get(i).getName() + " " + strMon;
			}
			return strMon;
		}
	}
	public String toString() {
		return String.format("Name: %s\nGold: %s\nMonsters: %s", name, gold, stringMonsters());
	}
	public void sleepMon() {
		for (Monster i : monsters) {
			i.addHealth(i.getHealAmount());
			
		}
	}
}
