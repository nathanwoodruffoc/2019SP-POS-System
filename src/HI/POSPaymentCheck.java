package HI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Check;
import PD.Sale;
import PD.Session;
import PD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class POSPaymentCheck extends JPanel {
	private JTextField txtAmount;
	private JTextField txtRoutingnum;
	private JTextField txtAccnum;
	private JTextField txtChknum;
	private Check check = new Check();

	/**
	 * Create the panel.
	 */
	public POSPaymentCheck(JFrame currentFrame, JPanel paymentPanel, Sale sale) {
		setLayout(null);
		JPanel thisPanel = this;
		
		txtAmount = new JTextField();
		txtAmount.setBounds(82, 56, 116, 22);
		add(txtAmount);
		
		txtRoutingnum = new JTextField();
		txtRoutingnum.setBounds(82, 85, 116, 22);
		add(txtRoutingnum);
		
		txtAccnum = new JTextField();
		txtAccnum.setBounds(82, 114, 116, 22);
		add(txtAccnum);
		
		txtChknum = new JTextField();
		txtChknum.setBounds(82, 143, 116, 22);
		add(txtChknum);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check.setAmtTendered(new BigDecimal(txtAmount.getText()));
				check.setAmount(sale.calcAmount(check.getAmtTendered()));
				check.setRoutingNumber(txtRoutingnum.getText());
				check.setAccountNumber(txtAccnum.getText());
				check.setCheckNumber(txtChknum.getText());
				sale.addPayment(check);
				
				
				paymentPanel.remove(thisPanel);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(paymentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(22, 193, 97, 25);
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
		btnCancel.setBounds(128, 193, 97, 25);
		add(btnCancel);
		
		JLabel lblEnterCheckInfo = new JLabel("Enter Check Info");
		lblEnterCheckInfo.setBounds(22, 13, 104, 16);
		add(lblEnterCheckInfo);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(12, 59, 56, 16);
		add(lblAmount);
		
		JLabel lblAccNum = new JLabel("Acc. Num");
		lblAccNum.setBounds(12, 117, 56, 16);
		add(lblAccNum);
		
		JLabel lblRoutNum = new JLabel("Rout. Num.");
		lblRoutNum.setBounds(12, 88, 64, 16);
		add(lblRoutNum);
		
		JLabel lblChkNum = new JLabel("Chk. Num.");
		lblChkNum.setBounds(12, 146, 64, 16);
		add(lblChkNum);

	}
}
