package HI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import PD.Store;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSFrame extends JFrame {
	// 730 x 476
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void run(Store store) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POSFrame frame = new POSFrame(store);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public POSFrame(Store store) {
		JFrame currentFrame = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 536);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
		
		
		
		
		JMenu mnMaintenance = new JMenu("Maintenance");
		menuBar.add(mnMaintenance);
		
		JMenuItem mntmStore = new JMenuItem("Store");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new StoreEditPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintenance.add(mntmStore);
		
		JMenuItem mntmTaxCategories = new JMenuItem("Tax Categories");
		mntmTaxCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new TaxCategoryListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintenance.add(mntmTaxCategories);
		
		JMenuItem mntmRegisters = new JMenuItem("Registers");
		mntmRegisters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new RegisterListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		
		JMenuItem mntmItems = new JMenuItem("Items");
		mntmItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintenance.add(mntmItems);
		
		JMenuItem mntmCashiers = new JMenuItem("Cashiers");
		mntmCashiers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierListPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnMaintenance.add(mntmCashiers);
		mnMaintenance.add(mntmRegisters);
		
		
		
		
		
		
		JMenu mnPos = new JMenu("POS");
		menuBar.add(mnPos);
		
		JMenuItem mntmLogIn = new JMenuItem("Log In");
		mntmLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new POSLogin(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnPos.add(mntmLogIn);
		
		
		
		
		
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmItemReport = new JMenuItem("Items Report");
		mntmItemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ReportItems(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmItemReport);
		
		JMenuItem mntmCashierReport = new JMenuItem("Cashier Report");
		mntmCashierReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ReportCashiers(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmCashierReport);
		
		JMenuItem mntmSalesReport = new JMenuItem("Sales Report");
		mntmSalesReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ReportSales(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmSalesReport);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		getContentPane().removeAll();
		getContentPane().add(new POSHome(store));
		getContentPane().revalidate();
	}

}
