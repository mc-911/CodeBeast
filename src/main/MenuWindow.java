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

public class MenuWindow extends GameWindow{

	private JFrame frame;
	private int state;
	private Player player;
	private JButton viewMonsters;
	private JButton viewItems;
	private JComboBox comboBox_1;
	private JComboBox comboBox;
	private MainWindow mainWindow;
	private JButton btnNewButton;
	private int firstIndex;
	private int secondIndex;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public MenuWindow(Player player, GameWindow mainWindow) {
		this.mainWindow = (MainWindow) mainWindow;
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
		pane.setText("Please input your Player name");
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
					firstIndex = player.getMonsters().indexOf(combobox.getSelectedItem());
					secondIndex = player.getMonsters().indexOf(combobox.getSelectedItem());		
					if (firstIndex != secondIndex) {
						printMsg(String.format("%s and %s have been swapped", player.getMonster(firstIndex), player.getMonster(secondIndex)));
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
					comboBox.setEnabled(true);
					comboBox.setEnabled(true);
					for (Monster i: player.getMonsters()) {
						comboBox.addItem(i);
					}
					for (Items i: player.getItems()) {
						comboBox_1.addItem(i);
						printMsg(i.toString());
					}
					viewMonsters.setText("Back");
					viewItems.setText("Press to select item to use on monster");
					state = 3;
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
				mainWindow.show();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(505, 736, 89, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}

}
