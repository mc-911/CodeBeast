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

	private JFrame frame;
	private JTextField txtInputHere;
	private JComboBox combobox;
	private JLayeredPane pane;
	private JTextPane txtpnpleaseInputYour;
	private boolean buttonPressed = false;
	private JButton btnNewButton_1;
	private int state = 0;
	private int p_state = 0;
	private String str;
	private Player player = new Player();
	private Monster[] starters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	private List<Monster> starterList;
	private int index;
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
		frame.setBounds(100, 100, 1125, 827);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtpnpleaseInputYour = new JTextPane();
		txtpnpleaseInputYour.setText("Please input your Player name");
		txtpnpleaseInputYour.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtpnpleaseInputYour.setEditable(false);
		txtpnpleaseInputYour.setBounds(20, 11, 1067, 522);
		frame.getContentPane().add(txtpnpleaseInputYour);
		 
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(state);
				switch (p_state) {
					case 0:
						switch (state) {
						case 0:
							printMsg("Please input your Player name\n(Note: Must be between 3 and 15 characters and no numbers or special characters");
							str = txtInputHere.getText();
							if (!Player.checkName(str)) {
								printMsg("Invalid name");
							}
							else {
								state += 1;
								starterList = pickMonsters(player);
							}
							txtInputHere.setText("");
							break;
						case 1:
							if (checkInt(txtInputHere.getText())) {
								if (checkInt(txtInputHere.getText(), 1, 3)) {
									index = Integer.parseInt(txtInputHere.getText());
									state += 1;
									printMsg("Time to name your Monster\nInput your monsters name\n(Note: If you input nothing your monster will be given the default name");
								}
							}
							txtInputHere.setText("");
							break;
						
						case 2:
							if (txtInputHere.getText().strip() == "") {
					        	player.addMonster(starterList.get(index));
					        	printMsg("worked");
					        }
					        else {
					        	starterList.get(index).setName(txtInputHere.getText());
					        	player.addMonster(starterList.get(index - 1));
					        	printMsg("Picked" + starterList.get(index-1));
					        	state = 0;
					        	p_state += 1;					        						        	
					        }
						 	break;
						}
						
						
				}
				
				System.out.println(state);
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
		}});
		btnNewButton.setBounds(10, 550, 545, 183);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("clear");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtpnpleaseInputYour.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				
			}
		});
		btnNewButton_1.setBounds(10, 454, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		txtInputHere = new JTextField();
		txtInputHere.setToolTipText("Enter herre");
		txtInputHere.setHorizontalAlignment(SwingConstants.LEFT);
		txtInputHere.setBounds(565, 550, 522, 183);
		frame.getContentPane().add(txtInputHere);
		txtInputHere.setColumns(10);
		
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
	/**An instance method used to set the text shown in textPane,takes a String parameter text, returns void**/
	public void setTextPane(String text) {
		txtpnpleaseInputYour.setText(text);
	}
	/**An instance method used to set buttonPressed, takes a boolean parameter bool as its parameter, returns void**/
	public void setButtionPressed(boolean bool) {
		buttonPressed = bool;
	}
	/**An instance method used to get the text shown in textPane, returns String**/
	public String getTextPane() {
		return txtpnpleaseInputYour.getText();
	}
	/**An instance method, which shows this window, returns void**/
	public void showWindow() {
		this.frame.setVisible(true);
		
	}
	/**An instance method, used to get the text in textField, returns String**/
	@Override
	public String getText() {
		return txtInputHere.getText();
	}
	/**An instance method, used to set the set in textField, takes a String parameter str as its parameter, returns void**/
	@Override
	public void setText(String str) {
		System.out.println(str + "<--");
		txtInputHere.setText(str);
	
	}
	/**An instance method, that is used to check if the button has been pressed, returns boolean**/
	public boolean getButtonPressed() {
		return buttonPressed;
	}
	@Override
	public  void printMsg(String str) {
		this.setTextPane(getTextPane() + "\n" + str);
		if (getTextPane().split("\r\n|\r|\n").length >= 35) {
			this.setTextPane(str);
		}
	}
	/**A static method which shows the user a list of monsters so that the user may choose one as their starting  monster returns void**/
	public  List<Monster> pickMonsters(Player player) {
		List<Monster> starterslist = Arrays.asList(starters);
        Collections.shuffle(starterslist);
        printMsg(String.format("Time to pick your Monster\nYou have three options to pick from\n1. %s\n2. %s\n3. %s", starterslist.get(0), starterslist.get(1), starterslist.get(2)));
        printMsg("Input the number next your choosen monster to pick it!\n(Your input must be an integer)");
        return starterslist;
       
	}
	public  void addMon(Player player, Monster monster, String name) {
		printMsg("Time to name your Monster\nInput your monsters name\n(Note: If you input nothing your monster will be given the default name");
		/**A variable of String datatype used to store the name that the user wishes to call monster**/
        if (name.length() == 0) {
        	player.addMonster(monster);
        	printMsg("worked");
        }
        else {
        	monster.setName(name);
        	player.addMonster(monster);
        }
	}
	public boolean checkInt(String str) {
		return Environment.getUserInt(str, this);
	}
	public boolean checkInt(String str, int num1, int num2) {
		return Environment.getUserIntBounds(num1, num2, this, Integer.parseInt(str));
	}
	public void closeWindow() {
		frame.setVisible(false);
	}

	}




	