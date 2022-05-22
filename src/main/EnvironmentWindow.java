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

public class EnvironmentWindow extends GameWindow{

	private JTextField txtInputHere;
	private JFrame frame;
	private JComboBox combobox;
	private JLayeredPane pane;
	private boolean buttonPressed = false;
	private JButton btnNewButton_1;
	private int state = 0;
	private int p_state = 0;
	private String str;
	private Player player = new Player();
	private Monster[] starters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	private List<Monster> starterList;
	private int index;
	private boolean hard = false;
	private int gameLength;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
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
				switch (p_state) {
					case 0:
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
					        	p_state += 1;					        						        	
					        }
							player.setActiveMonster(0);
							exit();
						 	break;
						}
						
						
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
	/**An instance method, used to swap the positions of this windows ComboBox and textField, returns void**/
	public void swapCombo() {
		int firstp = pane.getLayer(combobox);
		int secondp = pane.getLayer(txtInputHere);
		pane.setLayer(combobox, secondp);
		pane.setLayer(txtInputHere, firstp);
		if (pane.getLayer(combobox) < pane.getLayer(txtInputHere)) {
			combobox.setEnabled(false);
			txtInputHere.setEnabled(true);
		}
		else {
			combobox.setEnabled(true);
			txtInputHere.setEnabled(false);
		}
		
		
	}
	/**An instance method returns the layer of this windows ComboBox, returns int**/
	public int getLayerCombo() {
		return pane.getLayer(combobox);
	}
	
	
	
	
	/**A static method which shows the user a list of monsters so that the user may choose one as their starting  monster returns void**/
	public  List<Monster> pickMonsters(Player player) {
		List<Monster> starterslist = Arrays.asList(starters);
        Collections.shuffle(starterslist);
        printMsg(String.format("Time to pick your Monster\nYou have three options to pick from\n1. %s\n2. %s\n3. %s", starterslist.get(0), starterslist.get(1), starterslist.get(2)));
        printMsg("Input the number next your choosen monster to pick it!\n(Your input must be an integer)");
        return starterslist;
       
	}
	public Player getPlayer() {
		return player;
	}
	public int getGameLength() {
		return gameLength;
	}
	public boolean getHard() {
		return hard;
	}

	}




	