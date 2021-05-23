package gui;


import javax.swing.JFrame;
import core.GameEnvironment;
import core.Island;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Panel;

public class ViewIslands {

	private JFrame viewIslandsWindow;
	
	private ArrayList<Island> islands = GameEnvironment.game.getIslands();
	private Island currentSelected = islands.get(0);
	private JLabel lblForIsland;
	private JLabel lblRouteInfo;
	
	/**
	 * Create the application.
	 */
	
	public ViewIslands() {
		initialize();
		viewIslandsWindow.setVisible(true);
	}
	
	public void closeWindow() {
		viewIslandsWindow.dispose();
	}

	public void returnToMenu() {
		GameEnvironment.returnToMenu();
		this.closeWindow();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JButton btnNassau = new JButton("Nassau");
		JButton btnTimplore = new JButton("Timplore");
		JButton btnUgriad = new JButton("Ugriad");
		JButton btnStGerbal = new JButton("St. Gerbal");
		JButton btnLucia = new JButton("Lucia");
		
		viewIslandsWindow = new JFrame();
		viewIslandsWindow.setTitle("Trader Game - View Islands");
		viewIslandsWindow.setBounds(100, 100, 707, 649);
		viewIslandsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewIslandsWindow.getContentPane().setLayout(null);
		
		JTextPane txtpnViewIsland = new JTextPane();
		txtpnViewIsland.setEditable(false);
		txtpnViewIsland.setText("       View Island Properties");
		txtpnViewIsland.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtpnViewIsland.setBackground(Color.LIGHT_GRAY);
		txtpnViewIsland.setBounds(0, 0, 691, 78);
		viewIslandsWindow.getContentPane().add(txtpnViewIsland);
		
		//button for Nassau
		btnNassau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(btnNassau, btnTimplore, btnUgriad, btnStGerbal, btnLucia);
				btnNassau.setBackground(SystemColor.activeCaptionBorder);
				currentSelected = islands.get(0);
				updateWindow();
			}
		});
		btnNassau.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNassau.setBackground(SystemColor.activeCaptionBorder);
		btnNassau.setBounds(10, 190, 209, 73);
		viewIslandsWindow.getContentPane().add(btnNassau);
		
		//button for Timplore
		btnTimplore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(btnNassau, btnTimplore, btnUgriad, btnStGerbal, btnLucia);
				btnTimplore.setBackground(SystemColor.activeCaptionBorder);
				currentSelected = islands.get(1);
				updateWindow();
			}
		});
		btnTimplore.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnTimplore.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnTimplore.setBounds(10, 274, 209, 73);
		viewIslandsWindow.getContentPane().add(btnTimplore);
		
		//button for Ugraid
		btnUgriad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(btnNassau, btnTimplore, btnUgriad, btnStGerbal, btnLucia);
				btnUgriad.setBackground(SystemColor.activeCaptionBorder);
				currentSelected = islands.get(2);
				updateWindow();
			}
		});
		btnUgriad.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnUgriad.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnUgriad.setBounds(10, 358, 209, 73);
		viewIslandsWindow.getContentPane().add(btnUgriad);
		
		//button for St Gerbal
		btnStGerbal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(btnNassau, btnTimplore, btnUgriad, btnStGerbal, btnLucia);
				btnStGerbal.setBackground(SystemColor.activeCaptionBorder);
				currentSelected = islands.get(3);
				updateWindow();
			}
		});
		btnStGerbal.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnStGerbal.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnStGerbal.setBounds(10, 442, 209, 73);
		viewIslandsWindow.getContentPane().add(btnStGerbal);
		
		//button for Lucia
		btnLucia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButton(btnNassau, btnTimplore, btnUgriad, btnStGerbal, btnLucia);
				btnLucia.setBackground(SystemColor.activeCaptionBorder);
				currentSelected = islands.get(4);
				updateWindow();
			}
		});
		btnLucia.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnLucia.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnLucia.setBounds(10, 526, 209, 73);
		viewIslandsWindow.getContentPane().add(btnLucia);
		
		JLabel lblLocationInfo = new JLabel("You ship is currently docked at: ");
		lblLocationInfo.setText(lblLocationInfo.getText() + GameEnvironment.game.getPlayer().getShip().getLocation().getName());
		lblLocationInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLocationInfo.setBounds(10, 89, 447, 41);
		viewIslandsWindow.getContentPane().add(lblLocationInfo);
		
		JLabel lblButtonInfo = new JLabel("Make a selection to view each island's properties");
		lblButtonInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblButtonInfo.setBounds(10, 138, 447, 41);
		viewIslandsWindow.getContentPane().add(lblButtonInfo);
		
		Panel panel = new Panel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(273, 190, 385, 409);
		viewIslandsWindow.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblForIsland = new JLabel("Island Properties for: ");
		lblForIsland.setText(lblForIsland.getText() + currentSelected.getName());
		lblForIsland.setBounds(10, 11, 288, 22);
		panel.add(lblForIsland);
		lblForIsland.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblRouteInfo = new JLabel("");
		lblRouteInfo.setText(currentSelected.viewIslandInfo(GameEnvironment.game.getPlayer().getShip().getLocation()));
		lblRouteInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRouteInfo.setBounds(10, 62, 365, 233);
		panel.add(lblRouteInfo);
		
		JButton btnThisSells = new JButton("Store Sells");
		btnThisSells.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnThisSells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Items to purchase grid
				String[] collumnNames = {"Name", "Description", "Value", "Weight"}; //Titles
				Object[][] itemsForPurchase = currentSelected.getStore().getItemSellObjects(); //Items
