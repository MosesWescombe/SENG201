package gui;

import core.Entity;
import core.GameEnvironment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JLabel;

/**
 * Store Window. Here items are traded.
 * 
 * StoreWindow - Window object
 * ItemsToPurchase - JTable displaying items the user can purchase
 * ItemsToSell - JTable displaying items the user can sell to the store
 * ItemsForPurchase - Object[][] of items the user can purchase
 * ItemsToBuy - Object[][] of items the user can sell to the store
 */
public class StoreWindow {

	private JFrame storeWindow;
	private JTable itemsToPurchase;
	private JTable itemsToSell;
	private Object[][] itemsForPurchase = GameEnvironment.getPlayerShip().getLocation().getStore().getItemSellObjects(); //Items
	private Object[][] itemsToBuy = GameEnvironment.getPlayerShip().getLocation().getStore().getItemsBuyObjects();
	

	/**
	 * Create the application.
	 */
	public StoreWindow() {
		initialize();
		storeWindow.setVisible(true);
	}
	
	public void closeWindow() {
		storeWindow.dispose();
	}

	/**
	 * Return to the main menu
	 */
	public void returnToMenu() {
		GameEnvironment.returnToMenu();
		this.closeWindow();
	}
	
	/**
	 * Calls the Stores sell function, selling an item to the user. Applies checks to see if the user can afford the items
	 */
	public void buyItem() {
		int index = itemsToPurchase.getSelectedRow();
		//Check the user selected an item
		if (index < 0) {
			return;
		}
		//Find the item selected
		Entity selectedItem = (Entity)itemsForPurchase[index][4];
		if (selectedItem == null) {
			return;
		}

		String result = GameEnvironment.getPlayerShip().getLocation().getStore().sell(selectedItem);

		//Handle Errors
		if (result == "WeightError") {
			//Not enough capacity
			JOptionPane.showMessageDialog(storeWindow, "You do not have enough CARGO CAPACITY to purchase this item.", "Cannot Select Item", JOptionPane.ERROR_MESSAGE);
		} else if (result == "CostError") {
			//Not enough money
			JOptionPane.showMessageDialog(storeWindow, "You do not have enough MONEY to purchase this item.", "Cannot Select Item", JOptionPane.ERROR_MESSAGE);
		} else {
			//Update Window
			closeWindow();
			new StoreWindow();
		}
	}

	/**
	 * Sells an item to the store. Calling the stores purchase function.
	 */
	public void sellItem() {
		int index = itemsToSell.getSelectedRow();
		//Check the user selected an item
		if (index < 0) {
			return;
		}
		//Find the item selected
		Entity selectedItem = (Entity)itemsToBuy[index][4];
		if (selectedItem == null) {
			return;
		}
		
		GameEnvironment.getPlayerShip().getLocation().getStore().purchase(selectedItem);

		closeWindow();
		new StoreWindow();
	}

	/**
	 * Updates the purchase JTable
	 */
	private void updateSellGrid() {
		//Items to purchase grid
		String[] collumnNames = new String[] {"Name", "Description", "Value", "Weight"}; //Titles
		itemsToBuy = GameEnvironment.getPlayerShip().getLocation().getStore().getItemsBuyObjects(); //Items
		// Object[][] itemsToBuy = {
		// 	{"This", "That" ,"Price","Other price", ""},
		// 	{"This", "That" ,"Price","Other price", ""},
		// 	{"This", "That" ,"Price","Other price", ""}
		// };
		
		itemsToSell = new JTable(itemsToBuy, collumnNames);
		itemsToSell.setDefaultEditor(Object.class, null);
		itemsToSell.setFont(new Font("Tahoma", Font.PLAIN, 14));
		itemsToSell.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		itemsToSell.getColumnModel().getColumn(0).setPreferredWidth(170);
		itemsToSell.getColumnModel().getColumn(1).setPreferredWidth(500);
		itemsToSell.getColumnModel().getColumn(2).setPreferredWidth(100);
		itemsToSell.getColumnModel().getColumn(3).setPreferredWidth(100);
	}

