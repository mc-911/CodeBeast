package main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MonJoinsWindow extends GameWindow{

	private JFrame frame;
	private Player player;
	private Monster monster;
	private JTextField textField;
	private GameWindow mainWindow;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the application.
	 */
	public MonJoinsWindow(Player player, Monster monster, GameWindow window) {
		this.mainWindow = window;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame = new JFrame();
		super.setFrame(frame);
		frame.setBounds(100, 100, 1125, 827);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		JTextPane pane = new JTextPane();
		pane.setText("Its time to name your new Monster!\r\nIf you dont wish to name your monster, simply press the enter button and your monster will be given the default name!");
		frame.getContentPane().add(pane);
		super.setInputhere(pane);
		pane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pane.setEditable(false);
		pane.setBounds(20, 11, 1067, 522);
		
		JButton enterButton = new JButton("Enter ");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().strip() != "") {
					monster.setName(getText());
				}
				player.addMonster(monster);
				textField.setText("");
				mainWindow.show();
				frame.dispose();
			}
		});
		enterButton.setBounds(20, 582, 487, 165);
		frame.getContentPane().add(enterButton);
		
		textField = new JTextField();
		textField.setBounds(545, 582, 540, 165);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

}
