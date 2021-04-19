package HI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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

import PD.Store;

public abstract class ReportPanel extends JPanel {
	protected List<Integer> columnWidths;
	protected JButton btnIncreaseDate;
	protected JButton btnDecreaseDate;
	protected JScrollPane scrollPane;
	protected JTextArea textArea;
	protected DatePicker datePicker;
	protected JButton btnAllTime;
	protected JButton btnToday;
	protected JLabel lblTitle;
	protected JButton btnClose;
	
	/**
	 * Create the panel.
	 */
	public ReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 141, 662, 295);
		add(scrollPane);

		textArea = new JTextArea();
		textArea.setFont(new Font("Courier New", Font.PLAIN, 16));
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		datePicker = new DatePicker();
		datePicker.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent arg0) {
				if (datePicker.getDate() != null) {
					btnIncreaseDate.setEnabled(true);
					btnDecreaseDate.setEnabled(true);
					generateReport(store, false);
				}
			}
		});
		datePicker.setBounds(291, 72, 160, 22);
		add(datePicker);
		
		
		btnAllTime = new JButton("All Time");
		btnAllTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateReport(store, true);
				btnIncreaseDate.setEnabled(false);
				btnDecreaseDate.setEnabled(false);
				datePicker.clear();
			}
		});
		btnAllTime.setBounds(182, 107, 97, 25);
		add(btnAllTime);
		
		btnToday = new JButton("Today");
		btnToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datePicker.setDate(LocalDate.now());
			}
		});
		btnToday.setBounds(463, 107, 97, 25);
		add(btnToday);
		
		
		
		
		btnIncreaseDate = new JButton(">");
		btnIncreaseDate.setEnabled(false);
		btnIncreaseDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datePicker.setDate(datePicker.getDate().plusDays(1));
			}
		});
		btnIncreaseDate.setBounds(378, 107, 73, 25);
		add(btnIncreaseDate);
		
		btnDecreaseDate = new JButton("<");
		btnDecreaseDate.setEnabled(false);
		btnDecreaseDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datePicker.setDate(datePicker.getDate().minusDays(1));
			}
		});
		btnDecreaseDate.setBounds(291, 107, 73, 25);
		add(btnDecreaseDate);
		
		
		lblTitle = new JLabel("Report");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 17, 736, 16);
		add(lblTitle);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHome(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(617, 13, 97, 25);
		add(btnClose);
	}
	
	protected abstract void generateReport(Store store, Boolean isAllTime);
	

}
