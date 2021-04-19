package HI;

import javax.swing.UIManager;

import PD.Store;

public class POSStart {
	public static void main(String args[])
	{
		try { 
	        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception ignored){}
		
		Store store = new Store();
		store.loadData(); //loads all data into store class
		POSFrame.run(store);
	}
}


//promoPrice = (PromoPrice) price;