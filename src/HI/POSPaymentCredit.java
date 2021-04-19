package HI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import PD.Credit;
import PD.Sale;
import PD.Session;
import PD.Store;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;

public class POSPaymentCredit extends JPanel
{
	private JTextField typeField;
	private JTextField amtField;
	private JTextField acctNumField;
	private JTextField expirationField;
	private Credit credit = new Credit();

	/**
	 * Create the panel.
	 */
	public POSPaymentCredit(JFrame currentFrame, JPanel paymentPanel, Sale sale)
	{
		POSPaymentCredit thisPanel = this;
		setLayout(null);
		
		DatePicker expirationDatePicker = new DatePicker();
		expirationDatePicker.setBounds(109, 130, 160, 22);
		add(expirationDatePicker);
		
		typeField = new JTextField();
		typeField.setBounds(109, 29, 160, 22);
		add(typeField);
		typeField.setColumns(10);
		
		amtField = new JTextField();
		amtField.setBounds(109, 61, 160, 22);
		add(amtField);
		amtField.setColumns(10);
		
		acctNumField = new JTextField();
		acctNumField.setBounds(109, 94, 160, 22);
		add(acctNumField);
		acctNumField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				credit.setCardType(typeField.getText());
				credit.setAmtTendered(new BigDecimal(amtField.getText()));
				credit.setAmount(sale.calcAmount(credit.getAmtTendered()));
				credit.setCardNumber(acctNumField.getText());
				credit.setExpirationDate(expirationDatePicker.getDate());
				sale.addPayment(credit);
				
				paymentPanel.remove(thisPanel);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(paymentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(23, 174, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paymentPanel.remove(thisPanel);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(paymentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(172, 174, 97, 25);
		add(btnCancel);		
		
		JLabel lblCredit = new JLabel("Credit");
		lblCredit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredit.setBounds(23, 3, 192, 16);
		add(lblCredit);
		
		JLabel lblCardType = new JLabel("Card Type:");
		lblCardType.setBounds(23, 32, 64, 16);
		add(lblCardType);
		
		JLabel lblAmount = new JLabel("Amount: ");
		lblAmount.setBounds(23, 64, 56, 16);
		add(lblAmount);
		
		JLabel lblAccount = new JLabel("Account #:");
		lblAccount.setBounds(23, 97, 64, 16);
		add(lblAccount);
		
		JLabel lblExpireDate = new JLabel("Expiration Date:");
		lblExpireDate.setBounds(23, 132, 70, 16);
		add(lblExpireDate);
	}
}
