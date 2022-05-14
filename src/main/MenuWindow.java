package main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuWindow extends GameWindow{

	private JFrame frame;
	private int state;
	private Player player;
	private JButton viewMonsters;
	private JButton viewItems;
	private JComboBox comboBox_1;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public MenuWindow(Player player) {
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
					printMsg(player.get)
					comboBox.setEnabled(true);
					comboBox_1.setEnabled(true);
					viewItems.setText("Back");
					state = 1;
				}
				
					
			}
		});
		viewMonsters.setBounds(20, 574, 327, 185);
		frame.getContentPane().add(viewMonsters);
		
		viewItems = new JButton("View Items");
		viewItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (state) {
				case 1:
					viewItems.setText("View Items");
					comboBox.setEnabled(false);
					comboBox_1.setEnabled(false);
					state = 0;
					
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
		pane.setVisible(true);
		comboBox.setEnabled(false);
		comboBox_1.setEnabled(false);
	}

}
