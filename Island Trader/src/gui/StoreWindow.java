package gui;

import javax.swing.JFrame;

import core.Entity;
import core.GameEnvironment;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;

public class StoreWindow {

	private JFrame storeWindow;
	private JTable itemsToPurchase;

	

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
		txtIslandStore.setBounds(0, 0, 1303, 78);
		txtIslandStore.setText("                  Island Store");
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
		
		JButton btnCompleteTransaction = new JButton("Complete Transaction");
		btnCompleteTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btnExitWithoutSale.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnExitWithoutSale.setBackground(SystemColor.activeCaptionBorder);
		panel_2.add(btnExitWithoutSale);
		

		//Tabbed Pannel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabbedPane.setBounds(10, 128, 1293, 515);
		storeWindow.getContentPane().add(tabbedPane);
		
		//Items to purchase grid
		JPanel panel = new JPanel();
		tabbedPane.addTab("Items To Purchase", null, panel, null);
		
		//Items to purchase grid
		String[] tableTitles = {"Name", "Description", "Value", "Weight"}; //Titles
		Object[][] data = GameEnvironment.game.getPlayer().getShip().getLocation().getStore().getItemSellObjects(); //Items
		
		itemsToPurchase = new JTable(data, tableTitles);
		itemsToPurchase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		itemsToPurchase.getColumnModel().getColumn(0).setPreferredWidth(20);
		itemsToPurchase.getColumnModel().getColumn(1).setPreferredWidth(50);
		itemsToPurchase.getColumnModel().getColumn(2).setPreferredWidth(10);
		itemsToPurchase.getColumnModel().getColumn(3).setPreferredWidth(10);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(itemsToPurchase);
		
		//Items to sell tab
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Items To Sell", null, panel_1, null);
		
		JList<Entity> itemsToSell = new JList<Entity>();
		panel_1.add(itemsToSell);
	}
}
