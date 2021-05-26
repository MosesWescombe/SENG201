package gui;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import core.GameEnvironment;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 * Main Menu window. Contains links to all other windows and functionalities.
 * 
 * MainScreen - Window object
 */
public class MainWindow {

	private JFrame mainScreen;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		mainScreen.setVisible(true);
	}

	/**
	 * Exit the game.
	 */
	public void closeMainMenu() {
		closeWindow();
		GameEnvironment.exit("Farewell Traveller");
	}
	
	/**
	 * Open Islands window
	 */
	private void openViewIslands() {
		new ViewIslandsWindow();
		this.closeWindow();
	}
	
	/**
	 * Open Sail window
	 */
	private void openSailScreen() {
		new SailWindow();
		this.closeWindow();
	}

	/**
	 * Refresh window
	 */
	private void openNewStore() {
		new StoreWindow();
		this.closeWindow();
	}

	private void closeWindow() {
		mainScreen.dispose();
	}

	/**
	 * Repair Action. Calls Repair function from Ship class
	 */
	private void repair() {
		if (GameEnvironment.getPlayerShip().getHealth() < GameEnvironment.getPlayerShip().getMaxHealth()) {
			String result = GameEnvironment.getPlayerShip().repair();

			JOptionPane.showMessageDialog(mainScreen, result, "Successfully Repaired", JOptionPane.INFORMATION_MESSAGE);
			this.closeWindow();
			new MainWindow();
		}
	}

	/**
	 * Initialize the contents of the mainScreen.
	 */
	private void initialize() {
		mainScreen = new JFrame();
		mainScreen.setTitle("Trader Game - Main Menu");
		mainScreen.getContentPane().setBackground(SystemColor.menu);
		mainScreen.setBounds(100, 100, 1183, 840);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.getContentPane().setLayout(null);

		//Main Title
		JTextPane txtpnMainMenu = new JTextPane();
		txtpnMainMenu.setForeground(new Color(255, 255, 255));
		txtpnMainMenu.setEditable(false);
		txtpnMainMenu.setFont(new Font("Eras Bold ITC", Font.PLAIN, 55));
		txtpnMainMenu.setText("                               Main Menu");
		txtpnMainMenu.setBackground(new Color(0, 0, 51));
		txtpnMainMenu.setBounds(0, 0, 1167, 78);
		mainScreen.getContentPane().add(txtpnMainMenu);
		
		//View Transactions
		JButton btnViewTransactions = new JButton("View Sales");
		btnViewTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//View Transactions Grid
				String[] collumnNames = {"Name", "Sale Location", "Purchase Price", "Sale Price"}; //Titles
				//Object[][] transactionHistory = {null, null, null, null};
				Object[][] transactionHistory = GameEnvironment.getPlayerShip().getTransactionHistoryObjects();
				
				JTable transactionsTable = new JTable(transactionHistory, collumnNames);
				transactionsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				transactionsTable.setDefaultEditor(Object.class, null);
				transactionsTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
				transactionsTable.setFillsViewportHeight(true);
				transactionsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				transactionsTable.getColumnModel().getColumn(0).setPreferredWidth(300);
				transactionsTable.getColumnModel().getColumn(1).setPreferredWidth(300);
				transactionsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
				transactionsTable.getColumnModel().getColumn(3).setPreferredWidth(100);

				JScrollPane scrollPane = new JScrollPane(transactionsTable);
				scrollPane.setSize(new Dimension(804, 900));
				scrollPane.setPreferredSize(new Dimension(804, scrollPane.getPreferredSize().height));

				//Display in popup window
				JOptionPane.showMessageDialog(mainScreen, scrollPane, "Transactions", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnViewTransactions.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnViewTransactions.setBackground(UIManager.getColor("Button.darkShadow"));
		btnViewTransactions.setBounds(30, 515, 228, 73);
		mainScreen.getContentPane().add(btnViewTransactions);
		
		//view Islands button
		JButton btnViewIslands = new JButton("View Islands");
		btnViewIslands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openViewIslands();
			}
		});
		btnViewIslands.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnViewIslands.setBackground(UIManager.getColor("Button.darkShadow"));
		btnViewIslands.setBounds(30, 615, 228, 73);
		mainScreen.getContentPane().add(btnViewIslands);
		
		//Visit Store button
		JButton btnVisitStore = new JButton("Visit Store");
		btnVisitStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openNewStore();
			}
		});
		btnVisitStore.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnVisitStore.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVisitStore.setBounds(288, 515, 228, 73);
		mainScreen.getContentPane().add(btnVisitStore);
		
		//Sail Button
		JButton btnSail = new JButton("Sail");
		btnSail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GameEnvironment.getPlayerShip().getHealth() == GameEnvironment.getPlayerShip().getMaxHealth()) {
				openSailScreen();
				} else {
					JOptionPane.showMessageDialog(mainScreen, "Your Ship needs repairs before you can sail!", "Ship Repairs Needed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSail.setBackground(UIManager.getColor("Button.darkShadow"));
		btnSail.setBounds(288, 615, 228, 73);
		mainScreen.getContentPane().add(btnSail);
		
		//End Game button
		JButton btnEndGame = new JButton("End Game");
		btnEndGame.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnEndGame.setBackground(UIManager.getColor("Button.darkShadow"));
		btnEndGame.setBounds(30, 715, 486, 73);
		mainScreen.getContentPane().add(btnEndGame);
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeMainMenu();
			}
		});
		
		JPanel topPanel = new JPanel();
		topPanel.setBorder(null);
		topPanel.setBackground(SystemColor.menu);
		topPanel.setBounds(30, 100, 1104, 251);
		mainScreen.getContentPane().add(topPanel);
		topPanel.setLayout(null);
		
			JPanel cargoPanel = new JPanel();
			cargoPanel.setBackground(Color.WHITE);
			cargoPanel.setBounds(511, 0, 593, 251);
			topPanel.add(cargoPanel);
				
				//List Current ship cargo in JTable
				Object[][] cargo = GameEnvironment.getPlayerShip().getCargoObjects();
				//Object[][] cargo = {{"","", ""},{"","", ""}};
				String[] cargoTableHeader = {"Name", "Description", "Weight (kg)"};
				cargoPanel.setLayout(null);


				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(0, 0, 593, 251);
				cargoPanel.add(scrollPane_1);
				
				JTable cargoTable = new JTable(cargo, cargoTableHeader);
				cargoTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
				cargoTable.setRowSelectionAllowed(false);
				cargoTable.setEnabled(false);
				cargoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				cargoTable.getColumnModel().getColumn(0).setPreferredWidth(190);
				cargoTable.getColumnModel().getColumn(1).setPreferredWidth(300);
				cargoTable.getColumnModel().getColumn(2).setPreferredWidth(100);
				scrollPane_1.setViewportView(cargoTable);
				
			JPanel imagePanel = new JPanel();
			imagePanel.setBackground(Color.WHITE);
			imagePanel.setBounds(0, -10, 485, 261);
			topPanel.add(imagePanel);
			
			JLabel lblImage = new JLabel("");
			lblImage.setIcon(new ImageIcon(MainWindow.class.getResource("/images/Main ship image.png")));
			imagePanel.add(lblImage);
		
		JPanel shipPropertiesPanel = new JPanel();
		shipPropertiesPanel.setLayout(null);
		shipPropertiesPanel.setBorder(null);
		shipPropertiesPanel.setBackground(Color.WHITE);
		shipPropertiesPanel.setBounds(541, 374, 593, 414);
		mainScreen.getContentPane().add(shipPropertiesPanel);

			//Title
			JLabel lblShipProperties = new JLabel("Ship Properties for ");
			lblShipProperties.setText(lblShipProperties.getText() + GameEnvironment.getPlayerShip().getName());
			lblShipProperties.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblShipProperties.setBounds(170, 11, 267, 38);
			shipPropertiesPanel.add(lblShipProperties);

			//Capacity
			JLabel lblCapacity = new JLabel("Capacity: ");
			lblCapacity.setText(lblCapacity.getText() + GameEnvironment.getPlayerShip().getCapacity() + "/" + GameEnvironment.getPlayerShip().getmaxCapacity() + "kg");
			lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblCapacity.setBounds(352, 99, 165, 38);
			shipPropertiesPanel.add(lblCapacity);
			
			//Speed
			JLabel lblSpeed = new JLabel("Speed: ");
			lblSpeed.setText(lblSpeed.getText() + GameEnvironment.getPlayerShip().getSailSpeed() + "km/h");
			lblSpeed.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblSpeed.setBounds(53, 96, 150, 44);
			shipPropertiesPanel.add(lblSpeed);
			
			//Health
			JLabel lblHealth = new JLabel("Health: ");
			lblHealth.setText(lblHealth.getText() + GameEnvironment.getPlayerShip().getHealth() + "/" + GameEnvironment.getPlayerShip().getMaxHealth());
			lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblHealth.setBounds(53, 45, 161, 54);
			shipPropertiesPanel.add(lblHealth);
			
			//Crew
			JLabel lblCrew = new JLabel("Crew: ");
			lblCrew.setText(lblCrew.getText() + GameEnvironment.getPlayerShip().getCrew());
			lblCrew.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblCrew.setBounds(356, 53, 145, 38);
			shipPropertiesPanel.add(lblCrew);

			//Repair Cost
			JLabel lblRepairCost = new JLabel("Repair Cost: $" + (GameEnvironment.getPlayerShip().getMaxHealth() - GameEnvironment.getPlayerShip().getHealth()));
			lblRepairCost.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRepairCost.setBounds(53, 151, 178, 31);
			shipPropertiesPanel.add(lblRepairCost);
			
			//Repair Button
			JButton btnRepair = new JButton("Repair");
			btnRepair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					repair();
				}
			});
			btnRepair.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnRepair.setBackground(UIManager.getColor("Button.darkShadow"));
			btnRepair.setBounds(352, 151, 145, 31);
			shipPropertiesPanel.add(btnRepair);
			
			//Upgrades Title
			JLabel lblShipUpgrades = new JLabel("Ship Upgrades");
			lblShipUpgrades.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblShipUpgrades.setBounds(191, 175, 218, 38);
			shipPropertiesPanel.add(lblShipUpgrades);
			
			//Upgrades Table
			Object[][] upgrades = GameEnvironment.getPlayerShip().getUpgradeObjects();
			//Object[][] upgrades = {{"",""},{"",""}};
			String[] header = {"Name", "Description"};


			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 228, 573, 175);
			shipPropertiesPanel.add(scrollPane);
			
			JTable upgradeTable = new JTable(upgrades, header);
			upgradeTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
			upgradeTable.setRowSelectionAllowed(false);
			upgradeTable.setEnabled(false);
			upgradeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			upgradeTable.getColumnModel().getColumn(0).setPreferredWidth(230);
			upgradeTable.getColumnModel().getColumn(1).setPreferredWidth(340);
			scrollPane.setViewportView(upgradeTable);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setBackground(Color.WHITE);
		playerPanel.setBounds(30, 374, 486, 115);
		mainScreen.getContentPane().add(playerPanel);
		playerPanel.setLayout(null);
			
			//Welcome Title
			JLabel lblWelcome = new JLabel("Welcome, ");
			lblWelcome.setText(lblWelcome.getText() + GameEnvironment.getPlayer().getName());
			lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblWelcome.setBounds(10, 11, 189, 46);
			playerPanel.add(lblWelcome);
			
			//Wallet
			JLabel lblWallet = new JLabel("Wallet: $");
			lblWallet.setText(lblWallet.getText() + GameEnvironment.getPlayer().getWallet());
			lblWallet.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblWallet.setBounds(260, 68, 160, 36);
			playerPanel.add(lblWallet);
			
			//Time
			JLabel lblDuration = new JLabel("Time Remaining: ");
			lblDuration.setText(lblDuration.getText() + GameEnvironment.getTime().getTimeRemaining() + " days");
			lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblDuration.setBounds(10, 72, 216, 29);
			playerPanel.add(lblDuration);
			
			//Location
			JLabel lblLocation = new JLabel("Location: ");
			lblLocation.setText(lblLocation.getText() + GameEnvironment.getPlayerShip().getLocation().getName());
			lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblLocation.setBounds(260, 20, 216, 29);
			playerPanel.add(lblLocation);
	}
}
