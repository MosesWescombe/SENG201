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

/**
 * Start Window. Gets users input before calling the setup functions.
 * 
 * StartMenuWindow - Window object
 * PlayerNameInput - Window input for player name
 * ShipNameInput - Window input for ship name
 * GameDuration - Slider selection from 20-50
 * PlayerName&ShipName Warnings - Error message asking user to select correct input
 */
public class StartMenuWindow {
	private JFrame startMenuWindow;
	private JTextField playerNameInput;
	private JTextField shipNameInput;
	private JSlider gameDuration;
	private JLabel playerNameWarning;
	private JLabel shipNameWarning;
	private int shipType = 1;

	/**
	 * Create the application.
	 */
	public StartMenuWindow() {
		initialize();
		startMenuWindow.setVisible(true);
	}
	
	public void closeWindow() {
		startMenuWindow.dispose();
	}

	/**
	 * Return to GameEnvironment passing all required variables. After checking the users input format.
	 */
	public void returnToGame() {
		//Check users input format
		boolean userNameOk = GameEnvironment.checkNameInput(playerNameWarning, playerNameInput.getText());
		boolean shipNameOk = GameEnvironment.checkNameInput(shipNameWarning, shipNameInput.getText());
		if ( !userNameOk || !shipNameOk) {
			return;
		} else {
			//Go back to game environment if the result is good
			String playerName = this.getPlayerNameInput().getText();
			String shipName = this.getShipNameInput().getText();
			int shipType = this.getShipType();
			int gameDuration = this.getGameDuration().getValue();

			GameEnvironment.setUpGame(playerName, shipName, shipType, gameDuration);
			this.closeWindow();
		}
	}

