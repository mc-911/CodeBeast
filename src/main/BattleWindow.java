package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
/**An class which uses Java Swing to create a window, used in conjunction with an instance of Battle, that the user may fight battles**/
public class BattleWindow extends GameWindow{
	/**A JButton which when pressed will have the users active monster attack the leading enemy monster**/
	private JButton Attack;
	/**A JButton which when pressed will display a description of the current monster the user is fighting to the user**/
	private JButton View;
	/**A JButton which when pressed will take the user to a window where they can manage their items/monsters**/
	private JButton monMenu;
	/**A Player variable which contains the player object associated with the user, used to perform several operations such as Battling, Using the Shop, getting a monster**/
	private Player player;
	/**A JFrame variable , used to create the window**/ 
	private JFrame frame;
	/**A MainWindow which is used to contain the MainWindow object that created this instance of BattleWindow, used to exit this window and go back to the MainWindow object window**/
	private MainWindow mainWindow;
	/**A Monster variable which is used to contain the Monster object/ Monster that the user is currently battling against**/
	private Monster leading;
	/**A Battle variable which contains the instance of Battle that the user is currently fighting**/
	private Battle battle;
	/**An int variable used to contain the amount of extra damage an enemy monster  will have, if hard is true**/
	private int dmgIncrease;
	/**An int variable used to contain the amount of gold/points the User/the Users monsters will earn upon winning this battle**/
	private int goldEarned;
	private JLayeredPane layeredPane;
	/**A JButton which when pressed exits this window and shows the window contained in mainWindow**/
	private JButton exit;
	private JPanel panel;
	/**a boolean variable used to determine the difficulty of the game, used to scale battles**/
	private boolean hard;
	JTextPane pane;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	/**The public constructor for this class, takes a Player parameter player, an int parameter time, and a int parameter rank, as its parameters, player is used to perform operations such as getting the monster the user will use to fight, increasing the amount gold/points the user has if they win, whilst the other parameters are used to calculate the amount of gold/points the user will earn if they win the battle, and to increase the damage of enemies**/
	public BattleWindow(MainWindow window, Battle battle, Player player, int time, boolean hard, int rank) {
		int mult = 1;
		if (hard) {
			mult = 2;
		}
		goldEarned =  5* (time +1) * (rank + 1) * mult;
		dmgIncrease = 5 * (rank + 1) * mult * (time + 1);
		
		this.player = player;
		this.mainWindow = window;	
		this.hard = hard;
		leading = battle.getLeading();
		if (hard) {
		leading.setDamageAmount(leading.getDamage() + dmgIncrease);
		}
		this.battle = battle;
		player.setActiveMonster(0);
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
		super.setInputhere(pane);
		pane.setText("Battle Started");
		pane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pane.setEditable(false);
		pane.setBounds(20, 11, 1067, 522);
		frame.getContentPane().add(pane);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(283, 550, 522, 183);
		frame.getContentPane().add(layeredPane);
		
		exit = new JButton("Exit");
		exit.setBounds(0, 0, 522, 183);
		layeredPane.add(exit);
		
		panel = new JPanel();
		layeredPane.setLayer(panel, 1);
		panel.setBounds(0, 0, 522, 183);
		layeredPane.add(panel);
		panel.setLayout(null);
		
		Attack = new JButton("Attack");
		Attack.setBounds(0, 0, 184, 183);
		panel.add(Attack);
		layeredPane.setLayer(Attack, 1);
		
		View = new JButton("Inspect ");
		View.setBounds(183, 0, 172, 183);
		panel.add(View);
		layeredPane.setLayer(View, 1);
		
		monMenu = new JButton("Monster Menu");
		monMenu.setBounds(350, 0, 172, 183);
		panel.add(monMenu);
		monMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuWindow(player, getSelf());
				getSelf().closeWindow();
			}
		});
		layeredPane.setLayer(monMenu, 1);
		Attack.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			player.getActiveMonster().attack(leading);
			printMsg(String.format("%s dealt %s damage to %s", player.getActiveMonster().getName(), player.getActiveMonster().getDamage(), leading.getName()));
			checkFoe();
		
		}
		}
		);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				battle.setDone(true);
				mainWindow.Show_2();
				frame.dispose();
			}
		});
		frame.setVisible(true);
		printMsg(String.format("%s HP: %s/%s DMG: %s\n%s HP: %s/%s DMG: %s", leading.getName(), leading.getCurrentHealth(),leading.getMaxHealth(),leading.getDamage(), player.getActiveMonster().getName(),player.getActiveMonster().getCurrentHealth(),player.getActiveMonster().getMaxHealth(),player.getActiveMonster().getDamage()));
	
		
	}
	

	
	/**An instance method, used to check if the monster that the user is currently fighting against, is downed, returns void**/
	public void checkFoe() {
		if ((leading.getCurrentHealth() == 0 )) {
			printMsg(leading.getName() + " is down!");
			if (battle.getFoeMonsters().indexOf(leading) == battle.getFoeMonsters().size() - 1) {
				won();
			}
			else {
				leading = battle.getLeading();
				if (hard) {
					leading.setDamageAmount(leading.getDamage() + dmgIncrease);
				}
			}
		}
		else {
			leading.attack(player.getActiveMonster());
			printMsg(String.format("%s dealt %s damage to %s", leading.getName(), leading.getDamage(), player.getActiveMonster().getName()));
			if (player.getActiveMonster().getCurrentHealth() == 0) {
				printMsg(String.format("%s is out!", player.getActiveMonster().getName()));
				player.getActiveMonster().setDaysFainted(player.getActiveMonster().getdaysFainted() + 1);
				player.getActiveMonster().setFaintedToday(true);
				if (battle.checkAllDowned(player)) {
					lost();
				}
				else {
					player.nextMon();
				}
			}
		}
		printMsg(String.format("%s HP: %s/%s DMG: %s\n%s HP: %s/%s DMG: %s", leading.getName(), leading.getCurrentHealth(),leading.getMaxHealth(),leading.getDamage(), player.getActiveMonster().getName(),player.getActiveMonster().getCurrentHealth(),player.getActiveMonster().getMaxHealth(),player.getActiveMonster().getDamage()));
	
	}
	/**An instance method, used when the user has "Won" the battle increases the points of the users monsters, the users points and gold also displays a message, and sets the exit button to be visible, returns void**/
	public void won() {
		pane.setText("");
		layeredPane.setLayer(panel, 0);
		layeredPane.setLayer(exit, 1);
		printMsg("You win!!!");
		for (Monster mon : player.getMonsters()) {
			mon.increasePoints(goldEarned, getSelf());
			printMsg(String.format("%s's points increased by %s", mon.getName(), goldEarned));
		}
		player.setGold(player.getGold() + goldEarned);
		printMsg(String.format("You've gained %s gold", goldEarned));
		
	}
	/**An instance method, called when the user loses this battle displays a message, and sets the exit button to be visible, returns void**/ 
	public void lost() {
		pane.setText("");
		layeredPane.setLayer(panel, 0);
		layeredPane.setLayer(exit, 1);
		printMsg("You lost, better luck next time!");
		
	}
}

