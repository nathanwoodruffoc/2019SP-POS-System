package PD;

/**
 * This class represents a UPC code on a product
 */
public class UPC implements Comparable {

	/**
	 * The item associated with the UPC code
	 */
	private Item item;
	/**
	 * The UPC code
	 */
	private String uPC;

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getUPC() {
		return this.uPC;
	}

	public void setUPC(String uPC) {
		this.uPC = uPC;
	}

	/**
	 * Default constructor
	 */
	public UPC() {
		// TODO - implement UPC.UPC
	}

	/**
	 * Constructor that takes uPC
	 * @param uPC UPC code to add
	 */
	public UPC(String uPC, Item item) {
		this();
		this.uPC = uPC;
		this.item = item;
	}

	/**
	 * Provides a string representation of UPC
	 */
	public String toString() {
		return uPC;
	}

	public int compareTo(UPC upC) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		//return 0;
	}
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}