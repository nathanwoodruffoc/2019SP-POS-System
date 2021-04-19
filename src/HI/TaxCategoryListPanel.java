package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import PD.Store;
import PD.TaxCategory;

public class TaxCategoryListPanel extends ListPanel<TaxCategory> {

	/**
	 * Create the panel.
	 */
	public TaxCategoryListPanel(JFrame currentFrame, Store store) {
		super("Tax Category List");
		setLayout(null);
		
		for (TaxCategory taxCategory : store.getTaxCategories().values())
			listModel.addElement(taxCategory);
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, new TaxCategory(), true));
				currentFrame.getContentPane().revalidate();
			}});
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}});
		
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				store.removeTaxCategory(list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
	}
}
