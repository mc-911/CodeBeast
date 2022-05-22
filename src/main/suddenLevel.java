package main;

import java.util.*;
/**A subclass of RandomEvent, an instance of this class is a "Random Event**/
public class suddenLevel extends RandomEvent {
	/**An instance method, used to start this random event, takes a Monster variable mon and a Player variable player, and a GameWindow variable window as its parameters, returns void**/
	public void startEvent(Monster mon, Player player, GameWindow window) {
		window.printMsg("You check on one of your monsters during the night...");
		mon.levelUp(window);
		
		
	}
	

}
