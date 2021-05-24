package gui;

import javax.swing.JFrame;

import core.Event;
import core.GameEnvironment;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PiratesWindow {

	private JFrame piratesWindow;
	private int attemptsRemaining = Event.getChances();
	private JLabel lblAttempts;

	/**
	 * Create the application.
	 */
	public PiratesWindow() {
		initialize();
		piratesWindow.setVisible(true);
	}
	
	public void closeWindow() {
		piratesWindow.dispose();
	}

	private void returnToMenu() {
		GameEnvironment.returnToMenu();
		this.closeWindow();
	}

	private void pirateEvent(String direction) {
		String result = Event.piratesGame(direction);

		if (result == "Failure") {
			attemptsRemaining--;
			JOptionPane.showMessageDialog(piratesWindow, "Oh NO! The pirates take the same route, try again!", "Wrong Direction!", JOptionPane.INFORMATION_MESSAGE);

			updateAttempts();
		} else {
			JOptionPane.showMessageDialog(piratesWindow, "The pirates have been avoided! You will not suffer any concequences", "Success!", JOptionPane.INFORMATION_MESSAGE);
			returnToMenu();
		}

		if (attemptsRemaining <= 0) {
			String ending = Event.piratesFailure(this);

			if (ending != "GAMEOVER") {
				JOptionPane.showMessageDialog(piratesWindow, ending, "The Pirates Leave...", JOptionPane.INFORMATION_MESSAGE);
				returnToMenu();
			}
		}
	}

	public void updateAttempts() {
		lblAttempts.setText("Attempts Remaining: " + attemptsRemaining);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		piratesWindow = new JFrame();
		piratesWindow.setBounds(100, 100, 700, 481);
		piratesWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		piratesWindow.getContentPane().setLayout(null);
		
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setBounds(0, 0, 684, 67);
		txtpnTitle.setText("                Pirates!!");
		txtpnTitle.setForeground(Color.WHITE);
		txtpnTitle.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtpnTitle.setEditable(false);
		txtpnTitle.setBackground(new Color(0, 0, 51));
		piratesWindow.getContentPane().add(txtpnTitle);
		
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			panel.setBounds(10, 78, 664, 353);
			piratesWindow.getContentPane().add(panel);
			panel.setLayout(null);
			
				JLabel lblNewLabel = new JLabel("Pirates are trying to board your ship.");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel.setBounds(175, 43, 301, 35);
				panel.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("Quickly select a direction to try and avioid them!");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblNewLabel_1.setBounds(136, 77, 393, 29);
				panel.add(lblNewLabel_1);
				
				JButton btnSouth = new JButton("South");
				btnSouth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pirateEvent("south");
					}
				});
				btnSouth.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnSouth.setBackground(SystemColor.activeCaptionBorder);
				btnSouth.setBounds(249, 297, 143, 45);
				panel.add(btnSouth);
				
				JButton btnNorth = new JButton("North");
				btnNorth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pirateEvent("north");
					}
				});
				btnNorth.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnNorth.setBackground(SystemColor.activeCaptionBorder);
				btnNorth.setBounds(249, 158, 143, 45);
				panel.add(btnNorth);
				
				JButton btnEast = new JButton("East");
				btnEast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pirateEvent("east");
					}
				});
				btnEast.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnEast.setBackground(SystemColor.activeCaptionBorder);
				btnEast.setBounds(439, 224, 143, 45);
				panel.add(btnEast);
				
				JButton btnWest = new JButton("West");
				btnWest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pirateEvent("west");
					}
				});
				btnWest.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnWest.setBackground(SystemColor.activeCaptionBorder);
				btnWest.setBounds(59, 224, 143, 45);
				panel.add(btnWest);
				
				lblAttempts = new JLabel("Attempts Remaining: ");
				lblAttempts.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblAttempts.setBounds(250, 234, 156, 30);
				panel.add(lblAttempts);
				updateAttempts();
	}
}
