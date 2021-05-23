package gui;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import core.GameEnvironment;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class MainWindow {

	private JFrame mainScreen;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		mainScreen.setVisible(true);
	}
	
	public void closeWindow() {
		mainScreen.dispose();
	}

	public void closeMainMenu() {
		GameEnvironment.closeMainScreen(this);
	}
	
	private void openViewIslands() {
		GameEnvironment.viewIslands(this);
	}

	private void openStore() {
		GameEnvironment.openStoreWindow();
		this.closeWindow();
	}

	/**
	 * Initialize the contents of the mainScreen.
	 */
	private void initialize() {
		mainScreen = new JFrame();
		mainScreen.setBounds(100, 100, 880, 840);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.getContentPane().setLayout(null);
		
		JTextPane txtpnMainMenu = new JTextPane();
		txtpnMainMenu.setEditable(false);
		txtpnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtpnMainMenu.setText("                   Main Menu");
		txtpnMainMenu.setBackground(Color.LIGHT_GRAY);
		txtpnMainMenu.setBounds(0, 0, 864, 78);
		mainScreen.getContentPane().add(txtpnMainMenu);
		
		JButton btnViewTransactions = new JButton("View Transactions");
		btnViewTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnViewTransactions.setBackground(SystemColor.activeCaptionBorder);
			}
		});
		btnViewTransactions.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnViewTransactions.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnViewTransactions.setBounds(30, 515, 209, 73);
		mainScreen.getContentPane().add(btnViewTransactions);
		
		JButton btnViewShip = new JButton("View Ship");
		btnViewShip.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnViewShip.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnViewShip.setBounds(30, 717, 209, 73);
		mainScreen.getContentPane().add(btnViewShip);
		
		JButton btnViewIslands = new JButton("View Islands");
		btnViewIslands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openViewIslands();
			}
		});
		btnViewIslands.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnViewIslands.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnViewIslands.setBounds(30, 615, 209, 73);
		mainScreen.getContentPane().add(btnViewIslands);
		
		JButton btnVisitStore = new JButton("Visit Store");
		btnVisitStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openStore();
			}
		});
		btnVisitStore.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnVisitStore.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnVisitStore.setBounds(263, 515, 209, 73);
		mainScreen.getContentPane().add(btnVisitStore);
		
		JButton btnSail = new JButton("Sail");
		btnSail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSail.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnSail.setBounds(263, 615, 209, 73);
		mainScreen.getContentPane().add(btnSail);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnExit.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnExit.setBounds(263, 717, 209, 73);
		mainScreen.getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeMainMenu();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(30, 100, 801, 263);
		mainScreen.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		// JList list_1 = new JList();
		// list_1.setBounds(791, 11, -779, 241);
		// panel_1.add(list_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(496, 374, 335, 414);
		mainScreen.getContentPane().add(panel_2);
		
		JLabel lblCapacity = new JLabel("Capacity: ");
		lblCapacity.setText(lblCapacity.getText() + GameEnvironment.game.getPlayer().getShip().getCapacity() + "/" + GameEnvironment.game.getPlayer().getShip().getmaxCapacity() + "kg");
		lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCapacity.setBounds(170, 85, 165, 38);
		panel_2.add(lblCapacity);
		
		JLabel lblSpeed = new JLabel("Speed: ");
		lblSpeed.setText(lblSpeed.getText() + GameEnvironment.game.getPlayer().getShip().getSailSpeed() + "km/h");
		lblSpeed.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSpeed.setBounds(10, 82, 150, 44);
		panel_2.add(lblSpeed);
		
		JLabel lblHealth = new JLabel("Health: ");
		lblHealth.setText(lblHealth.getText() + GameEnvironment.game.getPlayer().getShip().getHealth() + "/" + GameEnvironment.game.getPlayer().getShip().getMaxHealth());
		lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHealth.setBounds(10, 37, 161, 54);
		panel_2.add(lblHealth);
		
		JLabel lblShipProperties = new JLabel("Ship Properties for ");
		lblShipProperties.setText(lblShipProperties.getText() + GameEnvironment.game.getPlayer().getShip().getName());
		lblShipProperties.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblShipProperties.setBounds(10, 0, 267, 38);
		panel_2.add(lblShipProperties);
		
		JLabel lblCrew = new JLabel("Crew: ");
		lblCrew.setText(lblCrew.getText() + GameEnvironment.game.getPlayer().getShip().getCrew());
		lblCrew.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrew.setBounds(170, 45, 145, 38);
		panel_2.add(lblCrew);
		
		// JList list = new JList();
		// list.setBackground(UIManager.getColor("Button.background"));
		// list.setBounds(10, 228, 315, 175);
		// panel_2.add(list);
		
		JLabel lblShipUpgrades = new JLabel("Ship Upgrades");
		lblShipUpgrades.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblShipUpgrades.setBounds(10, 179, 218, 38);
		panel_2.add(lblShipUpgrades);
		
		JLabel lblRepairCost = new JLabel("Repair Cost: ");
		lblRepairCost.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRepairCost.setBounds(10, 126, 128, 31);
		panel_2.add(lblRepairCost);
		
		JButton btnRepair = new JButton("Repair");
		btnRepair.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRepair.setBackground(SystemColor.activeCaptionBorder);
		btnRepair.setBounds(170, 124, 145, 31);
		panel_2.add(btnRepair);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(30, 374, 442, 115);
		mainScreen.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome, ");
		lblWelcome.setText(lblWelcome.getText() + GameEnvironment.game.getPlayer().getName());
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWelcome.setBounds(10, 11, 189, 46);
		panel_3.add(lblWelcome);
		
		JLabel lblWallet = new JLabel("Wallet: $");
		lblWallet.setText(lblWallet.getText() + GameEnvironment.game.getPlayer().getWallet());
		lblWallet.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWallet.setBounds(236, 68, 160, 36);
		panel_3.add(lblWallet);
		
		JLabel lblDuration = new JLabel("Time Remaining: ");
		lblDuration.setText(lblDuration.getText() + GameEnvironment.time.getTimeRemaining() + "days");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDuration.setBounds(10, 72, 216, 29);
		panel_3.add(lblDuration);
		
		JLabel lblLocation = new JLabel("Location: ");
		lblLocation.setText(lblLocation.getText() + GameEnvironment.game.getPlayer().getShip().getLocation().getName());
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLocation.setBounds(236, 20, 216, 29);
		panel_3.add(lblLocation);
	}
}
