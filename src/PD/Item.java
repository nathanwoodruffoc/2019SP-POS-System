package PD;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * This class represents an item in the store
 */
public class Item implements Comparable<Item> {

	/**
	 * All the prices for an item
	 */
	private TreeSet<Price> prices;
	/**
	 * All the UPC codes for an item
	 */
	private TreeMap<String, UPC> uPCs;
	/**
	 * Sale line items that contain the item
	 */
	private Collection<SaleLineItem> saleLineItems;
	/**
	 * Tax category for the item
	 */
	private TaxCategory taxCategory;
	/**
	 * The item number
	 */
	private String number;
	/**
	 * The item's description
	 */
	private String description;

	public TreeSet<Price> getPrices() {
		return this.prices;
	}

	public TreeMap<String, UPC> getUPCs() {
		return this.uPCs;
	}

	public Collection<SaleLineItem> getSaleLineItems() {
		return this.saleLineItems;
	}

	public TaxCategory getTaxCategory() {
		return this.taxCategory;
	}

	public void setTaxCategory(TaxCategory taxCategory) {
		this.taxCategory = taxCategory;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Default Constructor
	 */
	public Item() {
		prices = new TreeSet<Price>();
		uPCs = new TreeMap<String, UPC>();
		//saleLineItems = new Collection<SaleLineItem>;
	}

	/**
	 * Constructor that takes number and description
	 * @param number The item number
	 * @param description The item description
	 */
	public Item(String number, String description) {
		this();
		this.number = number;
		this.description = description;
	}

	/**
	 * Adds a price to the item
	 * @param price The item price
	 */
	public void addPrice(Price price) {
		this.prices.add(price);
	}

	/**
	 * Removes a price from an item
	 * @param price Item price
	 */
	public void removePrice(Price price) {
		this.prices.remove(price);
	}

	/**
	 * Adds a UPC code to an item
	 * @param uPC Item UPC code
	 */
	public void addUPC(UPC uPC) {
		this.uPCs.put(uPC.toString(), uPC);
	}

	/**
	 * Removes a UPC code from the item
	 * @param uPC Item UPC code
	 */
	public void removeUPC(UPC uPC) {
		this.uPCs.remove(uPC.getUPC(), uPC);
	}

	/**
	 * Gets a price of an item not including tax for a certain date
	 * @param date Date to check for a price
	 * @return Price of an item for a certain date
	 */
	public BigDecimal getPriceForDate(LocalDate date) {
		Price result = null;
		for (Price price : this.prices) {
			if (price.isEffective(date)) result = price;
		}
		return result.getPrice();
	}

	/**
	 * Gets the tax rate for a certain day
	 * @param date Day to get tax rate for
	 * @return Tax rate for a certain day
	 */
	public BigDecimal getTaxRateForDate(LocalDate date) {
		// TODO - Fix this to be like getPriceForDate
		TaxRate result = null;
		for (TaxRate i : this.taxCategory.getTaxRates()) {
			if (i.getEffectiveDate().compareTo(date) <= 0) {
				result = i;
			}
		}
		return result.getTaxRate().setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Gets the item's price including tax for a certain date and quantity
	 * @param date Date to get price for
	 * @param quantity Quantity to get price for
	 * @return Price for a certain day and quantity
	 */
	public BigDecimal getAmount(LocalDate date, int quantity) {
		return this.getPriceForDate(date).multiply(new BigDecimal(quantity)).add(this.getPriceForDate(date).multiply(new BigDecimal(quantity).multiply(this.getTaxRateForDate(date))));
	}
	
	/**
	 * Gets the item's price including tax for a certain date
	 * @param date Date to get price for
	 * @return Price for a certain day and quantity
	 */
	public BigDecimal getAmount(LocalDate date) {
		return getAmount(date, 1);
	}

	
	
	
	public int compareTo(Item item) { 
		return this.number.compareTo(item.getNumber());
	}
	
	
	
	
	/**
	 * Provides a string representation of Item
	 */
	public String toString() {
		return this.number + " " + this.description;
	}
	
	public String toString2() {
		String result = new String();
		String upcCodes = new String();
		for (UPC u : uPCs.values()) upcCodes += u.getUPC() + " ";
		
		result += this.number + "" + this.description + "\t\t$";
		
		if (this.prices.size() > 0) result += this.getPriceForDate(LocalDate.now()).setScale(2, RoundingMode.HALF_UP) + "\t";
		
		result += this.taxCategory.getCategory() + ", %" 
				+ this.taxCategory.getTaxRateForDate(LocalDate.now()).setScale(2, RoundingMode.HALF_UP) + "\tUPC:" + upcCodes + "\n";
		
		return result;
	}

}