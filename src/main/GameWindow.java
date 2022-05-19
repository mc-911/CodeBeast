package main;

import javax.swing.JFrame;
import javax.swing.JTextPane;

public abstract class GameWindow {
	private JFrame frame = new JFrame();
	private WindowManager manager;
	private JTextPane inputHere;
	
	public void closeWindow() {
		getFrame().setVisible(false);
	}
	public  void printMsg(String str) {
		this.setTextPane(getTextPane() + "\n" + str);
		if (getTextPane().split("\r\n|\r|\n").length >= 35) {
			this.setTextPane(str);
		}
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
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
	public void exit() {
		manager.closeEnvi();
	}
	public WindowManager getManager() {
		return manager;
	}
	public void setManager(WindowManager manager) {
		this.manager = manager;
	}
	public GameWindow getSelf() {
		return this;
	}
	public void show() {
		frame.setVisible(true);
	}
	/**A static method used to get an input of data type integer from the user, returns int**/
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
