package main;


import java.lang.reflect.Array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
/**A class which is the Game Environement which is responsible for starting the main gameplay of the game, contains methods which are utilized by other classes**/
public class Environment {
	/**a boolean variable used to determine the difficulty of the game, used to scale battles**/
	private static boolean hardmode = false;
	/**a boolean variable used to see if the game is over is only used when the user has let all their monsters fainted and the user cant buy anymore**/
	private static boolean gameOver = false;
	/**an int variable used to keep track of how long the game has gone on for, can't be greater than gameLength**/
	private int currentDay = 1;
	/**A static String variable which either contains 1 (meaning the time is morning) 2, (meaning the time is afternoon) or 3 (meaning the time is night)**/
	private static int time = 0;
	/**A String variable which is used in conjuction with String.Format() in showBattle() to show the user which battles they can fight**/
	private static String battleFormat = "%s. Enemies: %s";
	/**A static String variable which contains the max amount of days the game can go on for**/
	private static int gameLength;
	/**A String used to display to the user which monsters they can pick as their starter**/
	private static String starterString = "Time to pick your Monster\nYou have three options to pick from\n1. %s\n2. %s\n3. %s";
	/**An array of monster objects, used to have the user pick a starting monster**/
	private static Monster[] starters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	/**A format used to display to the user their current gold, the current day, and how many days they have left in the game**/
	private static String userInfoF = "Gold: %s Day: %s Days Remaining: %s, Time: %s";
	/**A string array which holds a string corresponding to the current time**/
	private static String[] times = {"Morning", "Afternoon", "Night"};
	private static EnvironmentWindow window;
	/**A method used to set gameOver to the parameter bool returns void**/
	public static void setOver(boolean bool) {
		
		gameOver = bool;
	}
	/**A method that gets the user's input as an integer makes sure that the input is between num1 and num2 (inclusive) returns an integer**/
	public static boolean  getUserIntBounds(int num1, int num2, GameWindow window, int input) {
		boolean picked;
		picked = false;
		if (input < num1|| input > num2) {
			window.printMsg(String.format("Your input must be between %s, and %s (inclusive)", num1, num2));
		}
		else {
			picked = true;
		}
		return picked;
	}
	/**A static method used to display an array of purchasable items (which will contain either monsters or items but not both) in an ordered manner to the user returns void**/
	public static void displayList(Purchasable[] p, GameWindow window) {
		int i = 0;
		for (Purchasable d : p) {
			window.printMsg(String.format("%s. %s", i, d));
			i++;
		}
		
	}
	
