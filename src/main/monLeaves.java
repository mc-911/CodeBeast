package main;
/**A subclass of RandomEvent, an instance of this class is a "Random Event**/
public class monLeaves extends RandomEvent{
	/**An instance method, used to start this random event, takes a Monster variable mon and a Player variable player as its parameters, returns void**/
	public void startEvent(Monster mon, Player player) {
		Environment.printMsg("You wake up in the middle of the night to find!\n" + mon.getName() + " has left!");
		player.getMonsters().remove(mon);
	}

}
