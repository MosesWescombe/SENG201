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
	
	private void openSailScreen() {
		GameEnvironment.sailScreen(this);
	}

	private void openStore() {
		GameEnvironment.openStoreWindow();
		this.closeWindow();
	}

	private void repair() {
		if (GameEnvironment.game.getPlayer().getShip().getHealth() < GameEnvironment.game.getPlayer().getShip().getMaxHealth()) {
			String result = GameEnvironment.game.getPlayer().getShip().repair();

			JOptionPane.showMessageDialog(mainScreen, "Successfully Repaired", result, JOptionPane.PLAIN_MESSAGE);
			this.closeWindow();
			new MainWindow();
		}
	}

	/**
	 * Initialize the contents of the mainScreen.
	 */
	private void initialize() {
		mainScreen = new JFrame();
		mainScreen.getContentPane().setBackground(SystemColor.menu);
		mainScreen.setBounds(100, 100, 1183, 840);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.getContentPane().setLayout(null);
		
		JTextPane txtpnMainMenu = new JTextPane();
		txtpnMainMenu.setForeground(new Color(255, 255, 255));
		txtpnMainMenu.setEditable(false);
		txtpnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtpnMainMenu.setText("                             Main Menu");
		txtpnMainMenu.setBackground(new Color(0, 0, 51));
		txtpnMainMenu.setBounds(0, 0, 1167, 78);
		mainScreen.getContentPane().add(txtpnMainMenu);
		
		JButton btnViewTransactions = new JButton("View Sales");
		btnViewTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Items to purchase grid
				String[] collumnNames = {"Name", "Sale Location", "Purchase Price", "Sale Price"}; //Titles
				Object[][] transactionHistory = GameEnvironment.game.getPlayer().getShip().getTransactionHistoryObject();
				
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

				JOptionPane.showMessageDialog(mainScreen, scrollPane, "The store at this island sells", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnViewTransactions.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnViewTransactions.setBackground(UIManager.getColor("Button.darkShadow"));
		btnViewTransactions.setBounds(30, 515, 228, 73);
		mainScreen.getContentPane().add(btnViewTransactions);
		
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
		
		JButton btnVisitStore = new JButton("Visit Store");
		btnVisitStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openStore();
			}
		});
		btnVisitStore.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnVisitStore.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVisitStore.setBounds(288, 515, 228, 73);
		mainScreen.getContentPane().add(btnVisitStore);
		
		JButton btnSail = new JButton("Sail");
		btnSail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GameEnvironment.game.getPlayer().getShip().getHealth() == GameEnvironment.game.getPlayer().getShip().getMaxHealth()) {
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(30, 100, 1104, 251);
		mainScreen.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JPanel cargoPanel = new JPanel();
		cargoPanel.setBackground(Color.WHITE);
		cargoPanel.setBounds(511, 0, 593, 251);
		panel_1.add(cargoPanel);

			Object[][] cargo = GameEnvironment.game.getPlayer().getShip().getCargoObject();
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
		imagePanel.setBounds(0, 0, 485, 251);
		panel_1.add(imagePanel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(null);
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(541, 374, 593, 414);
		mainScreen.getContentPane().add(panel_2);
		
			JLabel lblCapacity = new JLabel("Capacity: ");
			lblCapacity.setText(lblCapacity.getText() + GameEnvironment.game.getPlayer().getShip().getCapacity() + "/" + GameEnvironment.game.getPlayer().getShip().getmaxCapacity() + "kg");
			lblCapacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblCapacity.setBounds(352, 99, 165, 38);
			panel_2.add(lblCapacity);
			
			JLabel lblSpeed = new JLabel("Speed: ");
			lblSpeed.setText(lblSpeed.getText() + GameEnvironment.game.getPlayer().getShip().getSailSpeed() + "km/h");
			lblSpeed.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblSpeed.setBounds(53, 96, 150, 44);
			panel_2.add(lblSpeed);
			
			JLabel lblHealth = new JLabel("Health: ");
			lblHealth.setText(lblHealth.getText() + GameEnvironment.game.getPlayer().getShip().getHealth() + "/" + GameEnvironment.game.getPlayer().getShip().getMaxHealth());
			lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblHealth.setBounds(53, 45, 161, 54);
			panel_2.add(lblHealth);
			
			JLabel lblShipProperties = new JLabel("Ship Properties for ");
			lblShipProperties.setText(lblShipProperties.getText() + GameEnvironment.game.getPlayer().getShip().getName());
			lblShipProperties.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblShipProperties.setBounds(170, 11, 267, 38);
			panel_2.add(lblShipProperties);
			
			JLabel lblCrew = new JLabel("Crew: ");
			lblCrew.setText(lblCrew.getText() + GameEnvironment.game.getPlayer().getShip().getCrew());
			lblCrew.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblCrew.setBounds(356, 53, 145, 38);
			panel_2.add(lblCrew);
			
			JLabel lblShipUpgrades = new JLabel("Ship Upgrades");
			lblShipUpgrades.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblShipUpgrades.setBounds(191, 175, 218, 38);
			panel_2.add(lblShipUpgrades);
			
			JLabel lblRepairCost = new JLabel("Repair Cost: ");
			lblRepairCost.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRepairCost.setBounds(53, 151, 128, 31);
			panel_2.add(lblRepairCost);
			
			JButton btnRepair = new JButton("Repair");
			btnRepair.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					repair();
				}
			});
			btnRepair.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnRepair.setBackground(UIManager.getColor("Button.darkShadow"));
			btnRepair.setBounds(352, 151, 145, 31);
			panel_2.add(btnRepair);
					
			Object[][] upgrades = GameEnvironment.game.getPlayer().getShip().getUpgradeObjects();
			//Object[][] upgrades = {{"",""},{"",""}};
			String[] header = {"Name", "Description"};


			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 228, 573, 175);
			panel_2.add(scrollPane);
			
			JTable upgradeTable = new JTable(upgrades, header);
			upgradeTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
			upgradeTable.setRowSelectionAllowed(false);
			upgradeTable.setEnabled(false);
			upgradeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			upgradeTable.getColumnModel().getColumn(0).setPreferredWidth(230);
			upgradeTable.getColumnModel().getColumn(1).setPreferredWidth(340);
			scrollPane.setViewportView(upgradeTable);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(30, 374, 486, 115);
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
		lblWallet.setBounds(260, 68, 160, 36);
		panel_3.add(lblWallet);
		
		JLabel lblDuration = new JLabel("Time Remaining: ");
		lblDuration.setText(lblDuration.getText() + GameEnvironment.time.getTimeRemaining() + "days");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDuration.setBounds(10, 72, 216, 29);
		panel_3.add(lblDuration);
		
		JLabel lblLocation = new JLabel("Location: ");
		lblLocation.setText(lblLocation.getText() + GameEnvironment.game.getPlayer().getShip().getLocation().getName());
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLocation.setBounds(260, 20, 216, 29);
		panel_3.add(lblLocation);
	}
}
