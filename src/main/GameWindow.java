package main;

import javax.swing.JFrame;
import javax.swing.JTextPane;

/**An abstract class which every class that creates a Window using Java Swing will extend, contains methods reused by each of this subclasses**/
public abstract class GameWindow {
	/**A JFrame, used to hold components to make the window work**/
	private JFrame frame = new JFrame();
	/**An WindowManager variable, used to hold the instance of WindowManager that created this instance of GameWindow**/
	private WindowManager manager;
	/**An JTextPane variable, used to contain a JTextPane used to display information to the user**/
	private JTextPane inputHere;
	
	/**An instance method, used to close this window (set this instance of GameWindow to be visible), returns voids**/
	public void closeWindow() {
		getFrame().setVisible(false);
	}
	/**An instance method, used to display a String in this window, takes a String parameter str as its parameter, returns void**/
	public void printMsg(String str) {
		this.setTextPane(getTextPane() + "\n" + str);
		if (getTextPane().split("\r\n|\r|\n").length >= 35) {
			this.setTextPane(str);
		}
	}
	/**An instance method, used to get frame, returns JFrame**/
	public JFrame getFrame() {
		return frame;
	}
	/**An instance method, sets frame to be the JFrame parameter frame, returns frame**/
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	/**An instance method used to get the text from inputHere, returns String**/
	public String getTextPane() {
		return getInputhere().getText();
	}
	/**An instance method, which shows this window, returns void**/
	public void showWindow() {
		this.getFrame().setVisible(true);
		
	}
	/**An instance method used to set the text shown in textPane,takes a String parameter text, returns void**/
	public void setTextPane(String text) {
		getInputhere().setText(text);
	}
	public JTextPane getInputhere() {
		return inputHere;
	}
	public void setInputhere(JTextPane inputhere) {
		this.inputHere = inputhere;
	}
	/**An instance method, used to set the set in textField, takes a String parameter str as its parameter, returns void**/

	public void setText(String str) {
		inputHere.setText(str);
	
	}
	/**An instance method, used to get the text in textField, returns String**/

	public String getText() {
		return inputHere.getText();
	}
	/**An instance method, used to call closeEnvi from the instance of WindowManager in manager, returns void**/
	public void exit() {
		manager.closeEnvi();
	}
	/**An instance method, returns manager, returns WindowManager**/
	public WindowManager getManager() {
		return manager;
	}
	/**An instance method, used to set manager to be the WindowManager parameter manager, returns void**/
	public void setManager(WindowManager manager) {
		this.manager = manager;
	}
	/**An instance method which returns this instance of GameWindow, returns GameWindow**/
	public GameWindow getSelf() {
		return this;
	}
	/**An instance method, which is used to set this window to be visible, returns void**/
	public void show() {
		frame.setVisible(true);
	}
	/**A method used to get an input of data type integer from the user, returns int**/
	public  boolean checkInt(String str) {
		/**A Scanner object used to get user input**/
		 /**A variable of boolean type, used to keep track of whether or not the user has inputed a valid integer**/
		 boolean selected = false;
		 /**A variable of integer data type, used to store the user's input**/
		 int input = 0;
		 /**A while loop used to get the user's input via scanner.nextLine() if the input is a valid integer it'll successfully convert the input into an integer and store it in input selected will be set to true  and the while loop will end, if not it'll throw a java.lang.NumberFormatException which will be caught and will print out an error message then the loop will continue**/ 
		 
		 try {
			 input = Integer.parseInt(str);
			 selected = true;
		 }
		 catch (java.lang.NumberFormatException e) {
			 getSelf().printMsg("Your input must be an integer\n(i.e 1, 2, 3)");
	     }
		 return selected;
	}
	/**A method that gets the user's input as an integer makes sure that the input is between num1 and num2 (inclusive) returns an integer**/
	public boolean checkInt(String in, int num1, int num2) {
		int input = Integer.parseInt(in);
		boolean picked;
		picked = false;
		if (input < num1|| input > num2) {
			getSelf().printMsg(String.format("Your input must be between %s, and %s (inclusive)", num1, num2));
		}
		else {
			picked = true;
		}
		return picked;
	}

}
