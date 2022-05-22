package main;



import java.util.ArrayList;
import java.util.Random;
/**An instance of this class is used to have the user battle, can also be thought of as a battle**/
public class Battle {
	/**An ArrayList<Monster> object which contains the monsters that the user will be battling against in this battle**/
	ArrayList<Monster> foeMonsters = new ArrayList<Monster>();
	/**A static variable of data type String which holds the message holding what the user can do in battle**/
	static String choiceMenu = "Input 0 to attack using %s\nInput 1 to inspect the foe\nInput 2 to open the Monster Menu to switch your active Monster";
	/**A Monster variable which points to the current monster the user is fighting**/
	Monster leading;
	/**A Monster variable which points to the monster the user is currently using**/
	Monster active;
	/**A variable of boolean data type which is used to determine if the user has lost this battle, is initialized as false**/
	boolean playerLost = false;
	/**A variable of boolean data type is used to see if the user has already fought this battle**/
	boolean done;
	/**A method which is used to generate the monsters in foeMonsters that the user will fight against in this battle**/
	BattleWindow window;
	public void genMonsters() {
		/**A variable of data type Random which points to a new Random object, it's to get a random integer [0,4]**/
		Random random = new Random();
		/**A variable of int data type used to point to a random int in range [0,4]**/
		int num;
		/**A for loop which is used to add a random monster to foeMonsters**/
		for (int i = 0; i != 5; i++) {
			/**Gets an integer in range [0,4] used in conjunction with the switch cases below to determine which monster to add to foeMonsters**/
			num = random.nextInt(5);
			switch (num) {
			case 0:
				foeMonsters.add(new Jerry());
				break;
			case 1:
				foeMonsters.add(new Flame());
				break;
			case 2:
				foeMonsters.add(new Parihaka());
				break;
			case 3:
				foeMonsters.add(new Vesuvius());
				break;
			case 4:
				foeMonsters.add(new Everest());
			}
			
		}
		
	}
	/**A toString method returns a textual representation of the battle which is the names of the monsters in foeMonsters, returns String**/
	public String toString() {
		String monNames = "";
		for (int i = 0; i != foeMonsters.size(); i++) {
			monNames = foeMonsters.get(i).getName() + " " + monNames;
		}
		return monNames;
	}
	/**An instance method which checks if all of the user's monsters have been downed, returns boolean**/
	public boolean checkAllDowned(Player player) {
		for (Monster mon : player.getMonsters()) {
			if (mon.getCurrentHealth() != 0) {
				return false;
			}
		}
		return true;
		
	}
	
	/**An instance method returns done, used to check if this instance of Battle has already been fought, returns boolean**/
	public boolean getDone() {
		return done;
	}
	/**An instance method, used to get the monster that the user will be fighting against, returns Monster**/
	public Monster getLeading() {
		Monster mon;
		mon = foeMonsters.get(0);
		for (Monster i: foeMonsters) {
			if (i.getCurrentHealth() != 0) {
				mon = i;
				break;
			}
		}
		return mon;
	}
	/**An instance method, gets the list of monsters that the user will be fighting against, returns ArrayList<Monster>**/
	public ArrayList<Monster> getFoeMonsters(){
		return foeMonsters;
	}
	/**An instance method, sets done to be the boolean parameter bool, returns void**/
	public void setDone(boolean bool) {
		done = bool;
	}
	
	/**A constructor that will run genMonsters()**/
	public Battle() {
		genMonsters();


		
	}

}
