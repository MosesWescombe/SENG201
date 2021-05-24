package gui;

import javax.swing.JFrame;
import core.GameEnvironment;
import core.Island;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class SailScreen {

	private JFrame sailScreen;
	
	private ArrayList<Island> islands = GameEnvironment.game.getIslands();
	private Island currentSelected = islands.get(0);
	
	private JLabel lblShowRoute;
	private JButton btnSetSail;

	/**
	 * Create the application.
	 */
	public SailScreen() {
		initialize();
		sailScreen.setVisible(true);
	}
	
	public void closeWindow() {
		sailScreen.dispose();
	}
	
	public void returnToMenu() {
		GameEnvironment.returnToMenu();
		this.closeWindow();
	}

	public void setSail() {
		String result = GameEnvironment.sailToIsland(currentSelected);

		if (result == "PIRATES") {
			new PiratesWindow();
			this.closeWindow();
		} else if (result == "NONE") {
			JOptionPane.showMessageDialog(sailScreen, "An unneventful journey, you have succesfully traveled to a new Island.", "Travel Success", JOptionPane.PLAIN_MESSAGE);
			returnToMenu();
		} else {
			JOptionPane.showMessageDialog(sailScreen, result, "Event Occured!", JOptionPane.ERROR_MESSAGE);
			returnToMenu();
		}
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
		
		sailScreen = new JFrame();
		sailScreen.setTitle("Trader Game Sail Screen");
		sailScreen.setBounds(100, 100, 707, 649);
		sailScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sailScreen.getContentPane().setLayout(null);
		
		//title block for window.
		JTextPane txtpnSetSail = new JTextPane();
		txtpnSetSail.setEditable(false);
		txtpnSetSail.setText("                Set Sail");
		txtpnSetSail.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtpnSetSail.setBackground(Color.LIGHT_GRAY);
		txtpnSetSail.setBounds(0, 0, 691, 78);
		sailScreen.getContentPane().add(txtpnSetSail);
		
		//button action for Nassau
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
		sailScreen.getContentPane().add(btnNassau);
		
		//button action for Timplore
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
		sailScreen.getContentPane().add(btnTimplore);
		
		//button action for Ugriad
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
		sailScreen.getContentPane().add(btnUgriad);
		
		//button action for StGerbal
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
		sailScreen.getContentPane().add(btnStGerbal);
		
		//button action for Lucia
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
		sailScreen.getContentPane().add(btnLucia);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(276, 190, 385, 409);
		sailScreen.getContentPane().add(panel);
		
		lblShowRoute = new JLabel("");
		lblShowRoute.setText(currentSelected.viewIslandInfo(GameEnvironment.game.getPlayer().getShip().getLocation())[0]);
		lblShowRoute.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblShowRoute.setBounds(10, 69, 365, 238);
		panel.add(lblShowRoute);
		
		JLabel lblRouteInfo = new JLabel("Route info:");
		lblRouteInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRouteInfo.setBounds(10, 11, 351, 25);
		panel.add(lblRouteInfo);
		
		btnSetSail = new JButton("Set Sail!");
		btnSetSail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSail();
			}
		});
		btnSetSail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnSetSail.setBounds(110, 331, 166, 48);
		panel.add(btnSetSail);
		
		JButton btnReturn = new JButton("< Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenu();
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReturn.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnReturn.setBounds(452, 96, 209, 73);
		sailScreen.getContentPane().add(btnReturn);
		
		JLabel lblLocationStatus = new JLabel("You ship is currently docked at: ");
		lblLocationStatus.setText(lblLocationStatus.getText() + GameEnvironment.game.getPlayer().getShip().getLocation().getName());
		lblLocationStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLocationStatus.setBounds(10, 89, 447, 41);
		sailScreen.getContentPane().add(lblLocationStatus);
		
		JLabel lblSelectionInfo = new JLabel("Select an island to sail to:");
		lblSelectionInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectionInfo.setBounds(10, 141, 351, 25);
		sailScreen.getContentPane().add(lblSelectionInfo);

		updateWindow();
	}
	
	private void updateWindow() {
		String[] result = currentSelected.viewIslandInfo(GameEnvironment.game.getPlayer().getShip().getLocation());
		lblShowRoute.setText(result[0]);
		if (result[1] == "false") {
			btnSetSail.setVisible(false);
		} else {
			btnSetSail.setVisible(true);
		}
	}
	
	private void resetButton(JButton btnNassau, JButton btnTimplore, JButton btnUgriad, JButton btnStGerbal, JButton btnLucia) {
		btnNassau.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnTimplore.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnUgriad.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnStGerbal.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnLucia.setBackground(UIManager.getColor("CheckBox.darkShadow"));	
	}
}
