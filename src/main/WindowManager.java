package main;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**This class is used to start the game, only manages two windows enviWindow and mainWindow**/
public class WindowManager {
	/**An EnvironmentWindow variable, used to "open" a new instance of EnvironmentWindow**/
	EnvironmentWindow enviWindow;
	/**An MainWindow variable, used to "open" a new instance of EnvironmentWindow**/
	MainWindow mainWindow;
	
	/**An instance method, which will create a new instance of Environment**/
	public void startEnvi() {
		enviWindow = new EnvironmentWindow(this);
	}
	/**An instance method, which will close enviWindow*/
	public void closeEnvi() {
		enviWindow.closeWindow();
		startMain();
	}
	/**An instance method, which will create a new instance of MainWindow**/
	public void startMain() {
		mainWindow = new MainWindow(enviWindow.getPlayer(), enviWindow.getGameLength(), enviWindow.getHard());
		enviWindow.getFrame().dispose();
	}
	
	public static void main(String[] args) {
		WindowManager manager = new WindowManager();
		manager.startEnvi();
		
		
		
	}

}
