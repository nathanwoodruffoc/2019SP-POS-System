package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import PD.Cashier;
import PD.Store;
import javax.swing.JPasswordField;

public class CashierEditPanel extends JPanel {
	
	JTextField nameTextField;
	private JTextField numberTextField;
	private JTextField phoneTextField;
	private JTextField addressTextField;
	private JTextField cityTextField;
	private JTextField stateTextField;
	private JTextField zipTextField;
	private JTextField ssnTextField;
	private JPasswordField passwordField;
	/**
	 * Create the panel.
	 */
	public CashierEditPanel(JFrame currentFrame, Store store, Cashier cashier, Boolean isAdd) {
		setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cashier.getPerson().setName(nameTextField.getText());
				cashier.getPerson().setSSN(ssnTextField.getText());
				cashier.getPerson().setAddress(addressTextField.getText());
				cashier.getPerson().setCity(cityTextField.getText());
				cashier.getPerson().setState(stateTextField.getText());
				cashier.getPerson().setZip(zipTextField.getText());
				cashier.getPerson().setPhone(phoneTextField.getText());
				cashier.setPassword(passwordField.getText());
				cashier.setNumber(numberTextField.getText());
				
				if(isAdd) store.addCashier(cashier);
					
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(405, 323, 97, 25);
		add(btnSave);
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(516, 323, 97, 25);
		add(btnCancel);
		
		
		//Name
		nameTextField = new JTextField();
		if (!isAdd) nameTextField.setText(cashier.getPerson().getName());
		nameTextField.setBounds(181, 129, 116, 22);
		add(nameTextField);
		
		//Number
		numberTextField = new JTextField();
		if (!isAdd) numberTextField.setText(cashier.getNumber());
		numberTextField.setBounds(181, 164, 116, 22);
		add(numberTextField);
		
		//Phone
		phoneTextField = new JTextField();
		if (!isAdd) phoneTextField.setText(cashier.getPerson().getPhone());
		phoneTextField.setBounds(181, 196, 116, 22);
		add(phoneTextField);

		//Address
		addressTextField = new JTextField();
		if (!isAdd) addressTextField.setText(cashier.getPerson().getAddress());
		addressTextField.setBounds(181, 228, 116, 22);
		add(addressTextField);
		
		//City
		cityTextField = new JTextField();
		if (!isAdd) cityTextField.setText(cashier.getPerson().getCity());
		cityTextField.setBounds(181, 260, 116, 22);
		add(cityTextField);
		
		//State
		stateTextField = new JTextField();
		if (!isAdd) stateTextField.setText(cashier.getPerson().getState());
		stateTextField.setBounds(181, 292, 116, 22);
		add(stateTextField);
		
		//Zip
		zipTextField = new JTextField();
		if (!isAdd) zipTextField.setText(cashier.getPerson().getZip());
		zipTextField.setBounds(181, 324, 116, 22);
		add(zipTextField);
		
		//SSN
		ssnTextField = new JTextField();
		if (!isAdd) ssnTextField.setText(cashier.getPerson().getSSN());
		ssnTextField.setBounds(497, 129, 116, 22);
		add(ssnTextField);
		
		//Password
		passwordField = new JPasswordField();
		if (!isAdd) passwordField.setText(cashier.getPassword());
		passwordField.setBounds(497, 161, 116, 22);
		add(passwordField);
		
		
		
		JLabel lblCashierEdit = new JLabel("Cashier Edit");
		lblCashierEdit.setBounds(308, 55, 86, 16);
		add(lblCashierEdit);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(102, 132, 56, 16);
		add(lblName);
		
		JLabel lblId = new JLabel("ID: ");
		lblId.setBounds(102, 164, 56, 16);
		add(lblId);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(102, 196, 56, 16);
		add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(102, 228, 56, 16);
		add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(102, 260, 56, 16);
		add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(102, 292, 56, 16);
		add(lblState);
		
		JLabel lblZip = new JLabel("ZIP:");
		lblZip.setBounds(102, 324, 56, 16);
		add(lblZip);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(405, 164, 67, 16);
		add(lblPassword);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setBounds(405, 132, 56, 16);
		add(lblSsn);
	}
}
