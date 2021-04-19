package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Cashier;
import PD.Item;
import PD.Store;

public class CashierListPanel extends ListPanel<Cashier> {

	/**
	 * Create the panel.
	 */
	public CashierListPanel(JFrame currentFrame, Store store) {
		super("Cashier List");
		setLayout(null);

		for (Cashier cashier : store.getCashiers().values()) listModel.addElement(cashier);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierEditPanel(currentFrame, store, new Cashier(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeCashier(list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
	}

}
