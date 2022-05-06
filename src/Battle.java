

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Battle {
	ArrayList<Monster> foeMonsters = new ArrayList<Monster>();
	static Monster[] monArray = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	static String choiceMenu = "Input 0 to attack using %s\nInput 1 to inspect the foe\nInput 2 to open the Monster Menu to switch your active Monster";
	int activeMonster = 0;
	Monster leading;
	Monster active;
	boolean playerLost = false;
	//if done is true then the user has already done this battle
	boolean done;
	
	public void genMonsters() {
		Random random = new Random();
		for (int i = 0; i != 5; i++) {
			foeMonsters.add((Monster) Array.get(monArray, random.nextInt(5)));
		}
		
	}
	public void scaleMonsters(int time, boolean hard, int rank) {
		for (int i = 0; i != foeMonsters.size(); i++) {
			if (hard == true){
				foeMonsters.get(i).setHardFoe();
			}
			foeMonsters.get(i).setCurrentHealth(foeMonsters.get(i).getCurrentHealth() * 1 + (int) time / 2 * rank / 2);
			foeMonsters.get(i).setMaxHealth(foeMonsters.get(i).getCurrentHealth() * 1 + (int) time / 2 * rank / 2);
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
			monDead = false;
			while (monDead == false && playerLost == false) {
				turnOver = false;
				while (turnOver == false && playerLost == false) {
					System.out.println(String.format("A evil looking monster named %s is preparing to attack!", leading.getName()));
					System.out.println(String.format(choiceMenu, active.getName()));
					System.out.println(String.format("%s HP: %s/%s DMG: %s\n%s HP: %s/%s DMG: %s", leading.getName(), leading.getCurrentHealth(),leading.getMaxHealth(),leading.getDamage(), active.getName(),active.getCurrentHealth(),active.getMaxHealth(),active.getDamage()));
					
					inputValid = false;
					while (inputValid == false) {
						input = Environment.getUserInt();
						if (input < 0 || input > 4) {
							System.out.println("Input must either be 0, 1, or 2");
						}
						else {
							inputValid = true;
						}
					}
					switch (input) {
					case 0:
						player.getMonster(activeMonster).attack(foeMonsters.get(i));
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
				if (leading.currentHealth == 0) {
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
					player.monsterMenu();
					active = player.getActiveMonster();					
					
				}
			
			}
			if (playerLost == true) {
				System.out.println("You've lost this battle...");
				break;
			}
			
		}
		if (playerLost == false) {
			//an integer which is determined by hard, used to scale gold earned and the points gained for each monster
			System.out.println("You Win!!");
			int mult = 1;
			if (hard) {
				mult = 5;
			}
			for (Monster mon : player.getMonsters()) {
				mon.increasePoints(5 * time * rank * mult);
			}
			player.setGold(player.getGold() + 5 * time * rank * mult);
			System.out.println(String.format("You've gained %s gold", 5 * time * rank * mult));
		}
		
	}
	public boolean getDone() {
		return done;
	}
	public Battle(Player player, int time, boolean hard, int rank) {
		genMonsters();
		scaleMonsters(time, hard, rank);

		
	}

}
