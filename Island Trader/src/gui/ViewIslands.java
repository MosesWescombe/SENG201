package gui;


import javax.swing.JFrame;

import core.CommandLine;
import core.GameEnvironment;
import core.Island;

import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
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

	public void returnToGame() {
		GameEnvironment.closeViewIslandsWindow(this);
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
		btnNassau.setBackground(UIManager.getColor("CheckBox.darkShadow"));
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
