package main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.awt.event.ActionEvent;
/**An class which uses Java Swing to create a window, used in conjunction with an instance of Player, that the user may manage their monsters and items**/
public class MenuWindow extends GameWindow{
	/**An int variable used to keep track of what the JButtons in this class should do when pressed**/
	private int state;
	/**A Player variable which contains the player object associated with the user, used to perform several operations such as Battling, Using the Shop, getting a monster**/
	private Player player;
	/**A JButton which when pressed will either, display the users monster to the user, enter "Monster order change mode", get the indexes from firtIndex and secondIndex and swap the positions of the monsters at those indexes, or act as a  "Back" button**/
	private JButton viewMonsters;
	/**A JButton which when pressed will either, display the users items to the user, enter, use the selected item in comboBox_1 on the selected monster in comboBox_2,  or act as a  "Back" button**/
	private JButton viewItems;
	/**A JComboBox, or a dropdown menu, which is used to hold a list of the users monsters or items so that the user may swap the postitions of two of their monsters, or so that they may use an item on a monster, is used in conjuction with comboBox to achieve this**/
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	/**A GameWindow variable, contains the window that created this window, used to that the user may go back to that window once the user is done with this one**/
	private GameWindow window;
	private JButton btnNewButton;
	/**An int variable used to hold the index of the first monster the user wishes to swap, used in conjuction with secondIndex to swap the position of two monsters in the users list of monsters/party**/
	private int firstIndex;
	/**An int variable used to hold the index of the second monster the user wishes to swap, used in conjuction with firtIndex to swap the position of two monsters in the users list of monsters/party**/
	private int secondIndex;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	/**A public constructor, takes an Player parameter player, and a GameWindow parameter window as its a parameters**/
	public MenuWindow(Player player, GameWindow window) {
		this.window =  window;
		state = 0;
		this.player = player;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JComboBox combobox = new JComboBox();
		combobox.setEnabled(true);
		combobox.setBounds(293, 550, 261, 22);
		JFrame frame = new JFrame();
		super.setFrame(frame);
		frame.setBounds(100, 100, 1125, 827);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		JTextPane pane = new JTextPane();
		frame.getContentPane().add(pane);
		super.setInputhere(pane);
		pane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pane.setEditable(false);
		pane.setBounds(20, 11, 1067, 522);
		viewMonsters = new JButton("View Monsters");
		viewMonsters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (state) {
				case 0:
					printMsg("Monster Order: " + player.getMonsterOrder());
					for (int i = 0; i != player.getMonsters().size(); i++) {
						if (player.getMonster(i).getCurrentHealth() == 0) {
							printMsg(i + ". Downed: " + player.getMonster(i).toString());
						}
						else {
							printMsg(i + ". " + player.getMonster(i).toString());
						}
					}
					viewItems.setText("Back");
					viewMonsters.setText("Enter change Monster Order mode");
					state = 1;
					break;
				case 1:
					comboBox.setEnabled(true);
					comboBox_1.setEnabled(true);
					for (Monster i: player.getMonsters()) {
						comboBox.addItem(i);
					}
					for (Monster i: player.getMonsters()) {
						comboBox_1.addItem(i);
					}
					state = 2;
					viewMonsters.setText("Press to swap monsters");
					break;
				case 2:
					firstIndex = player.getMonsters().indexOf((Monster) comboBox.getSelectedItem());
					secondIndex = player.getMonsters().indexOf((Monster) comboBox_1.getSelectedItem());		
					if (firstIndex != secondIndex) {
						System.out.println("Shouldnt see this");
						printMsg(String.format("%s and %s have been swapped", player.getMonster(firstIndex).getName(), player.getMonster(secondIndex).getName()));
						Collections.swap(player.getMonsters(), firstIndex, secondIndex);
						comboBox.removeAll();
						comboBox_1.removeAll();
						state = 0;
						viewMonsters.setText("View Monsters");
						viewItems.setText("View Items");
					}
					else {
						printMsg("You have selected the same monster for each of the dropDown menus");
					}
					break;
				case 3:
					viewItems.setText("View Items");
					viewMonsters.setText("View Monsters");
					comboBox.setEnabled(false);
					comboBox_1.setEnabled(false);
					comboBox.removeAllItems();
					comboBox_1.removeAllItems();
					state = 0;
					break;
					
				}
				
				
					
			}
		});
		viewMonsters.setBounds(20, 574, 327, 185);
		frame.getContentPane().add(viewMonsters);
		
		viewItems = new JButton("View Items");
		viewItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (state) {
				case 0:
					if (player.getItems().size() != 0) {
					comboBox.setEnabled(true);
					comboBox_1.setEnabled(true);
					for (Monster i: player.getMonsters()) {
						comboBox.addItem(i);
					}
					for (Items i: player.getItems()) {
						comboBox_1.addItem(i);
						printMsg(i.toString());
					}
					viewMonsters.setText("Back");
					comboBox.removeItem(player.getItems().size()-1);
					viewItems.setText("Press to select item to use on monster");
					state = 3;
					}
					else {
						printMsg("You dont have any items!");
					}
					break;
				case 1:
					viewItems.setText("View Items");
					viewMonsters.setText("View Monsters");
					state = 0;
					comboBox.removeAllItems();
					comboBox_1.removeAllItems();
					break;
				case 2:
					comboBox.setEnabled(false);
					comboBox_1.setEnabled(false);
					comboBox.removeAll();
					comboBox_1.removeAll();
					viewMonsters.setText("Enter change Monster Order mode");
					state = 1;
					break;
				case 3:
					Items item = (Items) comboBox_1.getSelectedItem();
					Monster mon = (Monster) comboBox.getSelectedItem();
					item.drinkPotion(mon, player, getSelf());
					comboBox_1.removeAllItems();
					comboBox.removeAllItems();
					viewItems.setText("View Items");
					viewMonsters.setText("View Monsters");
					comboBox.setEnabled(false);
					comboBox_1.setEnabled(false);
					state = 0;
					break;
				}
			}
		});
		viewItems.setBounds(760, 574, 327, 185);
		frame.getContentPane().add(viewItems);
		
		comboBox = new JComboBox();
		comboBox.setBounds(357, 574, 157, 22);
		frame.getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(593, 574, 157, 22);
		frame.getContentPane().add(comboBox_1);
		comboBox.setEnabled(false);
		comboBox_1.setEnabled(false);
		
		btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.show();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(505, 736, 89, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}

}
