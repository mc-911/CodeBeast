package main;

import javax.swing.JFrame;
import javax.swing.JTextPane;

public abstract class GameWindow {
	private JFrame frame = new JFrame();
	private WindowManager manager;
	private JTextPane inputHere;
	public boolean checkInt(String str) {
		return Environment.getUserInt(str, this);
	}
	public boolean checkInt(String str, int num1, int num2) {
		return Environment.getUserIntBounds(num1, num2, this, Integer.parseInt(str));
	}
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

}
