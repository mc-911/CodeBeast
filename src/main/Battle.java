package main;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
/**An instance of this class is used to have the user battle**/
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
	/**An instance method which starts the actual fight of this instance of Battle, takes a Player object player, an int variable time, a boolean variable hard  an int variable rank as it's parameters, returns void**/
	public void startBattle(Player player, int time, boolean hard, int rank) {
		/**A variable of int datatype points to an int, which is used (in conjunction with time, rank, hard) to calculate the following the how much damage an enemy monster does, the amount of gold the user earns once the user wins the battle, the amount of points the user gets once they win, the amount of points each of the user's monsters get when the user wins the battle**/
		int multe = 1;
		/**An if statement that checks if the games difficulty is hard (if hard is equal to true), if it is it proceeds to set multe to contain 2, stuff listed in the comment of multe but by even more if the difficulty of the game is hard**/
		if (hard) {
			multe = 2;
		}
		/**A int variable contains an integer which is bonus damage an enemy monster gets, its value is determined via a formula**/
		int dmgIncrease = 5 * (rank + 1) * multe * (time + 1);
		System.out.println("Battle Started");
		/**A Monster variable used to contain an instance of Monster which will be the enemy monster the user is currently fighting**/
		Monster leading;
		/**A boolean variable used to contain a boolean, is used to determine if the user's turn is over during battling**/
		boolean turnOver;
		/**A boolean variable, used to contain a boolean, which is used to determine if the monster the user is currently fighting (aka leading) has no currrent health (i.e current health is 0 meaning that the mosnter has fainted/been downed**/
		boolean monDead = false;
		/**A integer variable, used to contain the users input when the user is picking a choice during the battling**/
		int input = 0;
		player.setActiveMonster(0);
		player.nextMon();
		active = player.getActiveMonster();
		System.out.println("Battle Started");
		/**A for loop used to start the main loop of the battle where we will set leading to contain the monster at index i in foeMonsters, then it will start a while loop which will contain the main loop of this battling system, where the user has several options to choose from, given that the user defeats the monster in leading the next iteration of the for loop will occur which'll set leading to contain the next monster in foeMonsters**/
		for (int i =0; i != foeMonsters.size(); i++) {
			leading = foeMonsters.get(i);
			leading.setDamageAmount(leading.getDamage() + (int) dmgIncrease);
			monDead = false;
			/**A while loop which is essentially main loop of this battling system it goes like this, user picks an option --> enemy monster attacks the users active monster then it loops**/
			while (monDead == false && playerLost == false) {
				turnOver = false;
				/**A while loop that allows a user to pick from several different options, if that option costs time the loop will end, and if the user loses it will end aswell, if the option doesn't cost time the loop will simply continue on to the next iteration**/
				while (turnOver == false && playerLost == false) {
					System.out.println(String.format("A evil looking monster named %s is preparing to attack!", leading.getName()));
					System.out.println(String.format(choiceMenu, active.getName()));
					System.out.println(String.format("%s HP: %s/%s DMG: %s\n%s HP: %s/%s DMG: %s", leading.getName(), leading.getCurrentHealth(),leading.getMaxHealth(),leading.getDamage(), active.getName(),active.getCurrentHealth(),active.getMaxHealth(),active.getDamage()));
					input = Environment.getUserIntBounds(0, 2);
					/**A switch case used to determine what to do with the users input (which option the user picked)**/
					switch (input) {
					case 0:
						active.attack(leading);
						System.out.println(String.format("%s dealt %s damage to %s", active.getName(), active.getDamage(), leading.getName()));
						turnOver = true;
						break;
					case 1:
						System.out.println(leading);
						break;
					
					case 2:
						player.monsterMenu();
						active = player.getActiveMonster();
						turnOver = true;
						break;
					}
				
				}
				/**An if statement used to check if the enemy monster has fainted (if its current health is equal to zero), if it hasn't the monster will attack the users active monster**/
				if (leading.getCurrentHealth() == 0) {
					System.out.println(leading.getName() + " is down!");
					monDead = true;
				}
				else {
					leading.attack(active);
					System.out.println(String.format("%s dealt %s damage to %s", leading.getName(), leading.getDamage(), active.getName()));
					
				}
				/**Checks if the users active monster has fainted (if its health is equal to zero), its used to increment daysFainted of the users active monster by one, set it's faintedToday to true, it'll then see if all of the players monsters are down (if they all have current health equal to zero) if this is true, the user has lost this battle, if not it will set the user's active monster to now be the next monster in the user's party that is not down**/
				if (active.getCurrentHealth() == 0) {
					System.out.println(String.format("%s is out!", active.getName()));
					active.setDaysFainted(active.getdaysFainted() + 1);
					active.setFaintedToday(true);
					if (this.checkAllDowned(player)) {
						playerLost = true;
						break;
					}
					player.nextMon();
					active = player.getActiveMonster();
									
					
				}
			
			}
			/**Checks if the user lost this battle, will then print out a message if thats the case, and break the for loop thus ending the battle**/
			if (playerLost == true) {
				System.out.println("You've lost this battle...");
				break;
			}
			
		}
		/**Checks if the user has won the battle, if this is the case it'll then perform several functions in order to increase the points of every monster the user has, increase the points of the user, and increase the gold of the user**/
		if (playerLost == false) {
			System.out.println("You Win!!");
			
			int mult = 1;
			if (hard) {
				mult = 5;
			}
			for (Monster mon : player.getMonsters()) {
				mon.increasePoints(5 * (time +1) * (rank + 1) * mult);
			}
			player.setGold(player.getGold() + 5 * (time +1) * (rank + 1) * mult);
			System.out.println(String.format("You've gained %s gold", 5 * time * rank * mult));
		}
		
	}
	/**An instance method returns done, used to check if this instance of Battle has already been fought, returns boolean**/
	public boolean getDone() {
		return done;
	}
	
	/**A constructor takes a Player variable player as its parameter, will then run genMonsters()**/
	public Battle(Player player) {
		genMonsters();


		
	}

}
