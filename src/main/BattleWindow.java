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

public class BattleWindow extends GameWindow{
	private JComboBox combobox;
	private JLayeredPane pane;
	private boolean buttonPressed = false;
	private JButton btnNewButton_1;
	private JButton Attack;
	private JButton View;
	private JButton pass_time;
	private Player player = new Player();
	private JFrame frame;
	private WindowManager manager;
	private boolean disabled = false;
	private int mode;
	private ArrayList<Battle> battlelist;
	private int time;
	private MainWindow mainWindow;
	private Monster leading;
	private Battle battle;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public BattleWindow(MainWindow window, Battle battle, Player player) {
		this.player = player;
		this.mainWindow = window;	
		leading = battle.getLeading();
		this.battle = battle;
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
		pane.setText("Please input your Player name");
		pane.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pane.setEditable(false);
		pane.setBounds(20, 11, 1067, 522);
		getFrame().getContentPane().add(pane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(283, 550, 522, 183);
		frame.getContentPane().add(layeredPane);
		
		Attack = new JButton("Attack");
		layeredPane.setLayer(Attack, 1);
		Attack.setBounds(0, 0, 184, 183);
		layeredPane.add(Attack);
		Attack.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			printMsg(String.format("%s dealt %s damage to %s", leading.getName(), leading.getDamage(), player.getActiveMonster().getName()));
			checkFoe();
		
		}
		}
		);
		
		View = new JButton("Inspect ");
		layeredPane.setLayer(View, 1);
		View.setBounds(183, 0, 172, 183);
		layeredPane.add(View);
		
		pass_time = new JButton("Monster Menu");
		pass_time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		layeredPane.setLayer(pass_time, 1);
		pass_time.setBounds(350, 0, 172, 183);
		layeredPane.add(pass_time);
		frame.setVisible(true);
		
	
		
	}
	
	public void startBattle() {
		frame.setVisible(false);
		
		
	}
	
	
	public void checkFoe() {
		if ((leading.getCurrentHealth() == 0 )) {
			if (battle.getFoeMonsters().indexOf(leading) == battle.getFoeMonsters().size() - 1) {
				//battle won
			}
			else {
				leading = battle.getLeading();
			}
		}
		else {
			leading.attack(player.getActiveMonster());
			printMsg(String.format("%s dealt %s damage to %s", leading.getName(), leading.getDamage(), player.getActiveMonster().getName()));
			if (player.getActiveMonster().getCurrentHealth() == 0) {
				if (battle.checkAllDowned(player)) {
					//User has lost
				}
				else {
					player.nextMon();
				}
			}
		}
		
	}
}

