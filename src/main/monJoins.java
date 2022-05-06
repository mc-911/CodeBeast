package main;
import java.lang.reflect.Array;
import java.util.*;
public class monJoins extends RandomEvent{
	public void startEvent(Player player) {
		Random rand = new Random();
		int i = rand.nextInt(5);
		Monster mon = (Monster) Array.get(Environment.getStarters(), i);
		Environment.addMon(player, mon);
	}
	

}
