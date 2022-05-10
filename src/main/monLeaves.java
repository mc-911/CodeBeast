package main;

public class monLeaves extends RandomEvent{
	public void startEvent(Monster mon, Player player) {
		System.out.println("You wake up in the middle of the night to find!\n" + mon.getName() + " has left!");
		player.getMonsters().remove(mon);
	}

}
