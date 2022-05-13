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
	public static int getUserIntBounds(int num1, int num2) {
		
		int input = 0;
		boolean picked;
		picked = false;
		while (picked == false) {
			input = Environment.getUserInt();
			if (input < num1|| input > num2) {
				printMsg(String.format("Your input must be between %s, and %s (inclusive)", num1, num2));
			}
			else {
				picked = true;
			}
		}
		return input;
	}
	/**A static method used to display an array of purchasable items (which will contain either monsters or items but not both) in an ordered manner to the user returns void**/
	public static void displayList(Purchasable[] p) {
		int i = 0;
		for (Purchasable d : p) {
			printMsg(String.format("%s. %s", i, d));
			i++;
		}
		
	}
	/**A static method used to get the user to input their desired game length, doesn't allow any invalid inputs, returns void**/
	public static void setGameLength() {
		
		String length;
		printMsg("Please input game length\ngame length can be between 5 and 15 days");
		gameLength = getUserIntBounds(6, 14);
		
	
		
	}
	/**A static method which shows the user a list of monsters so that the user may choose one as their starting  monster returns void**/
	public static void pickMonsters(Player player) {
		List<Monster> starterslist = Arrays.asList(starters);
        Collections.shuffle(starterslist);
        int selected = 0;
        boolean picked = false;
        int input;
        printMsg(String.format(starterString, starterslist.get(0), starterslist.get(1), starterslist.get(2)));
        printMsg("Input the number next your choosen monster to pick it!\n(Your input must be an integer)");
        while (picked == false) {
        	input = getUserInt();
        	if (!(input >= 1 && input <= 3)) {
        		printMsg("Input must be either 1, 2, or 3");
        	}
        	else {
        		selected = input - 1;
        		picked = true;
        	}
        }
        addMon(player, starterslist.get(selected));
       
        
		
		
	}
	/**A method which is used to see if a Random Event should occur, if one should occur it runs it, returns void**/
	public static void  randomEventCheck(Player player) {
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
		printMsg("During the night...");
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
				randList.get(num).startEvent(mon, player);
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
				randList.get(num).startEvent(null, player);
				occured = true;
			}
		}
		/**An if statement checks if occured is false, if occured is false it will print "Nothing happaend! this should only occur when no Random Event has occured"**/
		if (!occured) {
			printMsg("Nothing happened!");
		}
	}
	/**A method that is used to have the user choose the difficulty of the game returns void**/
	public static void selectDifficulty() {
		printMsg("Select your difficulty\nInput 0 for normal mode or input 1 for hardmode");
		/**A variable of data type int which holds the user's input, used to determine the state of hardmode (the difficulty of the game)**/
		int input = getUserInt();
		/**An if statement used to check if the user's input is valid if it's not goes to the else statement and calls selectDifficulty, if it is valid goes to the inner if statement and checks if input is 1 if it is it'll proceed to set hardmode to true**/
		if (input == 0 || input == 1) {
			if (input == 1) {
				hardmode = true;
			}
			
		}
		else {
			printMsg("Input must be either 0 or 1");
			selectDifficulty();
		}

		
	}
	/**A static method used to add a Monster object monster to the user's list of monsters, takes a Player object Player player and, a Monster object Monster monster as its parameters, returns void **/
	public static void addMon(Player player, Monster monster) {
		printMsg("Time to name your Monster\nInput your monsters name\n(Note: If you input nothing your monster will be given the default name");
		/**A variable of String datatype used to store the name that the user wishes to call monster**/
        String name = getUserString();
        if (name.length() == 0) {
        	player.addMonster(monster);
        	printMsg("worked");
        }
        else {
        	monster.setName(name);
        	player.addMonster(monster);
        }
        
		
	}
	/**A static method used to get an input of data type integer from the user, returns int**/
	public static int getUserInt() {
		/**A Scanner object used to get user input**/
		 /**A variable of boolean type, used to keep track of whether or not the user has inputed a valid integer**/
		 boolean selected = false;
		 /**A variable of integer data type, used to store the user's input**/
		 int input = 0;
		 /**A while loop used to get the user's input via scanner.nextLine() if the input is a valid integer it'll successfully convert the input into an integer and store it in input selected will be set to true  and the while loop will end, if not it'll throw a java.lang.NumberFormatException which will be caught and will print out an error message then the loop will continue**/ 
		 while (selected == false) {
			 try {
				 input = Integer.parseInt(getUserString());
				 selected = true;
			 }
			 catch (java.lang.NumberFormatException e) {
		         printMsg("Your input must be an integer\n(i.e 1, 2, 3)");
		     }
		 }
		 return input;
	}
	
	/**A static method used to get a String from the user's input, returns String**/
	public static String getUserString() {
		boolean pressed = false;
		while (pressed == false) {
			System.out.println("");
			pressed = window.getButtonPressed();
		}
		String str = window.getText();
		printMsg(str);
		window.setText("");
		window.setButtionPressed(false);
		return str;
		
		
	}
	/**A static method used to print a msg in a windows textPane, takes a String parameter str as its parameter, returns void**/
	public static void printMsg(String str) {
		window.setTextPane(window.getTextPane() + "\n" + str);
		if (window.getTextPane().split("\r\n|\r|\n").length >= 30) {
			window.setTextPane(str);
		}
		
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
			battles.add(new Battle(player));
		}
		return battles;
	}
	/**A static method which Shows the available battles to the user, takes An ArrayList<Battle> battles as its parameter, returns void**/
	public static void showBattles(ArrayList<Battle> battles) {
		
		for (int i = 0; i != battles.size(); i++) {
			printMsg(String.format(battleFormat, i, battles.get(i)));
		}
		
	}
	/**A static method that Has the user choose a battle to start, takes ArrayList<Battle> battles as its parameter, returns void**/
	public static Battle chooseBattle(ArrayList<Battle> battles) {
		printMsg("Pick your battle");
		int input = 0;
		boolean picked = false;
		while (picked == false) {
			input = getUserIntBounds(0, battles.size());
			if (battles.get(input).getDone() == true) {
				printMsg("You can't select a battle you've already done!");
			}
			else {
				picked = true;
			}
				
			
		}
		return battles.get(input);
	} 
	/**A static method that starts the main game play loop, takes Player player as its parameter, returns void**/
	public static void mainGameplay(Player player) {
		int input = 0;
		Battle fight;
		boolean over = false;
		int day = 1;
		printMsg("Starting Main gameplay...");
		for (int i = 1; i != gameLength && over == false; i++) {
			while (time != 3 && !(player.getMonsters().size() == 0 && player.getGold() < 10)) {
				//main game play
				ArrayList<Battle> battles = generateBattles(player);
				printMsg(String.format(userInfoF, player.getGold(), i, gameLength - i, Array.get(times, time) + "\n"));
				printMsg("Input 0 to view current battles\nInput 1 to go to the shop\nInput 2 to pass the time\nInput 3 to open your monster menu\nInput 4 to view your inventory\nInput 5 to view yourself.");
				input = getUserIntBounds(0, 5);
				switch (input) {
				case 0:
					showBattles(battles);
					Battle battle = chooseBattle(battles);
					battle.startBattle(player, time, hardmode, battles.indexOf(battle));
					time++;
					break;
				case 1:
					Shop shop = new Shop();
					shop.shopMenu(player);
					break;
				case 2:
					time++;
					break;
				case 3:
					player.monsterMenu();
					break;
				case 4:
					player.getInventory();
					break;
				case 5:
					printMsg(player.toString());
					break;
				}
			}
			day++;
			printMsg("Sleep time");
			randomEventCheck(player);
			player.sleepMon();
			time = 0;
		}
		
		gameOver(player, day);
		
		
	}
	/**A static method, is used to end the game, takes Player player, and int day as its parameters, returns void**/
	public static void gameOver(Player player, int day) {
		printMsg("GAME OVER");
		printMsg(String.format("Name: %s\nGame Duration: %s/%s days\nGold: %s\nPoints: %s", player.getName(), day, gameLength, player.getGold(), player.getPoints()));
	}
	/**A static method used to get starters**/
	public static Monster[] getStarters() {
		return starters;
	}
	/**A main method it starts the game**/
	public static void main(String[] args) {
		window = new EnvironmentWindow();
		window.showWindow();
		Player player = new Player();
		player.setName();
		setGameLength();
		pickMonsters(player);
		selectDifficulty();
		mainGameplay(player);
			
		
		
		
	
		
		
		
	}

}