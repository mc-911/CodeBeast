package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WindowManager {
	EnvironmentWindow enviWindow;
	MainWindow mainWindow;
	
	private static Monster[] starters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	public void startEnvi() {
		enviWindow = new EnvironmentWindow(this);
	}
	public void closeEnvi() {
		enviWindow.closeWindow();
		startMain();
	}
	public void startMain() {
		mainWindow = new MainWindow(enviWindow.getPlayer(), enviWindow.getGameLength(), enviWindow.getHard());
		enviWindow.getFrame().dispose();
	}
	
	public static void main(String[] args) {
		WindowManager manager = new WindowManager();
		manager.startEnvi();
		
		
		
	}

}