//				Object[][] itemsForPurchase = {
//					{"This", "That" ,"Price","Other price", ""},
//					{"This", "That" ,"Price","Other price", ""},
//					{"This", "That" ,"Price","Other price", ""}
//				};
				
				JTable itemsToPurchase = new JTable(itemsForPurchase, collumnNames);
				itemsToPurchase.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				itemsToPurchase.setDefaultEditor(Object.class, null);
				itemsToPurchase.setFont(new Font("Tahoma", Font.PLAIN, 14));
				itemsToPurchase.setFillsViewportHeight(true);
				itemsToPurchase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				itemsToPurchase.getColumnModel().getColumn(0).setPreferredWidth(200);
				itemsToPurchase.getColumnModel().getColumn(1).setPreferredWidth(400);
				itemsToPurchase.getColumnModel().getColumn(2).setPreferredWidth(100);
				itemsToPurchase.getColumnModel().getColumn(3).setPreferredWidth(100);

				JScrollPane scrollPane = new JScrollPane(itemsToPurchase);
				scrollPane.setSize(new Dimension(804, 900));
				scrollPane.setPreferredSize(new Dimension(804, scrollPane.getPreferredSize().height));

				JOptionPane.showMessageDialog(viewIslandsWindow, scrollPane, "The store at this island sells", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnThisSells.setBounds(10, 347, 167, 51);
		panel.add(btnThisSells);
		
		JButton btnThisBuys = new JButton("Store Buys");
		btnThisBuys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Items to purchase grid
				String[] collumnNames = {"Name", "Description", "Value", "Weight"}; //Titles
				Object[][] itemsForSale = currentSelected.getStore().getItemsBuyObjects(); //Items
//				Object[][] itemsForPurchase = {
//					{"This", "That" ,"Price","Other price", ""},
//					{"This", "That" ,"Price","Other price", ""},
//					{"This", "That" ,"Price","Other price", ""}
//				};
				
				JTable itemsToSell = new JTable(itemsForSale, collumnNames);
				itemsToSell.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				itemsToSell.setDefaultEditor(Object.class, null);
				itemsToSell.setFont(new Font("Tahoma", Font.PLAIN, 14));
				itemsToSell.setFillsViewportHeight(true);
				itemsToSell.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				itemsToSell.getColumnModel().getColumn(0).setPreferredWidth(200);
				itemsToSell.getColumnModel().getColumn(1).setPreferredWidth(400);
				itemsToSell.getColumnModel().getColumn(2).setPreferredWidth(100);
				itemsToSell.getColumnModel().getColumn(3).setPreferredWidth(100);

				JScrollPane scrollPane = new JScrollPane(itemsToSell);
				scrollPane.setSize(new Dimension(804, 900));
				scrollPane.setPreferredSize(new Dimension(804, scrollPane.getPreferredSize().height));

				JOptionPane.showMessageDialog(viewIslandsWindow, scrollPane, "The store at this island wants to buy", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnThisBuys.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnThisBuys.setBounds(208, 347, 167, 51);
		panel.add(btnThisBuys);
		
		JLabel lblStoreButtonInfo = new JLabel("The selected Island store info:");
		lblStoreButtonInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStoreButtonInfo.setBounds(10, 306, 365, 32);
		panel.add(lblStoreButtonInfo);
		
		//return to main menu Button
		JButton btnReturn = new JButton("< Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenu();
			}
		});
		btnReturn.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReturn.setBounds(449, 89, 209, 73);
		viewIslandsWindow.getContentPane().add(btnReturn);
			
	}
	
	private void updateWindow() {
		lblForIsland.setText("Island Properties for: " + currentSelected.getName());
		lblRouteInfo.setText(currentSelected.viewIslandInfo(GameEnvironment.game.getPlayer().getShip().getLocation()));
	}
	
	private void resetButton(JButton btnNassau, JButton btnTimplore, JButton btnUgriad, JButton btnStGerbal, JButton btnLucia) {
		btnNassau.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnTimplore.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnUgriad.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnStGerbal.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnLucia.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		
	}
}
