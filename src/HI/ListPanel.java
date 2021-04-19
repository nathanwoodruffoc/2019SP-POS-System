package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.SwingConstants;

public abstract class ListPanel<T> extends JPanel {

	protected JButton btnAdd;
	protected JButton btnEdit;
	protected JButton btnRemove;
	
	protected JList<T> list;
	protected DefaultListModel listModel = new DefaultListModel();

	/**
	 * Create the panel.
	 */
	public ListPanel(String title) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(163, 106, 406, 211);
		add(scrollPane);
		
		list = new JList(listModel);
		scrollPane.setViewportView(list);
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
		
		
		JLabel lblTitle = new JLabel(title);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 59, 730, 16);
		add(lblTitle);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(196, 350, 97, 25);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.setBounds(318, 350, 97, 25);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(440, 350, 97, 25);
		btnRemove.setEnabled(false);
		add(btnRemove);
		
		
		
	}

}
