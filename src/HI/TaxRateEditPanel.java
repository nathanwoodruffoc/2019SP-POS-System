package HI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import PD.Store;
import PD.TaxCategory;
import PD.TaxRate;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;

public class TaxRateEditPanel extends JPanel {
	private JTextField rateTextfield;

	/**
	 * Create the panel.
	 */
	public TaxRateEditPanel(JFrame currentFrame, JPanel currentPanel, TaxRate taxRate, TaxCategory taxCategory, Boolean isAdd) {
		setLayout(null);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(136, 78, 160, 22);
		if (!isAdd) datePicker.setDate(taxRate.getEffectiveDate());
		add(datePicker);
		
		rateTextfield = new JTextField();
		rateTextfield.setBounds(136, 109, 116, 22);
		if (!isAdd) rateTextfield.setText(taxRate.getTaxRate().toString());
		add(rateTextfield);
		rateTextfield.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxRate.setEffectiveDate(datePicker.getDate());
				taxRate.setTaxRate(new BigDecimal(rateTextfield.getText()));
				if (isAdd) taxCategory.addTaxRate(taxRate);
				
				//go back
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				//currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(65, 236, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go back
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(254, 236, 97, 25);
		add(btnCancel);
		
		JLabel lblEditTaxRate = new JLabel("Edit Tax Rate");
		lblEditTaxRate.setBounds(190, 13, 90, 16);
		add(lblEditTaxRate);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date:");
		lblEffectiveDate.setBounds(38, 80, 124, 16);
		add(lblEffectiveDate);
		
		JLabel lblRate = new JLabel("Rate:");
		lblRate.setBounds(90, 109, 56, 16);
		add(lblRate);

	}
}