	/**
	 * Updates the sell JTable
	 */
	private void updatePurchaseGrid() {
		//Items to purchase grid
		String[] collumnNames = {"Name", "Description", "Value", "Weight"}; //Titles
		itemsForPurchase = GameEnvironment.getPlayerShip().getLocation().getStore().getItemSellObjects(); //Items
		// Object[][] itemsForPurchase = {
		// 	{"This", "That" ,"Price","Other price", ""},
		// 	{"This", "That" ,"Price","Other price", ""},
		// 	{"This", "That" ,"Price","Other price", ""}
		// };
		
		itemsToPurchase = new JTable(itemsForPurchase, collumnNames);
		itemsToPurchase.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		itemsToPurchase.setDefaultEditor(Object.class, null);
		itemsToPurchase.setFont(new Font("Tahoma", Font.PLAIN, 14));
		itemsToPurchase.setFillsViewportHeight(true);
		itemsToPurchase.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		itemsToPurchase.getColumnModel().getColumn(0).setPreferredWidth(170);
		itemsToPurchase.getColumnModel().getColumn(1).setPreferredWidth(500);
		itemsToPurchase.getColumnModel().getColumn(2).setPreferredWidth(100);
		itemsToPurchase.getColumnModel().getColumn(3).setPreferredWidth(100);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		storeWindow = new JFrame();
		storeWindow.setTitle("Trader Game - Store");
		storeWindow.setBounds(100, 100, 1329, 840);
		storeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		storeWindow.getContentPane().setLayout(null);
		
		//Title
		JTextPane txtIslandStore = new JTextPane();
		txtIslandStore.setBounds(0, 0, 1313, 78);
		txtIslandStore.setText("                                 Island Store");
		txtIslandStore.setFont(new Font("Eras Bold ITC", Font.PLAIN, 55));
		txtIslandStore.setEditable(false);
		txtIslandStore.setBackground(new Color(0, 0, 51));
		txtIslandStore.setForeground(new Color(255, 255, 255));
		storeWindow.getContentPane().add(txtIslandStore);
		

		//Tabbed Pannel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(0, 128, 1313, 515);
		storeWindow.getContentPane().add(tabbedPane);
		
			//Items to purchase grid
			JPanel purchasePanel = new JPanel();
			tabbedPane.addTab("Items To Purchase", null, purchasePanel, null);
			purchasePanel.setLayout(new GridLayout(0, 1, 0, 0));
				
				//Add Grid
				updatePurchaseGrid();

				JScrollPane scrollPane = new JScrollPane(itemsToPurchase);
				purchasePanel.add(scrollPane);
			
			//Items to sell tab
			JPanel itemsToSellPanel = new JPanel();
			tabbedPane.addTab("Items To Sell", null, itemsToSellPanel, null);
			itemsToSellPanel.setLayout(new GridLayout(0, 1, 0, 0));
				
				//Add Grid
				updateSellGrid();

				JScrollPane scrollPaneSell = new JScrollPane(itemsToSell);
				itemsToSellPanel.add(scrollPaneSell);
				
				JButton btnCompleteTransaction = new JButton("Purchase Selected");
				btnCompleteTransaction.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buyItem();
					}
				});
				btnCompleteTransaction.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnCompleteTransaction.setBackground(UIManager.getColor("Button.darkShadow"));
				btnCompleteTransaction.setBounds(10, 654, 423, 136);
				storeWindow.getContentPane().add(btnCompleteTransaction);
				
				JButton btnSellSelected = new JButton("Sell Selected");
				btnSellSelected.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						sellItem();
					}
				});
				btnSellSelected.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnSellSelected.setBackground(UIManager.getColor("Button.darkShadow"));
				btnSellSelected.setBounds(441, 654, 431, 136);
				storeWindow.getContentPane().add(btnSellSelected);
				
				JButton btnExitWithoutSale = new JButton("Leave Store");
				btnExitWithoutSale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						returnToMenu();
					}
				});
				btnExitWithoutSale.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnExitWithoutSale.setBackground(UIManager.getColor("Button.darkShadow"));
				btnExitWithoutSale.setBounds(882, 654, 421, 136);
				storeWindow.getContentPane().add(btnExitWithoutSale);
				
				JLabel lblPlayerWallet = new JLabel("Player Wallet: $");
				lblPlayerWallet.setText(lblPlayerWallet.getText() + GameEnvironment.getPlayer().getWallet());
				lblPlayerWallet.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblPlayerWallet.setBounds(825, 104, 234, 53);
				storeWindow.getContentPane().add(lblPlayerWallet);
				
				JLabel lblCargoCapacity = new JLabel("Cargo Capacity: ");
				lblCargoCapacity.setText(lblCargoCapacity.getText() + GameEnvironment.getPlayerShip().getCapacity() + "/" + GameEnvironment.getPlayerShip().getMaxCapacity() + "kg");
				lblCargoCapacity.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblCargoCapacity.setBounds(1069, 104, 234, 53);
				storeWindow.getContentPane().add(lblCargoCapacity);
	}


	//Getters/Setters
	public Object[][] getItemsForPurchase() {
		return itemsForPurchase;
	}
	public void setItemsForPurchase(Object[][] itemsForPurchase) {
		this.itemsForPurchase = itemsForPurchase;
	}
	public Object[][] getItemsToBuy() {
		return itemsToBuy;
	}
}
