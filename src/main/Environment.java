package main;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class Environment {
	//a boolean variable used to determine the difficulty of the game, used to scale battles 
	private static boolean hardmode = false;
	//a boolean variable used to see if the game is over is only used when the user has let all their monsters fainted and the user cant buy anymore
	private static boolean gameOver = false;
	//an int variable used to keep track of how long the game has gone on for, can't be greater than gameLength
	private int currentDay = 1;
	//can either be 1 (morning) 2 (afternoon) or 3 (night)
	private static int time = 0;
	//A String variable which is used in conjuction with String.Format() in showBattle() to show the user which battles they can fight
	private static String battleFormat = "%s. Enemies: %s";
	//the max amount of days the game can go on for
	private static int gameLength;
	//A String used to display to the user which monsters they can pick as their starter
	private static String starterString = "Time to pick your Monster\nYou have three options to pick from\n1. %s\n2. %s\n3. %s";
	//An array of monster objects, used to 
	private static Monster[] starters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	//A format used to display to the user their current gold, the current day, and how many days they have left in the game
	private static String userInfoF = "Gold: %s Day: %s Days Remaining: %s, Time: %s";
	//A string array which holds a string corresponding to the current time
	private static String[] times = {"Morning", "Afternoon", "Night"};
	public static void setOver(boolean bool) {
		/**Used to set gameOver to the parameter bool**/
		gameOver = bool;
	}
	public static void setGameLength() {
		/**Has the user input their desired game length, doesn't allow any invalid inputs**/
		String length;
		Scanner scanner1 = new Scanner(System.in);
		System.out.println("Please input game length\ngame length can be between 5 and 15 days");
		try {
		gameLength =Integer.parseInt(scanner1.nextLine());
		if (gameLength < 6 || gameLength > 14) {
			throw new IllegalStateException();
		}
		
		}
		catch (java.lang.NumberFormatException e) {
			System.out.println("Your input must be an integer\n(i.e 10, 56, 28)");
			setGameLength();
			
		}
		catch (IllegalStateException e) {
			System.out.println("Your input must be between 5 and 15");
			setGameLength();
		}
	
		
	}
	public static void pickMonsters(Player player) {
		/**Shows the user a list of monsters so that the user may choose one as their starting  monster**/
		List<Monster> starterslist = Arrays.asList(starters);
        Collections.shuffle(starterslist);
        int selected = 0;
        boolean picked = false;
        int input;
        System.out.println(String.format(starterString, starterslist.get(0), starterslist.get(1), starterslist.get(2)));
        System.out.println("Input the number next your choosen monster to pick it!\n(Your input must be an integer)");
        while (picked == false) {
        	input = getUserInt();
        	if (!(input >= 1 && input <= 3)) {
        		System.out.println("Input must be either 1, 2, or 3");
        	}
        	else {
        		selected = input - 1;
        		picked = true;
        	}
        }
        addMon(player, starterslist.get(selected));
       
        
		
		
	}
	public static void selectDifficulty() {
		/**Has the user choose the difficulty of the game**/
		System.out.println("Select your difficulty\nInput 0 for normal mode or input 1 for hardmode");
		int input = getUserInt();
		if (input == 0 || input == 1) {
			if (input == 1) {
				hardmode = true;
			}
			
		}
		else {
			System.out.println("Input must be either 0 or 1");
			selectDifficulty();
		}

		
	}
	public static void addMon(Player player, Monster monster) {
		System.out.println("Time to name your Monster\nInput your monsters name\n(Note: If you input nothing your monster will be given the default name");
        String name = getUserString();
        if (name.length() == 0) {
        	player.addMonster(monster);
        	System.out.println("worked");
        }
        else {
        	monster.setName(name);
        	player.addMonster(monster);
        }
        
		
	}
	public static int getUserInt() {
		/**Used to get an integer from the user input**/
		 Scanner scanner = new Scanner(System.in);
		 boolean selected = false;
		 int input = 0;
		 while (selected == false) {
			 try {
				 input = Integer.parseInt(scanner.nextLine());
				 selected = true;
			 }
			 catch (java.lang.NumberFormatException e) {
		         System.out.println("Your input must be an integer\n(i.e 1, 2, 3)");
		     }
		 }
		 return input;
	}
	public static String getUserString() {
		/**Used to get a String from the user's input**/
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
		
	}
	public static ArrayList<Battle> generateBattles(Player player) {
		/**Used to generate battles in mainGameplay, so that the user may pick a battle**/
		ArrayList<Battle> battles = new ArrayList<Battle>();
		Random random = new Random();
		int num = random.nextInt(5);
		if (num < 3) {
			num = 3;
		}
		for (int i = 1; i <= num; i++) {
			battles.add(new Battle(player, time, hardmode, i));
		}
		return battles;
	}
	public static void showBattles(ArrayList<Battle> battles) {
		/**Shows the available battles to the user**/
		for (int i = 0; i != battles.size(); i++) {
			System.out.println(String.format(battleFormat, i, battles.get(i)));
		}
		
	}
	public static Battle chooseBattle(ArrayList<Battle> battles) {
		/**Has the user choose a battle to start**/
		System.out.println("Pick your battle");
		int input = 0;
		boolean picked = false;
		while (picked == false) {
			input = getUserInt();
			if (input < 0 || input > battles.size() - 1) {
				System.out.println(String.format("Input must be between -1 and %s", battles.size()));
			}
			else {
				if (battles.get(input).getDone() == true) {
					System.out.println("You can't select a battle you've already done!");
				}
				else {
					picked = true;
				}
				
			}
		}
		return battles.get(input);
	}
	public static void mainGameplay(Player player) {
		/**Starts the main game play loop**/
		int input = 0;
		Battle fight;
		boolean over = false;
		System.out.println("Starting Main gameplay...");
		for (int i = 1; i != gameLength && over == false; i++) {
			while (time != 3) {
				//main game play
				ArrayList<Battle> battles = generateBattles(player);
				System.out.println(String.format(userInfoF, player.getGold(), i, gameLength - i, Array.get(times, time) + "\n"));
				System.out.println("Input 0 to view current battles\nInput 1 to go to the shop\nInput 2 to pass the time\nInput 3 to open your monster menu");
				boolean picked = false;
				while (picked == false) {
				input = getUserInt();
				if (input < 0|| input >3) {
					System.out.println("Invalid Input");
				}
				else {
					picked = true;
				}
				}
				switch (input) {
				case 0:
					showBattles(battles);
					Battle battle = chooseBattle(battles);
					battle.startBattle(player, time, hardmode, battles.indexOf(battle));
					time++;
					break;
				case 2:
					time++;
					break;
				case 3:
					player.monsterMenu();
				}
				
				
					
				
			}
			System.out.println("Sleep time");
			monJoins eve = new monJoins();
			eve.startEvent(player);
			player.sleepMon();
			time = 0;
			
		}
		
		
	}
	public static Monster[] getStarters() {
		return starters;
	}

	public static void main(String[] args) {
		/**Starts the game**/
		Player player = new Player();
		player.setName();
		setGameLength();
		pickMonsters(player);
		selectDifficulty();
		mainGameplay(player);
		
		
		
		
		
	
		
		
		
	}

}
