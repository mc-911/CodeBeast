package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WindowManager {
	EnvironmentWindow enviWindow;
	
	private static Monster[] starters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	public void startEnvi() {
		enviWindow = new EnvironmentWindow();
	}
	public void startMain() {
		mainWindow
	}
	public void pickMonsters(Player player) {
		List<Monster> starterslist = Arrays.asList(starters);
        Collections.shuffle(starterslist);
        enviWindow.printMsg(String.format("Time to pick your Monster\nYou have three options to pick from\n1. %s\n2. %s\n3. %s", starterslist.get(0), starterslist.get(1), starterslist.get(2)));
        enviWindow.printMsg("Input the number next your choosen monster to pick it!\n(Your input must be an integer)");
	}
	public static void main(String[] args) {
		WindowManager manager = new WindowManager();
		manager.startEnvi();
		
		
		
	}

}
