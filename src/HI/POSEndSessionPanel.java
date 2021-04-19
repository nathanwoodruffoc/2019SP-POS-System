package HI;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import PD.Sale;
import PD.Session;
import PD.Store;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class POSEndSessionPanel extends JPanel {
	private JTextField txtSalesMade;
	private JTextField txtCashMade;
	private JTextField txtEnterCash;
	
	private JLabel dispCashDifference;

	/**
	 * Create the panel.
	 */
	public POSEndSessionPanel(JFrame currentFrame, JPanel currentPanel, Store store, Session session) {
		setLayout(null);

		BigDecimal totalCash = new BigDecimal(0); //total amount earned
		for (Sale s : session.getSales()) {
			totalCash = totalCash.add(s.calcTotal());
		}

		JLabel dispCashDifference = new JLabel("");
		dispCashDifference.setBounds(176, 241, 116, 16);
		add(dispCashDifference);
		
		txtEnterCash = new JTextField();
		txtEnterCash.setText("0");
		txtEnterCash.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) { update(); }
			public void removeUpdate(DocumentEvent e) { update(); }
			public void insertUpdate(DocumentEvent e) { update(); }
			public void update() {
				try {
					dispCashDifference.setText(session.calcCashCountDiff(new BigDecimal(txtEnterCash.getText())).toString());
				} catch (NumberFormatException e) {
					dispCashDifference.setText("");
				}
			}
		});
		txtEnterCash.setBounds(176, 209, 116, 22);
		add(txtEnterCash);
		
		JLabel dispSalesMade = new JLabel();
		dispSalesMade.setText("0");
		if (session.getSales() != null) dispSalesMade.setText(String.valueOf(session.getSales().size()));
		dispSalesMade.setBounds(176, 154, 56, 16);
		add(dispSalesMade);
		
		JLabel dispCashMade = new JLabel();
		dispCashMade.setText(totalCash.toString());
		dispCashMade.setBounds(176, 183, 56, 16);
		add(dispCashMade);		
		
		JButton btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(176, 270, 116, 25);
		add(btnEndSession);
		
		JLabel dispInitCash = new JLabel("0");
		dispInitCash.setText(session.getRegister().getCashDrawer().getCashAmount().subtract(totalCash).toString());
		dispInitCash.setBounds(176, 125, 81, 16);
		add(dispInitCash);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
			}
		});
		btnBack.setBounds(29, 270, 116, 25);
		add(btnBack);

		JLabel lblName = new JLabel(session.getCashier().toString());
		lblName.setBounds(176, 48, 116, 16);
		add(lblName);

		JLabel lblNumber = new JLabel(session.getRegister().toString());
		lblNumber.setBounds(176, 77, 116, 16);
		add(lblNumber);

		JLabel lblEndOfSession = new JLabel("Session Overview");
		lblEndOfSession.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndOfSession.setBounds(29, 13, 263, 16);
		add(lblEndOfSession);

		JLabel lblTotalSaleMade = new JLabel("Total Sales Made:");
		lblTotalSaleMade.setBounds(29, 154, 107, 16);
		add(lblTotalSaleMade);

		JLabel lblTotalCashMade = new JLabel("Total Cash Made:");
		lblTotalCashMade.setBounds(29, 183, 107, 16);
		add(lblTotalCashMade);

		JLabel lblEnterCash = new JLabel("Enter Cash:");
		lblEnterCash.setBounds(29, 212, 67, 16);
		add(lblEnterCash);

		JLabel lblCashDifference = new JLabel("Cash Difference:");
		lblCashDifference.setBounds(29, 241, 98, 16);
		add(lblCashDifference);

		JLabel lblCashier = new JLabel("Cashier:");
		lblCashier.setBounds(29, 48, 56, 16);
		add(lblCashier);

		JLabel lblRegister = new JLabel("Register:");
		lblRegister.setBounds(29, 77, 56, 16);
		add(lblRegister);
		
		JLabel lblInitialCash = new JLabel("Initial Cash:");
		lblInitialCash.setBounds(29, 125, 98, 16);
		add(lblInitialCash);

	}
}
