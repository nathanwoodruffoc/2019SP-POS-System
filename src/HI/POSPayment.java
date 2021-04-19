package HI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Cash;
import PD.Check;
import PD.Credit;
import PD.Payment;
import PD.Sale;
import PD.Session;
import PD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class POSPayment extends JPanel {
	private JTextField txtTotal;
	private JTextField txtTendered;
	private POSPaymentCash cashPanel;
	private POSPaymentCheck checkPanel;
	private POSPaymentCredit creditPanel;
	private JButton btnCompletePayment;
	
	private Sale tempSale;
	
	/**
	 * Create the panel.
	 */
	public POSPayment(JFrame currentFrame, JPanel currentPanel, Store store, Session session, Sale sale) {
		tempSale = new Sale(sale);
		
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				txtTendered.setText(sale.calcAmtTendered().add(tempSale.calcAmtTendered()).toString());
				btnCompletePayment.setEnabled(tempSale.calcTotal().compareTo(tempSale.calcTotalPayments()) <= 0);
			}
			public void ancestorMoved(AncestorEvent arg0) {}
			public void ancestorRemoved(AncestorEvent arg0) {}
		});
		JPanel paymentPanel = this;
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(235, 42, 325, 255);
		add(panel);
		remove(panel);
		
		txtTotal = new JTextField(sale.calcTotal().toString());
		txtTotal.setBounds(107, 60, 116, 22);
		add(txtTotal);
		
		txtTendered = new JTextField(sale.calcAmtTendered().toString());
		txtTendered.setBounds(107, 89, 116, 22);
		add(txtTendered);
		
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashPanel = new POSPaymentCash(currentFrame, paymentPanel, tempSale);
				cashPanel.setBounds(panel.getBounds());
				add(cashPanel);
				if (checkPanel != null) remove(checkPanel);
				if (creditPanel != null) remove(creditPanel);
				
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnCash.setBounds(39, 121, 97, 25);
		add(btnCash);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkPanel = new POSPaymentCheck(currentFrame, paymentPanel, tempSale);
				checkPanel.setBounds(panel.getBounds());
				add(checkPanel);
				if (cashPanel != null) remove(cashPanel);
				if (creditPanel != null) remove(creditPanel);
				
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnCheck.setBounds(39, 159, 97, 25);
		add(btnCheck);
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creditPanel = new POSPaymentCredit(currentFrame, paymentPanel, tempSale);
				creditPanel.setBounds(panel.getBounds());
				add(creditPanel);
				if (cashPanel != null) remove(cashPanel);
				if (checkPanel != null) remove(checkPanel);

				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnCredit.setBounds(39, 197, 97, 25);
		add(btnCredit);
		
		btnCompletePayment = new JButton("Complete Payment");
		btnCompletePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Payment payment : tempSale.getPayments()) sale.addPayment(payment);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnCompletePayment.setBounds(160, 308, 153, 25);
		add(btnCompletePayment);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(39, 308, 97, 25);
		add(btnCancel);
		
		JLabel lblPayments = new JLabel("Payments");
		lblPayments.setBounds(201, 13, 56, 16);
		add(lblPayments);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(39, 63, 56, 16);
		add(lblTotal);
		
		JLabel lblTendered = new JLabel("Tendered");
		lblTendered.setBounds(39, 92, 56, 16);
		add(lblTendered);
		

	}
}
