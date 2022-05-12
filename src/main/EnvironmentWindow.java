package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;

public class EnvironmentWindow {

	private JFrame frame;
	private JTextField textField;
	private JComboBox combobox;
	private JLayeredPane pane;
	private JTextPane textPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnvironmentWindow window = new EnvironmentWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EnvironmentWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 741, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textPane = new JTextPane();
		textPane.setText("Stuff goes here");
		textPane.setEditable(false);
		textPane.setBounds(10, 11, 709, 336);
		frame.getContentPane().add(textPane);
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.setBounds(10, 371, 340, 72);
		frame.getContentPane().add(btnNewButton);
		
		JLayeredPane layeredPane = new JLayeredPane();
		pane = layeredPane;
		layeredPane.setBounds(378, 371, 329, 72);
		frame.getContentPane().add(layeredPane);
		textField = new JTextField();
		layeredPane.setLayer(textField, 0);
		textField.setBounds(0, 0, 329, 72);
		layeredPane.add(textField);
		textField.setColumns(10);
		textField.setEnabled(false);
		JComboBox comboBox = new JComboBox();
		combobox = comboBox;
		layeredPane.setLayer(comboBox, 1);
		comboBox.setBounds(0, 0, 329, 72);
		layeredPane.add(comboBox);
	}
	/**An instance method, used to swap the positions of this windows ComboBox and textField, returns void**/
	public void swapCombo() {
		int firstp = pane.getLayer(combobox);
		int secondp = pane.getLayer(textField);
		pane.setLayer(combobox, secondp);
		pane.setLayer(textField, firstp);
		if (pane.getLayer(combobox) < pane.getLayer(textField)) {
			combobox.setEnabled(false);
			textField.setEnabled(true);
		}
		else {
			combobox.setEnabled(true);
			textField.setEnabled(false);
		}
		
		
	}
	/**An instance method returns the layer of this windows ComboBox, returns int**/
	public int getLayerCombo() {
		return pane.getLayer(combobox);
	}
	/**An instance method used to set the text shown in textPane,takes a String parameter text, returns void**/
	public void setTextPane(String text) {
		textPane.setText(text);
	}
	/**An instance method used to get the text shown in textPane, returns String**/
	public String getTextPane() {
		return textPane.getText();
	}
	/**An instance method, which shows this window, returns void**/
	public void showWindow() {
		this.frame.setVisible(true);
		
	}
}
