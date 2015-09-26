package com.matthew.simpleatm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 
 * @author Matthew Meacham
 * 
 *         A simple program for Huge Bank that allows a client with PIN 2222 to access their bank account and deposit or withdrawl money
 *         
 *         Assignment 1 for GET HIP
 *
 */
public class Assignment1 {

	// The frame that will display all the components
	private JFrame frame;

	private JLabel pinIsIncorrectLabel;

	public Assignment1() {
		// Use EventQueue to free up main Thread
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new PinFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// This class is the frame where a user enters their PIN
	public class PinFrame extends JFrame {
		private static final long serialVersionUID = 1L;

		// The JPanel for this frame to add components to
		private JPanel contentPane;
		// The password field that a client enters their pin into
		private JPasswordField pinPasswordField;
		// The submit button
		private JButton submitButton;

		// This is the correct pin that needs to be entered
		private final String CORRECT_PIN = "2222";

		// Create the frame and components
		public PinFrame() {
			// Create frame
			setTitle("Huge Bank Login");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 250, 250);
			setLocationRelativeTo(null);
			setResizable(false);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			// Create the label that says "Enter your PIN'
			JLabel enterYourPINLabel = new JLabel("Enter your PIN: ");
			enterYourPINLabel.setHorizontalAlignment(SwingConstants.CENTER);
			enterYourPINLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			enterYourPINLabel.setBounds(15, 71, 214, 20);
			contentPane.add(enterYourPINLabel);

			// Create the label that says "Huge Bank"
			JLabel hugeBankLabel = new JLabel("Huge Bank");
			hugeBankLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
			hugeBankLabel.setBounds(45, 11, 153, 38);
			contentPane.add(hugeBankLabel);

			// Create password field
			pinPasswordField = new JPasswordField();
			pinPasswordField.setBounds(57, 107, 129, 27);
			// Add a KeyListener so that we can use the enter/return key as well as the submit button to submit our PIN
			pinPasswordField.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent e) {
					// If the PIN is long enough, and we pressed enter, then check the PIN
					if (pinPasswordField.getPassword().length >= CORRECT_PIN.length()) {
						if (e.getKeyCode() == KeyEvent.VK_ENTER) checkPIN();
					}
				}

				public void keyReleased(KeyEvent e) {

				}

				public void keyTyped(KeyEvent e) {

				}
			});
			// Add a document listener so that when the value inside of the password field is changed, we can call our method
			// to check the password field (so we can enable/disable the submit button)
			pinPasswordField.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {

				}

				public void insertUpdate(DocumentEvent e) {
					checkPasswordField();
				}

				public void removeUpdate(DocumentEvent e) {
					checkPasswordField();
				}
			});
			contentPane.add(pinPasswordField);
			pinPasswordField.setColumns(10);

			// Create the exit button
			JButton exitButton = new JButton("Exit");
			// If a user hits Exit, it will terminate the program
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			exitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			exitButton.setBounds(15, 180, 89, 30);
			contentPane.add(exitButton);

			// Create the submit button
			submitButton = new JButton("Submit");
			// If the user hits Submit, it checks the entered PIN
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkPIN();
				}
			});
			submitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
			submitButton.setBounds(140, 180, 89, 30);
			submitButton.setEnabled(false);
			contentPane.add(submitButton);

			// Create the Label that says "pin is incorrect"
			pinIsIncorrectLabel = new JLabel("Pin is incorrect");
			pinIsIncorrectLabel.setForeground(Color.RED);
			pinIsIncorrectLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			pinIsIncorrectLabel.setHorizontalAlignment(SwingConstants.CENTER);
			pinIsIncorrectLabel.setBounds(12, 145, 219, 24);
			pinIsIncorrectLabel.setVisible(false);
			contentPane.add(pinIsIncorrectLabel);

			// Finally set the frame's visibility to true so that we can see it
			setVisible(true);
		}

		// Checks the PIN to see if it is the same as the CORRECT_PIN
		private void checkPIN() {
			// If it's the correct pin, dispose of the current frame, and set the frame to a new AccountFrame
			//Otherwise, set the label that says "Pin is incorrect" to visible
			if (characterArrayToString(pinPasswordField.getPassword()).equals(CORRECT_PIN)) {
				frame.dispose();
				frame = new AccountFrame();
			} else pinIsIncorrectLabel.setVisible(true);
		}

		//Takes an array of chars and transforms it to a String
		private String characterArrayToString(char[] characters) {
			//Create a StringBuilder to handle String creation
			StringBuilder builder = new StringBuilder();
			//Append each character in the char array to our StringBuilder
			for (int i = 0; i < characters.length; i++) {
				builder.append(characters[i]);
			}
			//Return the String in the StringBuilder
			return builder.toString();
		}

		//Checks to see if the password field has a long enough length and if it does, enables the submit button, otherwise disables it
		private void checkPasswordField() {
			if (pinPasswordField.getPassword().length < CORRECT_PIN.length()) submitButton.setEnabled(false);
			else submitButton.setEnabled(true);
		}
	}

	//This is the frame of the account page
	public class AccountFrame extends JFrame {
		private static final long serialVersionUID = 1L;

		//This frame's JPanel for adding components to
		private JPanel contentPane;

		//The label that shows the balance in the frame
		private JLabel balanceLabel;
		//The BigDecimal (essentially a double but without bounds)
		private BigDecimal balance = new BigDecimal("0.00");

		// Create the frame and components
		public AccountFrame() {
			//Create the frame
			setTitle("Huge Bank");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 250, 300);
			setLocationRelativeTo(null);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			//Create the label that says "Huge Bank"
			JLabel hugeBankLabel = new JLabel("Huge Bank");
			hugeBankLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
			hugeBankLabel.setBounds(42, 0, 150, 48);
			contentPane.add(hugeBankLabel);

			//Create the deposit button
			JButton depositButton = new JButton("Deposit");
			depositButton.setBounds(57, 108, 120, 40);
			//If they press the button, then set balance to itself plus the amount returned from requestAmount()
			//Then call setBalanceText() to set the balanceLabel text to the new balance amount
			depositButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					balance = balance.add(BigDecimal.valueOf(requestAmount("Please enter the amount you wish to deposit.")));
					setBalanceText();
				}
			});
			contentPane.add(depositButton);

			//Create the withdrawl button
			JButton withdrawlButton = new JButton("Withdrawl");
			withdrawlButton.setBounds(57, 159, 120, 40);
			//Get an amount from requestAmount(), then if the current balance minus that amount is less than zero, create an error message
			//Otherwise set the balance to the current balance minus the amount from requestAmount() and then call setBalanceText() to
			//set the balanceLabel text to the new balance amount
			withdrawlButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BigDecimal result = BigDecimal.valueOf(requestAmount("Please enter the amount you wish to withdrawl."));
					if (balance.subtract(result).toPlainString().contains("-")) JOptionPane.showMessageDialog(frame, "Sorry, you can't withdrawl that much.", "Withdrawl Error", JOptionPane.ERROR_MESSAGE);
					else {
						balance = balance.subtract(result);
						setBalanceText();
					}
				}
			});
			contentPane.add(withdrawlButton);

			//Create the exit button
			JButton exitButton = new JButton("Exit");
			exitButton.setBounds(57, 210, 120, 40);
			//If they hit it, go back to the enter pin frame
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					frame = new PinFrame();
				}
			});
			contentPane.add(exitButton);

			//Create the label that says "Account Balance"
			JLabel accountBalanceLabel = new JLabel("Account Balance:");
			accountBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
			accountBalanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			accountBalanceLabel.setBounds(10, 48, 214, 14);
			contentPane.add(accountBalanceLabel);

			//Create the label that says the balance amount
			balanceLabel = new JLabel("$0.00");
			balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
			balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			balanceLabel.setBounds(10, 73, 214, 20);
			contentPane.add(balanceLabel);

			//Set its visibility to true so that we can see it
			setVisible(true);
		}

		//Sets the balanceLabel value to the amount of balance
		private void setBalanceText() {
			//Grabs the current balance in the form of a String without scientific notation
			String currentBalance = balance.toPlainString();
			//Finds where the decimal is in the String
			int indexOfDecimal = currentBalance.indexOf(".");
			//Grabs the part of the String after the decimal
			String afterDecimal = currentBalance.substring(indexOfDecimal + 1, currentBalance.length());
			//This next statement formats the String if necessary
			//if the length of the String after the decimal is greater than two, then we will set currentBalance to the part of currentBalance
			//up through the decimal plus two numbers after the decimal
			if (afterDecimal.length() > 2) currentBalance = currentBalance.substring(0, indexOfDecimal + 1) + afterDecimal.substring(0, 2);
			//Finally, set the text of balanceLabel to the currentBalance with a preceding dollar sign
			balanceLabel.setText("$" + currentBalance);
		}

		//Requests an amount from the user
		private double requestAmount(String message) {
			double result = -1.0d;
			try {
				//Create an Input Dialog with the message of the parameter message
				String response = JOptionPane.showInputDialog(message);
				//If the user hits cancel, it will return null, so we ensure that it isn't null (but if it is, just return 0.0d because 
				//they want no change to their account). If it wasn't null, set the result to whatever they entered in the input box
				if (response != null) result = Double.parseDouble(response);
				else return 0.0d;
			} catch (NumberFormatException e2) {
				//If they entered letters or other special characters, we catch that and call this method again saying
				//that that input wasn't valid
				return requestAmount("That input wasn't valid, please enter the amount you wish to deposit.");
			}
			//If result is negative then call this method again saying that that input wasn't valid
			if (result < 0.0d) return requestAmount("That input wasn't valid, please enter the amount you wish to deposit.");
			return result;
		}

	}

	public static void main(String[] args) {
		new Assignment1();
	}

}
