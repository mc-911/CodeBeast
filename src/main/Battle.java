package main;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Battle {
	ArrayList<Monster> foeMonsters = new ArrayList<Monster>();
	static String choiceMenu = "Input 0 to attack using %s\nInput 1 to inspect the foe\nInput 2 to open the Monster Menu to switch your active Monster";
	int activeMonster = 0;
	Monster leading;
	Monster active;
	boolean playerLost = false;
	//if done is true then the user has already done this battle
	boolean done;
	
	public void genMonsters() {
		Random random = new Random();
		int num;
		for (int i = 0; i != 5; i++) {
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

	public String toString() {
		String monNames = "";
		for (int i = 0; i != foeMonsters.size(); i++) {
			monNames = foeMonsters.get(i).getName() + " " + monNames;
		}
		return monNames;
	}
	public boolean checkAllDowned(Player player) {
		/**Checks to see if all of the monsters in the Player's party are downed (if their health is equal to zero)**/
		for (Monster mon : player.getMonsters()) {
			if (mon.getCurrentHealth() != 0) {
				return false;
			}
		}
		return true;
		
	}
	public void startBattle(Player player, int time, boolean hard, int rank) {
		int multe = 1;
		if (hard) {
			multe = 2;
		}
		System.out.println("Time: " + time + " rank: " + rank);
		int dmgIncrease = 5 * (rank + 1) * multe * (time + 1);
		System.out.println("Battle Started");
		Monster leading;
		boolean turnOver;
		boolean monDead = false;
		int input = 0;
		boolean inputValid;
		player.setActiveMonster(0);
		active = player.getActiveMonster();
		System.out.println("Battle Started");
		for (int i =0; i != foeMonsters.size(); i++) {
			leading = foeMonsters.get(i);
			leading.setDamageAmount(leading.getDamage() + (int) dmgIncrease);
			monDead = false;
			while (monDead == false && playerLost == false) {
				turnOver = false;
				while (turnOver == false && playerLost == false) {
					System.out.println(String.format("A evil looking monster named %s is preparing to attack!", leading.getName()));
					System.out.println(String.format(choiceMenu, active.getName()));
					System.out.println(String.format("%s HP: %s/%s DMG: %s\n%s HP: %s/%s DMG: %s", leading.getName(), leading.getCurrentHealth(),leading.getMaxHealth(),leading.getDamage(), active.getName(),active.getCurrentHealth(),active.getMaxHealth(),active.getDamage()));
					input = Environment.getUserIntBounds(0, 2);
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
				if (leading.getCurrentHealth() == 0) {
					System.out.println(leading.getName() + " is down!");
					monDead = true;
				}
				else {
					leading.attack(active);
					System.out.println(String.format("%s dealt %s damage to %s", leading.getName(), leading.getDamage(), active.getName()));
					
				}
				if (active.getCurrentHealth() == 0) {
					System.out.println(String.format("%s is out!", active.getName()));
					if (this.checkAllDowned(player)) {
						playerLost = true;
						break;
					}
					player.nextMon();
					active = player.getActiveMonster();
									
					
				}
			
			}
			if (playerLost == true) {
				System.out.println("You've lost this battle...");
				break;
			}
			
		}
		if (playerLost == false) {
			System.out.println("You Win!!");
			//an integer which is determined by hard, used to scale gold earned and the points gained for each monster
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
	public boolean getDone() {
		return done;
	}
	public Battle(Player player) {
		genMonsters();


		
	}

}
