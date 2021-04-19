package PD;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
/**
 * This class represents an item line on a receipt
 */
public class SaleLineItem {

	/**
	 * The item associated with the sale line item
	 */
	private Item item;
	/**
	 * The item quantity
	 */
	private int quantity;
	private Sale sale;

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Sale getSale() {
		return this.sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	/**
	 * Default constructor
	 */
	public SaleLineItem() {
		// TODO - implement SaleLineItem.SaleLineItem
	}

	/**
	 * Constructor that takes item number and quantity
	 * @param itemNumber Number of the item to add
	 * @param quantity Quantity of the item to add
	 */
	public SaleLineItem(String itemNumber, int quantity, Store store) {
		// TODO - implement SaleLineItem.SaleLineItem
		this();
		
		//find item based on the number and set it?  Or just set the item number?		
		
		this.quantity = quantity;
		throw new UnsupportedOperationException();
	}
	
	public SaleLineItem(Sale sale, Item item, int quantity) {
		this();
		this.item = item;
		this.quantity = quantity;
		this.sale = sale;
		//setitems
		//add sli to sale
		//getitem???????
		//connect stuff i guess
	}

	/**
	 * Calculates the subtotal of the item for today before tax.
	 * (Item price for today) * (quantity)
	 * @return The price of the item before tax.
	 */
	public BigDecimal calcSubTotal() {
		return item.getPriceForDate(sale.getDateTime().toLocalDate()).multiply(new BigDecimal(quantity));
	}

	/**
	 * Calculates the tax of an item
	 * (Item subtotal for today) * (Item tax rate for today)
	 * @return The tax of an item
	 */
	public BigDecimal calcTax() {
		return calcSubTotal().multiply(item.getTaxRateForDate(sale.getDateTime().toLocalDate())).setScale(2, RoundingMode.HALF_UP);
	}

	/**
	 * Provides a string representation of SaleLineItem
	 * @return A string representation of SaleLineItem
	 */
	public String toString() {
		//System.out.println(this.item.getPriceForDate(this.sale.getDateTime().toLocalDate()));
		//return "Qty: " + this.quantity + "\t\t" + this.item.toString();
		return this.item.getNumber() + " " + this.item.getDescription() + "\tQty:" + this.quantity + "\tPrice:$" + this.item.getPriceForDate(this.sale.getDateTime().toLocalDate())
		+ "\tSubtotal:$" + this.calcSubTotal() 
		+ "\t\tTax:$" + this.calcTax()
		+ "\t\tTotal:$" + this.calcSubTotal().add(this.calcTax()) + "\n";
	}

}