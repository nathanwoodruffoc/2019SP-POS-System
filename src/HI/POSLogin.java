package HI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Cashier;
import PD.Register;
import PD.Session;
import PD.Store;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class POSLogin extends JPanel {
	private JTextField cashField;
	private JPasswordField passwordField;
	private JLabel lblIncorrect;

	/**
	 * Create the panel.
	 */
	public POSLogin(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(190, 13, 56, 16);
		add(lblLogin);
		
		JLabel lblCashier = new JLabel("Cashier");
		lblCashier.setBounds(52, 74, 56, 16);
		add(lblCashier);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(52, 102, 56, 16);
		add(lblRegister);
		
		JLabel lblStartingCash = new JLabel("Starting Cash");
		lblStartingCash.setBounds(52, 164, 90, 16);
		add(lblStartingCash);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(52, 198, 80, 16);
		add(lblPassword);
		
		cashField = new JTextField();
		cashField.setBounds(138, 161, 116, 22);
		add(cashField);
		cashField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(138, 195, 116, 22);
		add(passwordField);
		
		
		DefaultComboBoxModel registersModel = new DefaultComboBoxModel();
		for (Register register : store.getRegisters().values()) {
			registersModel.addElement(register);
		}
		
		JComboBox registerCb = new JComboBox(registersModel);
		registerCb.setBounds(138, 99, 116, 22);
		add(registerCb);
		
		
		DefaultComboBoxModel cashiersModel = new DefaultComboBoxModel();
		for (Cashier cashier : store.getCashiers().values()) {
			cashiersModel.addElement(cashier);
		}
		
		JComboBox cashierCb = new JComboBox(cashiersModel);
		cashierCb.setBounds(138, 71, 116, 22);
		add(cashierCb);
		
		
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cashier cashier = ((Cashier) (cashierCb.getSelectedItem()));
				Register register = ((Register)(registerCb.getSelectedItem()));
				if (cashier.isAuthorized(passwordField.getText())) {
					if (cashField.getText().equals("")) cashField.setText("0");
					register.getCashDrawer().addCash(new BigDecimal(cashField.getText()));
					Session session = new Session(cashier, register);
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POSSale(currentFrame, store, session));
					currentFrame.getContentPane().revalidate();
				}
				else {
					lblIncorrect.setVisible(true);
					passwordField.setText("");
				}
				
			}
		});
		btnLogIn.setBounds(64, 244, 97, 25);
		add(btnLogIn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(266, 244, 97, 25);
		add(btnCancel);
		
		lblIncorrect = new JLabel("Incorrect Password");
		lblIncorrect.setForeground(Color.RED);
		lblIncorrect.setBounds(266, 198, 118, 16);
		lblIncorrect.setVisible(false);
		add(lblIncorrect);

	}
}
