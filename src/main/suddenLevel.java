package main;

import java.util.*;
public class suddenLevel extends RandomEvent {
	/**An event where the level of one of the player's monsters increases**/
	public void startEvent(Monster mon, Player player) {
		Random random = new Random();
		int picked_i = random.nextInt(player.getMonsters().size());
		Environment.printMsg("You check on one of your monsters during the night...");
		player.getMonster(picked_i).levelUp();
		
		
	}
	

}
