package HI;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import PD.Cash;
import PD.Check;
import PD.Credit;
import PD.Payment;
import PD.Sale;
import PD.SaleLineItem;
import PD.Session;
import PD.Store;

public class ReportSales extends ReportPanel {

	/**
	 * Create the panel.
	 */
	public ReportSales(JFrame currentFrame, Store store) {
		super(currentFrame, store);
		lblTitle.setText("Sales Report");
		columnWidths = Arrays.asList(14, 15, 6, 8, 8, 14);
	}
	
	protected void generateReport(Store store, Boolean isAllTime) {
		if (isAllTime) textArea.setText("Sales Report for all time:\n\n");
		else textArea.setText("Sales Report for " + datePicker.getDateStringOrEmptyString() + ":\n\n");
		
		List<String> result = Arrays.asList("Date", "Items Sold", "Cash", "Credit", "Check", "Total Payments");
		textArea.append(StringOperations.formatData(result, columnWidths) + "\n");
		
		
		Integer totalSales = 0;
		Integer totalItemsSold = 0;
		BigDecimal totalCash = new BigDecimal(0);
		BigDecimal totalCredit = new BigDecimal(0);
		BigDecimal totalCheck = new BigDecimal(0);
		
		Integer saleItemsSold;
		BigDecimal saleCash = new BigDecimal(0);
		BigDecimal saleCredit = new BigDecimal(0);
		BigDecimal saleCheck = new BigDecimal(0);
		for (Session session : store.getSessions()) {
			if (isAllTime || session.getEndDateTime().toLocalDate().equals(datePicker.getDate())) {
				totalSales += session.getSales().size();
				for (Sale sale : session.getSales()) {
					saleItemsSold = 0;
					saleCash = new BigDecimal(0);
					saleCredit = new BigDecimal(0);
					saleCheck = new BigDecimal(0);
					for (SaleLineItem sli : sale.getSaleLineItems()) {
						saleItemsSold += sli.getQuantity();
					}
					totalItemsSold += saleItemsSold;
					
					for (Payment pay : sale.getPayments()) {
						if (pay instanceof Cash) saleCash = saleCash.add(pay.getAmount());
						else if (pay instanceof Credit) saleCredit = saleCredit.add(pay.getAmount());
						else if (pay instanceof Check) saleCheck = saleCheck.add(pay.getAmount());
					}
					totalCash = totalCash.add(saleCash);
					totalCredit = totalCredit.add(saleCredit);
					totalCheck = totalCheck.add(saleCheck);
					
					result = Arrays.asList(sale.getDateTime().toLocalDate().toString(), saleItemsSold.toString(), 
							saleCash.toString(), saleCredit.toString(), saleCheck.toString(), 
							saleCash.add(saleCredit).add(saleCheck).toString());
					textArea.append(StringOperations.formatData(result, columnWidths) + "\n");
				}
			}
		}
		
		
		result = Arrays.asList("Sales", "Items Sold", "Cash", "Credit", "Check", "Total Payments");
		textArea.append("\nTotals:\n" + StringOperations.formatData(result, columnWidths) + "\n");
		
		result = Arrays.asList(totalSales.toString(), totalItemsSold.toString(), 
				totalCash.toString(), totalCredit.toString(), totalCheck.toString(), 
				totalCash.add(totalCredit).add(totalCheck).toString());
		textArea.append(StringOperations.formatData(result, columnWidths) + "\n");
	}
}
