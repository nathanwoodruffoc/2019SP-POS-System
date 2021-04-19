package HI;

import javax.swing.JPanel;

import PD.Store;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class POSHome extends JPanel {

	/**
	 * Create the panel.
	 */
	public POSHome(Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(store.getName());
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(12, 13, 544, 16);
		add(lblNewLabel);

	}

}
