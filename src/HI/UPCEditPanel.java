package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import PD.Item;
import PD.Store;
import PD.UPC;

public class UPCEditPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public UPCEditPanel(JFrame currentFrame, JPanel currentPanel, Item item, Store store, UPC upc, Boolean isAdd) {
		setLayout(null);
		JLabel lblUpcEdit = new JLabel("Edit UPC");
		lblUpcEdit.setBounds(184, 13, 88, 16);
		add(lblUpcEdit);
		
		JLabel lblUpc = new JLabel("UPC Code:");
		lblUpc.setBounds(104, 116, 61, 16);
		add(lblUpc);
		
		JTextField upcTextField = new JTextField();
		if (!isAdd) upcTextField.setText(upc.getUPC());
		upcTextField.setBounds(181, 113, 116, 22);
		add(upcTextField);
		//upcTextField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upc.setUPC(upcTextField.getText());
				
				if(isAdd) {
					upc.setItem(item);
					item.addUPC(upc);
					store.addUPC(upc);
				}
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(104, 289, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(267, 289, 97, 25);
		add(btnCancel);
		
	}

}
