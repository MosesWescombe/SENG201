package gui;
import core.GameEnvironment;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class StartMenu {
	
	private JFrame startMenuWindow;
	private JTextField playerNameInput;
	private JTextField shipNameInput;
	private JSlider gameDuration;
	private int shipType = 1;
	private JLabel playerNameWarning;
	private JLabel shipNameWarning;

	/**
	 * Create the application.
	 */
	public StartMenu() {
		initialize();
		startMenuWindow.setVisible(true);
	}
	
	public void closeWindow() {
		startMenuWindow.dispose();
	}

	public void returnToGame() {
		/**Returns to the GameEnvironment with the windows variables*/
		boolean end = true;
		if (playerNameInput.getText().length() < 3 || playerNameInput.getText().length() > 15) {
			playerNameWarning.setVisible(true);
			end = false;
		} else {
			playerNameWarning.setVisible(false);
		}
		
		if (shipNameInput.getText().length() < 3 || shipNameInput.getText().length() > 15) {
			shipNameWarning.setVisible(true);
			end = false;
		} else {
			shipNameWarning.setVisible(false);
		}
		
		
		if (!end) {
			return;
		}
		
		
		playerNameWarning.setVisible(false);
		shipNameWarning.setVisible(false);
		//Go back to game environment
		GameEnvironment.closeStartMenu(this);
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JButton cargoShip = new JButton("Cargo Ship");
		JButton battleShip = new JButton("Battle Ship");
		JButton cruiserShip = new JButton("Cruiser Ship");
		JButton dingy = new JButton("Dingy Ship");
		
		
		startMenuWindow = new JFrame();
		startMenuWindow.setTitle("Trader Game");
		startMenuWindow.setBounds(100, 100, 880, 840);
		startMenuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startMenuWindow.getContentPane().setLayout(null);
		
		//JButton cargoShip = new JButton("Cargo Ship");
		cargoShip.setBackground(SystemColor.activeCaptionBorder);
		cargoShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipType = 1;
				resetButton(cargoShip, battleShip, cruiserShip, dingy);
				cargoShip.setBackground(SystemColor.activeCaptionBorder);
			}
		});
		cargoShip.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cargoShip.setBounds(10, 495, 177, 73);
		startMenuWindow.getContentPane().add(cargoShip);
		
		//JButton battleShip = new JButton("Battle Ship");
		battleShip.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		battleShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipType = 2;
				resetButton(cargoShip, battleShip, cruiserShip, dingy);
				battleShip.setBackground(SystemColor.activeCaptionBorder);
			}
		});
		battleShip.setFont(new Font("Tahoma", Font.PLAIN, 22));
		battleShip.setBounds(227, 495, 177, 73);
		startMenuWindow.getContentPane().add(battleShip);
		
		//JButton cruiserShip = new JButton("Cruiser Ship");
		cruiserShip.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		cruiserShip.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cruiserShip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipType = 3;
				resetButton(cargoShip, battleShip, cruiserShip, dingy);
				cruiserShip.setBackground(SystemColor.activeCaptionBorder);
			}
		});
		cruiserShip.setBounds(458, 495, 177, 73);
		startMenuWindow.getContentPane().add(cruiserShip);
		
		//JButton dingy = new JButton("Dingy Ship");
		dingy.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		dingy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shipType = 4;
				resetButton(cargoShip, battleShip, cruiserShip, dingy);
				dingy.setBackground(SystemColor.activeCaptionBorder);
			}
		});
		dingy.setFont(new Font("Tahoma", Font.PLAIN, 22));
		dingy.setBounds(675, 495, 177, 73);
		startMenuWindow.getContentPane().add(dingy);
		
		JTextPane txtpnThisIsA = new JTextPane();
		txtpnThisIsA.setEditable(false);
		txtpnThisIsA.setBackground(Color.LIGHT_GRAY);
		txtpnThisIsA.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtpnThisIsA.setText("                  Trader Game");
		txtpnThisIsA.setBounds(0, 0, 862, 81);
		startMenuWindow.getContentPane().add(txtpnThisIsA);
		
		playerNameInput = new JTextField();
		playerNameInput.setText("Default");
		playerNameInput.setFont(new Font("Tahoma", Font.PLAIN, 22));
		playerNameInput.setBounds(37, 212, 319, 58);
		startMenuWindow.getContentPane().add(playerNameInput);
		playerNameInput.setColumns(10);
		
		shipNameInput = new JTextField();
		shipNameInput.setText("Default");
		shipNameInput.setFont(new Font("Tahoma", Font.PLAIN, 22));
		shipNameInput.setColumns(10);
		shipNameInput.setBounds(500, 212, 319, 58);
		startMenuWindow.getContentPane().add(shipNameInput);
		
		JLabel lblNewLabel = new JLabel("                Player Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(37, 165, 319, 25);
		startMenuWindow.getContentPane().add(lblNewLabel);
		
		JLabel lblShipName = new JLabel("                    Ship Name");
		lblShipName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblShipName.setBounds(500, 165, 319, 25);
		startMenuWindow.getContentPane().add(lblShipName);
		
		JLabel lblNewLabel_1 = new JLabel("Select Ship");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(366, 403, 177, 66);
		startMenuWindow.getContentPane().add(lblNewLabel_1);
		
		gameDuration = new JSlider();
		gameDuration.setValue(35);
		gameDuration.setPaintTicks(true);
		gameDuration.setPaintLabels(true);
		gameDuration.setMajorTickSpacing(5);
		gameDuration.setMinimum(20);
		gameDuration.setMaximum(50);
		gameDuration.setMinorTickSpacing(1);
		gameDuration.setSnapToTicks(true);
		gameDuration.setBounds(37, 359, 782, 45);
		startMenuWindow.getContentPane().add(gameDuration);
		
		JLabel lblNewLabel_1_1 = new JLabel("Choose Game Duration. Less is harder");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(227, 292, 556, 66);
		startMenuWindow.getContentPane().add(lblNewLabel_1_1);
		
		JTextPane txtpnfasterweakeraverage = new JTextPane();
		txtpnfasterweakeraverage.setEditable(false);
		txtpnfasterweakeraverage.setBackground(SystemColor.controlHighlight);
		txtpnfasterweakeraverage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnfasterweakeraverage.setText("-Faster\r\n-Weaker\r\n-Average Cargo\r\n-Average Crew Size");
		txtpnfasterweakeraverage.setBounds(458, 593, 177, 105);
		startMenuWindow.getContentPane().add(txtpnfasterweakeraverage);
		
		JTextPane txtpnslowerstrongeraverage = new JTextPane();
		txtpnslowerstrongeraverage.setEditable(false);
		txtpnslowerstrongeraverage.setText("-Slower\r\n-Stronger\r\n-Average Cargo\r\n-Large Crew Size");
		txtpnslowerstrongeraverage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnslowerstrongeraverage.setBackground(SystemColor.controlHighlight);
		txtpnslowerstrongeraverage.setBounds(227, 593, 177, 105);
		startMenuWindow.getContentPane().add(txtpnslowerstrongeraverage);
		
		JTextPane txtpnslowestaverageHealth = new JTextPane();
		txtpnslowestaverageHealth.setEditable(false);
		txtpnslowestaverageHealth.setText("-Slowest\r\n-Average Health\r\n-Large Cargo\r\n-Average Crew Size");
		txtpnslowestaverageHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnslowestaverageHealth.setBackground(SystemColor.controlHighlight);
		txtpnslowestaverageHealth.setBounds(10, 593, 177, 105);
		startMenuWindow.getContentPane().add(txtpnslowestaverageHealth);
		
		JTextPane txtpnslowestweakestsmallest = new JTextPane();
		txtpnslowestweakestsmallest.setEditable(false);
		txtpnslowestweakestsmallest.setText("-Slowest\r\n-Weakest\r\n-Smallest Cargo\r\n-Smallest Crew");
		txtpnslowestweakestsmallest.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtpnslowestweakestsmallest.setBackground(SystemColor.controlHighlight);
		txtpnslowestweakestsmallest.setBounds(675, 593, 177, 105);
		startMenuWindow.getContentPane().add(txtpnslowestweakestsmallest);
		
		JButton btnNewButton_2 = new JButton("START GAME");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToGame();
			}
		});
		btnNewButton_2.setBounds(10, 722, 842, 73);
		startMenuWindow.getContentPane().add(btnNewButton_2);
		
		playerNameWarning = new JLabel("Please select a string between 3 and 15 characters.");
		playerNameWarning.setVisible(false);
		playerNameWarning.setForeground(new Color(255, 0, 0));
		playerNameWarning.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerNameWarning.setBounds(23, 280, 381, 25);
		startMenuWindow.getContentPane().add(playerNameWarning);
		
		shipNameWarning = new JLabel("Please select a string between 3 and 15 characters.");
		shipNameWarning.setVisible(false);
		shipNameWarning.setForeground(new Color(255, 0, 0));
		shipNameWarning.setFont(new Font("Tahoma", Font.PLAIN, 16));
		shipNameWarning.setBounds(471, 280, 381, 25);
		startMenuWindow.getContentPane().add(shipNameWarning);
		
	}
	
	private void resetButton(JButton cargoShip, JButton battleShip, JButton cruiserShip, JButton dingy) {
		cargoShip.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		battleShip.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		dingy.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		cruiserShip.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		
	}

	public JTextField getPlayerNameInput() {
		return playerNameInput;
	}

	public JTextField getShipNameInput() {
		return shipNameInput;
	}

	public JSlider getGameDuration() {
		return gameDuration;
	}

	public int getShipType() {
		return shipType;
	}
}
