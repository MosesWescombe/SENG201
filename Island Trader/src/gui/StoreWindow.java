package gui;

import javax.swing.JFrame;

import core.GameEnvironment;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class StoreWindow {

	private JFrame storeWindow;
	private JTable itemsToPurchase;
	private JTable itemsToSell;
	

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

	public void purchaseItems() {
		/**Takes the items that have been selected and passes them back to the GameEnvironment to be managed */
		System.out.print(itemsToPurchase.getSelectedRows());
		itemsToPurchase.remove(0);
	}

	public void returnToMenu() {
		GameEnvironment.returnToMenu();
		this.closeWindow();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		storeWindow = new JFrame();
		storeWindow.setBounds(100, 100, 1329, 840);
		storeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		storeWindow.getContentPane().setLayout(null);
		
		JTextPane txtIslandStore = new JTextPane();
		txtIslandStore.setBounds(0, 0, 1313, 78);
		txtIslandStore.setText("                                Island Store");
		txtIslandStore.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtIslandStore.setEditable(false);
		txtIslandStore.setBackground(Color.LIGHT_GRAY);
		storeWindow.getContentPane().add(txtIslandStore);
		
		//Button Pannel
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 654, 1293, 136);
		panel_2.setBackground(Color.WHITE);
		storeWindow.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnCompleteTransaction = new JButton("Add To Cart");
		btnCompleteTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				purchaseItems();
			}
		});
		btnCompleteTransaction.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCompleteTransaction.setBackground(SystemColor.activeCaptionBorder);
		panel_2.add(btnCompleteTransaction);
		
		JButton btnExitWithoutSale = new JButton("Exit Without Sale");
		btnExitWithoutSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenu();
			}
		});
		
		JButton btnCompleteTransaction_1 = new JButton("Complete Transaction");
		btnCompleteTransaction_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCompleteTransaction_1.setBackground(SystemColor.activeCaptionBorder);
		panel_2.add(btnCompleteTransaction_1);
		btnExitWithoutSale.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnExitWithoutSale.setBackground(SystemColor.activeCaptionBorder);
		panel_2.add(btnExitWithoutSale);
		

		//Tabbed Pannel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(10, 128, 1293, 515);
		storeWindow.getContentPane().add(tabbedPane);
		
			//Items to purchase grid
			JPanel purchasePanel = new JPanel();
			tabbedPane.addTab("Items To Purchase", null, purchasePanel, null);
			purchasePanel.setLayout(new GridLayout(0, 1, 0, 0));

				//Items to purchase grid
				String[] collumnNames = {"Name", "Description", "Value", "Weight"}; //Titles
				//Object[][] itemsForPurchase = GameEnvironment.game.getPlayer().getShip().getLocation().getStore().getItemSellObjects(); //Items
				Object[][] itemsForPurchase = {
					{"This", "That" ,"Price","Other price", ""},
					{"This", "That" ,"Price","Other price", ""},
					{"This", "That" ,"Price","Other price", ""}
				};
				
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

				JScrollPane scrollPane = new JScrollPane(itemsToPurchase);
				purchasePanel.add(scrollPane);
			
				
			
			//Items to sell tab
			JPanel itemsToSellPanel = new JPanel();
			tabbedPane.addTab("Items To Sell", null, itemsToSellPanel, null);
			itemsToSellPanel.setLayout(new GridLayout(0, 1, 0, 0));
			
				//Items to purchase grid
				collumnNames = new String[] {"Name", "Description", "Value", "Weight"}; //Titles
				//Object[][] itemsToBuy = GameEnvironment.game.getPlayer().getShip().getLocation().getStore().getItemsBuyObjects(); //Items
				Object[][] itemsToBuy = {
					{"This", "That" ,"Price","Other price", ""},
					{"This", "That" ,"Price","Other price", ""},
					{"This", "That" ,"Price","Other price", ""}
				};
				
				itemsToSell = new JTable(itemsToBuy, collumnNames);
				itemsToSell.setDefaultEditor(Object.class, null);
				itemsToSell.setFont(new Font("Tahoma", Font.PLAIN, 14));
				itemsToSell.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				itemsToSell.getColumnModel().getColumn(0).setPreferredWidth(170);
				itemsToSell.getColumnModel().getColumn(1).setPreferredWidth(500);
				itemsToSell.getColumnModel().getColumn(2).setPreferredWidth(100);
				itemsToSell.getColumnModel().getColumn(3).setPreferredWidth(100);
				
				JScrollPane scrollPaneSell = new JScrollPane(itemsToSell);
				itemsToSellPanel.add(scrollPaneSell);
	}
}
