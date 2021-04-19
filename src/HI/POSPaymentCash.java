package HI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Cash;
import PD.Sale;
import PD.Session;
import PD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class POSPaymentCash extends JPanel {
	private JTextField txtAmount;
	private Cash cash = new Cash();

	/**
	 * Create the panel.
	 */
	public POSPaymentCash(JFrame currentFrame, JPanel paymentPanel, Sale sale) {
		JPanel thisPanel = this;
		setLayout(null);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(101, 64, 138, 22);
		add(txtAmount);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cash.setAmtTendered(new BigDecimal(txtAmount.getText()));
				cash.setAmount(sale.calcAmount(cash.getAmtTendered()));
				sale.addPayment(cash);
				
				paymentPanel.remove(thisPanel);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(paymentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(33, 137, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paymentPanel.remove(thisPanel);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(paymentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(142, 137, 97, 25);
		add(btnCancel);
		
		JLabel lblEnterCash = new JLabel("Enter Cash");
		lblEnterCash.setBounds(101, 13, 97, 16);
		add(lblEnterCash);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(33, 67, 56, 16);
		add(lblAmount);

	}

}
