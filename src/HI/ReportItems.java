package HI;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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

import PD.Item;
import PD.Sale;
import PD.SaleLineItem;
import PD.Session;
import PD.Store;

public class ReportItems extends ReportPanel {

	/**
	 * Create the panel.
	 */
	public ReportItems(JFrame currentFrame, Store store) {
		super(currentFrame, store);
		lblTitle.setText("Items Report");
		columnWidths = Arrays.asList(10, 20, 15);
	}

	protected void generateReport(Store store, Boolean isAllTime) {
		if (!isAllTime) textArea.setText("Items Report for " + datePicker.getDateStringOrEmptyString() + ":\n\n");
		else textArea.setText("Items Report for all time:\n\n");
		
		List<String> result = Arrays.asList("Item #", "Description", "Amount Sold");
		textArea.append(StringOperations.formatData(result, columnWidths) + "\n");

		TreeMap<Item, Integer> map = new TreeMap<Item, Integer>();
		for (Session session : store.getSessions()) {
			if (isAllTime || session.getEndDateTime().toLocalDate().equals(datePicker.getDate())) {
				for (Sale sale : session.getSales()) {
					for (SaleLineItem sli : sale.getSaleLineItems()) {
						if (map.containsKey(sli.getItem()))
							map.replace(sli.getItem(), map.get(sli.getItem()) + sli.getQuantity());
						else
							map.put(sli.getItem(), sli.getQuantity());
					}
				} 
			}
		}
		for (Item item : map.keySet()) {
			textArea.append(StringOperations.formatData(Arrays.asList(item.getNumber(), item.getDescription(), map.get(item).toString()), columnWidths) + "\n");
		}
	}
}
