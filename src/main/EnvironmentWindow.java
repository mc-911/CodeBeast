package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**An class which uses Java Swing to create a window, used in conjunction with an instance of Player, to start the game*/
public class EnvironmentWindow extends GameWindow{
	/**A JTextField variable, used to hold an instance of JTextField, used to get input from the user**/
	private JTextField txtInputHere;
	/**A JFrame, used to hold components to make the window work**/
	private JFrame frame;
	/**A JLayeredPane, used to hold components and be able to swap them**/
	private JLayeredPane pane;
	/**An int variable used to keep track of what the JButtons in this class should do when pressed**/
	private int state = 0;
	private String str;
	/**A Player variable which contains the player object associated with the user, used to perform several operations such as Battling, Using the Shop, getting a monster**/
	private Player player = new Player();
	/**An array of new Monster objects, used to have the user pick a starting monster**/
	private Monster[] starters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	/**A List of Monster objects,  used to have the user pick a starting monster**/
	private List<Monster> starterList;
	/**An int variable, which holds the users chosen starter Monster's index in starterList**/
	private int index;
	/**a boolean variable used to determine the difficulty of the game, used to scale battles**/
	private boolean hard = false;
	/**A int variable which contains the max amount of days the game can go on for**/
	private int gameLength;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	/**A public constructor that takes a WindowManager parameter manager as its parameter**/
	public EnvironmentWindow(WindowManager manager) {
		super.setManager(manager);;
		initialize();
		printMsg("Please input your Player name\n(Note: Must be between 3 and 15 characters and no numbers or special characters");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1125, 827);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		super.setFrame(frame);
		JTextPane txtpnpleaseInputYour = new JTextPane();
		super.setInputhere(txtpnpleaseInputYour);
		txtpnpleaseInputYour.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtpnpleaseInputYour.setEditable(false);
		txtpnpleaseInputYour.setBounds(20, 11, 1067, 522);
		frame.getContentPane().add(txtpnpleaseInputYour);
		super.setFrame(frame);
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						switch (state) {
						case 0:
							str = txtInputHere.getText();
							if (!Player.checkName(str)) {
								printMsg("Invalid name");
							}
							else {
								state += 1;
								player.setName(txtInputHere.getText());
								printMsg("Please input game length\r\n"+ "game length can be between 5 and 15 days");
							}
							txtInputHere.setText("");
							break;
						case 1:
							if (checkInt(txtInputHere.getText())) {
								if (checkInt(txtInputHere.getText(), 6, 14)) {
									state += 1;
									printMsg("Input 0 for normal mode or input 1 for hardmode");
									gameLength = Integer.parseInt(txtInputHere.getText());
								}
							}
							txtInputHere.setText("");
							break;
						case 2:
							if (checkInt(txtInputHere.getText())) {
								if (checkInt(txtInputHere.getText(), 0, 1)) {
									state += 1;
									setText("");
									starterList = pickMonsters(player);
									if (Integer.parseInt(txtInputHere.getText()) == 1) {
										hard = true;
									}
								}
							}
							txtInputHere.setText("");
							break;
						case 3:
							if (checkInt(txtInputHere.getText())) {
								if (checkInt(txtInputHere.getText(), 1, 3)) {
									index = Integer.parseInt(txtInputHere.getText());
									state += 1;
									printMsg("Time to name your Monster\nInput your monsters name\n(Note: If you input nothing your monster will be given the default name");
								}
							}
							txtInputHere.setText("");
							break;
						
						case 4:
							if (txtInputHere.getText().strip() == "") {
					        	player.addMonster(starterList.get(index - 1));
					        	printMsg("worked");
					        }
					        else {
					        	starterList.get(index - 1).setName(txtInputHere.getText());
					        	player.getMonsters().add(starterList.get(index-1));
					        	printMsg("Picked" + starterList.get(index-1));
					        	state = 0;				        						        	
					        }
							player.setActiveMonster(0);
							exit();
						 	break;
						}
						
						
				
				
				
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
		}});
		btnNewButton.setBounds(10, 550, 545, 183);
		getFrame().getContentPane().add(btnNewButton);
		
		
		txtInputHere = new JTextField();
		txtInputHere.setToolTipText("Enter herre");
		txtInputHere.setHorizontalAlignment(SwingConstants.LEFT);
		txtInputHere.setBounds(565, 550, 522, 183);
		getFrame().getContentPane().add(txtInputHere);
		txtInputHere.setColumns(10);
		frame.setVisible(true);
		
	}

	
	
	
	
	
	/**A static method which shows the user a list of monsters so that the user may choose one as their starting  monster returns void**/
	public  List<Monster> pickMonsters(Player player) {
		List<Monster> starterslist = Arrays.asList(starters);
        Collections.shuffle(starterslist);
        printMsg(String.format("Time to pick your Monster\nYou have three options to pick from\n1. %s\n2. %s\n3. %s", starterslist.get(0), starterslist.get(1), starterslist.get(2)));
        printMsg("Input the number next your choosen monster to pick it!\n(Your input must be an integer)");
        return starterslist;
       
	}
	/**An instance method, returns player, returns Player**/
	public Player getPlayer() {
		return player;
	}
	/**An instance method, returns gameLength, returns int**/
	public int getGameLength() {
		return gameLength;
	}
	/**An instance method, returns hard, returns boolean**/
	public boolean getHard() {
		return hard;
	}

	}




	