package HI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import PD.Store;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public StoreEditPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblEditStore = new JLabel("Edit Store");
		lblEditStore.setBounds(196, 32, 56, 16);
		add(lblEditStore);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(44, 105, 56, 16);
		add(lblName);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.setName(textField.getText());
				//go back
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(76, 241, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(266, 241, 97, 25);
		add(btnCancel);
		
		textField = new JTextField(store.getName());
		textField.setBounds(92, 102, 116, 22);
		add(textField);
		textField.setColumns(10);

	}
}
