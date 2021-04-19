package HI;

import javax.swing.JPanel;

import PD.Item;
import PD.Sale;
import PD.SaleLineItem;
import PD.Session;
import PD.Store;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.Color;

public class POSSale extends JPanel {
	private Sale sale = new Sale();
	private JTextField txtRegister;
	private JTextField txtCashier;
	private JTextField txtItem;
	private JTextField txtQty;
	private JTextField txtSubtotal;
	private JTextField txtTax;
	private JTextField txtTotal;
	private JTextField txtTendered;
	private JTextField txtChange;
	
	private DefaultListModel liListModel;

	private JButton btnCompleteSale;
	/**
	 * Create the panel.
	 */
	public POSSale(JFrame currentFrame, Store store, Session session) {
		setLayout(null);
		JPanel currentPanel = this;
		
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				if (!liListModel.isEmpty()) {
					//txtSubtotal.setText(sale.calcSubTotal().toString());
					//txtTax.setText(sale.calcTax().toString());
					//txtTotal.setText(sale.calcTotal().toString());
					txtTendered.setText(sale.calcAmtTendered().toString());
					txtChange.setText(sale.calcChange().toString());
					
					if (sale.calcAmtTendered().compareTo(sale.calcTotal()) >= 0)
						btnCompleteSale.setEnabled(true);
				}
			}
			public void ancestorMoved(AncestorEvent event) {}
			public void ancestorRemoved(AncestorEvent event) {}
		});
		
		JLabel lblItemNotFound = new JLabel("Item not found.");
		lblItemNotFound.setForeground(Color.RED);
		lblItemNotFound.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemNotFound.setBounds(78, 117, 113, 16);
		lblItemNotFound.setVisible(false);
		add(lblItemNotFound);
		
		
		liListModel = new DefaultListModel();
		JList<SaleLineItem> list = new JList<SaleLineItem>(liListModel);
		list.setBounds(20, 168, 300, 141);
		add(list);

		
		
		
		JCheckBox chckbxTaxable = new JCheckBox("Taxable");
		chckbxTaxable.setSelected(true);
		chckbxTaxable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sale.setTaxFree(!chckbxTaxable.isSelected());
				txtTax.setText(sale.calcTax().toString());
				txtTotal.setText(sale.calcTotal().toString());
				if (!sale.calcTotalPayments().equals(0))
					txtChange.setText(sale.calcChange().toString());
			}
		});
		chckbxTaxable.setBounds(380, 132, 113, 25);
		add(chckbxTaxable);

		JButton btnCancel = new JButton("Cancel Sale");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame, store, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(150, 360, 113, 25);
		add(btnCancel);

		JButton btnPayments = new JButton("Payments");
		btnPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, currentPanel, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnPayments.setBounds(283, 360, 97, 25);
		add(btnPayments);

		JButton btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.addSession(session);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSEndSessionPanel(currentFrame, currentPanel, store, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(20, 360, 107, 25);
		add(btnEndSession);

		btnCompleteSale = new JButton("Complete Sale");
		btnCompleteSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.getRegister().getCashDrawer().addCash(sale.calcTotalPayments().subtract(sale.calcChange()));
				session.setEndDateTime(LocalDateTime.now());
				session.addSale(sale);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame, store, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCompleteSale.setBounds(400, 360, 117, 25);
		btnCompleteSale.setEnabled(false);
		add(btnCompleteSale);

		txtRegister = new JTextField();
		txtRegister.setBounds(78, 47, 116, 22);
		txtRegister.setText(session.getRegister().getNumber());
		add(txtRegister);


		txtCashier = new JTextField();
		txtCashier.setBounds(78, 82, 116, 22);
		txtCashier.setText(session.getCashier().getPerson().getName());
		add(txtCashier);

		txtItem = new JTextField();  //item text field
		txtItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = store.findItemForUPC(txtItem.getText());
				if (item != null) {
					lblItemNotFound.setVisible(false);
					SaleLineItem sli = new SaleLineItem(sale, item, Integer.parseInt(txtQty.getText()));
					sale.addSaleLineItem(sli);
					liListModel.addElement(sli);
					
					txtItem.setText("");
					
					txtSubtotal.setText(sale.calcSubTotal().toString());
					txtTax.setText(sale.calcTax().toString());
					txtTotal.setText(sale.calcTotal().toString());
					txtTendered.setText(sale.calcAmtTendered().toString());
					txtChange.setText(sale.calcChange().toString());
				} else {
					lblItemNotFound.setVisible(true);
				}
			}
		});
		txtItem.setText("");
		txtItem.setBounds(78, 133, 116, 22);
		add(txtItem);

		txtQty = new JTextField();
		txtQty.setText("1");
		txtQty.setBounds(242, 133, 116, 22);
		add(txtQty);

		txtSubtotal = new JTextField();
		txtSubtotal.setBounds(400, 168, 116, 22);
		add(txtSubtotal);

		txtTax = new JTextField();
		txtTax.setBounds(400, 204, 116, 22);
		add(txtTax);

		txtTotal = new JTextField();
		txtTotal.setBounds(400, 239, 116, 22);
		add(txtTotal);

		txtTendered = new JTextField();
		txtTendered.setBounds(400, 274, 116, 22);
		add(txtTendered);

		txtChange = new JTextField();
		txtChange.setBounds(400, 309, 116, 22);
		add(txtChange);

		JLabel lblSale = new JLabel("Sale");
		lblSale.setBounds(264, 13, 56, 16);
		add(lblSale);

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(20, 50, 56, 16);
		add(lblRegister);

		JLabel lblCashier = new JLabel("Cashier");
		lblCashier.setBounds(20, 85, 56, 16);
		add(lblCashier);

		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(20, 136, 56, 16);
		add(lblItem);

		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(214, 136, 56, 16);
		add(lblQty);

		JLabel lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setBounds(332, 171, 56, 16);
		add(lblSubtotal);

		JLabel lblTax = new JLabel("Tax");
		lblTax.setBounds(332, 207, 56, 16);
		add(lblTax);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(332, 242, 56, 16);
		add(lblTotal);

		JLabel lblTendered = new JLabel("Tendered");
		lblTendered.setBounds(332, 277, 56, 16);
		add(lblTendered);

		JLabel lblChange = new JLabel("Change");
		lblChange.setBounds(332, 312, 56, 16);
		add(lblChange);

	}
}
