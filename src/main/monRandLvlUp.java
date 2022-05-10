package main;

public class monRandLvlUp extends RandomEvent{
	public void startEvent(Monster mon, Player player) {
		System.out.println("During the night..");
		mon.levelUp();
	}

}
