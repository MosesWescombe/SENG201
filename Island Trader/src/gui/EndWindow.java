package gui;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import core.GameEnvironment;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * This is the final display window. At the end of the game this will show final stats and a custom message passed in.
 * 
 * EndWindow - Current Window object
 * lblFinalMessage - Final message text box.
 */
public class EndWindow {

	private JFrame endWindow;
	private JLabel lblFinalMessage;
	private int daysRemaining = GameEnvironment.getTime().getTimeRemaining();
	private int wallet = GameEnvironment.getPlayer().getWallet();

	/**
	 * Create the application.
	 * 
	 * @param message String, Message to display in window
	 */
	public EndWindow(String message) {
		initialize();
		endWindow.setVisible(true);
		lblFinalMessage.setText(message);
	}

	public void closeWindow() {
		endWindow.dispose();
		System.exit(0);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		endWindow = new JFrame();
		endWindow.setBounds(100, 100, 806, 629);
		endWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endWindow.getContentPane().setLayout(null);
		
		//Title
		JTextPane txtpnGameOver = new JTextPane();
		txtpnGameOver.setBounds(0, 0, 790, 67);
		txtpnGameOver.setText("                  Game Over!");
		txtpnGameOver.setForeground(Color.WHITE);
		txtpnGameOver.setFont(new Font("Eras Bold ITC", Font.BOLD, 50));
		txtpnGameOver.setEditable(false);
		txtpnGameOver.setBackground(new Color(0, 0, 51));
		endWindow.getContentPane().add(txtpnGameOver);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnExit.setBounds(0, 517, 790, 73);
		endWindow.getContentPane().add(btnExit);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(255, 255, 255));
		infoPanel.setBounds(10, 78, 770, 428);
		endWindow.getContentPane().add(infoPanel);
		infoPanel.setLayout(null);

			//Days remaining
			JLabel lblDaysRemaining = new JLabel("Days Remaining: " + daysRemaining);
			lblDaysRemaining.setBounds(57, 172, 394, 37);
			infoPanel.add(lblDaysRemaining);
			lblDaysRemaining.setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			//Final wallet
			JLabel lblFinalBalance = new JLabel("Final Wallet Ballance: $" + wallet);
			lblFinalBalance.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblFinalBalance.setBounds(57, 220, 436, 37);
			infoPanel.add(lblFinalBalance);
			
			//End message
			lblFinalMessage = new JLabel("Ending Message");
			lblFinalMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblFinalMessage.setBounds(10, 75, 750, 71);
			infoPanel.add(lblFinalMessage);
	}

}
