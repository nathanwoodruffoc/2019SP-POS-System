package HI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Register;
import PD.Store;
import PD.TaxCategory;
import PD.TaxRate;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

public class TaxCategoryEditPanel extends JPanel {
	private JTextField textField;
	private JList<TaxRate> list = new JList<TaxRate>();
	private DefaultListModel listModel;
	
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRemove;
	/**
	 * Create the panel.
	 */
	public TaxCategoryEditPanel(JFrame currentFrame, Store store, TaxCategory taxCategory, Boolean isAdd) {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				listModel = new DefaultListModel();
				for (TaxRate taxRate : taxCategory.getTaxRates()) listModel.addElement(taxRate);
				list.setModel(listModel);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		setLayout(null);
		
		JLabel lblEditTaxCategory = new JLabel("Edit Tax Category");
		lblEditTaxCategory.setBounds(235, 13, 116, 16);
		add(lblEditTaxCategory);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(32, 96, 56, 16);
		add(lblName);
		
		textField = new JTextField(taxCategory.getCategory());
		textField.setBounds(74, 93, 116, 22);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.setCategory(textField.getText());
				if (isAdd) store.addTaxCategory(taxCategory);
				//go back
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
			
		});
		btnSave.setBounds(12, 262, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(130, 262, 97, 25);
		add(btnCancel);
		
		//DefaultListModel listModel2 = new DefaultListModel();
		//for (TaxRate taxRate : taxCategory.getTaxRates())
		//	listModel2.addElement(taxRate);
		//JList<TaxRate> list = new JList(listModel2);
		list.setBounds(256, 60, 294, 143);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (list.getSelectedValue() != null) {
					btnEdit.setEnabled(true);
					btnRemove.setEnabled(true);
				}
				else {
					btnEdit.setEnabled(false);
					btnRemove.setEnabled(false);
				}
				
			}
		});
		
		add(list);
		
		
		
		
		JLabel lblTaxRates = new JLabel("Tax Rates");
		lblTaxRates.setBounds(362, 36, 90, 16);
		add(lblTaxRates);
		
		JPanel currentPanel = this;
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, currentPanel, new TaxRate(), taxCategory, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(246, 216, 97, 25);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, currentPanel, list.getSelectedValue(), taxCategory, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEdit.setBounds(355, 216, 97, 25);
		add(btnEdit);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.removeTaxRate(list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnRemove.setBounds(464, 216, 97, 25);
		add(btnRemove);
		
		btnEdit.setEnabled(false);
		btnRemove.setEnabled(false);

	}
}
