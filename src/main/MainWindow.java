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

public class MainWindow extends GameWindow{


	private JTextField txtInputHere;
	private JComboBox combobox;
	private JLayeredPane pane;
	private boolean buttonPressed = false;
	private JButton btnNewButton_1;
	private JButton battles;
	private JButton shop;
	private JButton mon_menu;
	private JButton view_self;
	private JButton pass_time;
	private Player player;
	private JFrame frame;
	private WindowManager manager;
	private boolean disabled = false;
	
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
	private ShopMenu shopMenu;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
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
		combobox = new JComboBox();
		combobox.setEnabled(true);
		combobox.setBounds(293, 550, 261, 22);
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
		 
		btnNewButton = new JButton("Select");
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
				showBattles(battlelist);
				mon_menu.setEnabled(false);
				view_self.setEnabled(false);
				shop.setEnabled(false);
				pass_time.setEnabled(false);
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
	
		frame.getContentPane().add(combobox);
		frame.setVisible(true);
		btnNewButton.setEnabled(false);
		combobox.setEnabled(false);
		
		btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setBounds(293, 583, 261, 146);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_1 = btnNewButton_2;
		
	
		
	}
	public void turnAllOn() {
		combobox.setEnabled(false);
		mon_menu.setEnabled(true);
		view_self.setEnabled(true);
		shop.setEnabled(true);
		pass_time.setEnabled(true);
		disabled = false;
	}
	public void startBattle(Battle battle) {
		super.getFrame().setVisible(false);
		battleWindow = new BattleWindow(this, battle, player, time, hard, battlelist.indexOf(battle));
		time++;
		turnAllOn();
		
	}
	
	
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
			}
		}
		else {
			time += 1;
		}
		printMsg(String.format("Gold: %s Day: %s Days Remaining: %s, Time: %s", player.getGold(), day, gameLength - day, times[time] + "\n"));
	}
	public void startShop() {
		super.getFrame().setVisible(false);
		shopMenu = new ShopMenu(player, shop_o, this);
		turnAllOn();
	}
	public void Show_2() {
		show();
		if (player.getMonsters().size() == 0 && player.getGold() < 10) {
			gameOver();
		}
		checkTime();
	}
	/**An instance method,it is used to end the game, takes Player player, and int day as its parameters, returns void**/
	public void gameOver() {
		btnNewButton.setEnabled(false);
		btnNewButton_1.setEnabled(false);
		battles.setEnabled(false);
		shop.setEnabled(false);
		mon_menu.setEnabled(buttonPressed);
		view_self.setEnabled(false);
		pass_time.setEnabled(false);
		printMsg("GAME OVER");
		printMsg(String.format("Name: %s\nGame Duration: %s/%s days\nGold: %s\nPoints: %s", player.getName(), day, gameLength, player.getGold(), player.getPoints()));
	}
	/**A method that gets the user's input as an integer makes sure that the input is between num1 and num2 (inclusive) returns an integer**/
	public boolean  getUserIntBounds(int num1, int num2, GameWindow window, int input) {
		boolean picked;
		picked = false;
		if (input < num1|| input > num2) {
			window.printMsg(String.format("Your input must be between %s, and %s (inclusive)", num1, num2));
		}
		else {
			picked = true;
		}
		return picked;
	}
	/**A static method used to display an array of purchasable items (which will contain either monsters or items but not both) in an ordered manner to the user returns void**/
	public  void displayList(Purchasable[] p) {
		int i = 0;
		for (Purchasable d : p) {
			getSelf().printMsg(String.format("%s. %s", i, d));
			i++;
		}
		
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
			/**Sets the regular probabiltiy of a monster leveling up (monRandLvlUp occuring) , or a monser leaving (monLeaves occuring)**/
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
		/**An if statement checks if occured is false, if occured is false it will print "Nothing happaend! this should only occur when no Random Event has occured"**/
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
