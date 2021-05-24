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

public class EndWindow {

	private JFrame endWindow;
	private JLabel lblFinalMessage;
	private int daysRemaining = GameEnvironment.time.getTimeRemaining();
	private int wallet = GameEnvironment.game.getPlayer().getWallet();

	/**
	 * Create the application.
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
		
		JTextPane txtpnGameOver = new JTextPane();
		txtpnGameOver.setBounds(0, 0, 790, 67);
		txtpnGameOver.setText("                Game Over!");
		txtpnGameOver.setForeground(Color.WHITE);
		txtpnGameOver.setFont(new Font("Tahoma", Font.PLAIN, 50));
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 78, 770, 428);
		endWindow.getContentPane().add(panel);
		panel.setLayout(null);
		
			JLabel lblDaysRemaining = new JLabel("Days Remaining: " + daysRemaining);
			lblDaysRemaining.setBounds(57, 227, 394, 37);
			panel.add(lblDaysRemaining);
			lblDaysRemaining.setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			JLabel lblFinalBalance = new JLabel("Final Wallet Ballance: $" + wallet);
			lblFinalBalance.setFont(new Font("Tahoma", Font.PLAIN, 30));
			lblFinalBalance.setBounds(57, 320, 436, 37);
			panel.add(lblFinalBalance);
			
			lblFinalMessage = new JLabel("Ending Message");
			lblFinalMessage.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblFinalMessage.setBounds(10, 75, 750, 71);
			panel.add(lblFinalMessage);
	}

}
