package HI;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import PD.Cashier;
import PD.Sale;
import PD.Session;
import PD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReportCashiers extends ReportPanel {

	private BigDecimal totalAmount;

	/**
	 * Create the panel.
	 */
	public ReportCashiers(JFrame currentFrame, Store store) {
		super(currentFrame, store);
		lblTitle.setText("Cashier Report");
		columnWidths = Arrays.asList(10, 20, 15, 15);
	}
	
	protected void generateReport(Store store, Boolean isAllTime) {
		totalAmount = new BigDecimal(0);
		
		if (!isAllTime) textArea.setText("Cashier Report for " + datePicker.getDateStringOrEmptyString() + ":\n\n");
		else textArea.setText("Cashier Report for all time:\n\n");
		
		List<String> result = Arrays.asList("Name", "Number", "Cash Count", "Actual Cash");
		textArea.append(StringOperations.formatData(result, columnWidths) + "\n");

		for (Cashier cashier : store.getCashiers().values()) {
			result = new ArrayList<String>();
			result.add(cashier.getNumber());
			result.add(cashier.getPerson().getName());
			
			BigDecimal amount = new BigDecimal(0);
			for (Session s : cashier.getSessions()) {
				if (isAllTime || s.getEndDateTime().toLocalDate().isEqual(datePicker.getDate())) {
					amount = new BigDecimal(0);
					for (Sale sale : s.getSales())
						amount = amount.add(sale.calcTotal());
					totalAmount = totalAmount.add(amount);
				}
			} 
			result.add(amount.toString());
			result.add(amount.toString());
			textArea.append(StringOperations.formatData(result, columnWidths) + "\n");
		}
		result = Arrays.asList("Total:", "", "", totalAmount.toString());
		textArea.append("\n" + StringOperations.formatData(result, columnWidths) + "\n");
	}
}
