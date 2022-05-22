package main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**The class that implements the GUI needed to run the monJoins random event**/
public class MonJoinsWindow extends GameWindow{
	/**A JFrame, used to hold components to make the window work**/
	private JFrame frame;
	/**A Player variable which contains the player object associated with the user, used to perform several operations such as Battling, Using the Shop, getting a monster**/
	private Player player;
	/**A Monster variable, which holds the instance of Monster that will be added to the users list of monsters**/
	private Monster monster;
	/**A JTextField variable, used to hold an instance of JTextField, used to get input from the user**/
	private JTextField textField;
	/**A GameWindow variable, which holds the window that created this window, used so that we may go back to that window**/
	private GameWindow mainWindow;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the application.
	 */
	/**A public constructor that takes a Player parameter player, a Monster parameter monster, and a GameWindow parameter window**/
	public MonJoinsWindow(Player player, Monster monster, GameWindow window) {
		this.player = player;
		this.monster = monster;
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
					monster.setName(textField.getText());
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
		frame.setVisible(true);
		frame.setEnabled(true);
	}

}
