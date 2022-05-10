package main;
import java.lang.reflect.Array;
import java.util.*;
public class monJoins extends RandomEvent{
	public void startEvent(Monster mon, Player player) {
		Random rand = new Random();
		int i = rand.nextInt(5);
		Monster mone = (Monster) Array.get(new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()}, i);
		System.out.println(String.format("A monster named %s joins your party!", mone.getName()));
		Environment.addMon(player, mone);
	}
	

}
