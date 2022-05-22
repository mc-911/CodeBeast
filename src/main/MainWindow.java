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
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
/**An class which uses Java Swing to create a window, used in conjunction with instances of classes Battle, Shop, Player and "Window" classes such as BattleWindow, MenuWindow and ShopWindow, to provide the main game play loop of the game**/
public class MainWindow extends GameWindow{
	/**A JComboBox, used to have the user select a battle to fight**/
	private JComboBox comboBox;
	/**A JButton that displays the battles available to the user**/
	private JButton battles;
	/**A JButton which when pressed will open up a ShopMenu**/
	private JButton shop;
	/**A JButton which when pressed will take the user to a window where they can manage their items/monsters**/
	private JButton mon_menu;
	/**A JButton which when pressed will display to the user, a description of themselves**/
	private JButton view_self;
	/**A JButton which when pressed will call checkTime() essentially it "passes the time"**/ 
	private JButton pass_time;
	/**A Player variable which contains the player object associated with the user, used to perform several operations such as Battling, Using the Shop, getting a monster**/
	private Player player;
	/**A JFrame variable , used to create the window**/ 
	private JFrame frame;
	/**A boolean variable, used to keep track if certain JButtons have been disabled**/
	private boolean disabled = false;
	/**A ArrayList<Battle> variable used to contain the battles that the user may fight**/
	private ArrayList<Battle> battlelist;
	/**An int variable used to keep track of the current time of day, can either be 0, 1 or 2**/
	private int time;
	private BattleWindow battleWindow;
	/**A int variable which contains the max amount of days the game can go on for**/
	private	int gameLength;
	/**a boolean variable used to determine the difficulty of the game, used to scale battles**/
	private boolean hard;
	/**an int variable used to keep track of how long the game has gone on for, can't be greater than gameLength**/
	private int day;
	private MenuWindow menuWindow;
	/**A string array which holds a string corresponding to the current time**/
	private static String[] times = {"Morning", "Afternoon", "Night"};
	/**A Shop variable which contains an instance of the Shop class**/
	private Shop shop_o;
	/**A ShopMenu variable used to contain the ShopWindow Object that will be used to have the user "Go to the Shop"**/
	private ShopMenu shopMenu;
	/**A JButton which when pressed will start the battle that the user has selected from comboBox (Given the user hasn't already fought this battle)**/
	private JButton selectButton;
	/**A JButton which when pressed will close the game, used when the game has ended**/
	private JButton exitButton;
	/**An boolean variable used to keep track of whether or not the game is over**/
	private boolean gameOver;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	/**The public constructor for this class, takes a Player parameter player, an int parameter gameLength, and a boolean parameter hard, as its parameters**/
	public MainWindow(Player player, int gameLength, boolean hard) {
		shop_o = new Shop();
		day = 1;
		this.player = player;
		this.gameLength = gameLength;
		this.hard = hard;
		battlelist = generateBattles(player);
		initialize();
		printMsg(String.format("Gold: %s Day: %s Days Remaining: %s, Time: %s", player.getGold(), day, gameLength - day, Array.get(times, time) + "\n"));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		comboBox = new JComboBox();
		comboBox.setEnabled(true);
		comboBox.setBounds(293, 550, 261, 22);
		JFrame frame = new JFrame();
		super.setFrame(frame);
		frame.setBounds(100, 100, 1125, 827);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		JTextPane pane = new JTextPane();
		super.setInputhere(pane);
		pane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pane.setEditable(false);
		pane.setBounds(20, 11, 1067, 522);
		frame.getContentPane().add(pane);
		 
		selectButton = new JButton("Select");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Battle battle = (Battle) comboBox.getSelectedItem();
				if(battle.getDone()) {
					printMsg("You may not select this battle, as you've already fought it!");
				}
				else if (player.getMonsters().size() == 0) {
					printMsg("You may not fight as you have no monsters to fight with!");
				}
				else {
				printMsg(battle.toString());
				comboBox.removeAllItems();
				startBattle(battle);
				}
			}
		});
		selectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
		}});
		selectButton.setBounds(10, 550, 273, 183);
		frame.getContentPane().add(selectButton);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(565, 550, 522, 183);
		frame.getContentPane().add(layeredPane);
		
		battles = new JButton("View Battles");
		layeredPane.setLayer(battles, 1);
		battles.setBounds(0, 0, 184, 93);
		layeredPane.add(battles);
		battles.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			if (!disabled) {
				selectButton.setEnabled(true);
				comboBox.setEnabled(true);
				showBattles(battlelist);
				mon_menu.setEnabled(false);
				view_self.setEnabled(false);
				shop.setEnabled(false);
				pass_time.setEnabled(false);
				disabled = true;
				battles.setText("Back");
				for (Battle i : battlelist) {
				comboBox.addItem(i);
				}
			}
			else {
				battles.setText("View Battles");
				comboBox.setEnabled(false);
				selectButton.setEnabled(false);
				comboBox.removeAllItems();
				turnAllOn();
			}
		}
		}
		);
		
		mon_menu = new JButton("Open Menu");
		mon_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuWindow = new MenuWindow(player, getSelf());
				getSelf().closeWindow();
			}
		});
		layeredPane.setLayer(mon_menu, 1);
		mon_menu.setBounds(0, 90, 355, 93);
		layeredPane.add(mon_menu);
		
		shop = new JButton("Go to Shop");
		shop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startShop();
			}
		});
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
	
		frame.getContentPane().add(comboBox);
		frame.setVisible(true);
		selectButton.setEnabled(false);
		comboBox.setEnabled(false);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		exitButton.setEnabled(false);
		exitButton.setBounds(293, 583, 261, 146);
		frame.getContentPane().add(exitButton);
		
		
	
		
	}
	/**A public method which enables all of the JButtons, returns void**/
	public void turnAllOn() {
		comboBox.setEnabled(false);
		mon_menu.setEnabled(true);
		view_self.setEnabled(true);
		shop.setEnabled(true);
		pass_time.setEnabled(true);
		disabled = false;
	}
	/**A public method, which "Starts a Battle", takes a Battle parameter battle, returns void**/
	public void startBattle(Battle battle) {
		super.getFrame().setVisible(false);
		battleWindow = new BattleWindow(this, battle, player, time, hard, battlelist.indexOf(battle));
		turnAllOn();
		battles.setText("View Battles");
		selectButton.setEnabled(false);
		
	}
	
	/**A public method, runs checks to see if day or time should increment or if the game is over, returns void**/
	public void checkTime() {
		if (time == 2 && day == gameLength) {
			gameOver();
		}
		else if (time == 2) {
			day++;
			time = 0;
			try {
				randomEventCheck(player);
			}
			catch ( java.util.ConcurrentModificationException e) {
				
			}
			player.sleepMon();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (player.getMonsters().size() == 0 && player.getGold() < 10) {
				gameOver();
			}
			else {
			printMsg("New Day");
			battlelist = generateBattles(player);
			comboBox.removeAllItems();
			}
		}
		else {
			time += 1;
		}
		if (!gameOver) {
		printMsg(String.format("Gold: %s Day: %s Days Remaining: %s, Time: %s", player.getGold(), day, gameLength - day, times[time] + "\n"));
		}
		
	}
	/**A method used to open the Shop, creates a new instance of ShopMenu, returns void**/
	public void startShop() {
		super.getFrame().setVisible(false);
		shopMenu = new ShopMenu(player, shop_o, this);
		turnAllOn();
	}
	/**A method used to display this instance of MainWindow, check if the user has lost, and calls checkTime, called for actions where time would increase e.g Battling, Going to the Shop, returns void**/
	public void Show_2() {
		show();
		if (player.getMonsters().size() == 0 && player.getGold() < 10) {
			gameOver();
		}
		else {
		checkTime();
		}
	}
	/**An instance method,it is used to end the game, takes Player player, and int day as its parameters, returns void**/
	public void gameOver() {
		selectButton.setEnabled(false);
		exitButton.setEnabled(true);
		battles.setEnabled(false);
		shop.setEnabled(false);
		mon_menu.setEnabled(false);
		view_self.setEnabled(false);
		pass_time.setEnabled(false);
		gameOver = true;
		setTextPane("");
		printMsg("GAME OVER");
		printMsg(String.format("Name: %s\nGame Duration: %s/%s days\nGold: %s\nPoints: %s", player.getName(), day, gameLength, player.getGold(), player.getPoints()));
	}
	
	
	/**A method which is used to see if a Random Event should occur, if one should occur it runs it, returns void**/
	public  void  randomEventCheck(Player player) {
		/**A random object used to get an integer between [0, 99] used for determining which event should fire given an event should fire**/
		Random rand = new Random();
		/**A monJoins Object will be used with randList to determine the likelihood of the Monster Joins or (monJoins) event occurring given that a monster can join**/
		monJoins monJoin = new monJoins();
		/**A monLeaves Object will be used with randList to determine the likelihood of that the monLeaves event will occur given a Monster mon (basically determines the likelihood that a Monster mon will leave during the night)**/
		monLeaves monLeave = new monLeaves();
		/**A monRandLvlUp Object will be used with randList to determine the likelihood of that the monRandLvlUp event will occur given a Monster mon (basically determines the likelihood that a Monster mon will level up during the night)**/
		monRandLvlUp up = new monRandLvlUp();
		/**An variable of int data type used to store a value [0, 99] given by rand.nextInt(100), in conjuction with randList it determines if an event should occur given a specific Monster mon, and which event should occur**/
		int num;
		/**A variable of boolean data type  used to keep track of whether or not an event has**/
		boolean occured =false;
		getSelf().printMsg("During the night...");
		/**A list of RandomEvent objects used to determine the probability that an event occurs, the amount of times a specific RandomEvent object occurs in the list is its likelihood of occurring**/
		ArrayList<RandomEvent> randList;
		/**A forEarch loop used to iterate through the user's list of monsters, and to determine for each one if an event should fire which event should fire then fires that event via calling startEvent(mon,player)**/
		for (Monster mon : player.getMonsters()) {
			/**Sets the regular probability of a monster leveling up (monRandLvlUp occurring) , or a monster leaving (monLeaves occurring)**/
			randList = new ArrayList<>(Arrays.asList(monLeave, monLeave, monLeave, monLeave, up, up, up, up, up, up, up, up));
			/**A for loop used to add monLeave to randList the amount of times this is done is the amount of days a monster has fainted in a row (given by mon.getDaysFainted()), used to increase the likelihood a specific monster leaves**/
			for (int i = mon.getdaysFainted(); i != 0; i-- ) {randList.add(monLeave);}
			/**Gets an integer [0,99] used to determine if a Random Event should occur**/
			num = rand.nextInt(100);
			/**Checks to see if num corresponds to an index in randlist is if it does it, gets the randomEvent object at that index then it proceeds to start that event via startEvent(mon,player), and sets occured to be true**/
			if (num <= randList.size() - 1) {
				randList.get(num).startEvent(mon, player, getSelf());
				occured = true;
			}
		}
		/**Used to check if the user has the max amount of monsters if the user doesn't proceeds to determine the likelihood monJoins occurs**/
		if (player.getMonsters().size() != 4) {
			/**The regular likelihood monJoins occurs**/
			randList = new ArrayList<>(Arrays.asList(monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin, monJoin));
			/**A for loop used to add up to randlist per the amount of free slots a user has for a new monsters (this is determined by gettting the 4 - the amount of monsters the user has**/
			for (int i = player.getMonsters().size(); i != 0; i--) {randList.add(monJoin);}
			num = rand.nextInt(100);
			if (num <= randList.size() - 1) {
				randList.get(num).startEvent(null, player, getSelf());
				occured = true;
			}
		}
		/**An if statement checks if occured is false, if occured is false it will print "Nothing happened! this should only occur when no Random Event has occured"**/
		if (!occured) {
			getSelf().printMsg("Nothing happened!");
		}
	}
	


	


	/**A method used to generate battles in mainGameplay, so that the user may pick a battle, takes a Player object player has its parameter, returns ArrayList<Battle>**/
	public ArrayList<Battle> generateBattles(Player player) {
		ArrayList<Battle> battles = new ArrayList<Battle>();
		Random random = new Random();
		int num = random.nextInt(5);
		if (num < 3) {
			num = 3;
		}
		for (int i = 1; i <= num; i++) {
			battles.add(new Battle());
		}
		return battles;
	}
	/**A  method which Shows the available battles to the user, takes An ArrayList<Battle> battles as its parameter, returns void**/
	public void showBattles(ArrayList<Battle> battles) {
		for (int i = 0; i != battles.size(); i++) {
			printMsg(String.format("%s. Enemies: %s", i, battles.get(i)));
		}
		
		
	}
}
