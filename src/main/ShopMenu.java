package main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**An class which uses Java Swing to create a window, used in conjunction with an instance of Shop, that the user may go to the Shop**/
public class ShopMenu extends GameWindow{
	private JFrame frame;
	/**An int variable used to keep track of what the JButtons should do when pressed**/
	private int state;
	private JTextPane pane;
	/**A JButton which when pressed will either display the items the user can buy, buy the item the user has selected from the dropdown menu (assuming the user has enough gold), or it may act has a "Back" button**/
	private JButton viewItems;
	/**A JButton which when pressed will either display the monsters the user can buy, buy the monster the user has selected from the dropdown menu (assuming the user has enough gold), or it may act has a "Back" button**/
	private JButton viewMonsters;
	/**A JButton which when pressed will either display the monsters the user can sell, sell the monster the user has selected from the dropdown menu, or it may act has a "Back" button**/
	private JButton sellMonsters;
	/**A JButton which when pressed will either display the items the user can sell, sell the item the user has selected from the dropdown menu, or it may act has a "Back" button**/
	private JButton sellItems;
	/**A JComboBox or a dropdown menu, used to have the user pick from a list of Monsters or Items they want to buy/sell**/
	private JComboBox comboBox;
	/**A Shop variable which contains an instance of the Shop class used in conjunction with an instance of this class, to provide a "Shop" to the user**/
	private Shop shop;
	/**A Player variable which contains the player object associated with the user, used to perform several operations such as Battling, Using the Shop, getting a monster**/
	private Player player;
	/**An Items Array that contains items that the user can buy**/
	private Items[] items = new Items[] {new HealthPotion(), new AttackPotion(), new MaxHealthPotion(), new LvlUpPotion()};
	private JTextField textField;
	/**A Monster variable that contains the monster that the user has brought**/
	private Monster broughtMonster;
	/**A JButton used so that the user may name a monster they brought**/
	private JButton enterButton;
	/**A JButton which the user can press to close this window, and show the previous window**/
	private JButton btnNewButton;
	/**A MainWindow variable which contains the window that created this window/ShopMenu object**/
	private MainWindow mainWindow;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	/**A public constructor for this class, takes a Player parameter player, a Shop parameter shop, and a MainWindow parameter window, as its parameters, the purpose of these parameters is so that we can add/remove items/monsters to/from the user via buy and sell using an instance of Shop that contains monsters, and so that the user can exit this window and go back to the previous window**/
	public ShopMenu(Player player, Shop shop, MainWindow window) {
		this.mainWindow = window;
		this.shop = shop;
		this.player = player;
		state = 0;
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
		pane = new JTextPane();
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
					viewItems.setEnabled(false);;
					sellItems.setEnabled(false);
					comboBox.setEnabled(true);
					sellMonsters.setText("Back");
					viewMonsters.setText("Buy Monster");
					shop.displayMonsters(getSelf());
					for (int i = 0; i != shop.getMonsters().length; i++) {comboBox.addItem(shop.getMonsters()[i]);}
					printMsg("To buy an monster select the monster from  the dropdown menu in the middle, and press buy");
					state = 1;
					break;
				case 1:
					if (((Monster) comboBox.getSelectedItem()).getBrought()) {
						printMsg("You've already brought this monster!");
					}
					else if (shop.buy(player, (Monster) comboBox.getSelectedItem(), getSelf())) {
						viewMonsters.setEnabled(false);
						sellMonsters.setEnabled(false);
						textField.setEnabled(true);
						textField.setVisible(true);
						enterButton.setEnabled(true);
						enterButton.setVisible(true);
						broughtMonster = (Monster) comboBox.getSelectedItem();
						printMsg("Its time to name your new Monster!\nIf you dont wish to name your monster, simply press the enter button and your monster will be given the default name!");
						comboBox.setEnabled(false);
					}
					break;
				}
			}
		});
		viewMonsters.setBounds(20, 544, 377, 112);
		frame.getContentPane().add(viewMonsters);
		
		viewItems = new JButton("View Items");
		viewItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (state) {
				case 0:
					viewMonsters.setEnabled(false);;
					sellMonsters.setEnabled(false);
					comboBox.setEnabled(true);
					sellItems.setText("Back");
					viewItems.setText("Buy item");
					shop.displayItems(getSelf());
					for (int i = 0; i != items.length; i++) {comboBox.addItem(items[i]);}
					printMsg("To buy an item select the item from  the dropdown menu in the middle, and press buy");
					state = 1;
					break;
				case 1:
					if (shop.buy(player, (Items) comboBox.getSelectedItem(), getSelf())) {
						textField.setEnabled(false);
						textField.setVisible(false);
						enterButton.setEnabled(false);
						enterButton.setVisible(false);
						turnOn();
						comboBox.removeAllItems();
						sellItems.setText("Sell Items");
						viewItems.setText("View Items");
						comboBox.setEnabled(false);
						state = 0;
					}
					break;
				case 2:
					comboBox.setEnabled(false);
					comboBox.removeAllItems();
					sellItems.setText("Sell Items");
					viewItems.setText("View Items");
					state = 0;	
					break;
				}
			}
		});
		viewItems.setBounds(710, 544, 377, 112);
		frame.getContentPane().add(viewItems);
		
		comboBox = new JComboBox();
		comboBox.setBounds(407, 544, 293, 22);
		frame.getContentPane().add(comboBox);
		
		sellMonsters = new JButton("Sell Monsters");
		sellMonsters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (state) {
				case 0:
					if (player.getMonsters().size() != 0) {
					viewItems.setEnabled(false);;
					sellItems.setEnabled(false);
					comboBox.setEnabled(true);
					sellMonsters.setText("Sell Monster");
					viewMonsters.setText("Back");
					for (Monster i : player.getMonsters()) {
						comboBox.addItem(i);
						printMsg(i.toString());
					}
					state = 2;
					printMsg("To sell an Monster select the Monster from  the dropdown menu in the middle, and press Sell Monster");
					}
					else {
						printMsg("You have no Monsters to sell!");
					}
					break;
				case 1:
					turnOn();
					comboBox.setEnabled(false);
					comboBox.removeAllItems();
					sellMonsters.setText("Sell Monsters");
					viewMonsters.setText("View Monsters");
					state = 0;
					break;
				case 2:
					shop.sell(player, (Monster) comboBox.getSelectedItem(), getSelf());
					turnOn();
					comboBox.setEnabled(false);
					comboBox.removeAllItems();
					sellMonsters.setText("Sell Monsters");
					viewMonsters.setText("View Monsters");
					state = 0;	
					break;
				}
				
			}
		});
		sellMonsters.setBounds(20, 665, 377, 112);
		frame.getContentPane().add(sellMonsters);
		
		sellItems = new JButton("Sell Items");
		sellItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (state) {
				case 0:
					if (player.getItems().size() != 0) {
					viewMonsters.setEnabled(false);;
					sellMonsters.setEnabled(false);
					comboBox.setEnabled(true);
					viewItems.setText("Back");
					sellItems.setText("Sell Item");
					for (Items i : player.getItems()) {
						comboBox.addItem(i);
						printMsg(i.toString());
					}
					
					state = 2;
					}
					else {
						printMsg("You have no items to sell!");
					}
					break;
				case 1:
					turnOn();
					comboBox.setEnabled(false);
					comboBox.removeAllItems();
					sellItems.setText("Sell Items");
					viewItems.setText("View Items");
					state = 0;
					break;
				case 2:
					shop.sell(player, (Items) comboBox.getSelectedItem(), getSelf());
					turnOn();
					comboBox.setEnabled(false);
					comboBox.removeAllItems();
					sellItems.setText("Sell Items");
					viewItems.setText("View Items");
					state = 0;	
					break;
				}
				
					
			}
		});
		sellItems.setBounds(710, 667, 377, 112);
		frame.getContentPane().add(sellItems);
		
		textField = new JTextField();
		textField.setBounds(407, 577, 293, 53);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().strip() != "") {
					broughtMonster.setName(textField.getText());
				}
				textField.setText("");
				textField.setEnabled(false);
				textField.setVisible(false);
				enterButton.setEnabled(false);
				enterButton.setVisible(false);
				((Monster) comboBox.getSelectedItem()).setBrought(true);
				player.getMonsters().add((Monster) comboBox.getSelectedItem());
				turnOn();
				comboBox.removeAllItems();
				sellMonsters.setText("Sell Monsters");
				viewMonsters.setText("View Monsters");
				state = 0;
			}
		});
		enterButton.setBounds(407, 641, 293, 58);
		frame.getContentPane().add(enterButton);
		textField.setEnabled(false);
		textField.setVisible(false);
		enterButton.setEnabled(false);
		
		btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.Show_2();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(510, 754, 89, 23);
		frame.getContentPane().add(btnNewButton);
		enterButton.setVisible(false);
		frame.setVisible(true);
		comboBox.setEnabled(false);
	}
	/**A public method which enables all of the JButtons, returns void**/
	public void turnOn() {
		viewMonsters.setEnabled(true);
		viewItems.setEnabled(true);
		sellMonsters.setEnabled(true);
		sellItems.setEnabled(true);
	}
}