	/**A method which is used to see if a Random Event should occur, if one should occur it runs it, returns void**/
	public static void  randomEventCheck(Player player, GameWindow window) {
		/**A random object used to get an integer between [0, 99] used for determining which event should fire given an event should fire**/
		Random rand = new Random();
		/**A monJoins Object will be used with randList to determine the likelihood of the Monster Joins or (monJoins) event occurring given that a monster can join**/
		monJoins monJoin = new monJoins();
		/**A monLeaves Object will be used with randList to determine the likelihood of that the monLeaves event will occur given a Monster mon (basically determines the likelihood that a Monster mon will leave during the night)**/
		monLeaves monLeave = new monLeaves();
		/**A monRandLvlUp Object will be used with randList to determine the likelihood of that the monRandLvlUp event will occur given a Monster mon (basically determines the likelihood that a Monster mon will level up during the night)**/
		monRandLvlUp up = new monRandLvlUp();
		/**An variable of int data type used to store a value [0, 99] given by rand.nextInt(100), in conjuction with randList it determines if an event should occur given a specific Monster mon, and which event should occur**/
		int num;
		/**A variable of boolean data type  used to keep track of whether or not an event has**/
		boolean occured =false;
		window.printMsg("During the night...");
		/**A list of RandomEvent objects used to determine the probability that an event occurs, the amount of times a specific RandomEvent object occurs in the list is its likelihood of occurring**/
		ArrayList<RandomEvent> randList;
		/**A forEarch loop used to iterate through the user's list of monsters, and to determine for each one if an event should fire which event should fire then fires that event via calling startEvent(mon,player)**/
		for (Monster mon : player.getMonsters()) {
			/**Sets the regular probabiltiy of a monster leveling up (monRandLvlUp occuring) , or a monser leaving (monLeaves occuring)**/
			randList = new ArrayList<>(Arrays.asList(monLeave, monLeave, monLeave, monLeave, up, up, up, up, up, up, up, up));
			/**A for loop used to add monLeave to randList the amount of times this is done is the amount of days a monster has fainted in a row (given by mon.getDaysFainted()), used to increase the likelihood a specific monster leaves**/
			for (int i = mon.getdaysFainted(); i != 0; i-- ) {randList.add(monLeave);}
			/**Gets an integer [0,99] used to determine if a Random Event should occur**/
			num = rand.nextInt(100);
			/**Checks to see if num corresponds to an index in randlist is if it does it, gets the randomEvent object at that index then it proceeds to start that event via startEvent(mon,player), and sets occured to be true**/
			if (num <= randList.size() - 1) {
				randList.get(num).startEvent(mon, player, window);
				occured = true;
			}
		}
		/**Used to check if the user has the max amount of monsters if the user doesn't proceeds to determine the likelihood monJoins occurs**/
		if (player.getMonsters().size() != 4) {
			/**The regular likelihood monJoins occurs**/
			randList = new ArrayList<>(Arrays.asList(monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin));
			/**A for loop used to add up to randlist per the amount of free slots a user has for a new monsters (this is determined by gettting the 4 - the amount of monsters the user has**/
			for (int i = player.getMonsters().size(); i != 0; i--) {randList.add(monJoin);}
			num = rand.nextInt(100);
			if (num <= randList.size() - 1) {
				randList.get(num).startEvent(null, player, window);
				occured = true;
			}
		}
		/**An if statement checks if occured is false, if occured is false it will print "Nothing happaend! this should only occur when no Random Event has occured"**/
		if (!occured) {
			window.printMsg("Nothing happened!");
		}
	}
	

	/**A static method used to get an input of data type integer from the user, returns int**/
	public static boolean getUserInt(String str, GameWindow window) {
		/**A Scanner object used to get user input**/
		 /**A variable of boolean type, used to keep track of whether or not the user has inputed a valid integer**/
		 boolean selected = false;
		 /**A variable of integer data type, used to store the user's input**/
		 int input = 0;
		 /**A while loop used to get the user's input via scanner.nextLine() if the input is a valid integer it'll successfully convert the input into an integer and store it in input selected will be set to true  and the while loop will end, if not it'll throw a java.lang.NumberFormatException which will be caught and will print out an error message then the loop will continue**/ 
		 
		 try {
			 input = Integer.parseInt(str);
			 selected = true;
		 }
		 catch (java.lang.NumberFormatException e) {
	         window.printMsg("Your input must be an integer\n(i.e 1, 2, 3)");
	     }
		 return selected;
	}
	
	/**A static method used to get a String from the user's input, returns String**/
	public static String getUserString(GameWindow window) {
		String str = window.getText();
		return str;
		
		
	}

	/**A static method used to generate battles in mainGameplay, so that the user may pick a battle, takes a Player object player has its parameter, returns ArrayList<Battle>**/
	public static ArrayList<Battle> generateBattles(Player player) {
		ArrayList<Battle> battles = new ArrayList<Battle>();
		Random random = new Random();
		int num = random.nextInt(5);
		if (num < 3) {
			num = 3;
		}
		for (int i = 1; i <= num; i++) {
			battles.add(new Battle());
		}
		return battles;
	}
	/**A static method which Shows the available battles to the user, takes An ArrayList<Battle> battles as its parameter, returns void**/
	public static void showBattles(ArrayList<Battle> battles, GameWindow window) {
		for (int i = 0; i != battles.size(); i++) {
			window.printMsg(String.format(battleFormat, i, battles.get(i)));
		}
		
		
	}
	
	
	
	/**A static method used to get starters**/
	public static Monster[] getStarters() {
		return starters;
	}
	
}