package main;

import java.util.*;
public class suddenLevel extends RandomEvent {
	/**An event where the level of one of the player's monsters increases**/
	public void startEvent(Monster mon, Player player, GameWindow window) {
		window.printMsg("You check on one of your monsters during the night...");
		mon.levelUp(window);
		
		
	}
	

}
