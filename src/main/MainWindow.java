package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class MainWindow {


	private JFrame frame;
	private JTextField txtInputHere;
	private JComboBox combobox;
	private JLayeredPane pane;
	private JTextPane txtpnpleaseInputYour;
	private boolean buttonPressed = false;
	private int state = 0;
	private int p_state = 0;
	private String str;
	private Player player = new Player();
	private Monster[] starters = new Monster[] {new Vesuvius(), new Everest(), new Parihaka(), new Flame(), new Jerry()};
	private List<Monster> starterList;
	private int index;
	private JButton btnNewButton_2;
	private JButton btnNewButton_5;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1125, 827);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtpnpleaseInputYour = new JTextPane();
		txtpnpleaseInputYour.setText("Please input your Player name");
		txtpnpleaseInputYour.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtpnpleaseInputYour.setEditable(false);
		txtpnpleaseInputYour.setBounds(20, 11, 1067, 522);
		frame.getContentPane().add(txtpnpleaseInputYour);
		 
		JButton btnNewButton = new JButton("Disabled");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(state);
				switch (p_state) {
					case 0:
						switch (state) {
						case 0:
							printMsg("Please input your Player name\n(Note: Must be between 3 and 15 characters and no numbers or special characters");
							str = txtInputHere.getText();
							if (!Player.checkName(str)) {
								printMsg("Invalid name");
							}
							else {
								state += 1;
								starterList = pickMonsters(player);
							}
							txtInputHere.setText("");
							break;
						case 1:
							if (checkInt(txtInputHere.getText())) {
								if (checkInt(txtInputHere.getText(), 1, 3)) {
									index = Integer.parseInt(txtInputHere.getText());
									state += 1;
									printMsg("Time to name your Monster\nInput your monsters name\n(Note: If you input nothing your monster will be given the default name");
								}
							}
							txtInputHere.setText("");
							break;
						
						case 2:
							if (txtInputHere.getText().strip() == "") {
					        	player.addMonster(starterList.get(index));
					        	printMsg("worked");
					        }
					        else {
					        	starterList.get(index).setName(txtInputHere.getText());
					        	player.addMonster(starterList.get(index - 1));
					        	printMsg("Picked" + starterList.get(index-1));
					        	state = 0;
					        	p_state += 1;					        						        	
					        }
						 	break;
						}
						
						
				}
				
				System.out.println(state);
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				
		}});
		btnNewButton.setBounds(10, 550, 545, 183);
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
		
		JButton button = new JButton("New button");
		button.setBounds(0, 0, 216, 172);
		layeredPane.add(button);
		
		btnNewButton_2 = new JButton("View Battles");
		layeredPane.setLayer(btnNewButton_2, 1);
		btnNewButton_2.setBounds(0, 0, 184, 93);
		layeredPane.add(btnNewButton_2);
		
		btnNewButton_5 = new JButton("Open your monsters menu");
		layeredPane.setLayer(btnNewButton_5, 1);
		btnNewButton_5.setBounds(0, 90, 184, 93);
		layeredPane.add(btnNewButton_5);
		
		btnNewButton_3 = new JButton("view inventory");
		layeredPane.setLayer(btnNewButton_3, 1);
		btnNewButton_3.setBounds(183, 90, 172, 93);
		layeredPane.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Go to Shop");
		layeredPane.setLayer(btnNewButton_4, 1);
		btnNewButton_4.setBounds(183, 0, 172, 93);
		layeredPane.add(btnNewButton_4);
		
		btnNewButton_6 = new JButton("view yourself");
		layeredPane.setLayer(btnNewButton_6, 1);
		btnNewButton_6.setBounds(350, 90, 172, 93);
		layeredPane.add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("Pass time");
		layeredPane.setLayer(btnNewButton_7, 1);
		btnNewButton_7.setBounds(350, 0, 172, 93);
		layeredPane.add(btnNewButton_7);
		
	}
}
