package HI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Item;
import PD.Price;
import PD.Store;
import PD.TaxCategory;
import PD.TaxRate;
import PD.UPC;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;

public class ItemEditPanel extends JPanel {
	private JTextField numTextField;
	private JTextField descTextField;
	private JButton btnUpcEdit;
	private JButton btnUpcRemove;
	private JButton btnPriceEdit;
	private JButton btnPriceRemove;
	private DefaultListModel upcListModel;
	private DefaultListModel priceListModel;
	private JList<UPC> listUPC;
	private JList<Price> listPrices;
	private JPanel currentPanel = this;
	

	/**
	 * Create the panel.
	 */
	public ItemEditPanel(JFrame currentFrame, Store store, Item item, Boolean isAdd) {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				upcListModel = new DefaultListModel();
				for (UPC upc : item.getUPCs().values()) upcListModel.addElement(upc);
				listUPC.setModel(upcListModel);
				
				priceListModel = new DefaultListModel();
				for (Price price : item.getPrices()) priceListModel.addElement(price);
				listPrices.setModel(priceListModel);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		setLayout(null);
		
		numTextField = new JTextField();
		numTextField.setBounds(98, 45, 116, 22);
		add(numTextField);
		numTextField.setColumns(10);
		if (!isAdd) numTextField.setText(item.getNumber());
		
		descTextField = new JTextField();
		descTextField.setBounds(98, 77, 176, 22);
		add(descTextField);
		descTextField.setColumns(10);
		if (!isAdd) descTextField.setText(item.getDescription());
		
		
		//Tax Categories Combo Box
		DefaultComboBoxModel<TaxCategory> boxModel = new DefaultComboBoxModel<TaxCategory>();
		for (TaxCategory taxCategory : store.getTaxCategories().values()) {
			boxModel.addElement(taxCategory);
		}	
		
		JComboBox<TaxCategory> taxCategoryComboBox = new JComboBox<TaxCategory>(boxModel);
		taxCategoryComboBox.setBounds(98, 110, 176, 22);
		if (!isAdd) taxCategoryComboBox.setSelectedItem(item.getTaxCategory());
		add(taxCategoryComboBox);
		
		
		
		
		//UPC List
		upcListModel = new DefaultListModel();
		//if (!isAdd) for (UPC upc : item.getUPCs().values()) upcListModel.addElement(upc);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(375, 42, 260, 90);
		add(scrollPane);
		
		listUPC = new JList(upcListModel);
		listUPC.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (listUPC.getSelectedValue() != null) {
					btnUpcEdit.setEnabled(true);
					btnUpcRemove.setEnabled(true);
				}
				else {
					btnUpcEdit.setEnabled(false);
					btnUpcRemove.setEnabled(false);
				}
			}
		});
		scrollPane.setViewportView(listUPC);
		
		
		
		
		//Price List
		priceListModel = new DefaultListModel();
		//if (!isAdd) for (Price price : item.getPrices()) priceListModel.addElement(price);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(376, 223, 258, 106);
		add(scrollPane_1);
		
		listPrices = new JList(priceListModel);
		listPrices.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listPrices.getSelectedValue() != null) {
					btnPriceEdit.setEnabled(true);
					btnPriceRemove.setEnabled(true);
				}
				else {
					btnPriceEdit.setEnabled(false);
					btnPriceRemove.setEnabled(false);
				}
			}
		});
		scrollPane_1.setViewportView(listPrices);
		
		
		
		
		
		JButton btnUpcAdd = new JButton("Add");
		btnUpcAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, currentPanel, item, store, new UPC(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnUpcAdd.setBounds(350, 146, 97, 25);
		add(btnUpcAdd);
		
		btnUpcEdit = new JButton("Edit");
		btnUpcEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, currentPanel, item, store, listUPC.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnUpcEdit.setBounds(459, 146, 97, 25);
		add(btnUpcEdit);
		
		btnUpcRemove = new JButton("Remove");
		btnUpcRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeUPC(listUPC.getSelectedValue());
				item.removeUPC(listUPC.getSelectedValue());
				upcListModel.removeElement(listUPC.getSelectedValue());
			}
		});
		btnUpcRemove.setBounds(569, 146, 97, 25);
		add(btnUpcRemove);

		
		JButton btnPriceAdd = new JButton("Add");
		btnPriceAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, currentPanel, item, new Price(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnPriceAdd.setBounds(350, 344, 97, 25);
		add(btnPriceAdd);
		
		btnPriceEdit = new JButton("Edit");
		btnPriceEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, currentPanel, item, listPrices.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnPriceEdit.setBounds(459, 344, 97, 25);
		add(btnPriceEdit);
		
		btnPriceRemove = new JButton("Remove");
		btnPriceRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.removePrice(listPrices.getSelectedValue());
				priceListModel.removeElement(listPrices.getSelectedValue());
			}
		});
		btnPriceRemove.setBounds(569, 344, 97, 25);
		add(btnPriceRemove);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.setNumber(numTextField.getText());
				item.setDescription(descTextField.getText());
				item.setTaxCategory((TaxCategory) taxCategoryComboBox.getSelectedItem());
				if (isAdd) store.addItem(item);
				
				//go back
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(68, 221, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(177, 221, 97, 25);
		add(btnCancel);
		
		btnUpcEdit.setEnabled(false);
		btnUpcRemove.setEnabled(false);
		btnPriceEdit.setEnabled(false);
		btnPriceRemove.setEnabled(false);
		
		JLabel lblItemNumber = new JLabel("Item Number");
		lblItemNumber.setBounds(12, 48, 96, 16);
		add(lblItemNumber);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(24, 80, 96, 16);
		add(lblDescription);
		
		JLabel lblTaxCategory = new JLabel("Tax Category");
		lblTaxCategory.setBounds(12, 113, 96, 16);
		add(lblTaxCategory);
		
		JLabel lblEditItem = new JLabel("Edit Item");
		lblEditItem.setBounds(196, 13, 56, 16);
		add(lblEditItem);
		
		JLabel lblUpcs = new JLabel("UPCs");
		lblUpcs.setBounds(488, 13, 56, 16);
		add(lblUpcs);
		
		JLabel lblPrices = new JLabel("Prices");
		lblPrices.setBounds(488, 194, 56, 16);
		add(lblPrices);
	}
}
