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

public class ShopMenu extends GameWindow{

	private JFrame frame;
	private int state;
	private JTextPane pane;
	private JButton viewItems;
	private JButton viewMonsters;
	private JButton sellMonsters;
	private JButton sellItems;
	private JComboBox comboBox;
	private Shop shop;
	private Player player;
	private Items[] items = new Items[] {new HealthPotion(), new AttackPotion(), new MaxHealthPotion(), new LvlUpPotion()};
	private JTextField textField;
	private Monster broughtMonster;
	private JButton enterButton;
	private JButton btnNewButton;
	private MainWindow mainWindow;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
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
						player.getItems().add((Items) comboBox.getSelectedItem());
						turnOn();
						comboBox.removeAllItems();
						sellItems.setText("Sell Items");
						viewItems.setText("View Items");
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
	}
	public void turnOn() {
		viewMonsters.setEnabled(true);
		viewItems.setEnabled(true);
		sellMonsters.setEnabled(true);
		sellItems.setEnabled(true);
	}
}
