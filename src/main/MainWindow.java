package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class MainWindow extends GameWindow{


	private JTextField txtInputHere;
	private JComboBox combobox;
	private JLayeredPane pane;
	private boolean buttonPressed = false;
	private JButton btnNewButton_1;
	private JButton battles;
	private JButton view_inventory;
	private JButton shop;
	private JButton mon_menu;
	private JButton view_self;
	private JButton pass_time;
	private Player player;
	private JFrame frame;
	private WindowManager manager;
	private boolean disabled = false;
	private int mode;
	private ArrayList<Battle> battlelist;
	private int time;
	private BattleWindow battleWindow;
	private	int gameLength;
	private boolean hard;
	private int day;
	/**A string array which holds a string corresponding to the current time**/
	private static String[] times = {"Morning", "Afternoon", "Night"};
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public MainWindow(Player player, int gameLength, boolean hard) {
		day = 1;
		this.player = player;
		this.gameLength = gameLength;
		this.hard = hard;
		battlelist = Environment.generateBattles(player);
		initialize();
		printMsg(String.format("Gold: %s Day: %s Days Remaining: %s, Time: %s", player.getGold(), day, gameLength - day, Array.get(times, time) + "\n"));
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
		super.setInputhere(pane);
		pane.setText("Please input your Player name");
		pane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pane.setEditable(false);
		pane.setBounds(20, 11, 1067, 522);
		getFrame().getContentPane().add(pane);
		 
		JButton btnNewButton = new JButton("Select");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Battle battle = (Battle) combobox.getSelectedItem();
				if(battle.getDone()) {
					printMsg("You may not select this battle, as you've already fought it!");
				}
				else {
				printMsg(battle.toString());
				startBattle(battle);
				}
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
		}});
		btnNewButton.setBounds(10, 550, 273, 183);
		frame.getContentPane().add(btnNewButton);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(565, 550, 522, 183);
		frame.getContentPane().add(layeredPane);
		txtInputHere = new JTextField();
		txtInputHere.setBounds(0, 0, 522, 183);
		layeredPane.add(txtInputHere);
		txtInputHere.setToolTipText("Enter herre");
		txtInputHere.setHorizontalAlignment(SwingConstants.LEFT);
		txtInputHere.setColumns(10);
		
		battles = new JButton("View Battles");
		layeredPane.setLayer(battles, 1);
		battles.setBounds(0, 0, 184, 93);
		layeredPane.add(battles);
		battles.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			if (!disabled) {
				btnNewButton.setEnabled(true);
				combobox.setEnabled(true);
				Environment.showBattles(battlelist, getSelf());
				mon_menu.setEnabled(false);
				view_self.setEnabled(false);
				shop.setEnabled(false);
				pass_time.setEnabled(false);
				view_inventory.setEnabled(false);
				disabled = true;
				for (Battle i : battlelist) {
				combobox.addItem(i);
				}
			}
			else {
				combobox.setEnabled(false);
				btnNewButton.setEnabled(false);
				combobox.removeAllItems();
				turnAllOn();
			}
		}
		}
		);
		
		mon_menu = new JButton("Open your monsters menu");
		layeredPane.setLayer(mon_menu, 1);
		mon_menu.setBounds(0, 90, 184, 93);
		layeredPane.add(mon_menu);
		
		view_inventory = new JButton("view inventory");
		layeredPane.setLayer(view_inventory, 1);
		view_inventory.setBounds(183, 90, 172, 93);
		layeredPane.add(view_inventory);
		
		shop = new JButton("Go to Shop");
		layeredPane.setLayer(shop, 1);
		shop.setBounds(183, 0, 172, 93);
		layeredPane.add(shop);
		
		view_self = new JButton("view yourself");
		view_self.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printMsg(player.toString());
			}
		});
		layeredPane.setLayer(view_self, 1);
		view_self.setBounds(350, 90, 172, 93);
		layeredPane.add(view_self);
		
		pass_time = new JButton("Pass time");
		pass_time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkTime();
				printMsg("Time Pased");
			}
		});
		layeredPane.setLayer(pass_time, 1);
		pass_time.setBounds(350, 0, 172, 93);
		layeredPane.add(pass_time);
	
		frame.getContentPane().add(combobox);
		frame.setVisible(true);
		btnNewButton.setEnabled(false);
		combobox.setEnabled(false);
		
	
		
	}
	public void turnAllOn() {
		combobox.setEnabled(false);
		mon_menu.setEnabled(true);
		view_self.setEnabled(true);
		shop.setEnabled(true);
		pass_time.setEnabled(true);
		view_inventory.setEnabled(true);
		disabled = false;
	}
	public void startBattle(Battle battle) {
		super.getFrame().setVisible(false);
		battleWindow = new BattleWindow(this, battle, player, time, hard, battlelist.indexOf(battle));
		time++;
		checkTime();
		turnAllOn();
		
	}
	
	
	public void checkTime() {
		printMsg(String.format("Gold: %s Day: %s Days Remaining: %s, Time: %s", player.getGold(), day, gameLength - day, Array.get(times, time) + "\n"));
		if (time == 2) {
			day++;
			time = 0;
			printMsg("New Day");
			battlelist = Environment.generateBattles(player);
		}
		else {
			time += 1;
		}
	}
}