	/**
	 * Toggle Selected button.
	 * 
	 * @param cargoShip Cargo ship button
	 * @param battleShip Battle ship button
	 * @param cruiserShip Cruiser ship button
	 * @param dingy Dingy button
	 */
	private void resetButton(JButton cargoShip, JButton battleShip, JButton cruiserShip, JButton dingy) {
		cargoShip.setBackground(UIManager.getColor("Button.darkShadow"));
		battleShip.setBackground(UIManager.getColor("Button.darkShadow"));
		dingy.setBackground(UIManager.getColor("Button.darkShadow"));
		cruiserShip.setBackground(UIManager.getColor("Button.darkShadow"));
		
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
		
		//Cargo Ship option button
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
		
		//Battle ship button
		battleShip.setBackground(UIManager.getColor("Button.darkShadow"));
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
		
		//Cruiser Ship button
		cruiserShip.setBackground(UIManager.getColor("Button.darkShadow"));
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
		
		//Dingy button
		dingy.setBackground(UIManager.getColor("Button.darkShadow"));
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
		
		JTextPane userInputPanel = new JTextPane();
		userInputPanel.setForeground(new Color(255, 255, 255));
		userInputPanel.setEditable(false);
		userInputPanel.setBackground(new Color(0, 0, 51));
		userInputPanel.setFont(new Font("Eras Bold ITC", Font.PLAIN, 55));
		userInputPanel.setText("                 Trader Game");
		userInputPanel.setBounds(0, 0, 862, 81);
		startMenuWindow.getContentPane().add(userInputPanel);

			JLabel lblPlayerName = new JLabel("                Player Name");
			lblPlayerName.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPlayerName.setBounds(37, 165, 319, 25);
			startMenuWindow.getContentPane().add(lblPlayerName);
			
			JLabel lblShipName = new JLabel("                    Ship Name");
			lblShipName.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblShipName.setBounds(500, 165, 319, 25);
			startMenuWindow.getContentPane().add(lblShipName);
			
			JLabel lblSelectShip = new JLabel("Select Ship");
			lblSelectShip.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblSelectShip.setBounds(366, 403, 177, 66);
			startMenuWindow.getContentPane().add(lblSelectShip);

			//Player name input
			playerNameInput = new JTextField();
			playerNameInput.setFont(new Font("Tahoma", Font.PLAIN, 22));
			playerNameInput.setBounds(37, 212, 319, 58);
			startMenuWindow.getContentPane().add(playerNameInput);
			playerNameInput.setColumns(10);
			
			//Ship name input
			shipNameInput = new JTextField();
			shipNameInput.setFont(new Font("Tahoma", Font.PLAIN, 22));
			shipNameInput.setColumns(10);
			shipNameInput.setBounds(500, 212, 319, 58);
			startMenuWindow.getContentPane().add(shipNameInput);

			JLabel lblGameDuration = new JLabel("Choose Game Duration. Less is harder");
			lblGameDuration.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblGameDuration.setBounds(227, 292, 556, 66);
			startMenuWindow.getContentPane().add(lblGameDuration);
		
			//Game Duration Slider
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
		
		JTextPane txtpnCruiserDetails = new JTextPane();
		txtpnCruiserDetails.setEditable(false);
		txtpnCruiserDetails.setBackground(SystemColor.controlHighlight);
		txtpnCruiserDetails.setFont(new Font("Dialog", Font.BOLD, 16));
		txtpnCruiserDetails.setText("-Fastest\r\n-Weaker\r\n-Average Cargo\r\n-Average Crew Size");
		txtpnCruiserDetails.setBounds(458, 593, 177, 105);
		startMenuWindow.getContentPane().add(txtpnCruiserDetails);
		
		JTextPane txtpnsBattleDetails = new JTextPane();
		txtpnsBattleDetails.setEditable(false);
		txtpnsBattleDetails.setText("-Slower\r\n-Stronger\r\n-Average Cargo\r\n-Large Crew Size");
		txtpnsBattleDetails.setFont(new Font("Dialog", Font.BOLD, 16));
		txtpnsBattleDetails.setBackground(SystemColor.controlHighlight);
		txtpnsBattleDetails.setBounds(227, 593, 177, 105);
		startMenuWindow.getContentPane().add(txtpnsBattleDetails);
		
		JTextPane txtpnCargoDetails = new JTextPane();
		txtpnCargoDetails.setEditable(false);
		txtpnCargoDetails.setText("-Slower\r\n-Average Health\r\n-Large Cargo\r\n-Average Crew Size");
		txtpnCargoDetails.setFont(new Font("Dialog", Font.BOLD, 16));
		txtpnCargoDetails.setBackground(SystemColor.controlHighlight);
		txtpnCargoDetails.setBounds(10, 593, 177, 105);
		startMenuWindow.getContentPane().add(txtpnCargoDetails);
		
		JTextPane txtpnDingyDetails = new JTextPane();
		txtpnDingyDetails.setEditable(false);
		txtpnDingyDetails.setText("-Slowest\r\n-Weakest\r\n-Smallest Cargo\r\n-Smallest Crew");
		txtpnDingyDetails.setFont(new Font("Dialog", Font.BOLD, 16));
		txtpnDingyDetails.setBackground(SystemColor.controlHighlight);
		txtpnDingyDetails.setBounds(675, 593, 177, 105);
		startMenuWindow.getContentPane().add(txtpnDingyDetails);
		
		//Start Game button
		JButton btnStartGame = new JButton("START GAME");
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToGame();
			}
		});
		btnStartGame.setBounds(10, 722, 842, 73);
		startMenuWindow.getContentPane().add(btnStartGame);
		
		//Player name warning
		playerNameWarning = new JLabel("Please input a name (3-15, non special characters)");
		playerNameWarning.setVisible(false);
		playerNameWarning.setForeground(new Color(255, 0, 0));
		playerNameWarning.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerNameWarning.setBounds(23, 280, 408, 25);
		startMenuWindow.getContentPane().add(playerNameWarning);
		
		//Ship name warning
		shipNameWarning = new JLabel("Please input a name (3-15, non special characters)");
		shipNameWarning.setVisible(false);
		shipNameWarning.setForeground(new Color(255, 0, 0));
		shipNameWarning.setFont(new Font("Tahoma", Font.PLAIN, 16));
		shipNameWarning.setBounds(458, 280, 404, 25);
		startMenuWindow.getContentPane().add(shipNameWarning);
		
	}


	//Getters/Setters
	public JTextField getPlayerNameInput() {
		return playerNameInput;
	}
	public JTextField getShipNameInput() {
		return shipNameInput;
	}
	public JSlider getGameDuration() {
		return gameDuration;
	}
	public JLabel getShipNameWarning() {
		return shipNameWarning;
	}
	public void setShipNameWarning(JLabel shipNameWarning) {
		this.shipNameWarning = shipNameWarning;
	}
	public int getShipType() {
		return shipType;
	}
}
