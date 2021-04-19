package HI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import PD.Item;
import PD.Price;
import PD.PromoPrice;
import com.github.lgooddatepicker.components.DatePicker;

public class PriceEditPanel extends JPanel {

	private Price price = null;
	/**
	 * Create the panel.
	 */
	public PriceEditPanel(JFrame currentFrame, JPanel currentPanel, Item item, Price _price, Boolean isAdd) {
		price = _price;
		setLayout(null);
		
		JLabel lblPriceEdit = new JLabel("Price Edit");
		lblPriceEdit.setBounds(212, 47, 77, 16);
		add(lblPriceEdit);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setBounds(108, 132, 38, 16);
		add(lblPrice);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date:");
		lblEffectiveDate.setBounds(57, 167, 83, 16);
		add(lblEffectiveDate);
		
		JLabel lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(84, 202, 56, 16);
		add(lblEndDate);
		lblEndDate.setVisible(false);
		
		
		DatePicker datePicker = new DatePicker();
		if (!isAdd) datePicker.setDate(price.getEffectiveDate());
		datePicker.setBounds(152, 165, 160, 22);
		add(datePicker);
		
		DatePicker endDatePicker = new DatePicker();
		endDatePicker.setBounds(152, 200, 160, 22);
		add(endDatePicker);
		endDatePicker.setVisible(false);
		
		JTextField priceTextField = new JTextField();
		priceTextField.setBounds(152, 129, 116, 22);
		add(priceTextField);
		if (!isAdd) priceTextField.setText(price.getPrice().toString());
		
		JCheckBox chckbxPromoPrice = new JCheckBox("Promo Price");
		chckbxPromoPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxPromoPrice.isSelected()) {
					price = new PromoPrice();
					lblEndDate.setVisible(true);
					endDatePicker.setVisible(true);
				}
				else {
					price = new Price();
					lblEndDate.setVisible(false);
					endDatePicker.setVisible(false);
				}
			}
		});
		chckbxPromoPrice.setBounds(292, 128, 113, 25);
		add(chckbxPromoPrice);
		chckbxPromoPrice.setSelected(false);
		
		
		if(price instanceof PromoPrice) {
			chckbxPromoPrice.setSelected(true);
			if (!isAdd) endDatePicker.setDate(((PromoPrice) price).getEndDate());
			lblEndDate.setVisible(true);
			endDatePicker.setVisible(true);
		}
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price.setPrice(new BigDecimal(priceTextField.getText()));
				price.setEffectiveDate(datePicker.getDate());
				if(price instanceof PromoPrice) ((PromoPrice) price).setEndDate(endDatePicker.getDate());
				
				if(isAdd) item.addPrice(price);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnSave.setBounds(84, 295, 97, 25);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().repaint();
			}
		});
		btnCancel.setBounds(293, 295, 97, 25);
		add(btnCancel);
	}
}
