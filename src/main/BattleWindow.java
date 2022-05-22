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

public class BattleWindow extends GameWindow{
	private JComboBox combobox;
	private JLayeredPane pane;
	private JButton btnNewButton_1;
	private JButton Attack;
	private JButton View;
	private JButton pass_time;
	private Player player = new Player();
	private JFrame frame;
	private MainWindow mainWindow;
	private Monster leading;
	private Battle battle;
	private int dmgIncrease;
	private int goldEarned;
	private JLayeredPane layeredPane;
	private JButton exit;
	private JPanel panel;
	private boolean hard;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public BattleWindow(MainWindow window, Battle battle, Player player, int time, boolean hard, int rank) {
		int mult = 1;
		if (hard) {
			mult = 2;
		}
		goldEarned =  5* (time +1) * (rank + 1) * mult;
		dmgIncrease = 5 * (rank + 1) * mult * (time + 1);
		
		this.player = player;
		this.mainWindow = window;	
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
		JTextPane pane = new JTextPane();
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
		
		pass_time = new JButton("Monster Menu");
		pass_time.setBounds(350, 0, 172, 183);
		panel.add(pass_time);
		pass_time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		layeredPane.setLayer(pass_time, 1);
		Attack.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			player.getActiveMonster().attack(leading);
			printMsg(String.format("%s dealt %s damage to %s", player.getActiveMonster().getName(), player.getActiveMonster().getDamage(), leading.getName()));
			checkFoe();
		
		}
		}
		);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainWindow.Show_2();
				frame.dispose();
			}
		});
		frame.setVisible(true);
		printMsg(String.format("%s HP: %s/%s DMG: %s\n%s HP: %s/%s DMG: %s", leading.getName(), leading.getCurrentHealth(),leading.getMaxHealth(),leading.getDamage(), player.getActiveMonster().getName(),player.getActiveMonster().getCurrentHealth(),player.getActiveMonster().getMaxHealth(),player.getActiveMonster().getDamage()));
	
		
	}
	

	
	
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
	public void won() {
		layeredPane.setLayer(panel, 0);
		layeredPane.setLayer(exit, 1);
		for (Monster mon : player.getMonsters()) {
			mon.increasePoints(goldEarned, getSelf());
			printMsg(String.format("%s's points increased by %s", mon.getName(), goldEarned));
		}
		player.setGold(player.getGold() + goldEarned);
		printMsg(String.format("You've gained %s gold", goldEarned));
		
	}
	public void lost() {
		layeredPane.setLayer(panel, 0);
		layeredPane.setLayer(exit, 1);
		printMsg("You lost lol");
		
	}
}

