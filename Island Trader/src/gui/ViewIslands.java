package gui;


import javax.swing.JFrame;

import core.GameEnvironment;
import core.Island;

import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class ViewIslands {

	private JFrame viewIslandsWindow;
	
	private ArrayList<Island> islands = new ArrayList<>();
	
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
		
		JButton btnNassau = new JButton("Nassau");
		btnNassau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNassau.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNassau.setBackground(SystemColor.activeCaptionBorder);
		btnNassau.setBounds(10, 190, 209, 73);
		viewIslandsWindow.getContentPane().add(btnNassau);
		
		JButton btnTimplore = new JButton("Timplore");
		btnTimplore.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnTimplore.setBackground(SystemColor.activeCaptionBorder);
		btnTimplore.setBounds(10, 274, 209, 73);
		viewIslandsWindow.getContentPane().add(btnTimplore);
		
		JButton btnUgriad = new JButton("Ugriad");
		btnUgriad.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnUgriad.setBackground(SystemColor.activeCaptionBorder);
		btnUgriad.setBounds(10, 358, 209, 73);
		viewIslandsWindow.getContentPane().add(btnUgriad);
		
		JButton btnStGerbal = new JButton("St. Gerbal");
		btnStGerbal.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnStGerbal.setBackground(SystemColor.activeCaptionBorder);
		btnStGerbal.setBounds(10, 442, 209, 73);
		viewIslandsWindow.getContentPane().add(btnStGerbal);
		
		JButton btnLucia = new JButton("Lucia");
		btnLucia.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnLucia.setBackground(SystemColor.activeCaptionBorder);
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
		
		
	}
}
