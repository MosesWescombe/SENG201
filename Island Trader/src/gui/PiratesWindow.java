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

	/**
	 * Return to the main window
	 */
	private void returnToMenu() {
		GameEnvironment.returnToMenu();
		this.closeWindow();
	}

	//Call Pirate event and manage outcomes
	private void pirateEvent(String direction) {
		String result = Event.piratesGame(direction);

		//Incorrect guess
		if (result == "FAILURE") {
			attemptsRemaining--;
			JOptionPane.showMessageDialog(piratesWindow, "Oh NO! The pirates take the same route, try again!", "Wrong Direction!", JOptionPane.INFORMATION_MESSAGE);

			updateAttempts();
		} else { //Success
			JOptionPane.showMessageDialog(piratesWindow, "The pirates have been avoided! You will not suffer any concequences", "Success!", JOptionPane.INFORMATION_MESSAGE);
			returnToMenu();
		}

		//Pirates steal yo goods
		if (attemptsRemaining <= 0) {
			String ending = Event.piratesFailure(this);

			if (ending != "GAMEOVER") {
				JOptionPane.showMessageDialog(piratesWindow, ending, "The Pirates Leave...", JOptionPane.INFORMATION_MESSAGE);
				returnToMenu();
			}
		}
	}

	/**
	 * Update attempts lbl
	 */
	public void updateAttempts() {
		lblAttempts.setText("Attempts Remaining: " + attemptsRemaining);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		piratesWindow = new JFrame();
		piratesWindow.setTitle("Trader Game - PIRATES!");
		piratesWindow.setBounds(100, 100, 700, 481);
		piratesWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		piratesWindow.getContentPane().setLayout(null);
		
		//Title
		JTextPane txtpnTitle = new JTextPane();
		txtpnTitle.setBounds(0, 0, 684, 67);
		txtpnTitle.setText("                Pirates!!");
		txtpnTitle.setForeground(Color.WHITE);
		txtpnTitle.setFont(new Font("Tahoma", Font.PLAIN, 50));
		txtpnTitle.setEditable(false);
		txtpnTitle.setBackground(new Color(0, 0, 51));
		piratesWindow.getContentPane().add(txtpnTitle);
		
			JPanel mainPanel = new JPanel();
			mainPanel.setBackground(new Color(255, 255, 255));
			mainPanel.setBounds(10, 78, 664, 353);
			piratesWindow.getContentPane().add(mainPanel);
			mainPanel.setLayout(null);

				JLabel lblEvent = new JLabel("Pirates are trying to board your ship.");
				lblEvent.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblEvent.setBounds(175, 43, 301, 35);
				mainPanel.add(lblEvent);
				
				JLabel lblInstruction = new JLabel("Quickly select a direction to try and avioid them!");
				lblInstruction.setFont(new Font("Tahoma", Font.PLAIN, 18));
				lblInstruction.setBounds(136, 77, 393, 29);
				mainPanel.add(lblInstruction);
				
				//South button
				JButton btnSouth = new JButton("South");
				btnSouth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pirateEvent("south");
					}
				});
				btnSouth.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnSouth.setBackground(SystemColor.activeCaptionBorder);
				btnSouth.setBounds(249, 297, 143, 45);
				mainPanel.add(btnSouth);
				
				//North Button
				JButton btnNorth = new JButton("North");
				btnNorth.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pirateEvent("north");
					}
				});
				btnNorth.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnNorth.setBackground(SystemColor.activeCaptionBorder);
				btnNorth.setBounds(249, 158, 143, 45);
				mainPanel.add(btnNorth);
				
				//East Button
				JButton btnEast = new JButton("East");
				btnEast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pirateEvent("east");
					}
				});
				btnEast.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnEast.setBackground(SystemColor.activeCaptionBorder);
				btnEast.setBounds(439, 224, 143, 45);
				mainPanel.add(btnEast);
				
				//West Button
				JButton btnWest = new JButton("West");
				btnWest.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pirateEvent("west");
					}
				});
				btnWest.setFont(new Font("Tahoma", Font.PLAIN, 22));
				btnWest.setBackground(SystemColor.activeCaptionBorder);
				btnWest.setBounds(59, 224, 143, 45);
				mainPanel.add(btnWest);
				
				//Attempts remaining lbl
				lblAttempts = new JLabel("Attempts Remaining: ");
				lblAttempts.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblAttempts.setBounds(250, 234, 156, 30);
				mainPanel.add(lblAttempts);

				//Update Attempts
				updateAttempts();
	}
}
